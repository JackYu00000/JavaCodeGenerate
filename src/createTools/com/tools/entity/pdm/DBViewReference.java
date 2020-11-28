package com.tools.entity.pdm;

public class DBViewReference {

	/**
	 * pdm中唯一标识
	 */
	public String oId;
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系名称,Name
	 */
	public String name;
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系Code
	 */
	public String code;
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系父表
	 */
	public String refFatherTable;
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系父表中关联列，oid
	 */
	public String refFatherColumn;

	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系子表，oid 
	 */
	public String refChildTable;
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系子表中关联列，oid
	 */
	public String refChildColumn;
	public String getoId() {
		return oId;
	}
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public String getRefFatherTable() {
		return refFatherTable;
	}
	public String getRefFatherColumn() {
		return refFatherColumn;
	}
	public String getRefChildTable() {
		return refChildTable;
	}
	public String getRefChildColumn() {
		return refChildColumn;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setRefFatherTable(String refFatherTable) {
		this.refFatherTable = refFatherTable;
	}
	public void setRefFatherColumn(String refFatherColumn) {
		this.refFatherColumn = refFatherColumn;
	}
	public void setRefChildTable(String refChildTable) {
		this.refChildTable = refChildTable;
	}
	public void setRefChildColumn(String refChildColumn) {
		this.refChildColumn = refChildColumn;
	}
	
}
