<#include "../function.ftl">package ${packname}.controller.interfacewithrest;import org.apache.commons.beanutils.PropertyUtils;import java.lang.reflect.InvocationTargetException;import java.util.LinkedHashMap;import java.util.Map;<#-- import java.util.Date;有创建时间、更新时间项目使用，其他项目可删除 --><#list cols as col><#if (col.primaryKey && col.inSelfTable)><#if (col.type?upper_case?starts_with("VARCHAR"))>import org.apache.commons.lang3.StringUtils;import com.is.web.common.utils.Utils;</#if></#if></#list>import javax.annotation.Resource;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import jodd.bean.loader.RequestParamBeanLoader;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.RestController;import ${packname}.controller.BaseController;import ${packname}.entity.runtime.InterfaceJsonResponseJson;import ${packname}.entity.${tab};import ${packname}.services.${tab}Service;<#list joinTables as jt>import ${packname}.entity.<@genAttributeByForSetAndGet colName_ABFSAG='${jt.tableCode?replace("^T_","","r")}' />;import ${packname}.services.<@genAttributeByForSetAndGet colName_ABFSAG='${jt.tableCode?replace("^T_","","r")}' />Service;</#list>import ${packname}.web.Pagination;import ${packname}.web.annotation.Login;import ${packname}.web.LoginResultReturnType;import ${packname}.web.LoginRequestFromType;@RestController@RequestMapping("/apirest/${tab?lower_case}")public class ${tab}InterfaceRestController extends BaseController {		@Resource	private ${tab}Service ${tab?uncap_first}Svc;		<#list joinTables as jt>	@Resource	private <@genAttributeByForSetAndGet colName_ABFSAG='${jt.tableCode?replace("^T_","","r")}' />Service <@genAttributeByCol colName_ABC='${jt.tableCode?replace("^T_","","r")}' />Svc;	</#list>		RequestParamBeanLoader rpbl = new RequestParamBeanLoader();		@Login(value=LoginResultReturnType.page,reqFromType=LoginRequestFromType.restInterface)	@RequestMapping(value="/save")	public void save(HttpServletRequest request, HttpServletResponse response){		${tab}  ${tab?uncap_first}Upd = new ${tab}();		rpbl.load(${tab?uncap_first}Upd,request);		<#list cols as col><#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign><#if (col.primaryKey && col.inSelfTable)><#if colInteger=='true'>		if(<#list cols as col><#if col.primaryKey>${tab?uncap_first}Upd.get<@getTableKeyForSetGet cols/>()==null<#break></#if></#list>){			 <#-- ${tab?uncap_first}Upd.setCreateTime(new Date());有创建时间、更新时间项目使用，其他项目可删除 -->			 <#-- ${tab?uncap_first}Upd.setUpdateTime(new Date());有创建时间、更新时间项目使用，其他项目可删除 -->			 ${tab?uncap_first}Svc.add${tab}(${tab?uncap_first}Upd);		}else{			if(null!=${tab?uncap_first}Upd.get<@getTableKeyForSetGet cols/>s()){				for(int i<@getTableKeyForSetGet cols/>=0;i<@getTableKeyForSetGet cols/><${tab?uncap_first}Upd.get<@getTableKeyForSetGet cols/>s().size();i<@getTableKeyForSetGet cols/>++){					${tab?uncap_first}Upd.set<@getTableKeyForSetGet cols/>((Integer) ${tab?uncap_first}Upd.get<@getTableKeyForSetGet cols/>s().get(i<@getTableKeyForSetGet cols/>));					${tab?uncap_first}Svc.upd${tab}ByPrimaryKey(${tab?uncap_first}Upd);				}			}else{				<#-- ${tab?uncap_first}Upd.setUpdateTime(new Date());有创建时间、更新时间项目使用，其他项目可删除 -->				${tab?uncap_first}Svc.upd${tab}ByPrimaryKey(${tab?uncap_first}Upd);//				${tab}  ${tab?uncap_first} = new ${tab}();		//更新数据过滤条件//				${tab?uncap_first}Svc.upd${tab}ByFilter(${tab?uncap_first}Upd,${tab?uncap_first});			}		}		<#else>		if(StringUtils.isBlank(${tab?uncap_first}Upd.get<@getTableKeyForSetGet cols/>())){			 <#list cols as col><#if col.primaryKey >${tab?uncap_first}Upd.set<@getTableKeyForSetGet cols/>(Utils.shortUUIDRandom());</#if></#list>			 <#-- ${tab?uncap_first}Upd.setCreateTime(new Date());有创建时间、更新时间项目使用，其他项目可删除 -->			 <#-- ${tab?uncap_first}Upd.setUpdateTime(new Date());有创建时间、更新时间项目使用，其他项目可删除 -->			 ${tab?uncap_first}Svc.add${tab}(${tab?uncap_first}Upd);		}else{			if(null!=${tab?uncap_first}Upd.get<#list cols as col><#if col.primaryKey><@getTableKeyForSetGet cols/></#if></#list>()){				for(String t<#list cols as col><#if col.primaryKey><@getTableKeyForSetGet cols/></#if></#list> : ${tab?uncap_first}Upd.get<#list cols as col><#if col.primaryKey><@getTableKeyForSetGet cols/></#if></#list>().split(",")){					<#list cols as col><#if col.primaryKey >${tab?uncap_first}Upd.set<@getTableKeyForSetGet cols/>(t<#list cols as col><#if col.primaryKey><@getTableKeyForSetGet cols/></#if></#list>);</#if></#list>					${tab?uncap_first}Svc.upd${tab}ByPrimaryKey(${tab?uncap_first}Upd);					<#-- ${tab?uncap_first}Upd.setUpdateTime(new Date());有创建时间、更新时间项目使用，其他项目可删除 -->	//				${tab}  ${tab?uncap_first} = new ${tab}();		//更新数据过滤条件	//				${tab?uncap_first}Svc.upd${tab}ByFilter(${tab?uncap_first}Upd,${tab?uncap_first});				}			}		}		</#if></#if></#list>		InterfaceJsonResponseJson ijrj = new InterfaceJsonResponseJson();		ijrj.put("error", "0");		ijrj.put("msg", "sucess");		writeToJson(response, ijrj);	}		@Login(value=LoginResultReturnType.page,reqFromType=LoginRequestFromType.restInterface)	@RequestMapping(value={"/list"<@compress single_line=true><#list cols as cola><#assign colShow>${checkColSearch(cola.code)?string}</#assign><#if (colShow=='true')>							,"/list<#list cols as colb><#assign colIndex=colb_index+1><#if (colb_index<cola_index)>/{pmkey${colIndex}:.+}/{pmval${colIndex}:.+}</#if></#list>"</#if></#list></@compress>})	public void list(HttpServletRequest request, HttpServletResponse response	,@PathVariable Map<String,String> params){		Pagination page = new Pagination();		${tab}  ${tab?uncap_first} = new ${tab}();		Map<String,String> beanMap = new LinkedHashMap<String,String>();		for(int im=1;im<=params.size()/2;im++){			beanMap.put(params.get("pmkey"+im), params.get("pmval"+im));		}		try {			PropertyUtils.copyProperties(${tab?uncap_first}, beanMap);			PropertyUtils.copyProperties(page, beanMap);		} catch (IllegalAccessException | InvocationTargetException				| NoSuchMethodException e) {			// TODO Auto-generated catch block			e.printStackTrace();		}		<#if (joinTables?size>0)>//		page =  ${tab?uncap_first}Svc.getAll${tab}ByPage(page,  ${tab?uncap_first});		page =  ${tab?uncap_first}Svc.getAll${tab}ByPageWithJoin(page, ${tab?uncap_first});//		List<${tab}> resultSk = ${tab?uncap_first}Svc.getAll${tab}BySearchKey(${tab?uncap_first}, searchCol);//		List<${tab}> resultSkJ = ${tab?uncap_first}Svc.getAll${tab}BySearchKeyWithJoin(${tab?uncap_first}, searchCol);		<#else>		page =  ${tab?uncap_first}Svc.getAll${tab}ByPage(page,  ${tab?uncap_first});//		List<${tab}> resultSk = ${tab?uncap_first}Svc.getAll${tab}BySearchKey(${tab?uncap_first}, searchCol);		</#if>		InterfaceJsonResponseJson ijrj = new InterfaceJsonResponseJson();		ijrj.put("error", "0");		ijrj.put("msg", "sucess");		ijrj.put("data", page.getData());		page.setData(null);		ijrj.put("page", page);		writeToJson(response, ijrj);	}		@Login(value=LoginResultReturnType.page,reqFromType=LoginRequestFromType.restInterface)	@RequestMapping(value="/modify/<@compress>{<@getTableKey cols/>}")</@compress>	public void modify(HttpServletRequest request, HttpServletResponse response,		@PathVariable("<#list cols as col><#if (col.primaryKey && col.inSelfTable)><@genAttributeByCol colName_ABC='${col.code}'/></#if></#list>")<#list cols as col><#if (col.primaryKey && col.inSelfTable)><#if col.type?upper_case?contains("TIME") || col.type?upper_case?contains("DATE")>java.util.Date <@genAttributeByCol colName_ABC='${col.code}'/><#elseif col.type?upper_case?contains("BOOL")>boolean <@genAttributeByCol colName_ABC='${col.code}'/><#elseif col.type?upper_case?contains("INT")>Integer <@genAttributeByCol colName_ABC='${col.code}'/><#else>${col.pub} <@genAttributeByCol colName_ABC='${col.code}'/></#if></#if></#list>){		<#list joinTables as jt>		mv.addObject("<@genAttributeByCol colName_ABC='${jt.tableCode?replace("^T_","","r")}' />Opt", <@genAttributeByCol colName_ABC='${jt.tableCode?replace("^T_","","r")}' />Svc.getAll<@genAttributeByForSetAndGet colName_ABFSAG='${jt.tableCode?replace("^T_","","r")}' />BySearch(new <@genAttributeByForSetAndGet colName_ABFSAG='${jt.tableCode?replace("^T_","","r")}' />()));		</#list>		${tab}  ${tab?uncap_first} = ${tab?uncap_first}Svc.get${tab}ByPrimaryKey(<#list cols as col><#if (col.primaryKey && col.inSelfTable)><@genAttributeByCol colName_ABC='${col.code}'/></#if></#list>);		InterfaceJsonResponseJson ijrj = new InterfaceJsonResponseJson();		ijrj.put("error", "0");		ijrj.put("msg", "sucess");		ijrj.put("data", ${tab?uncap_first});		writeToJson(response, ijrj);	}		@Login(value=LoginResultReturnType.page,reqFromType=LoginRequestFromType.restInterface)	@RequestMapping(value="/show/<@compress>{<@getTableKey cols/>}")</@compress>	public void show(HttpServletRequest request, HttpServletResponse response,		@PathVariable("<#list cols as col><#if (col.primaryKey && col.inSelfTable)><@genAttributeByCol colName_ABC='${col.code}'/></#if></#list>")<#list cols as col><#if (col.primaryKey && col.inSelfTable)><#if col.type?upper_case?contains("TIME") || col.type?upper_case?contains("DATE")>java.util.Date <@genAttributeByCol colName_ABC='${col.code}'/><#elseif col.type?upper_case?contains("BOOL")>boolean <@genAttributeByCol colName_ABC='${col.code}'/><#elseif col.type?upper_case?contains("INT")>Integer <@genAttributeByCol colName_ABC='${col.code}'/><#else>${col.pub} <@genAttributeByCol colName_ABC='${col.code}'/></#if></#if></#list>){		${tab}  ${tab?uncap_first} = ${tab?uncap_first}Svc.get${tab}ByPrimaryKey(<#list cols as col><#if (col.primaryKey && col.inSelfTable)><@genAttributeByCol colName_ABC='${col.code}'/></#if></#list>);		InterfaceJsonResponseJson ijrj = new InterfaceJsonResponseJson();		ijrj.put("error", "0");		ijrj.put("msg", "sucess");		ijrj.put("data", ${tab?uncap_first});		writeToJson(response, ijrj);	}		@Login(value=LoginResultReturnType.page,reqFromType=LoginRequestFromType.restInterface)	@RequestMapping(value="/del/<@compress>{<@getTableKey cols/>}")</@compress>	public void del(HttpServletRequest request, HttpServletResponse response		,@PathVariable("<#list cols as col><#if (col.primaryKey && col.inSelfTable)><@genAttributeByCol colName_ABC='${col.code}'/></#if></#list>")<#list cols as col><#if (col.primaryKey && col.inSelfTable)><#if col.type?upper_case?contains("TIME") || col.type?upper_case?contains("DATE")>java.util.Date <@genAttributeByCol colName_ABC='${col.code}'/><#elseif col.type?upper_case?contains("BOOL")>boolean <@genAttributeByCol colName_ABC='${col.code}'/><#elseif col.type?upper_case?contains("INT")>Integer <@genAttributeByCol colName_ABC='${col.code}'/><#else>${col.pub} <@genAttributeByCol colName_ABC='${col.code}'/></#if></#if></#list>){		${tab?uncap_first}Svc.del${tab}ByPrimaryKey(<#list cols as col><#if (col.primaryKey && col.inSelfTable)><@genAttributeByCol colName_ABC='${col.code}'/></#if></#list>);//		${tab?uncap_first}Svc.del${tab}ByFilter(<#list cols as col><#if (col.primaryKey && col.inSelfTable)><@genAttributeByCol colName_ABC='${col.code}'/></#if></#list>);		InterfaceJsonResponseJson ijrj = new InterfaceJsonResponseJson();		ijrj.put("error", "0");		ijrj.put("msg", "sucess");		writeToJson(response, ijrj);	}}