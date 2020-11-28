package com.tools.entity.postman;

import java.util.List;

/**
 * Created by Administrator on 2015/11/27.
 */
public class PManRequest extends PManBase {

	public PManRequest(){}
	
	/**
	 * @param id		id
	 * @param name		名称
	 * @param description	描述
	 * @param dataMode	raw、form、binary、x-form
	 * @param descriptionFormat
	 * @param headers
	 * @param method POST、GET
	 * @param currentHelper
	 * @param collectionId
	 * @param rawModeData
	 * @param url
	 */
	public PManRequest(String id,String name,String description,String dataMode
			,String descriptionFormat,String headers,String method,String currentHelper
			,String collectionId,String rawModeData,String url,String folder){
		this.id = id;
		this.name = name;
		this.description = description;
		this.dataMode = dataMode;
		this.descriptionFormat = descriptionFormat;
		this.headers = headers;
		this.method = method;
		this.currentHelper = currentHelper;
		this.collectionId = collectionId;
		this.rawModeData = rawModeData;
		this.url = url;
		this.folder = folder;
	}
	
    String folder;

    String dataMode;

    String rawModeData;

    String descriptionFormat;

    String headers;

    String method;

    String pathVariables;

    String url;

    String preRequestScript;

    String tests;

    String currentHelper;

    String helperAttributes;

    String collectionId;

    /**
     * form 提交时的参数列表<br/>
     *
     */
    List<PManRequestFormEntity> data;

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getDataMode() {
		return dataMode;
	}

	public void setDataMode(String dataMode) {
		this.dataMode = dataMode;
	}

	public String getRawModeData() {
		return rawModeData;
	}

	public void setRawModeData(String rawModeData) {
		this.rawModeData = rawModeData;
	}

	public String getDescriptionFormat() {
		return descriptionFormat;
	}

	public void setDescriptionFormat(String descriptionFormat) {
		this.descriptionFormat = descriptionFormat;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPathVariables() {
		return pathVariables;
	}

	public void setPathVariables(String pathVariables) {
		this.pathVariables = pathVariables;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPreRequestScript() {
		return preRequestScript;
	}

	public void setPreRequestScript(String preRequestScript) {
		this.preRequestScript = preRequestScript;
	}

	public String getTests() {
		return tests;
	}

	public void setTests(String tests) {
		this.tests = tests;
	}

	public String getCurrentHelper() {
		return currentHelper;
	}

	public void setCurrentHelper(String currentHelper) {
		this.currentHelper = currentHelper;
	}

	public String getHelperAttributes() {
		return helperAttributes;
	}

	public void setHelperAttributes(String helperAttributes) {
		this.helperAttributes = helperAttributes;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public List<PManRequestFormEntity> getData() {
		return data;
	}

	public void setData(List<PManRequestFormEntity> data) {
		this.data = data;
	}

    
}
