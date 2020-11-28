package com.tools.entity;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public class CreateUtils {
	
	/**
	 * 根据表名（dx_picture_detail），返回对象名（DxPictureDetail）
	 * @param tableName 表名 
	 * @return 
	 */
	public static String getTab(String tableName){
		String temp = tableName.toLowerCase();
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
	
	/**
	 * 返回列名（IS_FULLCUT），返回属性名（isFullcut）
	 * @param colCode
	 * @return 驼峰格式
	 */
	public static String getCol(String colCode){
		if(StringUtils.isNotBlank(colCode)){
			String temp = StringUtils.lowerCase(colCode.replace("_", " "));
			return WordUtils.uncapitalize(WordUtils.capitalize(temp).replace(" ", ""));
		}
		return null;
	}
	
	/**
	 * 返回table中left join sql语句，使用sql脚本
	 * @param table
	 * @return
	 */
	public static String genTableLeftJoinSqlWithSql(DBTable table){
		StringBuffer createViewSql = new StringBuffer();
		for(int i=0;i<table.getJoinTables().size();i++){
			createViewSql.append("LEFT JOIN (SELECT ");
			createViewSql.append(table.getJoinTables().get(i).getJoinPrimaryKey());
			for(int j=0;j<table.getJoinTables().get(i).getColume().size();j++){
				createViewSql.append(",");
				createViewSql.append(table.getJoinTables().get(i).getColume().get(j));
			}
			createViewSql.append(" FROM ");
			createViewSql.append(table.getJoinTables().get(i).getTableCode());
			createViewSql.append(") ");
			createViewSql.append(table.getJoinTables().get(i).getTabelAliasCode());
			createViewSql.append(" ON ");
			createViewSql.append(table.getJoinTables().get(i).getTabelAliasCode());
			createViewSql.append(".");
			createViewSql.append(table.getJoinTables().get(i).getJoinPrimaryKey());
			createViewSql.append("= ");
			createViewSql.append("TA.");
			createViewSql.append(table.getJoinTables().get(i).getJoinOnColume());
			createViewSql.append(" ");
		}
		return createViewSql.toString();
	}

	
	public static String genTableLeftJoinSqlWithTable(DBTable table, String viewSql,
			StringBuffer joinTables){
		StringBuffer createViewSql = new StringBuffer();
		for(int i=0;i<table.getJoinTables().size();i++){
			createViewSql.append("LEFT JOIN ");
			createViewSql.append(table.getJoinTables().get(i).getTableCode());
			createViewSql.append(" ");
			createViewSql.append(table.getJoinTables().get(i).getTabelAliasCode());
			createViewSql.append(" ON ");
			createViewSql.append(table.getJoinTables().get(i).getTabelAliasCode());
			createViewSql.append(".");
			createViewSql.append(table.getJoinTables().get(i).getJoinPrimaryKey());
			createViewSql.append("= TA.");
			createViewSql.append(table.getJoinTables().get(i).getJoinOnColume());
			createViewSql.append(" ");
			
			if(i>0)
				joinTables.append(",");
			joinTables.append(table.getJoinTables().get(i).getTableName());
		}
		viewSql = viewSql.concat(createViewSql.toString());
		return viewSql;
	}
}
