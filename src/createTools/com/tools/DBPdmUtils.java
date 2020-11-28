package com.tools;

import com.tools.entity.CreateUtils;
import com.tools.entity.DBColumn;
import com.tools.entity.DBTable;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBPdmUtils {
	
	public static int getDBTableSelfColumnSize(DBTable table){
		int cSize = 0;
		for(DBColumn dbc:table.getCols()){
			if(dbc.isInSelfTable()){
				cSize += 1;
			}
		}
		return cSize;
	}
	
	public static int getDBTableInTableColumnSize(DBTable table){
		int cSize = 0;
		for(DBColumn dbc:table.getCols()){
			if(dbc.isInTableColumn()){
				cSize += 1;
			}
		}
		return cSize;
	}
	
	/**
	 * 返回表格的json格式字符串
	 * @param table	表格
	 * @param isSelf	是否只返回本表的，或者包括扩展查询字段
	 * @return
	 */
	public static String genTableJson(DBTable table,boolean isSelf){
		StringBuffer tabSB = new StringBuffer();
		tabSB.append("{").append("\r");
		for(DBColumn dbc:table.getCols()){
			if(dbc.isInSelfTable()){
				if(tabSB.length()>2){
					tabSB.append(",\r");
				}
				tabSB.append("\t\"").append(CreateUtils.getCol(dbc.getCode()) + "\":\"" + dbc.getName() + "," + dbc.getType() + "\"");
			}else{
				if(!isSelf){
					if(tabSB.length()>2){
						tabSB.append(",\r");
					}
					tabSB.append("\t\"").append(CreateUtils.getCol(dbc.getCode()) + "\":\"" + dbc.getName() + "," + dbc.getType() + "\"");
				}
			}
		}
		tabSB.append("\r\t").append("}\r");
		return tabSB.toString();
	}
	/**
	 * 给可以比较大小的字段，生成用户比较的参数值
	 * @param colsnew
	 * @param dbc
	 */
	public static void genIntegerMultsCol(List<DBColumn> colsnew, DBColumn dbc) {
		//给类型为非字符串类型的字段，加入列表类型，用于获取数据
		if(dbc.isPrimaryKey()){
			DBColumn nldbcMs = new DBColumn();
			nldbcMs.setCode(dbc.getCode() + "_MULTS");
			nldbcMs.setName(dbc.getName() + "多个值检索条件");
			nldbcMs.setComment(dbc.getName() + "多个值检索条件");
			nldbcMs.setoId(dbc.getoId() + "_MULTS");
			nldbcMs.setType(dbc.getType());
			nldbcMs.setTypeExpend("Array");
			nldbcMs.setPrimaryKey(false);
			nldbcMs.setInSelfTable(false);
			nldbcMs.setInTableColumn(false);
			colsnew.add(nldbcMs);
		}
	}
	
	public static boolean checkColumnCanCompare(DBColumn dbc){
		if(StringUtils.contains(dbc.getType(), "date")
				|| StringUtils.startsWith(dbc.getType().toUpperCase(), "DECIMAL")
				|| StringUtils.startsWith(dbc.getType().toUpperCase(), "FLOAT")
				|| StringUtils.startsWith(dbc.getType().toUpperCase(), "INT")
				|| StringUtils.startsWith(dbc.getType().toUpperCase(), "SMALLINT")
				|| StringUtils.startsWith(dbc.getType().toUpperCase(), "MEDIUMINT")
				|| StringUtils.startsWith(dbc.getType().toUpperCase(), "TINYINT")
				|| StringUtils.startsWith(dbc.getType().toUpperCase(), "BIGINT")){
			return true;
		}
		return false;
	}
	
	public static String getKey(String tkey){
		String temp = tkey.toLowerCase();
		
		temp = StringUtils.replace(temp, "_", " ");
		if(temp.indexOf("t ")==0){
			temp = StringUtils.substring(temp, 2);
		}
		
		temp = WordUtils.capitalize(temp);
		temp = StringUtils.remove(temp, " ");
		StringBuffer buffer =new StringBuffer();
		if(temp.length()>=2){
			char arr[] = temp.toCharArray();
			int isUpper = 0;
			for(char c :arr){
				if(CharUtils.isAsciiAlphaUpper(c)&&isUpper!=0){
					c = StringUtils.lowerCase(""+c).charAt(0);
					isUpper=0;
				}else if(CharUtils.isAsciiAlphaUpper(c)&&isUpper==0){
					isUpper++;
				}else {
					isUpper=0;
				}
				buffer.append(c);
			}
		}
		return buffer.toString();
	}
	
	public static void renameBaseFolder(String filePath){
		File root = new File(filePath);
		File newFolder = new File(filePath + Start.webname);
		File[] files = root.listFiles();
		if(null!=files){
			for(File file:files){
				if(file.isDirectory() && !Start.renameFolderExpects.contains(file.getName())){
					file.renameTo(newFolder);
				}
			}
		}

		modifyFiles(newFolder.getAbsolutePath());
	}
	
	public static void modifyFiles(String filePath){
		File root = new File(filePath);
		File[] files = root.listFiles();
		if(null!=files){
			for(File file:files){
				if(!file.isDirectory()){
					try{
						BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
						StringBuffer strBuffer = new StringBuffer();
						for (String temp = null; (temp = bufReader.readLine()) != null; temp = null) {
							Pattern p = Pattern.compile(Start.replacePackageTextRegx);
							Matcher m = p.matcher(temp);
							while (m.find()) {
								if(!Start.renameFolderExpects.contains(m.group(1))){
									temp = temp.replaceFirst(Start.replacePackageTextRegx, Start.packagename + ".");
								}
							}
							strBuffer.append(temp);
							strBuffer.append(System.getProperty("line.separator"));//行与行之间的分割
						}
						bufReader.close();
						PrintWriter printWriter = new PrintWriter(file.getAbsolutePath());//替换后输出的文件位置（切记这里的/tmp/source 在你的本地必须有这个文件夹）
						printWriter.write(strBuffer.toString().toCharArray());
						printWriter.flush();
						printWriter.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					modifyFiles(file.getAbsolutePath());
				}
			}
		}

	}
}
