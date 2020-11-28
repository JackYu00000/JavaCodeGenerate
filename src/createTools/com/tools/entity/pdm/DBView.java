package com.tools.entity.pdm;

import java.util.ArrayList;
import java.util.List;

public class DBView {
	/**
	 * 唯一标识
	 */
	String oId;
	/**
	 * 名称
	 */
	String name;
	/**
	 * code
	 */
	String code;
	/**
	 * 注释
	 */
	String comment;
	/**
	 * 视图的检索sql
	 */
	String viewSqlQuery;
	/**
	 * 视图显示字段
	 */
	List<DBViewColumn> columns = new ArrayList<DBViewColumn>();
	
	public void addCol(DBViewColumn col){
		columns.add(col);
	}
	
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getViewSqlQuery() {
		return viewSqlQuery;
	}
	public void setViewSqlQuery(String viewSqlQuery) {
		this.viewSqlQuery = viewSqlQuery;
	}
	public List<DBViewColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<DBViewColumn> columns) {
		this.columns = columns;
	}
	
	
}
