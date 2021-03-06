package com.tools.entity.runtime.Controllers;

import java.util.UUID;

import com.tools.Start;
import com.tools.entity.CreateUtils;
import com.tools.entity.DBTable;
import com.tools.entity.postman.PManRequest;

public class ControllerMethodSave extends ControllerMethodBase {

	public ControllerMethodSave(DBTable table){
		this.table = table;
	}
	
	@Override
	public PManRequest genInterfaceJsonPMRequest(String collectionId,String jsonString, String lastFolderId) {
		this.pmanRequest = new PManRequest(UUID.randomUUID().toString()
				,table.getName() + " -- 保存"
				,table.getName() + " -- 保存"
				,"raw"
				,"html"
				,"Content-Type: application/json"
				,"POST"
				,"normal"
				,collectionId
				,jsonString
				,Start.pmanRequestHost + "/apijson/" + CreateUtils.getTab(table.getCode()).toLowerCase() + "/save"
				,lastFolderId);
		return this.pmanRequest;
	}

	@Override
	public PManRequest genInterfaceMobiPMRequest(String collectionId,String jsonString, String lastFolderId) {
		this.pmanRequest = new PManRequest(UUID.randomUUID().toString()
				,table.getName() + " -- 保存"
				,table.getName() + " -- 保存"
				,"raw"
				,"html"
				,"author_key: {{author_key}}\nrequest-type: ios\nContent-Type: application/json\n"
				,"POST"
				,"normal"
				,collectionId
				,jsonString
				,Start.pmanRequestHost + "/apimobi/" + CreateUtils.getTab(table.getCode()).toLowerCase() + "/save"
				,lastFolderId);
		return this.pmanRequest;
	}

	@Override
	public String genInterfaceJsonLocalStorage(String jsonString) {
		StringBuffer jsContent = new StringBuffer();
		jsContent.append("localStorage.setItem(\"");
		jsContent.append("/apijson/");
		jsContent.append(CreateUtils.getTab(table.getCode()).toLowerCase());
		jsContent.append("/save");
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
		jsContent.append("/save");
		jsContent.append("\", JSON.stringify(");
		jsContent.append(jsonString);
		jsContent.append("));\n");
		return jsContent.toString();
	}

}
