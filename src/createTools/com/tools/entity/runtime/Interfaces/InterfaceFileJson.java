package com.tools.entity.runtime.Interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tools.DBPdmUtils;
import com.tools.Start;
import com.tools.TemplateFile;
import com.tools.ToTemplateFileGenUtils;
import com.tools.entity.CreateUtils;
import com.tools.entity.DBColumn;
import com.tools.entity.DBTable;
import com.tools.entity.postman.PManFolder;
import com.tools.entity.postman.PManJson;
import com.tools.entity.postman.PManRequest;
import com.tools.entity.runtime.Controllers.ControllerMethodDel;
import com.tools.entity.runtime.Controllers.ControllerMethodList;
import com.tools.entity.runtime.Controllers.ControllerMethodSave;
import com.tools.entity.runtime.Controllers.ControllerMethodShow;
import com.tools.web.pbase.Pagination;
import jxl.CellView;
import jxl.Workbook;
import jxl.write.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class InterfaceFileJson extends InterfaceFileBase {

	@Override
	public void interExcel(File path ,List<DBTable> tables) throws WriteException, IOException {
		CellView cellView = new CellView();  
	    cellView.setAutosize(true); //设置自动大小
	       
		WritableFont bold = new WritableFont(WritableFont.ARIAL,16,WritableFont.BOLD);//设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
        WritableCellFormat titleFormate = new WritableCellFormat(bold);//生成一个单元格样式控制对象
        titleFormate.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        titleFormate.setWrap(true);
        
        WritableFont noBold = new WritableFont(WritableFont.ARIAL,14,WritableFont.NO_BOLD);//设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
        WritableCellFormat titleNoBold = new WritableCellFormat(noBold);//生成一个单元格样式控制对象
        titleNoBold.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        titleNoBold.setWrap(true);
        
		String f = TemplateFile.realPathBuild("jsonapi_readme_" + Start.pmanRequestHost.replace("http://", "").replace(":8080", "").replaceAll("\\.", "_"),"",path.getPath());		
		//创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(new File(f + ".xls"));
		String lastTableGroupOid = "";			//PDTable分组id
		Map<String,WritableSheet> sheetMap = new HashMap<String,WritableSheet>();
		Map<String,String> sheetName = new HashMap<String,String>();
		
		for(DBTable table:tables){
			if(StringUtils.isBlank(table.getTableGroupOid())){
				sheetMap.put(table.getObjectID(), null);
				sheetName.put(table.getObjectID(), table.getName());
			}else{
				if(StringUtils.isNotBlank(table.getTableGroupOid()) &&
						!StringUtils.equals(table.getTableGroupOid(), lastTableGroupOid)){
					sheetMap.put(table.getTableGroupOid(), null);
					sheetName.put(table.getTableGroupOid(), table.getName());
				}
				lastTableGroupOid = table.getTableGroupOid();
			}
		}
		
		int sheetIndex = -1;
		for(String keySet:sheetMap.keySet()){
			sheetIndex += 1;
			WritableSheet sheet = workbook.createSheet(sheetName.get(keySet) + "模块接口", sheetIndex);
			sheet.addCell(new Label(0,0,"数据名",titleFormate));
			sheet.addCell(new Label(1,0,"接口名",titleFormate));
			sheet.addCell(new Label(2,0,"接口地址",titleFormate));
			sheet.addCell(new Label(3,0,"提交参数",titleFormate));
			sheet.addCell(new Label(4,0,"参数说明",titleFormate));
			sheetMap.put(keySet, sheet);
		}
		
		for(DBTable table:tables){
			Pagination page = new Pagination();
			WritableSheet sheet;
			if(StringUtils.isBlank(table.getTableGroupOid())){
				sheet = sheetMap.get(table.getObjectID());
			}else{
				sheet = sheetMap.get(table.getTableGroupOid());
			}
			int beginRow = sheet.getRows();
			int beginColumn = 0;
			sheet.mergeCells(0, beginRow, 0, beginRow + 3);
			
			sheet.addCell(new Label(beginColumn,beginRow,table.getName(),titleNoBold));
			sheet.addCell(new Label(beginColumn + 1,beginRow,"保存",titleNoBold));
			sheet.addCell(new Label(beginColumn + 1,beginRow + 1,"单个",titleNoBold));
			sheet.addCell(new Label(beginColumn + 1,beginRow + 2,"列表",titleNoBold));
			sheet.addCell(new Label(beginColumn + 1,beginRow + 3,"删除",titleNoBold));
			
			sheet.addCell(new Label(beginColumn + 2,beginRow,Start.pmanRequestHost + "/apijson/" + CreateUtils.getTab(table.getCode()).toLowerCase() + "/save",titleNoBold));
			sheet.addCell(new Label(beginColumn + 2,beginRow + 1,Start.pmanRequestHost + "/apijson/" + CreateUtils.getTab(table.getCode()).toLowerCase() + "/show",titleNoBold));
			sheet.addCell(new Label(beginColumn + 2,beginRow + 2,Start.pmanRequestHost + "/apijson/" + CreateUtils.getTab(table.getCode()).toLowerCase() + "/list",titleNoBold));
			sheet.addCell(new Label(beginColumn + 2,beginRow + 3,Start.pmanRequestHost + "/apijson/" + CreateUtils.getTab(table.getCode()).toLowerCase() + "/del",titleNoBold));
			
			sheet.addCell(new Label(beginColumn + 3,beginRow,"{\"data\":" + DBPdmUtils.genTableJson(table,true) + "}",titleNoBold));
			sheet.addCell(new Label(beginColumn + 3,beginRow + 1,"{\"data\":\r{" + ToTemplateFileGenUtils.getDBTabelPrimaryKeyJsonString(table) + "\r}\r}",titleNoBold));
			sheet.addCell(new Label(beginColumn + 3,beginRow + 2,"{\"data\":" + DBPdmUtils.genTableJson(table,false) + ",\r\"page\":" + page.toJsonString() + "\r}",titleNoBold));
			sheet.addCell(new Label(beginColumn + 3,beginRow + 3,"{\"data\":\r{" + ToTemplateFileGenUtils.getDBTabelPrimaryKeyJsonString(table) + "\r}\r}",titleNoBold));
			
			sheet.mergeCells(beginColumn + 4, beginRow, beginColumn + 4, beginRow + 3);
			StringBuffer clm = new StringBuffer();
			for(DBColumn dbc:table.getCols()){
				clm.append(CreateUtils.getCol(dbc.getCode()) + ":" + dbc.getName() + "\n");
			}
			sheet.addCell(new Label(beginColumn + 4,beginRow,clm.toString(),titleNoBold));
		}
		for(String keySet:sheetMap.keySet()){
			sheetIndex += 1;
			WritableSheet sheet = sheetMap.get(keySet);
			
			for(int tmpVidx=0; tmpVidx<sheet.getColumns() ; tmpVidx++){
				sheet.setColumnView(tmpVidx, cellView);
			}
		}
		workbook.write(); 
		workbook.close();

	}

	@Override
	public void interPostMan(File path ,List<DBTable> tables,String webname) throws Exception {
		PManJson pmJson = new PManJson();
		pmJson.setId(UUID.randomUUID().toString());
		pmJson.setName(webname + " json " + Start.pmanRequestHost.replace("http://", "").replace(":8080", ""));
		pmJson.setDescription(webname + " json 接口测试，" + Start.pmanRequestHost.replace("http://", "").replace(":8080", ""));
		
		List<String> order = new ArrayList<String>();				//排序
		List<PManFolder> folders = new ArrayList<PManFolder>();		//目录
		List<PManRequest> requests = new ArrayList<PManRequest>();	//请求
		
		String lastTableGroupOid = "";			//PDTable分组id
		String lastFolderId = "";				//postman中文件夹id
		for(DBTable table:tables){
			PManFolder pmf = new PManFolder();
			if(StringUtils.isBlank(table.getTableGroupOid())){
				order.add(table.getObjectID());
				pmf.setId(table.getObjectID());
				pmf.setName(table.getName());
				pmf.setDescription(table.getName());
				lastFolderId = pmf.getId();
				folders.add(pmf);
			}else{
				if(StringUtils.isNotBlank(table.getTableGroupOid()) &&
						!StringUtils.equals(table.getTableGroupOid(), lastTableGroupOid)){
					String uid = UUID.randomUUID().toString();
					order.add(uid);
					pmf.setId(uid);
					pmf.setName(table.getName());
					pmf.setDescription(table.getName());
					lastFolderId = pmf.getId();
					folders.add(pmf);
				}
				lastTableGroupOid = table.getTableGroupOid();
			}
			
			//显示保存接口
			requests.add(new ControllerMethodSave(table).genInterfaceJsonPMRequest(pmJson.getId(), "{\"data\":" + DBPdmUtils.genTableJson(table, true) + "}", lastFolderId));
			//显示单个接口
			requests.add(new ControllerMethodShow(table).genInterfaceJsonPMRequest(pmJson.getId(),"{\"data\":{\n\t" + ToTemplateFileGenUtils.getDBTabelPrimaryKeyJsonString(table) + "\n\t}\n}", lastFolderId));
			//显示列表接口
			Pagination page = new Pagination();
			requests.add(new ControllerMethodList(table).genInterfaceJsonPMRequest(pmJson.getId(),"{\"data\":" + DBPdmUtils.genTableJson(table, false) + ",\n\"page\":" + page.toJsonString() + "}", lastFolderId));
			//显示删除接口
			requests.add(new ControllerMethodDel(table).genInterfaceJsonPMRequest(pmJson.getId(),"{\"data\":{\n\t" + ToTemplateFileGenUtils.getDBTabelPrimaryKeyJsonString(table) + "\n\t}\n}", lastFolderId));
		}
		
		for(PManFolder tmpPMF:folders){
			for(PManRequest tmpPMR:requests){
				if(StringUtils.equals(tmpPMR.getFolder(), tmpPMF.getId())){
					tmpPMF.getOrder().add(tmpPMR.getId());
				}
			}
		}
		pmJson.setOrder(order);
		pmJson.setFolders(folders);
		pmJson.setRequests(requests);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pmJson);
        
        String f = TemplateFile.realPathBuild("jsonapi_postman_" + Start.pmanRequestHost.replace("http://", "").replace(":8080", "").replaceAll("\\.", "_"),"",path.getPath());			
		File out = new File(f + TemplateFile.POSTMAN_JSON_COLLECTION_END);
		FileUtils.write(out, "");
		Map<String, String> root = new HashMap<String, String>(); 
		root.put("postman", json); 
		TemplateFile.process(TemplateFile.INTERFACE_POSTMAN, out, root);

	}

	@Override
	public void interJsp(File path ,List<DBTable> tables) throws Exception {
		StringBuffer sbli = new StringBuffer();
		for(DBTable table:tables){
			if(sbli.length()>0)
				sbli.append(",");
			sbli.append("\"" + CreateUtils.getTab(table.getCode()).toLowerCase() + "\":\"" + table.getName() + "\"");
		}
		StringBuffer jsContent = new StringBuffer();
		for(DBTable table:tables){
			Pagination page = new Pagination();
			//保存接口json格式
			jsContent.append(new ControllerMethodSave(table).genInterfaceJsonLocalStorage("{\"data\":" + DBPdmUtils.genTableJson(table, true) + "}"));
			//列表接口json格式
			jsContent.append(new ControllerMethodList(table).genInterfaceJsonLocalStorage("{\"data\":" + DBPdmUtils.genTableJson(table, false) + ",\npage:" + page.toJsonString() + "}"));
			//显示单个接口json格式
			jsContent.append(new ControllerMethodShow(table).genInterfaceJsonLocalStorage("{data:{" + ToTemplateFileGenUtils.getDBTabelPrimaryKeyJsonString(table) + "}}"));
			//删除接口json格式
			jsContent.append(new ControllerMethodDel(table).genInterfaceJsonLocalStorage("{data:{" + ToTemplateFileGenUtils.getDBTabelPrimaryKeyJsonString(table) + "}}"));
		}
		String f = TemplateFile.realPathBuild("jsonapi","",path.getPath());			
		File out = new File(f + TemplateFile.JSP_END);
		FileUtils.write(out, "");
		Map<String, String> root = new HashMap<String, String>(); 
        root.put("navjson", sbli.toString()); 
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM");
        root.put("createDate",format.format(new Date()));
        TemplateFile.process(TemplateFile.JSON_INTERFACE_JSON, out, root);
		
		String fLs = TemplateFile.realPathBuild("jsonapi_localStorage","",path.getPath());			
		File outLs = new File(fLs + ".js");
		FileUtils.write(outLs, "");
		Map<String, String> rootLs = new HashMap<String, String>(); 
		rootLs.put("jscontent",jsContent.toString());
		TemplateFile.process(TemplateFile.JSCONTENT, outLs, rootLs);

	}

}
