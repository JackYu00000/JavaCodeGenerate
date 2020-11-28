package com.tools.entity.pdm;

import java.util.ArrayList;
import java.util.List;

/**
 * 从pdm中解析出表格分组 group分组信息
 * @author hanbing
 *
 */
public class DBTableGroupSymbol {
	/**
	 * pdm 中表格分组，oid
	 */
	public String id;
	/**
	 *  pdm 中表格分组，组中表格oid列表
	 */
	public List<DBTableTableSymbol> tSymbol = new ArrayList<DBTableTableSymbol>();
	
	public void addTs(DBTableTableSymbol dbtts){
		tSymbol.add(dbtts);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<DBTableTableSymbol> gettSymbol() {
		return tSymbol;
	}
	public void settSymbol(List<DBTableTableSymbol> tSymbol) {
		this.tSymbol = tSymbol;
	}
	
}
