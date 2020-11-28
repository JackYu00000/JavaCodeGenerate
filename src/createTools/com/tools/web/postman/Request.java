package com.tools.web.postman;

import java.sql.Timestamp;
import java.util.List;

import com.google.gson.annotations.SerializedName;
public class Request extends RequestBase{
	public List<String> order;
	public List<RequestFolderItem> folders;
	public long timestamp;
	public String remoteLink;
	
	@SerializedName("public")
	public boolean publicStr;
	
	public boolean hasRequests = true;
	public List<RequestItem> requests;
	
	public Request(String id,String name,String description){
		this.id = id;
		this.name = name;
		this.description = description;
		this.timestamp = new Timestamp(System.currentTimeMillis()).getTime();
	}
	
	public List<String> getOrder() {
		return order;
	}
	public void setOrder(List<String> order) {
		this.order = order;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getRemoteLink() {
		return remoteLink;
	}
	public void setRemoteLink(String remoteLink) {
		this.remoteLink = remoteLink;
	}
	
	public boolean isPublicStr() {
		return publicStr;
	}

	public void setPublicStr(boolean publicStr) {
		this.publicStr = publicStr;
	}

	public boolean isHasRequests() {
		return hasRequests;
	}
	public void setHasRequests(boolean hasRequests) {
		this.hasRequests = hasRequests;
	}

	public List<RequestFolderItem> getFolders() {
		return folders;
	}

	public void setFolders(List<RequestFolderItem> folders) {
		this.folders = folders;
	}

	public List<RequestItem> getRequests() {
		return requests;
	}

	public void setRequests(List<RequestItem> requests) {
		this.requests = requests;
	}
	
}
