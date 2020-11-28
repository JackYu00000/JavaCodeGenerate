<#include "../function.ftl">
package ${packname}.entity.refenum;

import org.apache.commons.lang3.StringUtils;
/**
 * 枚举 ：${name}引用数据<br/>
<#list refCols as col>
		<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		<#assign colJavaType>${col.type} ${col.typeExpend}</#assign>
 * 		EV_${col.type?upper_case} <b>${col.typeExpend}</b> ${col.name}  <br/>
	</#list> 
 * @author hanbing
 *
 */
public enum ${tab}Ref{
	
	<#list refCols as col>
		<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		<#assign colJavaType>${col.type} ${col.typeExpend}</#assign>
    /**
	 * ${col.type} <b>${col.typeExpend}</b> <br/>
	 * ${col.name}
	 */
	 EV_${col.type?upper_case}("${col.type?uncap_first}","${col.name}")<#if (col_index==(refCols?size-1))>;<#else>,</#if>
	</#list>
	
	/**
	 * 数值
	 */
	public String enumVal;
	/**
	 * 显示内容
	 */
	public String enumShow;
	/**
	 * 获取数值
	 * @return
	 */
	public String getEnumVal() {
		return enumVal;
	}
	/**
	 * 设定数值
	 * @param eVal
	 */
	public void setEnumVal(String enumVal) {
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
	 * @param eShow
	 */
	public void setEnumShow(String enumShow) {
		this.enumShow = enumShow;
	}
	
	private ${tab}Ref(String eVal,String eShow){
		this.enumVal = eVal;
		this.enumShow = eShow;
	}
	
	/**
	 * 判断值，是否在枚举范围值内
	 * @param val	需要判断的值
	 * @return
	 */
	public static boolean checkIsEnumValue(String val){
		for(${tab}Ref ${tab?uncap_first}Ref:${tab}Ref.values()){
			if(StringUtils.equals(${tab?uncap_first}Ref.getEnumVal(), val)){
				return true;
			}
		}
		return false;
	}
}
