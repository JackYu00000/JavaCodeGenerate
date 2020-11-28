package com.tools.web.postman;

public class RequestDataItem {
	public String key;
	public String value;
	public String type;
	
	public RequestDataItem(String key,String value,String type){
		this.key = key;
		this.value = value;
		this.type = type;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
