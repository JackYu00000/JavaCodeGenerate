package com.tools.web.postman;

import java.util.List;

public class RequestFolderItem  extends RequestBase{

	public boolean write = true;
	public List<String> order;
	public String collection_name = super.name;
	public String collection_owner = super.owner;
	public String collection_id = super.id;
	public String collection = super.id;
	
	public RequestFolderItem(String id,String name,String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public boolean isWrite() {
		return write;
	}
	public void setWrite(boolean write) {
		this.write = write;
	}
	public List<String> getOrder() {
		return order;
	}
	public void setOrder(List<String> order) {
		this.order = order;
	}
	public String getCollection_name() {
		return collection_name;
	}
	public void setCollection_name(String collection_name) {
		this.collection_name = collection_name;
	}
	public String getCollection_owner() {
		return collection_owner;
	}
	public void setCollection_owner(String collection_owner) {
		this.collection_owner = collection_owner;
	}
	public String getCollection_id() {
		return collection_id;
	}
	public void setCollection_id(String collection_id) {
		this.collection_id = collection_id;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	
	
}
