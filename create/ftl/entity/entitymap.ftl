<#include "../function.ftl">
package ${packname}.entity;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.LinkedHashMap;

<#list cols as col>
		<#if (!col.primaryKey && col.inSelfTable)>
		<#if (col.type?upper_case?contains('VARCHAR'))>
import org.apache.commons.lang3.StringUtils;
<#break>
		</#if>
		</#if></#list>
<#assign hasdate>${columnHasDateType(cols)?string}</#assign>
<#assign hasdatetime>${columnHasDateTimeType(cols)?string}</#assign>
<#assign hastime>${columnHasTimeType(cols)?string}</#assign>

import com.fasterxml.jackson.annotation.JsonIgnore;
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

<#list cols as col>
<#if (!col.primaryKey && col.inSelfTable)>
<#if (col.comment!?index_of("RADIO")>-1 || col.comment!?index_of("CHECKBOX")>-1)>
import ${packname}.entity.entityenum.${tab}<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>;
</#if>
</#if></#list>
/**
   数据对象：${name}
*/
public class ${tab} extends EntityQualification implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@SuppressWarnings("serial")
	public static final Map<String,String> columMap = new LinkedHashMap<String,String>(){{ 
		<#list cols as col><#if (col.inTableColumn)>put("<@genAttributeByCol colName_ABC='${col.code}'/>","${col.code?upper_case}");</#if></#list>
	}};
	
	@SuppressWarnings("unused")
	private static final String NAME_OF_TABLE_IN_DATABASE = "${name}";
	@SuppressWarnings("unused")
	private static final String CODE_OF_TABLE_IN_DATABASE = "${code?upper_case}";
	
	/**
	 * 初始化空<i>${name}</i><br>
	 * @return ${tab}
	 */
	public ${tab}(){}
	
	/**
	 * 初始化设定好主键值的<i>${name}</i><br>
	 * @return ${tab}
	 */
	public ${tab}(<@compress><@getTableKeyParamFirst cols/></@compress>){
	<#list cols as col><#if (col.primaryKey && col.inSelfTable && col_index=0)>
		<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		this.${colAttr} = ${colAttr};
	</#if></#list>	
	}
	
<#assign colShow>${tableHasMultKeys(cols)?string}</#assign>
<#if (colShow=="true")>
	/**
	 * 初始化设定好主键值的<i>${name}</i><br>
	 * @return ${tab}
	 */
	public ${tab}(<@compress><@getTableKeyParam cols/></@compress>){
	<#list cols as col><#if (col.primaryKey && col.inSelfTable)>
		<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		this.${colAttr} = ${colAttr};
	</#if></#list>	
	}
</#if>
	public ${tab}(${tab} ${tab?uncap_first}){
		if(null==${tab?uncap_first}){
			return;
		}
	<#list cols as col><#if col.inSelfTable>
<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		<@compress single_line=true>this.set${colAttr?cap_first}(${tab?uncap_first}.get${colAttr?cap_first}());</@compress> 
	</#if>
</#list>
	}
	
<#if (refCols?size>0)>	
	/**
<#list refCols as col>
		<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		<#assign colJavaType><@getColJavaType col/></#assign>
	 * 	${col.name} ${colJavaType}
</#list>
	 */
	public void removeRefData(String refKey){
		if(null!=this.refData){
			this.refData.remove(refKey);
		}
	}
	
	/**
<#list refCols as col>
		<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		<#assign colJavaType><@getColJavaType col/></#assign>
	 * 	${col.name} ${colJavaType}
</#list>
	 */
	public void addRefData(String refKey,Object refObject){
		if(null==this.refData){
			this.refData = new HashMap<String,Object>();
		}
		this.refData.put(refKey,refObject);
	}
</#if>
	public Map<String,Object> checkLength(${tab} ${tab?uncap_first}) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", true);
<#list cols as col><#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		<#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
<#if (!col.primaryKey && col.inSelfTable)>
<#if (col.mandatory=='1' && !col.defaultValue?exists)>
		<#if colInteger=="true">
		if(null == ${tab?uncap_first}.get<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>()){
			resultMap.put("result", false);
			resultMap.put("msg", "${col.name}[${colAttr}]"+"：需要设定初始值。");
		}
		<#elseif (col.type?upper_case?contains('VARCHAR'))>
		if(null == ${tab?uncap_first}.get<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>()){
			resultMap.put("result", false);
			resultMap.put("msg", "${col.name}[${colAttr}]"+"：需要设定初始值。");
		}
		</#if>
</#if>
<#if (col.type?upper_case?contains('VARCHAR'))>
		if(StringUtils.isNotBlank(${tab?uncap_first}.get<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>()) && StringUtils.length(${tab?uncap_first}.get<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>())> ${col.type?replace('\\D','','r')}){
			resultMap.put("result", false);
			resultMap.put("msg", "${col.name}[${colAttr}]"+"：值太长。");
		}
</#if>
<#if (col.comment!?index_of("RADIO")>-1 || col.comment!?index_of("CHECKBOX")>-1)>
<#if colInteger=="true">
		if(null != ${tab?uncap_first}.get<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>() && !${tab}<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>.checkIsEnumValue(${tab?uncap_first}.get<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>())){
<#else>
		if(StringUtils.isNotBlank(${tab?uncap_first}.get<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>()) && !${tab}<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>.checkIsEnumValue(${tab?uncap_first}.get<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>())){
</#if>
			resultMap.put("result", false);
			resultMap.put("msg", "状态[${colAttr}]"+"：值不在正确范围内。");
		}
</#if>
</#if></#list>
		return resultMap;
	}

	<#assign needWriteDefalue = false>
	<#list cols as col><#if (!col.primaryKey && col.mandatory=='1' && col.inSelfTable && col.defaultValue?exists)>
		<#assign needWriteDefalue = true><#break> 
	</#if></#list>
	<#if needWriteDefalue> 
	public void writeDefaultForNotNull(${tab} ${tab?uncap_first}) {
		<#list cols as col><#if (!col.primaryKey && col.mandatory=='1' && col.inSelfTable && col.defaultValue?exists)>
//		${tab?uncap_first}.set<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>(null==${tab?uncap_first}.get<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>() ? <#if col.type?upper_case?contains("DECIMAL")><#if col.defaultValue?exists>Double.valueOf(${col.defaultValue})<#else>0.0</#if><#elseif col.type?upper_case?contains("INT")><#if col.defaultValue?exists>Integer.valueOf(${col.defaultValue})<#else>0</#if><#else>"${col.defaultValue}"</#if> : ${tab?uncap_first}.get<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>()); 
		</#if></#list>
	}
	</#if> 

<#list cols as col><#if col.inSelfTable>
<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
<#assign colJavaType><@getColJavaType col/></#assign>
 	/**
	 * <#if col.inSelfTable><b>${col.name}</b><#else>${col.name}</#if> <i>${col.type}</i><#if col.mandatory=='1'> <b>不能为空<#if col.defaultValue?exists>，默认值为：${col.defaultValue}<#elseif col.type?upper_case?contains("DECIMAL")>，默认值为：0.0<#elseif col.type?upper_case?contains("INT")>，默认值为：0</#if></b></#if><br>
	 * ${col.comment!}<br><#if (col.comment!?index_of("RADIO")>-1 || col.comment!?index_of("CHECKBOX")>-1)>
	 * 枚举字段，<i>${tab}<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/></i></#if><#if (col.joinTables!?size>0)>
	 * 表数据主键</#if>
	 */
	<@writeJacksonSerializer col/>
	protected <@compress single_line=true>${colJavaType} ${colAttr};</@compress> 
	
	</#if>
</#list>
	
<#list cols as col><#if col.inSelfTable>
<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
<#assign colJavaType><@getColJavaType col/></#assign>
	/**
	 * <#if col.inSelfTable><b>获取${col.name}</b><#else>获取${col.name}</#if>
	 * @return <@compress single_line=true>${colJavaType}</@compress>
	 */
	public <@compress single_line=true>${colJavaType}</@compress> get<@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/>() {
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

<#if (refCols?size>0)>
 	/**
     * 设定
	 */
	protected Map<String,Object> refData; 

	/**
	 * 获取 Map<String,Object> refData
	 * @return Map<String,Object> refData
	 */
	public Map<String,Object> getRefData() {
		return refData;
	}
	/**
	 * 设定 Map<String,Object> refData
	 * @param ${colAttr}<br/>
	 */
	public void setRefData(Map<String,Object> refData) {
		this.refData = refData;
	}
</#if>
}
