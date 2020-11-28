<#include "../function.ftl">
<#assign colcop>${getColCompares(cols)}</#assign>
<#assign colOut=0 />
package ${packname}.entity.updateinsqlenum;

import org.apache.commons.lang3.StringUtils;
/**
 * 枚举 ：${name} 可以在sql语句中自己预算更新的值<br/>
 * @author hanbing
 */
public enum ${tab}UpdateInSqlCols{
	
	<#list cols as col>
	<#if ((!col.primaryKey && col.inTableColumn) || (col.primaryKey && col.inSelfTable))><#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
	<#if colInteger=='true' || col.type?upper_case?contains("DATE")><#assign colCode><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		<#assign colOut=colOut+1 />
		/**
		 * ${col.name!}
		 */
		EV_${colCode?upper_case}("${colCode}")<#if (colOut=colcop?number)>;<#else>,</#if>
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

	private ${tab}UpdateInSqlCols(String eVal){
		this.enumVal = eVal;
	}
	
	/**
	 * 判断值，是否在枚举范围值内
	 * @param val	需要判断的值
	 * @return
	 */
	public static boolean checkIsEnumValue(String val){
		for(${tab}UpdateInSqlCols ${tab?uncap_first}UpdateInSqlCols:${tab}UpdateInSqlCols.values()){
			if(StringUtils.equals(${tab?uncap_first}UpdateInSqlCols.getEnumVal(), val)){
				return true;
			}
		}
		return false;
	}
}
