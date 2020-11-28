package com.tools.entity.runtime.Controllers;

import com.tools.entity.DBTable;
import com.tools.entity.postman.PManRequest;

public abstract class ControllerMethodBase {
	
	DBTable table;
	
	PManRequest pmanRequest;
	
	/**
	 * 生成 postman json使用的request 对象
	 * @param table	表格
	 * @param packageName	包名
	 * @param collectionId	对应的文件id
	 * @param jsonString	JSON 字符串
	 * @param pmanRequestHost	请求地址的域名
	 * @param lastFolderId	所属文件夹的id
	 * @return
	 */
	public abstract PManRequest genInterfaceJsonPMRequest(String collectionId,String jsonString,String lastFolderId);
	
	/**
	 * 生成 postman mobi使用的request 对象
	 * @param table	表格
	 * @param packageName	包名
	 * @param collectionId	对应的文件id
	 * @param jsonString	JSON 字符串
	 * @param pmanRequestHost	请求地址的域名
	 * @param lastFolderId	所属文件夹的id
	 * @return
	 */
	public abstract PManRequest genInterfaceMobiPMRequest(String collectionId,String jsonString,String lastFolderId);
	
	/**
	 * 生成 jsp页面中 localStorage 保存的json格式信息 字符串
	 * @param jsonString	需要输出的JSON格式
	 * @return
	 */
	public abstract String genInterfaceJsonLocalStorage(String jsonString);

	public abstract String genInterfaceMobiLocalStorage(String jsonString);
	
}
