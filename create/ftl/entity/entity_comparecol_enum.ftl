<#include "../function.ftl">
<#assign colcop>${getColCompares(cols)}</#assign>
<#assign colOut=0 />
package ${packname}.entity.comparecolsenum;

import org.apache.commons.lang3.StringUtils;
/**
 * 枚举 ：${name} 可以比较查询字段<br/>
 * @author hanbing
 */
public enum ${tab}CompareCols{
	
	<#list cols as col>
	<#if ((!col.primaryKey && col.inTableColumn) || (col.primaryKey && col.inSelfTable))><#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
	<#if colInteger=='true' || col.type?upper_case?contains("DATE")><#assign colCode><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		<#assign colOut=colOut+1 />
		/**
		 * ${col.name!}比较逻辑类型
		 */
		EV_${colCode?upper_case}BETWEENTYPE("${colCode}BetweenType"),
		/**
		 * ${col.name!}大于值
		 */
		EV_${colCode?upper_case}GREATERTHAN("${colCode}GreaterThan"),
		/**
		 * ${col.name!}小于值
		 */
		EV_${colCode?upper_case}LESSTHAN("${colCode}LessThan"),
		/**
		 * ${col.name!}多值
		 */
		EV_${colCode?upper_case}MULTS("${colCode}Mults")<#if (colOut=colcop?number)>;<#else>,</#if>
	</#if></#if>
</#list>
	
	/**
	 * 数值
	 */
	public String enumVal;
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

	private ${tab}CompareCols(String eVal){
		this.enumVal = eVal;
	}
	
	/**
	 * 判断值，是否在枚举范围值内
	 * @param val	需要判断的值
	 * @return
	 */
	public static boolean checkIsEnumValue(String val){
		for(${tab}CompareCols ${tab?uncap_first}CompareCols:${tab}CompareCols.values()){
			if(StringUtils.equals(${tab?uncap_first}CompareCols.getEnumVal(), val)){
				return true;
			}
		}
		return false;
	}
}
