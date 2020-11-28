package com.tools.entity;

import com.tools.entity.ftl.JoinTable;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class DBColumn {
	/**
	 * 列所属表格唯一标示
	 */
	String tableOId;

	/**
	 * 唯一标识
	 */
	String oId;
	/**
	 * 名称
	 */
	public String name;
	/**
	 * code
	 */
	String code;
	/**
	 * 类型
	 */
	String type;
	/**
	 * 注释
	 */
	String comment;
	/**
	 * 是否必填
	 */
	String mandatory="";
	/**
	 * 默认值
	 */
	String defaultValue;
	boolean primaryKey = false;	//	是否是主键
	public boolean inSelfTable = true;  //是否是join查询字段，不在本表格中存在，false时为不在本表格
	boolean inTableColumn = true;	//是否是数据表中的字段，把扩展查询字段排除出去
	String typeExpend = "";			//字段类型的扩展类型，如数组，list等
	
	/**
	 * 列所在的表格是否有left join 查询字段<br/>
	 * 用于判断mapper是否有WithJoin方法
	 */
	public boolean fatherTableHasJoin = false;

	/**
	 * 扩展查询的表
	 * LEFT JOIN
	 */
	List<JoinTable> joinTables;
	/**
	 * 关联查询<br/>
	 * 父表列CODE
	 */
	String refFatherColumn;
	
	/**
	 * 关联查询<br/>
	 * 子表
	 */
	DBTable refChildTable;
	
	/**
	 * 关联查询<br/>
	 * 子表列CODE
	 */
	String refChildColumn;
	/**
	 * 关联查询<br/>
	 * 子表关联列，是否是主键
	 */
	boolean refChildColumnPrimaryKey = false;
	
	
	public String getTableOId() {
		return tableOId;
	}
	public void setTableOId(String tableOId) {
		this.tableOId = tableOId;
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

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	public boolean isInSelfTable() {
		return inSelfTable;
	}
	public void setInSelfTable(boolean inSelfTable) {
		this.inSelfTable = inSelfTable;
	}
	
	public String getTypeExpend() {
		return typeExpend;
	}
	public void setTypeExpend(String typeExpend) {
		this.typeExpend = typeExpend;
	}
	public String getMandatory() {
		return mandatory;
	}
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isInTableColumn() {
		return inTableColumn;
	}
	public void setInTableColumn(boolean inTableColumn) {
		this.inTableColumn = inTableColumn;
	}
	/**
	 * @return the refFatherColumn
	 */
	public String getRefFatherColumn() {
		return refFatherColumn;
	}
	/**
	 * @param refFatherColumn the refFatherColumn to set
	 */
	public void setRefFatherColumn(String refFatherColumn) {
		this.refFatherColumn = refFatherColumn;
	}
	
	public DBTable getRefChildTable() {
		return refChildTable;
	}
	public void setRefChildTable(DBTable refChildTable) {
		this.refChildTable = refChildTable;
	}
	/**
	 * @return the refChildColumn
	 */
	public String getRefChildColumn() {
		return refChildColumn;
	}
	/**
	 * @param refChildColumn the refChildColumn to set
	 */
	public void setRefChildColumn(String refChildColumn) {
		this.refChildColumn = refChildColumn;
	}
	/**
	 * @return the refChildColumnPrimaryKey
	 */
	public boolean isRefChildColumnPrimaryKey() {
		return refChildColumnPrimaryKey;
	}
	/**
	 * @param refChildColumnPrimaryKey the refChildColumnPrimaryKey to set
	 */
	public void setRefChildColumnPrimaryKey(boolean refChildColumnPrimaryKey) {
		this.refChildColumnPrimaryKey = refChildColumnPrimaryKey;
	}
	public boolean isFatherTableHasJoin() {
		return fatherTableHasJoin;
	}
	public void setFatherTableHasJoin(boolean fatherTableHasJoin) {
		this.fatherTableHasJoin = fatherTableHasJoin;
	}
	public List<JoinTable> getJoinTables() {
		return joinTables;
	}
	public void setJoinTables(List<JoinTable> joinTables) {
		this.joinTables = joinTables;
	}
	

}
