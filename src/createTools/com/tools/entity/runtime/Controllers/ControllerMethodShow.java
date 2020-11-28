package com.tools.entity.runtime.Controllers;

import java.util.UUID;

import com.tools.Start;
import com.tools.entity.CreateUtils;
import com.tools.entity.DBTable;
import com.tools.entity.postman.PManRequest;

public class ControllerMethodShow extends ControllerMethodBase {

	public ControllerMethodShow(DBTable table){
		this.table = table;
	}
	
	@Override
	public PManRequest genInterfaceJsonPMRequest(String collectionId,String jsonString, String lastFolderId) {
		this.pmanRequest = new PManRequest(UUID.randomUUID().toString()
				,table.getName() + " -- 详情"
				,table.getName() + " -- 详情"
				,"raw"
				,"html"
				,"Content-Type: application/json"
				,"POST"
				,"normal"
				,collectionId
				,jsonString
				,Start.pmanRequestHost + "/apijson/" + CreateUtils.getTab(table.getCode()).toLowerCase() + "/show"
				,lastFolderId);
		return this.pmanRequest;
	}

	@Override
	public PManRequest genInterfaceMobiPMRequest(String collectionId,
			String jsonString, String lastFolderId) {
		this.pmanRequest = new PManRequest(UUID.randomUUID().toString()
				,table.getName() + " -- 详情"
				,table.getName() + " -- 详情"
				,"raw"
				,"html"
				,"author_key: {{author_key}}\nrequest-type: ios\nContent-Type: application/json\n"
				,"POST"
				,"normal"
				,collectionId
				,jsonString
				,Start.pmanRequestHost + "/apimobi/" + CreateUtils.getTab(table.getCode()).toLowerCase() + "/{主键值}"
				,lastFolderId);
		return this.pmanRequest;
	}
	
	@Override
	public String genInterfaceJsonLocalStorage(String jsonString) {
		StringBuffer jsContent = new StringBuffer();
		jsContent.append("localStorage.setItem(\"");
		jsContent.append("/apijson/");
		jsContent.append(CreateUtils.getTab(table.getCode()).toLowerCase());
		jsContent.append("/show");
		jsContent.append("\", JSON.stringify(");
		jsContent.append(jsonString);
		jsContent.append("));\n");
		return jsContent.toString();
	}

	@Override
	public String genInterfaceMobiLocalStorage(String jsonString) {
		StringBuffer jsContent = new StringBuffer();
		jsContent.append("localStorage.setItem(\"");
		jsContent.append("/apimobi/");
		jsContent.append(CreateUtils.getTab(table.getCode()).toLowerCase());
		jsContent.append("\",\"\"");
		jsContent.append(");\n");
		return jsContent.toString();
	}

}
