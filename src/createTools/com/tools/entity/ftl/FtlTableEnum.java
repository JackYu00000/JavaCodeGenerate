package com.tools.entity.ftl;

import com.tools.DBPdmUtils;
import com.tools.entity.DBColumn;


public class FtlTableEnum {
	String key;
	String tableName;
	DBColumn col;
	String packname="";
	
	public String getKey(){
		return DBPdmUtils.getKey(key);
	}
	public void setKey(String key) {
		this.key = key;
	}
	public DBColumn getCol() {
		return col;
	}
	public void setCol(DBColumn col) {
		this.col = col;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPackname() {
		return packname;
	}
	public void setPackname(String packname) {
		this.packname = packname;
	}

	
}
