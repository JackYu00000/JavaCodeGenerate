package com.tools.entity.pdm;

public class DBViewColumn {
	/**
	 * 列所属表格唯一标示
	 */
	String viewOId;

	/**
	 * 唯一标识
	 */
	String oId;
	/**
	 * 名称
	 */
	String name;
	/**
	 * code
	 */
	String code;
	/**
	 * 类型
	 */
	String type;
	/**
	 * 注释
	 */
	String comment;
	/**
	 * 是否显示字段，用于判断是否是关联键
	 * 0:不显示的字段是关联字段
	 */
	String displayed = "1";
	
	public String getViewOId() {
		return viewOId;
	}
	public void setViewOId(String viewOId) {
		this.viewOId = viewOId;
	}
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDisplayed() {
		return displayed;
	}
	public void setDisplayed(String displayed) {
		this.displayed = displayed;
	}
	
	
}
