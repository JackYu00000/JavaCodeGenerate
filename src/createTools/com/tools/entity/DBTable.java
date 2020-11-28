package com.tools.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tools.Start;
import com.tools.entity.ftl.FtlJoinTable;
import com.tools.entity.ftl.JoinTableSql;
import com.tools.entity.pdm.DBExtendedDependencyTable;
import com.tools.entity.pdm.DBTPk;

public class DBTable {
	
	/**
	 * 唯一标识
	 */
	String oId;
	/**
	 * 唯一标示 uuid
	 */
	String objectID;
	/**
	 * 名称
	 */
	public String name;
	/**
	 * code
	 */
	String code;
	/**
	 * 描述
	 */
	String description;
	/**
	 * 视图的检索sql
	 * 在生成xml时，生成查询语句使用
	 */
	String viewSqlQuery;
	
	/**
	 * 生成视图时，视图的code
	 */
	String viewCode;
	
	/**
	 * 视图的检索sql，left join select 语句，不是完整表
	 * 在生成xml时，生成查询语句使用
	 */
	String viewSqlQueryDetail;
	
	String tableKeyWordOid;
	String tableGroupOid;										//表格在pdm中group分组信息
	String tablePrimaryKeyRefOid;		//表格主键应用的 oid
	List<DBTPk> pks = new ArrayList<DBTPk>();
	List<DBColumn> cols = new ArrayList<DBColumn>();			//普通列字段
	List<DBColumn> childCols = new ArrayList<DBColumn>();		//存储子表数据的 数据列
	List<DBExtendedDependencyTable> dependency = new ArrayList<DBExtendedDependencyTable>();
	List<FtlJoinTable> joinTables = new ArrayList<FtlJoinTable>();
	/**
	 * 是否是视图
	 */
	Boolean isView = false;
	/**
	 * 是否有用户比较的列<br/>
	 * 用于判断是否生成枚举
	 */
	boolean hasCompareColumn = false;
	
	/**
	 * 是否有扩展字段:left join,chnval<br>
	 */
	boolean hasExtendColumn = false;
	
	Map<String,JoinTableSql> joinTableSql = new HashMap<String,JoinTableSql>();	//jointable 创建sql语句
	
	/**
	 * 表格 中关联查询其他表的列<br/>
	 * <i>1..1,1..*</i>
	 */
	List<DBColumn> refCols = new ArrayList<DBColumn>();		//存储子表数据的 数据列
	String packname="";
	String mapperpackatename = "";
	String svcname = "";
	String actionbase = "";
	String action = "";
	String vojo = "";
	
	public String getTab(){
		return CreateUtils.getTab(code.toLowerCase());
	}

	public void addPk(DBTPk pk){
		pks.add(pk);
	}
	public void addCol(DBColumn col){
		cols.add(col);
	}
	public void addDependency(DBExtendedDependencyTable depend){
		dependency.add(depend);
	}
	
	public String getoId() {
		return oId;
	}

	public void setoId(String oId) {
		this.oId = oId;
	}
	
	
	public String getViewCode() {
		String result = code.replaceAll(Start.pdmDBTablePrefixStr, "");
		result = result.replaceAll("_", "");
		viewCode = "VW_" + result;
		return viewCode;
	}

	public void setViewCode(String viewCode) {
		this.viewCode = viewCode;
	}

	public String getObjectID() {
		return objectID;
	}


	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}


	public String getPackname() {
		return packname;
	}
	public void setPackname(String packname) {
		this.packname = packname;
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
	public List<DBColumn> getCols() {
		return cols;
	}
	public void setCols(List<DBColumn> cols) {
		this.cols = cols;
	}
	
	public List<DBColumn> getChildCols() {
		return childCols;
	}

	public void setChildCols(List<DBColumn> childCols) {
		this.childCols = childCols;
	}

	public String getSvcname() {
		return svcname;
	}
	public void setSvcname(String svcname) {
		this.svcname = svcname;
	}
	public String getActionbase() {
		return actionbase;
	}
	public void setActionbase(String actionbase) {
		this.actionbase = actionbase;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getVojo() {
		return vojo;
	}
	public void setVojo(String vojo) {
		this.vojo = vojo;
	}
	public String getMapperpackatename() {
		return mapperpackatename;
	}
	public void setMapperpackatename(String mapperpackatename) {
		this.mapperpackatename = mapperpackatename;
	}

	public List<DBTPk> getPks() {
		return pks;
	}

	public void setPks(List<DBTPk> pks) {
		this.pks = pks;
	}

	public List<DBExtendedDependencyTable> getDependency() {
		return dependency;
	}

	public void setDependency(List<DBExtendedDependencyTable> dependency) {
		this.dependency = dependency;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<FtlJoinTable> getJoinTables() {
		return joinTables;
	}

	public void setJoinTables(List<FtlJoinTable> joinTables) {
		this.joinTables = joinTables;
	}

	public String getTableKeyWordOid() {
		return tableKeyWordOid;
	}

	public void setTableKeyWordOid(String tableKeyWordOid) {
		this.tableKeyWordOid = tableKeyWordOid;
	}

	public String getTableGroupOid() {
		return tableGroupOid;
	}

	public void setTableGroupOid(String tableGroupOid) {
		this.tableGroupOid = tableGroupOid;
	}

	/**
	 * @return the refCols
	 */
	public List<DBColumn> getRefCols() {
		return refCols;
	}

	/**
	 * @param refCols the refCols to set
	 */
	public void setRefCols(List<DBColumn> refCols) {
		this.refCols = refCols;
	}


	public String getViewSqlQuery() {
		return viewSqlQuery;
	}


	public void setViewSqlQuery(String viewSqlQuery) {
		this.viewSqlQuery = viewSqlQuery;
	}


	public Map<String, JoinTableSql> getJoinTableSql() {
		return joinTableSql;
	}


	public void setJoinTableSql(Map<String, JoinTableSql> joinTableSql) {
		this.joinTableSql = joinTableSql;
	}


	public String getTablePrimaryKeyRefOid() {
		return tablePrimaryKeyRefOid;
	}


	public void setTablePrimaryKeyRefOid(String tablePrimaryKeyRefOid) {
		this.tablePrimaryKeyRefOid = tablePrimaryKeyRefOid;
	}


	public boolean isHasCompareColumn() {
		return hasCompareColumn;
	}


	public void setHasCompareColumn(boolean hasCompareColumn) {
		this.hasCompareColumn = hasCompareColumn;
	}


	public boolean isHasExtendColumn() {
		return hasExtendColumn;
	}


	public void setHasExtendColumn(boolean hasExtendColumn) {
		this.hasExtendColumn = hasExtendColumn;
	}


	public String getViewSqlQueryDetail() {
		return viewSqlQueryDetail;
	}


	public void setViewSqlQueryDetail(String viewSqlQueryDetail) {
		this.viewSqlQueryDetail = viewSqlQueryDetail;
	}


	public Boolean getIsView() {
		return isView;
	}


	public void setIsView(Boolean isView) {
		this.isView = isView;
	}
	
	
}
