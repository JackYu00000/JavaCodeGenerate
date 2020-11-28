<#include "../function.ftl">
package ${packname}.entity.extend;

<#assign hasdate>${columnHasDateType(cols)?string}</#assign>
<#assign hasdatetime>${columnHasDateTimeType(cols)?string}</#assign>
<#assign hastime>${columnHasTimeType(cols)?string}</#assign>
<#if (hasdate=="true" || hasdatetime=="true")>
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
</#if>
<#if (hasdate=="true")>
import ${packname}.web.JacksonDateFormater;
</#if>
<#if (hasdatetime=="true")>
import ${packname}.web.JacksonDateFormater;
</#if>
<#if (hastime=="true")>
import ${packname}.web.JacksonTimeFormater;
</#if>
import ${packname}.entity.${tab};

<#list cols as col>
<#assign colischnval>${columnIsChnvalType(col)?string}</#assign>
<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
<#if (colischnval=="true")>
import ${packname}.entity.entityenum.${tab}${colAttr?replace('Chnval','')?cap_first};
</#if>
</#list>
/**
   数据对象：${name}扩展数据
*/
public class ${tab}Extend extends ${tab} {

	private static final long serialVersionUID = 1L;
	
	public ${tab}Extend(){}
	
	public ${tab}Extend(<@compress><@getTableKeyParamFirst cols/></@compress>){
	<#list cols as col><#if (col.primaryKey && col.inSelfTable && col_index=0)>
		<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		this.${colAttr} = ${colAttr};
	</#if></#list>	
	}
	
	public ${tab}Extend(${tab} ${tab?uncap_first}){
		if(null==${tab?uncap_first}){
			return;
		}
	<#list cols as col><#if col.inSelfTable>
<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		<@compress single_line=true>this.set${colAttr?cap_first}(${tab?uncap_first}.get${colAttr?cap_first}());</@compress> 
	</#if>
</#list>
	}
	
	public ${tab}Extend(${tab}Extend ${tab?uncap_first}Extend){
		if(null==${tab?uncap_first}Extend){
			return;
		}
	<#list cols as col>
<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		<@compress single_line=true>this.set${colAttr?cap_first}(${tab?uncap_first}Extend.get${colAttr?cap_first}());</@compress> 
</#list>
	}
<#list cols as col><#if (!col.primaryKey && !col.inSelfTable)>
<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
<#assign colJavaType><@getColJavaType col/></#assign>
 	/**
	 * ${col.name} <i>${col.type}</i><#if col.mandatory=='1'> <b>不能为空<#if col.defaultValue?exists>，默认值为：${col.defaultValue}<#elseif col.type?upper_case?contains("DECIMAL")>，默认值为：0.0<#elseif col.type?upper_case?contains("INT")>，默认值为：0</#if></b></#if><br>
	 * ${col.comment!}<br><#if (col.comment!?index_of("RADIO")>-1 || col.comment!?index_of("CHECKBOX")>-1)>
	 * 枚举字段，<i>${tab}<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/></i></#if><#if (col.joinTables!?size>0)>
	 * 表数据主键</#if>
	 */
	<@writeJacksonSerializer col/>
	protected <@compress single_line=true>${colJavaType} ${colAttr};</@compress> 
	
	</#if>
</#list>
<#list cols as col><#if (!col.primaryKey && !col.inSelfTable)>
<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
<#assign colJavaType><@getColJavaType col/></#assign>
	/**
	 * <#if col.inSelfTable><b>获取${col.name}</b><#else>获取${col.name}</#if>
	 * @return <@compress single_line=true>${colJavaType}</@compress>
	 */
	public <@compress single_line=true>${colJavaType}</@compress> get<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>() {
<#if col.code?ends_with('_CHNVAL')>
		if(null!=this.${colAttr?replace('Chnval','')}){
			${colAttr} = ${tab}${colAttr?replace('Chnval','')?cap_first}.getEnumShowByEnumVal(this.${colAttr?replace('Chnval','')});
		}
</#if>
		return ${colAttr};
	}
	/**
	 * <#if col.inSelfTable><b>设定${col.name}</b><#else>设定${col.name}</#if>
	 * @param ${colAttr}<br/><#if (col.comment!?index_of("RADIO")>-1 || col.comment!?index_of("CHECKBOX")>-1)>
	 * 枚举字段，<i>${tab}<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/></i></#if>
	 */
	public void set<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>(<@compress single_line=true>${colJavaType}</@compress> ${colAttr}) {
		this.${colAttr} = ${colAttr};
	}
</#if></#list>
}
