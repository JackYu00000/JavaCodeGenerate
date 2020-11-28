package com.tools.entity.postman;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hanbing on 2015/11/27.
 */
public class PManJson extends PManBase {
    /**
     * 是否公开 <br/>
     * json 中对应为 public
     */
	@SerializedName("public")
    static Boolean isPublic = false;

    List<String> order;

    Long timestamp;

    String remoteLink;

    /**
     * 文件夹
     */
    List<PManFolder> folders;
    /**
     * 提交参数
     */
    List<PManRequest> requests;
    
	public Boolean getIsPublic() {
		return isPublic;
	}
	public List<String> getOrder() {
		return order;
	}
	public void setOrder(List<String> order) {
		this.order = order;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getRemoteLink() {
		return remoteLink;
	}
	public void setRemoteLink(String remoteLink) {
		this.remoteLink = remoteLink;
	}
	public List<PManFolder> getFolders() {
		return folders;
	}
	public void setFolders(List<PManFolder> folders) {
		this.folders = folders;
	}
	public List<PManRequest> getRequests() {
		return requests;
	}
	public void setRequests(List<PManRequest> requests) {
		this.requests = requests;
	}
    
    

}
