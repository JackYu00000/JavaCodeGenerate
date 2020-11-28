package com.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tools.entity.CreateUtils;
import com.tools.entity.DBTable;
import com.tools.entity.ftl.FtlTableEnum;
import com.tools.entity.pdm.DBView;
import com.tools.entity.runtime.Interfaces.InterfaceFileJson;
import com.tools.entity.runtime.Interfaces.InterfaceFileMobi;
import com.tools.web.pbase.InterfaceExclusionStrategy;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToTemplateFile extends TemplateFile {
	
	
	/**
	 * 表 对应 对象
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int tabelEntity(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			try{
				String f = realPathBuild(CreateUtils.getTab(table.getCode()), "", path.getPath());			
				File out = new File(f+JAVA_END);
				FileUtils.write(out, "");
				
				process(JAVA_FTL, out, table);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return 1;
	}

	public int tabelExtend(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			if(table.isHasExtendColumn()){
				try{
					String f = realPathBuild(CreateUtils.getTab(table.getCode()), "", path.getPath());			
					File out = new File(f + "Extend" +JAVA_END);
					FileUtils.write(out, "");
					
					process(JAVA_EXTEND_FTL, out, table);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
	
	/**
	 * 表 对应 对象Vojo
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int tabelEntityVojo(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			try{
				String f = realPathBuild(CreateUtils.getTab(table.getCode()), "", path.getPath());			
				File out = new File(f + "Vojo" +JAVA_END);
				FileUtils.write(out, "");
				
				process(JAVA_VOJO_FTL, out, table);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return 1;
	}
	
	/**
	 * 表 数据字段 枚举
	 * @param path
	 * @param tableEnums
	 * @return
	 * @throws Exception
	 */
	public int tabelEnum(File path ,List<FtlTableEnum> tableEnums) throws Exception {
		for(FtlTableEnum dte:tableEnums){
			try{
//				System.out.println(dte.getKey());
				String f = realPathBuild(dte.getKey(), "", path.getPath());			
				File out = new File(f+JAVA_END);
				FileUtils.write(out, "");
				
				process(ENUM_FTL, out, dte);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		return 1;
	}
	
	/**
	 * 表 扩展查询，1对1，1对多 枚举
	 * @param path
	 * @param tables
	 * @return
	 */
	public int tableRefEnum(File path ,List<DBTable> tables){
		for(DBTable table:tables){
			if(null!= table.getRefCols() && table.getRefCols().size()>0){
				try{
					String f = realPathBuild(CreateUtils.getTab(table.getCode()), "", path.getPath());			
					File out = new File(f + "Ref" +JAVA_END);
					FileUtils.write(out, "");
					
					process(REF_ENUM_FTL, out, table);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
	
	/**
	 * 表 可以进行大小比较 的列
	 * @param path
	 * @param tables
	 * @return
	 */
	public int tableCompareColEnum(File path ,List<DBTable> tables){
		for(DBTable table:tables){
			if(table.isHasCompareColumn()){
				try{
					String f = realPathBuild(CreateUtils.getTab(table.getCode()), "", path.getPath());			
					File out = new File(f + "CompareCols" +JAVA_END);
					FileUtils.write(out, "");
					
					process(COMPARE_COL_ENUM_FTL, out, table);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return 1;
	}

	/**
	 * 表 可以进行sql语句中根据自己值，自增、减 的列
	 * @param path
	 * @param tables
	 * @return
	 */
	public int tableUpdateInSqlColEnum(File path ,List<DBTable> tables){
		for(DBTable table:tables){
			if(table.isHasCompareColumn()){
				try{
					String f = realPathBuild(CreateUtils.getTab(table.getCode()), "", path.getPath());			
					File out = new File(f + "UpdateInSqlCols" +JAVA_END);
					FileUtils.write(out, "");
					
					process(UPDATE_IN_SQL_COL_ENUM_FTL, out, table);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
	
	
	/**
	 * vojo
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int javaVojo(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			String f = realPathBuild(CreateUtils.getTab(table.getCode()) + "WithUpload", table.getVojo(), path.getPath());			
			File out = new File(f+JAVA_END);
			FileUtils.write(out, "");
			
			process(JAVA_WITHUPLOAD, out, table);
		}
		return 1;
	}
	
	/**
	 * 数据层 mapper.xml
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int xmlMapper(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			String f = realPathBuild(CreateUtils.getTab(table.getCode()),"", path.getPath());			
			File outXml = new File(f.replace("java/com/len","resources") + "Api"+MAPPER_XML_END);
			FileUtils.write(outXml, "");
			process(XML_FTL, outXml, table);
			
			File outInterface = new File(f +"Api"+ MAPPER_INTER_END);
			FileUtils.write(outInterface, "");
			process(INTER_FTL, outInterface, table);
			
			if(null!=table.getJoinTables() && table.getJoinTables().size()>0){
				table.setIsView(true);
				File outXmlExtendView = new File(f.replace("java/com/len","resources") + "ExtendView" + MAPPER_XML_END);
				FileUtils.write(outXmlExtendView, "");
				process(XML_FTL, outXmlExtendView, table);
				
				File outInterfaceView = new File(f + "ExtendView" + MAPPER_INTER_END);
				FileUtils.write(outInterfaceView, "");
				process(INTER_FTL, outInterfaceView, table);
			}
		}
		return 1;
		
	}
	
	/**
	 * 生成创建View的sql
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int genViewSql(File path ,List<DBTable> tables,List<DBView> pdmViews) throws Exception {
		Map<String,String> sqlMap = new HashMap<String,String>();
		Map<String,String> lastAddSqlMap = new HashMap<String,String>();		//依赖较多需要最后执行的sql语句
		StringBuffer createViewSql = new StringBuffer();
		if(null!=pdmViews){
			for(DBView view:pdmViews){
				if(StringUtils.startsWith(view.getCode(), "SQLVW_")){
					createViewSql.append(ToTemplateFileGenUtils.genPdmViewSql(view));
					sqlMap.put(view.getCode(), ToTemplateFileGenUtils.genPdmViewSql(view));
				}
			}
		}
		for(DBTable table:tables){
			String viewSql = "";
			boolean lastAdd = false;			//是否最后执行的sql
			if(table.getJoinTables().size()>0){
				for(int i=0;i<table.getJoinTables().size();i++){
					if(StringUtils.startsWith(table.getJoinTables().get(i).getTableCode(), "SQLVVW_")){
						lastAdd = true;
						break;
					}
				}
				String viewName = "VW_" + CreateUtils.getTab(table.getCode()).toUpperCase();
				StringBuffer joinTables = new StringBuffer();
				viewSql = viewSql.concat("drop VIEW if exists ");
				viewSql = viewSql.concat(viewName);
				viewSql = viewSql.concat(";\n");
				viewSql = viewSql.concat("create VIEW ");
				viewSql = viewSql.concat(viewName);
				viewSql = viewSql.concat(" as select ");
				for(int ita=0;ita<table.getCols().size();ita++){
					if(table.getCols().get(ita).isInSelfTable()){
						if(ita>0)
							viewSql = viewSql.concat(",");
						viewSql = viewSql.concat("TA.");
						viewSql = viewSql.concat(table.getCols().get(ita).getCode());
					}
				}
				for(int itsc=0;itsc<table.getJoinTables().size();itsc++){
					for(int itsca=0;itsca<table.getJoinTables().get(itsc).getColume().size();itsca++){
						viewSql = viewSql.concat(",");
						viewSql = viewSql.concat(table.getJoinTables().get(itsc).getTabelAliasCode());
						viewSql = viewSql.concat(".");
						viewSql = viewSql.concat(table.getJoinTables().get(itsc).getColume().get(itsca));
						viewSql = viewSql.concat(" AS ");
						viewSql = viewSql.concat(table.getJoinTables().get(itsc).getColumeAlias().get(itsca));
					}
				}
				viewSql = viewSql.concat(" from ");
				viewSql = viewSql.concat(table.getCode());
				viewSql = viewSql.concat(" TA ");
				for(int i=0;i<table.getJoinTables().size();i++){
					if(StringUtils.startsWith(table.getJoinTables().get(i).getTableCode(), "SQLVVW_")){
						lastAdd = true;
						break;
					}
				}
				table.setViewSqlQueryDetail(viewSql.substring(viewSql.indexOf(" as select ") + " as ".length()) + CreateUtils.genTableLeftJoinSqlWithSql(table));
				viewSql = CreateUtils.genTableLeftJoinSqlWithTable(table, viewSql, joinTables);
				table.setViewSqlQuery(viewSql.substring(viewSql.indexOf(" as select ") + 4));
				viewSql = viewSql.concat(";\n");
				if(!lastAdd){
					createViewSql.append(viewSql);
					sqlMap.put(viewName, viewSql);
				}else{
					lastAddSqlMap.put(viewName, viewSql);
				}
				/**
				 * mysql 不支持view中包含子查询
				 */
//				createViewSql.append(CreateUtils.genTableLeftJoinSqlWithSql(table));
			}
		}
		if(null!=pdmViews){
			for(DBView view:pdmViews){
				if(!sqlMap.containsKey(view.getCode())){
					createViewSql.append(ToTemplateFileGenUtils.genPdmViewSql(view));
				}
			}
		}
		for(String k:lastAddSqlMap.keySet()){
			createViewSql.append(lastAddSqlMap.get(k));
		}
		System.out.println("数据表有变化是，需要重新执行视图创建脚本。");
		String f = realPathBuild("createviewsql","",path.getPath());			
		File out = new File(f +SQL_END);
		FileUtils.write(out, "");
		Map<String, String> root = new HashMap<String, String>(); 
        root.put("createviewsql",createViewSql.toString());
		process(SQL_CREATEVIEW, out, root);
		return 1;
	}

	public int genMobiPostManTest(File path ,List<DBTable> tables) throws Exception{
		String f = realPathBuild("mobiposttest","",path.getPath());	
		File out = new File(f +JSON_COLLECTION_END);
		StringBuffer jsonInterfaceTest = new StringBuffer();
		Map<String, String> root = new HashMap<String, String>(); 
        root.put("mobiposttest",jsonInterfaceTest.toString());
		process(SQL_CREATEVIEW, out, root);
//		postmanjson
		return 1;
		
	}
	
	/**
	 * 数据层 接口测试
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int interMapperTest(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			String f = realPathBuild(CreateUtils.getTab(table.getCode()),"", path.getPath());			
			File out = new File(f + MAPPER_INTER_TEST_END);
			FileUtils.write(out, "");
			
			process(INTER_TEST_FTL, out, table);
		}
		return 1;
	}	
	
	/**
	 * service 层接口
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int svc(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			String f = realPathBuild(CreateUtils.getTab(table.getCode()), table.getSvcname(), path.getPath());			
			File out = new File(f + "ApiService" +JAVA_END);
			FileUtils.write(out, "");
			
			process(SVC_FTL, out, table);
		}
		return 1;
	}	
	
	/**
	 * service 层接口实现
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int svcImpl(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			String f = realPathBuild(CreateUtils.getTab(table.getCode()),  table.getSvcname(), path.getPath());			
			File out = new File(f + "ApiServiceImpl" +JAVA_END);
			FileUtils.write(out, "");
			
//			process(SVCIMPL_FTL, out, table);
			process(REF_SVCIMPL_FTL, out, table);
		}
		return 1;
	}	
	
	/**
	 * 后台管理 controller
	 * @param path
	 * @param tables
	 * @param leftMenuPath
	 * @return
	 * @throws Exception
	 */
	public int managerController(File path ,List<DBTable> tables,String leftMenuPath) throws Exception {
		StringBuffer sbli = new StringBuffer();
		String lastTableGroupOid = "";
		for(DBTable table:tables){
			if(StringUtils.isNotBlank(table.getTableGroupOid()) && 
					!StringUtils.equals(table.getTableGroupOid(), lastTableGroupOid)){
				if(StringUtils.isNotBlank(lastTableGroupOid) && 
						!StringUtils.equals(table.getTableGroupOid(), lastTableGroupOid)){
					sbli.append("</ul></li>");
				}
				sbli.append("<li><a>");
				sbli.append(table.getName());
				sbli.append("</a><ul class=\"navigation_sub\">");
				lastTableGroupOid = table.getTableGroupOid();
			}
			sbli.append("<li class='blblue'><a href=\"<c:url value='/mg/");
			sbli.append(CreateUtils.getTab(table.getCode()).toLowerCase());
			if(!StringUtils.isBlank(table.getTableGroupOid())){
				sbli.append("' />\">");
				sbli.append(table.getName());
				sbli.append("</a><div class=\"help\"><a href=\"<c:url value='/mg/");
			}else{
				sbli.append("' />\" style=\"width:80%\"><%-- 子菜单删除  style=\"width:80%\" --%>");
				sbli.append(table.getName());
				sbli.append("</a><div class=\"help fa\"><!-- 子菜单删除 fa --><a href=\"<c:url value='/mg/");
			}
			sbli.append(CreateUtils.getTab(table.getCode()).toLowerCase());
			sbli.append("/new' />\">+1</a></div>\n</li>\n");
			sbli.append("\n");
			
			
			String f = realPathBuild(CreateUtils.getTab(table.getCode()),  table.getAction(), path.getPath());			
			File out = new File(f + "ManagerController" +JAVA_END);
			FileUtils.write(out, "");
			process(ACTION_FTL, out, table);
		}
		String f = realPathBuild("leftmenu","",leftMenuPath);			
		File out = new File(f +JSP_END);
		FileUtils.write(out, "");
		Map<String, String> root = new HashMap<String, String>(); 
		sbli.append("</li></ul>");
        root.put("menu", sbli.toString()); 
		process(JSP_LEFTMENU, out, root);
		return 1;
	}
	
	/**
	 * 生成数据接口api页面
	 * @param path	文件路径
	 * @param tables	表列表

	 * @return
	 * @throws Exception
	 */
	public int databaseApiContent(File path ,List<DBTable> tables) throws Exception {
		StringBuffer sbli = new StringBuffer();
		StringBuffer sbView = new StringBuffer();		//生成计算用视图
		StringBuffer sbDict = new StringBuffer();		//字典表
		StringBuffer sbSubset = new StringBuffer();		//数据表数据字表，数据原表与字典表之间的关联表，存储一对多数值的表
		for(DBTable table:tables){
			if(StringUtils.startsWith(table.getCode(), "T_DICT_")){
				ToTemplateFileGenUtils.writeNavMenu(sbDict, table);
			}else if(StringUtils.startsWith(table.getCode(), "T_SUBSET_")){
				ToTemplateFileGenUtils.writeNavMenu(sbSubset, table);
			}else if(StringUtils.startsWith(table.getCode(), "SQLVW_") || StringUtils.startsWith(table.getCode(), "SQLVVW_")){
				ToTemplateFileGenUtils.writeNavMenu(sbView, table);
			}else{
				ToTemplateFileGenUtils.writeNavMenu(sbli, table);
			}
		}
		StringBuffer sbContent = new StringBuffer();
		StringBuffer sbViewContent = new StringBuffer();
		StringBuffer sbDictContent = new StringBuffer();	//字典表
		StringBuffer sbSubsetContent = new StringBuffer();	//数据表数据字表，数据原表与字典表之间的关联表，存储一对多数值的表
//		sbContent.append("<% String url = request.getScheme()+\"://\"+ request.getServerName() + \":\" + request.getServerPort(); %>");
//		sbViewContent.append("<% String url = request.getScheme()+\"://\"+ request.getServerName() + \":\" + request.getServerPort(); %>");
//		sbDictContent.append("<% String url = request.getScheme()+\"://\"+ request.getServerName() + \":\" + request.getServerPort(); %>");
//		sbSubsetContent.append("<% String url = request.getScheme()+\"://\"+ request.getServerName() + \":\" + request.getServerPort(); %>");
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").enableComplexMapKeySerialization().setExclusionStrategies(new InterfaceExclusionStrategy()).create();
		for(DBTable table:tables){
			if(StringUtils.startsWith(table.getCode(), "T_DICT_")){
				ToTemplateFileGenUtils.writeSection(sbDictContent, gson, table);
			}else if(StringUtils.startsWith(table.getCode(), "T_SUBSET_")){
				ToTemplateFileGenUtils.writeSection(sbSubsetContent, gson, table);
			}else if(StringUtils.startsWith(table.getCode(), "SQLVW_") || StringUtils.startsWith(table.getCode(), "SQLVVW_")){
				ToTemplateFileGenUtils.writeSection(sbViewContent,gson,table);
			}else{
				ToTemplateFileGenUtils.writeSection(sbContent, gson, table);
			}
		}
		writeDatabaseJsp(path,"", sbli, sbContent);
		writeDatabaseJsp(path,"_view", sbView, sbViewContent);
		writeDatabaseJsp(path,"_dict", sbDict, sbDictContent);
		writeDatabaseJsp(path,"_subset", sbSubset, sbSubsetContent);
		return 1;
	}

	/**
	 * 生成数据结构md说明文件
	 * @param path	文件路径
	 * @param tables	表列表

	 * @return
	 * @throws Exception
	 */
	public int databaseMdContent(File path ,List<DBTable> tables) throws Exception {
//		StringBuffer sbView = new StringBuffer();		//生成计算用视图
//		StringBuffer sbDict = new StringBuffer();		//字典表
//		StringBuffer sbSubset = new StringBuffer();		//数据表数据字表，数据原表与字典表之间的关联表，存储一对多数值的表
//		for(DBTable table:tables){
//			if(StringUtils.startsWith(table.getCode(), "T_DICT_")){
//				ToTemplateFileGenUtils.writeNavMenu(sbDict, table);
//			}else if(StringUtils.startsWith(table.getCode(), "T_SUBSET_")){
//				ToTemplateFileGenUtils.writeNavMenu(sbSubset, table);
//			}else if(StringUtils.startsWith(table.getCode(), "SQLVW_") || StringUtils.startsWith(table.getCode(), "SQLVVW_")){
//				ToTemplateFileGenUtils.writeNavMenu(sbView, table);
//			}else{
//				ToTemplateFileGenUtils.writeNavMenu(sbli, table);
//			}
//		}
		StringBuffer sbContent = new StringBuffer();
//		StringBuffer sbViewContent = new StringBuffer();
//		StringBuffer sbDictContent = new StringBuffer();	//字典表
//		StringBuffer sbSubsetContent = new StringBuffer();	//数据表数据字表，数据原表与字典表之间的关联表，存储一对多数值的表
//		sbContent.append("<% String url = request.getScheme()+\"://\"+ request.getServerName() + \":\" + request.getServerPort(); %>");
//		sbViewContent.append("<% String url = request.getScheme()+\"://\"+ request.getServerName() + \":\" + request.getServerPort(); %>");
//		sbDictContent.append("<% String url = request.getScheme()+\"://\"+ request.getServerName() + \":\" + request.getServerPort(); %>");
//		sbSubsetContent.append("<% String url = request.getScheme()+\"://\"+ request.getServerName() + \":\" + request.getServerPort(); %>");
		for(DBTable table:tables){
//			if(StringUtils.startsWith(table.getCode(), "T_DICT_")){
//				ToTemplateMdFileGenUtils.writeSection(sbDictContent, gson, table);
//			}else if(StringUtils.startsWith(table.getCode(), "T_SUBSET_")){
//				ToTemplateMdFileGenUtils.writeSection(sbSubsetContent, gson, table);
//			}else if(StringUtils.startsWith(table.getCode(), "SQLVW_") || StringUtils.startsWith(table.getCode(), "SQLVVW_")){
//				ToTemplateMdFileGenUtils.writeSection(sbViewContent,gson,table);
//			}else{
				ToTemplateMdFileGenUtils.genTableStructure(sbContent, table);
//			}
		}
		writeDatabaseMd(path,"", sbContent);
//		writeDatabaseJsp(path,"_view", sbView, sbViewContent);
//		writeDatabaseJsp(path,"_dict", sbDict, sbDictContent);
//		writeDatabaseJsp(path,"_subset", sbSubset, sbSubsetContent);
		return 1;
	}

	public int javaExampleMd(File path ,List<DBTable> tables) throws Exception {
		StringBuffer sbContent = new StringBuffer();
		sbContent.append("# Java 使用示例").append("\n");
		for(DBTable table:tables){
			String tabEntityName = CreateUtils.getTab(table.getCode()).replaceFirst(CreateUtils.getTab(table.getCode()).substring(0, 1),CreateUtils.getTab(table.getCode()).substring(0, 1).toLowerCase());
			ToTemplateMdFileGenUtils.genJavaCodeMd(sbContent, table,tabEntityName);
		}
		writeJavaExampleCodeMd(path,"", sbContent);
		return 1;
	}

	public int databaseExcel(File path ,List<DBTable> tables) throws IOException, RowsExceededException, WriteException, InstantiationException, IllegalAccessException, ClassNotFoundException{
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
        
        WritableFont noBoldItalic = new WritableFont(WritableFont.ARIAL,14,WritableFont.NO_BOLD,true,                                 // 斜体  
                UnderlineStyle.NO_UNDERLINE,            // 下划线  
                Colour.GREY_50_PERCENT);//设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
        WritableCellFormat titleNoBoldItalic = new WritableCellFormat(noBoldItalic);//生成一个单元格样式控制对象
        titleNoBoldItalic.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        titleNoBoldItalic.setWrap(true);
        
		String f = realPathBuild("database_readme","",path.getPath());		
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
			sheet.mergeCells(0, 0, 0, 1);
			sheet.addCell(new Label(0,0,"数据表",titleFormate));
			sheet.mergeCells(1, 0, 1, 1);
			sheet.addCell(new Label(1,0,"扩展字段数据表\\引用表、视图",titleFormate));
			sheet.mergeCells(2, 0, 5, 1);
			sheet.addCell(new Label(2,0,"数据列",titleFormate));
			sheet.mergeCells(6, 0, 6, 1);
			sheet.addCell(new Label(6,0,"创建SQL",titleFormate));
			sheetMap.put(keySet, sheet);
		}
		
		for(DBTable table:tables){
			ToTemplateFileGenUtils.genTableExcelSheet(titleNoBold, sheetMap, table,true);
			if(null!=table.getJoinTables() && table.getJoinTables().size()>0){
				if(StringUtils.startsWith(table.getCode(), Start.pdmDBTablePrefixStr))
					table.setCode("VW_".concat(table.getCode().substring(2, table.getCode().length())));
				else
					table.setCode("VW_".concat(table.getCode()));
				table.setName(table.getName().concat("视图"));
				ToTemplateFileGenUtils.genTableExcelSheet(titleNoBoldItalic, sheetMap, table,false);
			}
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
		return 1;
	}

	/**
	 * 生成JSON接口页面
	 * @param path	文件路径
	 * @param tables	表列表

	 * @return
	 * @throws Exception
	 */
	public int interJsonContent(File path ,List<DBTable> tables) throws Exception {
		new InterfaceFileJson().interJsp(path, tables);
		return 1;
	}

	/**
	 * 生成JSON接口postman测试文件
	 * @param path	文件路径
	 * @param tables	表列表

	 * @return
	 * @throws Exception
	 */
	public int interJsonPostMan(File path ,List<DBTable> tables,String webname) throws Exception {
		new InterfaceFileJson().interPostMan(path, tables, webname);
		return 1;
	}
	
	/**
	 * 生成JSON接口excel说明文档
	 * @param path	文件路径
	 * @param tables	表列表

	 * @return
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public int interJsonExcel(File path ,List<DBTable> tables) throws IOException, RowsExceededException, WriteException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		new InterfaceFileJson().interExcel(path, tables);
		return 1;
	}
	
	/**
	 * 生成移动端接口页面
	 * @param path	文件路径
	 * @param tables	表列表

	 * @return
	 * @throws Exception
	 */
	public int interMobiContent(File path ,List<DBTable> tables) throws Exception {
		new InterfaceFileMobi().interJsp(path, tables);
		return 1;
	}
	
	/**
	 * 生成MOBI接口postman测试文件
	 * @param path	文件路径
	 * @param tables	表列表
	 * @return
	 * @throws Exception
	 */
	public int interMobiPostMan(File path ,List<DBTable> tables,String webname) throws Exception {
		new InterfaceFileMobi().interPostMan(path, tables, webname);
		return 1;
	}
	
	/**
	 * 生成MOBI接口excel说明文档
	 * @param path	文件路径
	 * @param tables	表列表
	 * @return
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public int interMobiExcel(File path ,List<DBTable> tables) throws IOException, RowsExceededException, WriteException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		new InterfaceFileMobi().interExcel(path, tables);
		return 1;
	}
	
	/**
	 * 生成 spring 配置文件
	 * @param path	路径
	 * @param packageName	java包路径
	 * @return
	 * @throws Exception
	 */
	public int applicationContextContent(File path ,String packageName) throws Exception {
		String f = realPathBuild("applicationContext-context","",path.getPath());			
		File out = new File(f + XML_END);
		FileUtils.write(out, "");
		Map<String, String> root = new HashMap<String, String>(); 
        root.put("packagename",packageName);
        
		process(APPLICATION_CONTEXT, out, root);
		
//		String fmysql = realPathBuild("applicationContext-mysql.xml","",path.getPath());
//		String testfmysql = fmysql.replace("WebContent\\WEB-INF\\", "src\\conf\\");
//		String fjdbc = realPathBuild("mysql_jdbc.properties","",path.getPath());
//		String testfjdbc = fjdbc.replace("WebContent\\WEB-INF\\", "src\\conf\\");
//		String fsite = realPathBuild("site.properties","",path.getPath());
//		String testfsite = fsite.replace("WebContent\\WEB-INF\\", "src\\conf\\");
//		String fview = "WebContent\\pages\\docs\\createviewsql.sql";
//		String testfview = "src\\test\\database_view.sql";
//		
//		FileUtils.copyFile(new File(fmysql), new File(testfmysql));
//		FileUtils.copyFile(new File(fjdbc), new File(testfjdbc));
//		FileUtils.copyFile(new File(fsite), new File(testfsite));
//		FileUtils.copyFile(new File(fview), new File(testfview));
//		modifyFile(test,"/WEB-INF/","");
//		modifyFile(new File(testfmysql),"/WEB-INF/","");
		return 1;
	}
    
	/**
	 * 生成 web.xml 
	 * @param path	路径
	 * @param webName	应用名称
	 * @return
	 * @throws Exception
	 */
    public int webXmlContent(File path ,String webName) throws Exception {
		String f = realPathBuild("web","",path.getPath());			
		File out = new File(f + XML_END);
		FileUtils.write(out, "");
		Map<String, String> root = new HashMap<String, String>(); 
        root.put("webname",webName);
        
		process(WEB_XML, out, root);
		return 1;
	}
    
    /**
     * view 展示 Controller
     * @param path	路径
     * @param tables	表列表
     * @return
     * @throws Exception
     */
	public int viewController(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			String f = realPathBuild(CreateUtils.getTab(table.getCode()),  table.getAction(), path.getPath());			
//			File out = new File(f + "ViewController" +JAVA_END);
			File out = new File(f + "ApiController" +JAVA_END);
			FileUtils.write(out, "");
			process(VIEWACTION_FTL, out, table);
		}
		return 1;
	}
	
	/**
	 * json 接口 Congroller
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int interfaceJson(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			String f = realPathBuild(CreateUtils.getTab(table.getCode()),  table.getAction(), path.getPath());			
			File out = new File(f + "InterfaceJsonController" +JAVA_END);
			FileUtils.write(out, "");
			
			process(INTERFACE_JSON_FTL, out, table);
		}
		return 1;
	}
	
	/**
	 * mobi 接口 Controller
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int interfaceMobi(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			String f = realPathBuild(CreateUtils.getTab(table.getCode()),  table.getAction(), path.getPath());			
			File out = new File(f + "InterfaceMobiController" +JAVA_END);
			FileUtils.write(out, "");
			
			process(INTERFACE_MOBI_FTL, out, table);
		}
		return 1;
	}
	
	/**
	 * rest 接口 Controller
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int interfaceRest(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			String f = realPathBuild(CreateUtils.getTab(table.getCode()),  table.getAction(), path.getPath());			
			File out = new File(f + "InterfaceRestController" +JAVA_END);
			FileUtils.write(out, "");
			
			process(INTERFACE_REST_FTL, out, table);
		}
		return 1;
	}
	
	/**
	 * 修改功能 jsp
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int jspmodify(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			String f = realPathBuild(CreateUtils.getTab(table.getCode()),  table.getAction(), path.getPath());			
			File out = new File(f.toLowerCase() + "/" + table.getCode().toLowerCase().replaceFirst("^t_", "").replace("_", "") + "_modify" + JSP_END);
			FileUtils.write(out, "");
			
			process(JSP_MODIFY, out, table);
		}
		return 1;
	}	
	
	/**
	 * 展示功能 jsp
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int jspshow(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			String f = realPathBuild(CreateUtils.getTab(table.getCode()),  table.getAction(), path.getPath());			
			File out = new File(f.toLowerCase() + "/" + table.getCode().toLowerCase().replaceFirst("^t_", "").replace("_", "")+ "_show" + JSP_END);
			FileUtils.write(out, "");
			
			process(JSP_SHOW, out, table);
		}
		return 1;
	}	
	
	/**
	 * 添加功能 jsp
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int jspadd(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			String f = realPathBuild(CreateUtils.getTab(table.getCode()),  table.getAction(), path.getPath());			
			File out = new File(f.toLowerCase() + "/" + table.getCode().toLowerCase().replaceFirst("^t_", "").replace("_", "")+ "_add" + JSP_END);
			FileUtils.write(out, "");
			
			process(JSP_ADD, out, table);
		}
		return 1;
	}	
	
	/**
	 * 扩展列表 功能 jsp
	 * @param path
	 * @param tables
	 * @return
	 * @throws Exception
	 */
	public int jsplist_with_colname(File path ,List<DBTable> tables) throws Exception {
		for(DBTable table:tables){
			String f = realPathBuild(CreateUtils.getTab(table.getCode()),  table.getAction(), path.getPath());
			File out = new File(f.toLowerCase() + "/" + table.getCode().toLowerCase().replaceFirst("^t_", "").replace("_", "")+ "_list" + JSP_END);
			FileUtils.write(out, "");
			
			process(JSP_LIST_WITH_COLNAME, out, table);

			File outHeader = new File(f.toLowerCase() + "/" + table.getCode().toLowerCase().replaceFirst("^t_", "").replace("_", "")+ "_list_header" + JSP_END);
			FileUtils.write(outHeader, "");
			process(JSP_LIST_HEADER, outHeader, table);

			File outSearch = new File(f.toLowerCase() + "/" + table.getCode().toLowerCase().replaceFirst("^t_", "").replace("_", "")+ "_list_search" + JSP_END);
			FileUtils.write(outSearch, "");
			process(JSP_LIST_SEARCH, outSearch, table);
			
			File outPagejs = new File(f.toLowerCase() + "/" + table.getCode().toLowerCase().replaceFirst("^t_", "").replace("_", "")+ "_list_page_js" + JSP_END);
			FileUtils.write(outPagejs, "");
			process(JSP_LIST_PAGE_JS, outPagejs, table);
			
			
		}
		return 1;
	}	
	
	private void writeDatabaseJsp(File path,String fileSuffix, StringBuffer sbli,
			StringBuffer sbContent) throws IOException, Exception {
		String f = realPathBuild("databaseapi","",path.getPath());			
//		File out = new File(f + fileSuffix +JSP_END);
		File out = new File(f + fileSuffix + HTML_END);
		FileUtils.write(out, "");
		Map<String, String> root = new HashMap<String, String>(); 
        root.put("nav", sbli.toString()); 
        root.put("content",sbContent.toString());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM");
        root.put("createDate",format.format(new Date()));
		process(JSP_DATABASE_API_HTML, out, root);
	}

	private void writeDatabaseMd(File path,String fileSuffix,
								  StringBuffer sbContent) throws IOException, Exception {
		String f = realPathBuild("databasemd","",path.getPath());
		File out = new File(f + fileSuffix + MD_END);
		FileUtils.write(out, "");
		Map<String, String> root = new HashMap<String, String>();
		root.put("content",sbContent.toString());
		process(DATABASE_MD, out, root);
	}

	private void writeJavaExampleCodeMd(File path,String fileSuffix,
								 StringBuffer sbContent) throws IOException, Exception {
		String f = realPathBuild("javaexample","",path.getPath());
		File out = new File(f + fileSuffix + MD_END);
		FileUtils.write(out, "");
		Map<String, String> root = new HashMap<String, String>();
		root.put("content",sbContent.toString());
		process(JAVAEXAMPLE_MD, out, root);
	}

//	private void modifyFile(File file,String oStr,String toStr) throws IOException{
//		BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//        StringBuffer strBuffer = new StringBuffer();
//        for (String temp = null; (temp = bufReader.readLine()) != null; temp = null) {
//     	   Pattern p = Pattern.compile(oStr);
//            Matcher m = p.matcher(temp);
//            while (m.find()) {
//         		   temp = temp.replaceFirst(oStr, toStr);
//            } 
//            strBuffer.append(temp);
//            strBuffer.append(System.getProperty("line.separator"));//行与行之间的分割
//        }
//        bufReader.close();
//        PrintWriter printWriter = new PrintWriter(file.getAbsolutePath());//替换后输出的文件位置（切记这里的/tmp/source 在你的本地必须有这个文件夹）
//        printWriter.write(strBuffer.toString().toCharArray());
//        printWriter.flush();
//        printWriter.close();
//	}
}
