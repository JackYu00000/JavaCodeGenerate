package com.tools.entity.ftl;

import com.tools.entity.DBTable;

public class JoinTable {
	
	/**
	 * option类型字段  扩展查询的表名
	 */
	String joinTableCode;		
	/**
	 * option类型字段  扩展查询的主键
	 */
	String joinTableKey;		
	/**
	 * option类型字段  扩展查询的显示值
	 */
	String joinTableShow;		
	
	/**
	 * 扩展查询的表
	 * LEFT JOIN
	 */
	DBTable joinTable;

	public String getJoinTableCode() {
		return joinTableCode;
	}

	public String getJoinTableKey() {
		return joinTableKey;
	}

	public String getJoinTableShow() {
		return joinTableShow;
	}

	public void setJoinTableCode(String joinTableCode) {
		this.joinTableCode = joinTableCode;
	}

	public void setJoinTableKey(String joinTableKey) {
		this.joinTableKey = joinTableKey;
	}

	public void setJoinTableShow(String joinTableShow) {
		this.joinTableShow = joinTableShow;
	}

	public DBTable getJoinTable() {
		return joinTable;
	}

	public void setJoinTable(DBTable joinTable) {
		this.joinTable = joinTable;
	}

	
	
}
