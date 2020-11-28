package com.tools.entity.pdm;

/**
 * 从pdm中解析出关联关系使用<br/>
 * 1..1,1..**
 * @author hanbing
 *
 */
public class DBReference {
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
	 * 关系对应类型，1对多，一对一
	 */
	public String cardinality;
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
	public void setoId(String oId) {
		this.oId = oId;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系名称,Name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系名称,Name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系Code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系Code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系对应类型，1对多，一对一
	 */
	public String getCardinality() {
		return cardinality;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系对应类型，1对多，一对一
	 */
	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系父表
	 */
	public String getRefFatherTable() {
		return refFatherTable;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系父表
	 */
	public void setRefFatherTable(String refFatherTable) {
		this.refFatherTable = refFatherTable;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系子表，oid 
	 */
	public String getRefChildTable() {
		return refChildTable;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系子表，oid 
	 */
	public void setRefChildTable(String refChildTable) {
		this.refChildTable = refChildTable;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系父表中关联列，oid
	 */
	public String getRefFatherColumn() {
		return refFatherColumn;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系父表中关联列，oid
	 */
	public void setRefFatherColumn(String refFatherColumn) {
		this.refFatherColumn = refFatherColumn;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系子表中关联列，oid
	 */
	public String getRefChildColumn() {
		return refChildColumn;
	}
	/**
	 * 本表关联外表<i>1..1,1..*</i><br/>
	 * 关系子表中关联列，oid
	 */
	public void setRefChildColumn(String refChildColumn) {
		this.refChildColumn = refChildColumn;
	}
	
	
}
