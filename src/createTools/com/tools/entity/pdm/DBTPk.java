package com.tools.entity.pdm;

import java.util.ArrayList;
import java.util.List;

public class DBTPk {
	/**
	 * 父层 o:Key 的oid
	 */
	String refId;		

	List<DBTPkeyId> keyIds = new ArrayList<DBTPkeyId>();
	
	public void addKeyOid(DBTPkeyId keyId){
		keyIds.add(keyId);
	}
	
	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public List<DBTPkeyId> getKeyIds() {
		return keyIds;
	}

	public void setKeyIds(List<DBTPkeyId> keyIds) {
		this.keyIds = keyIds;
	}
	
	
}
