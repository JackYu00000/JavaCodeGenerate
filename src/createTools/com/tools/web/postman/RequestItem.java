package com.tools.web.postman;

import java.util.List;

public class RequestItem extends RequestBase {
	public String dataMode;
	/**
	 * form 方式提交的数据
	 */
	public List<RequestDataItem> data;
	/**
	 * json 格式提交的数据
	 */
	public String rawModeData;
	public String descriptionFormat;
	public String headers;
	public String method;
	public String pathVariables;
	public String url;
	public String preRequestScript;
	public String tests;
	public long version;
	public String currentHelper;
	public String helperAttributes;
	public String lastUpdatedBy;
	public long lastRevision;
	public String history;
	public String folder;
	public String collectionId = super.id;
	
	public RequestItem(String id,String name,String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public String getDataMode() {
		return dataMode;
	}

	public void setDataMode(String dataMode) {
		this.dataMode = dataMode;
	}

	public List<RequestDataItem> getData() {
		return data;
	}

	public void setData(List<RequestDataItem> data) {
		this.data = data;
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

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
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

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public long getLastRevision() {
		return lastRevision;
	}

	public void setLastRevision(long lastRevision) {
		this.lastRevision = lastRevision;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}
	
}
