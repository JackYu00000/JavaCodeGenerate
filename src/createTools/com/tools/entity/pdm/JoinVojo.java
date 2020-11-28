package com.tools.entity.pdm;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
/**
 * 临时计算扩展字段的接口
 * @author han-win10pc
 *
 */
public class JoinVojo {
	/**
	 * 扩展查询列
	 */
	String joinCol;
	/**
	 * 扩展展示列
	 */
	List<String> joinShow = new ArrayList<String>();
	
	public String getJoinCol() {
		return joinCol;
	}
	public void setJoinCol(String joinCol) {
		this.joinCol = joinCol;
	}
	public List<String> getJoinShow() {
		return joinShow;
	}
	public void setJoinShow(List<String> joinShow) {
		this.joinShow = joinShow;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
