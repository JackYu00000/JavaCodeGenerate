<#include "../function.ftl">
package ${packname}.entity.vojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ${packname}.entityentity.${tab};
<#if hasExtendColumn>
import ${packname}.entityextend.${tab}Extend;
</#if>

@JsonIgnoreProperties({<@getJsonIgnoreColumn cols/>})
<#if hasExtendColumn>
public class ${tab}Vojo extends ${tab}Extend {
<#else>
public class ${tab}Vojo extends ${tab} {
</#if>
	private static final long serialVersionUID = 1L;
	
	public ${tab}Vojo(){}

	public ${tab}Vojo(${tab} ${tab?uncap_first}){
		if(null==${tab?uncap_first}){
			return;
		}
	<#list cols as col><#if col.inSelfTable>
<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		<@compress single_line=true>this.set${colAttr?cap_first}(${tab?uncap_first}.get${colAttr?cap_first}());</@compress> 
	</#if>
</#list>
	}
	
<#if hasExtendColumn>	
	public ${tab}Vojo(${tab}Extend ${tab?uncap_first}Extend){
		if(null==${tab?uncap_first}Extend){
			return;
		}
	<#list cols as col>
<#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
		<@compress single_line=true>this.set${colAttr?cap_first}(${tab?uncap_first}Extend.get${colAttr?cap_first}());</@compress> 
</#list>
	}
</#if>
}
