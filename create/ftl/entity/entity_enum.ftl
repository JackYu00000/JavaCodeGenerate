<#include "../function.ftl">
package ${packname}.entity.entityenum;
<#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
<#if colInteger=='true'><#else>import org.apache.commons.lang3.StringUtils;</#if>

import java.util.HashMap;
import java.util.Map;

<#assign tcomment="${col.comment!?replace('.*RADIO_','','r')}" /><#assign tcomment="${tcomment!?replace('.*CHECKBOX_','','r')}" /><#assign tcomment="${tcomment!?replace('.*OPTION_','','r')}" />
/**
 * 枚举 ：${tableName} ${col.name}<br/>
 * 值：${tcomment}
 * @author hanbing
 *
 */
public enum ${key}{
	
    <#list tcomment?split(",") as s><#assign enumSize=tcomment?split(",")?size-1 />
    /**
	 * 值：${s?split(":")?first} <br/>
	 * 显示：${s?split(":")?last}
	 */
	EV_${s?split(":")?first?replace("-","0")?trim}(<#if colInteger=='true'>${s?split(":")?first}<#else>"${s?split(":")?first}"</#if>,"${s?split(":")?last}")<#if (s_index==enumSize)>;<#else>,</#if>
	</#list>
	
	/**
	 * 数值
	 */
	public <@getColJavaType col/> enumVal;
	/**
	 * 显示内容
	 */
	public String enumShow;
	/**
	 * 获取数值
	 * @return
	 */
	public <@getColJavaType col/> getEnumVal() {
		return enumVal;
	}
	/**
	 * 设定数值
	 * @param enumVal
	 */
	public void setEnumVal(<@getColJavaType col/> enumVal) {
		this.enumVal = enumVal;
	}
	/**
	 * 获取显示内容
	 * @return
	 */
	public String getEnumShow() {
		return enumShow;
	}
	/**
	 * 设定显示内容
	 * @param enumShow
	 */
	public void setEnumShow(String enumShow) {
		this.enumShow = enumShow;
	}
	
	private ${key}(<@getColJavaType col/> eVal,String eShow){
		this.enumVal = eVal;
		this.enumShow = eShow;
	}
	
	/**
	 * 判断值，是否在枚举范围值内
	 * @param val	需要判断的值
	 * @return
	 */
	public static boolean checkIsEnumValue(<@getColJavaType col/> val){
		for(${key} ${key?uncap_first}:${key}.values()){
			if(<#if colInteger=='true'>${key?uncap_first}.getEnumVal().intValue() == val.intValue()<#else>StringUtils.equals(${key?uncap_first}.getEnumVal(), val)</#if>){
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据传入的值，返回对应的显示文本
	 * @param val	传入的值
	 * @return
	 */
	public static String getEnumShowByEnumVal(<@getColJavaType col/> val){
		for(${key} ${key?uncap_first}:${key}.values()){
			if(<#if colInteger=='true'>${key?uncap_first}.getEnumVal().intValue() == val.intValue()<#else>StringUtils.equals(${key?uncap_first}.getEnumVal(), val)</#if>){
				return ${key?uncap_first}.getEnumShow();
			}
		}
		return null;
	}

	/**
	 * 把枚举转换成 Map 返回
	 * @return
	 */
	public static Map toMap(){
		Map enumMap = new HashMap();
		for(${key} ${key?uncap_first}:${key}.values()){
			enumMap.put(${key?uncap_first}.enumVal,${key?uncap_first}.enumShow);
		}
		return enumMap;
	}
}
