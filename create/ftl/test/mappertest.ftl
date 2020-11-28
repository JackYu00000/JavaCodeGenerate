<#include "../function.ftl">
package ${mapperpackatename}.sql;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.math.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.is.web.common.utils.Utils;
import ${packname}.entity${tab};
<#list joinTables as jt>
<#assign jtCode><@genAttributeByForSetAndGet colName_ABFSAG='${jt.tableCode?replace("^T_","","r")}' /></#assign>
import ${packname}.entity${jtCode};
</#list>
<#assign tablePrimaryKeyColumnType><@getTableKeyType cols /></#assign>
<#assign colInteger>${checkColTypeIsToJavaNotString(tablePrimaryKeyColumnType)?string}</#assign>

@ContextConfiguration(locations={"classpath:applicationContext-hsql.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
public class ${tab}MapperTest {
<#list cols as col><#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
<#if (!col.primaryKey && col.inSelfTable)>
	<#assign updColIndex=col_index>
	<#assign updColSGet><@getTableUpdFirstColForSetGet cols updColIndex /></#assign>
	<#assign updColType=col.type />
    <#break>
</#if></#list>

	/**
	 * add${tab}(${tab} ${tab?uncap_first})
	 * 添加一条新<b>${name}</b>记录
	 */
	@Test
	public void test_添加一条新${name}记录(){
		${tab} ${tab?uncap_first} = gen${tab}();
		${tab?uncap_first}Mapper.add${tab}(${tab?uncap_first});
		Assert.assertNotNull(${tab?uncap_first}Mapper.get${tab}ByPrimaryKey(new ${tab}(${tab?uncap_first}.get<@compress single_line=true><@getTableKeyForSetGet cols />()</@compress>)));
	}
	
	/**
	 * del${tab}ByPrimaryKey(<@compress><@getTableKeyParamFirst cols/></@compress>)
	 * 根据主键删除一条<b>${name}</b>记录
	 */
	@Test
	public void test_根据主键删除一条${name}记录(){
		${tab} ${tab?uncap_first}A = gen${tab}();
		${tab} ${tab?uncap_first}B = gen${tab}();
		${tab?uncap_first}Mapper.add${tab}(${tab?uncap_first}A);
		${tab?uncap_first}Mapper.add${tab}(${tab?uncap_first}B);
		
		list = ${tab?uncap_first}Mapper.getAll${tab}BySearch(new ${tab}());
		countBefore = list.size();
		${tab?uncap_first}Mapper.del${tab}ByPrimaryKey(${tab?uncap_first}A.get<@compress single_line=true><@getTableKeyForSetGet cols />()</@compress>);
		list = ${tab?uncap_first}Mapper.getAll${tab}BySearch(new ${tab}());
		countAfter = list.size();
		Assert.assertEquals(countBefore-1,countAfter);
	}
	
	/**
	 * upd${tab}ByPrimaryKey(${tab} ${tab?uncap_first})
	 * 根据主键更新一条新<b>${name}</b>记录
	 */
	@Test
	public void test_根据主键更新一条新${name}记录(){
		${tab} ${tab?uncap_first}A = gen${tab}();
		${tab?uncap_first}Mapper.add${tab}(${tab?uncap_first}A);
		${tab} ${tab?uncap_first}B = gen${tab}();
		${tab?uncap_first}B.set<@compress single_line=true><@getTableKeyForSetGet cols /></@compress>(${tab?uncap_first}A.get<@compress single_line=true><@getTableKeyForSetGet cols />()</@compress>);
		${tab?uncap_first}Mapper.upd${tab}ByPrimaryKey(${tab?uncap_first}B);
		${tab} ${tab?uncap_first}C = ${tab?uncap_first}Mapper.get${tab}ByPrimaryKey(new ${tab}(${tab?uncap_first}A.get<@compress single_line=true><@getTableKeyForSetGet cols />()</@compress>));
		<#list cols as col><#assign colAttr><@genAttributeByCol colName_ABC='${col.code}'/></#assign>
<#if (!col.primaryKey && col.inSelfTable)>
      	Assert.assertEquals(${tab?uncap_first}C.get<@getTableKeyForSetGet cols/>(), ${tab?uncap_first}B.get<@getTableKeyForSetGet cols/>());
</#if></#list>
	}
	
	/**
	 * upd${tab}ByFilter(${tab} ${tab?uncap_first},${tab} ${tab?uncap_first}Upd)
	 * 根据条件更新多条新<b>${name}</b>记录
	 */
	@Test
	public void test_根据条件更新多条新${name}记录(){
		${tab} ${tab?uncap_first}A = gen${tab}();
		${tab?uncap_first}Mapper.add${tab}(${tab?uncap_first}A);
		${tab?uncap_first}Filter.set${updColSGet}(${tab?uncap_first}A.get${updColSGet}());
		${tab} ${tab?uncap_first}Upd = new ${tab}();
		<#if (colInteger=='true')>
		${tab?uncap_first}Upd.set${updColSGet}(${tab?uncap_first}A.get${updColSGet}() + 1);
		<#else>
		${tab?uncap_first}Upd.set${updColSGet}(new StringBuffer(${tab?uncap_first}A.get${updColSGet}()).reverse().toString());
		</#if>
		${tab?uncap_first}Mapper.upd${tab}ByFilter(${tab?uncap_first}Filter, ${tab?uncap_first}Upd);
		${tab} ${tab?uncap_first}C = ${tab?uncap_first}Mapper.get${tab}ByPrimaryKey(new ${tab}(${tab?uncap_first}A.get<@compress single_line=true><@getTableKeyForSetGet cols />()</@compress>));
		Assert.assertEquals(new StringBuffer(${tab?uncap_first}A.get${updColSGet}()).reverse().toString(), ${tab?uncap_first}C.get${updColSGet}());
	}
	
	/**
	 * getResultForSelectParamBySearch(String selectParam,${tab} ${tab?uncap_first})
	 * 根据sql语句进行查询，用户计算
	 */
	@Test
	public void test_根据sql语句进行查询_用户计算(){
		addTestData(5);
		list = ${tab?uncap_first}Mapper.getAll${tab}BySearch(new ${tab}());
		String sqlStr = " * from ${code}";
		List<?> listResult = ${tab?uncap_first}Mapper.getResultForSelectParamBySearch(sqlStr,new ${tab}());
		Assert.assertEquals(list.size(), listResult.size());
	}
	
	/**
	 * getResultForSelectParam(String selectParam)
	 * 根据sql语句进行查询，自行拼凑select之后的完整sql语句
	 */
	@Test
	public void test_根据sql语句进行查询_自行拼凑select之后的完整sql语句(){
		addTestData(5);
		list = ${tab?uncap_first}Mapper.getAll${tab}BySearch(new ${tab}());
		String sqlStr = " * from ${code}";
		List<?> listResult = ${tab?uncap_first}Mapper.getResultForSelectParam(sqlStr);
		Assert.assertEquals(list.size(), listResult.size());
	}
	
	/**
	 * get${tab}ByPrimaryKey(<@compress><@getTableKeyParamFirst cols/></@compress>)
	 * 根据主键，获取一条<b>${name}</b>记录内容
	 */
	@Test
	public void test_根据主键获取一条${name}记录内容(){
		addTestData(1);
		Assert.assertNotNull(${tab?uncap_first}Mapper.get${tab}ByPrimaryKey(new ${tab}(addTestList.get(0).get<@compress single_line=true><@getTableKeyForSetGet cols />()</@compress>)));
	}
	
	/**
	 * getAll${tab}BySearch(${tab} ${tab?uncap_first})
	 * 根据条件，查询全部<b>${name}</b>记录
	 */
	@Test
	public void test_根据条件查询全部${name}记录(){
		addTestData(10);
		list = ${tab?uncap_first}Mapper.getAll${tab}BySearch(new ${tab}());
		Assert.assertEquals(10, list.size());
	}
	
	/**
	 * getAll${tab}BySearchKey(${tab} ${tab?uncap_first},List<String> colList)
	 * 根据关键字对指定列进行查询，查询全部<b>${name}</b>记录
	 */
	@Test
	public void test_根据关键字对指定列进行查询_查询全部${name}记录(){
		${tab} ${tab?uncap_first}A = gen${tab}();
		${tab?uncap_first}Mapper.add${tab}(${tab?uncap_first}A);
		
		<#if (colInteger=='true')>
		String kval = String.valueOf(${tab?uncap_first}A.get${updColSGet}());
		<#else>
		String kval = ${tab?uncap_first}A.get${updColSGet}();
		</#if>
		${tab} ${tab?uncap_first}D = gen${tab}();
		${tab?uncap_first}D.setSearchKey(kval);
		List<String> scol = new ArrayList<String>();
		scol.add(${tab?uncap_first}D.${tab?uncap_first}Colum.get("${updColSGet?uncap_first}"));
		list = ${tab?uncap_first}Mapper.getAll${tab}BySearchKey(${tab?uncap_first}D,scol);
		Assert.assertEquals(1, list.size());
	}

<#if (joinTables?size>0)>
<#list joinTables as jt><#if (!jt.tableCode?starts_with("SQLVW_") && !jt.tableCode?starts_with("SQLVVW_"))>	
<#list jt.colume as col>
	<#assign joinColTmp=jt.columeAlias[col_index] />
	<#assign joinColType><@genTypeByColName joinColTmp cols /></#assign>
	<#if (!joinColType?upper_case?contains("DATE") && !joinColType?upper_case?contains("TIME"))>
	<#assign colAttr><@genAttributeByCol colName_ABC='${col}'/></#assign>
	<#assign joinColSGet><@genAttributeByForSetAndGet col?replace("${jt.joinOnColume}","","r") /></#assign>
	<#assign joinColSGetR><@genAttributeByForSetAndGet joinColTmp /></#assign>
      	<#break>
    </#if>
</#list>
<#assign jtCode><@genAttributeByForSetAndGet colName_ABFSAG='${jt.tableCode?replace("^T_","","r")}' /></#assign>
	/**
	 * get${tab}ByPrimaryKeyWithJoin(${tab} ${tab?uncap_first})
	 * 根据主键，获取一条<b>${name}</b>记录内容、及关联数据  ${jt.tableName?replace(" ","","r")}
	 */
	@Test
	public void test_根据主键获取一条${name}记录内容及关联数据_${jt.tableName?replace(" ","","r")}(){
		${jtCode} ${jtCode?uncap_first} = ${jtCode}MapperTest.gen${jtCode}();
		${jtCode?uncap_first}Mapper.add${jtCode}(${jtCode?uncap_first});
	
		${tab} ${tab?uncap_first} = gen${tab}();
		${tab?uncap_first}.set<@genAttributeByForSetAndGet jt.joinOnColume />(${jtCode?uncap_first}.get<@genAttributeByForSetAndGet jt.joinPrimaryKey />());
		${tab?uncap_first}Mapper.add${tab}(${tab?uncap_first});
		
		${tab} ${tab?uncap_first}A = ${tab?uncap_first}Mapper.get${tab}ByPrimaryKeyWithJoin(new ${tab}(${tab?uncap_first}.get<@compress single_line=true><@getTableKeyForSetGet cols />()</@compress>));
		<#list jt.colume as jtc>
		Assert.assertEquals(${tab?uncap_first}A.get<@genAttributeByForSetAndGet jt.columeAlias[jtc_index] />(),${jtCode?uncap_first}.get<@genAttributeByForSetAndGet jt.columeAlias[jtc_index]?replace("${jt.joinOnColume}","","r") />());
		</#list>	 
	}
	
	/**
	 * getAll${tab}BySearchWithJoin(${tab} ${tab?uncap_first})
	 * 根据条件，查询全部<b>${name}</b>记录、及关联数据 ${jt.tableName?replace(" ","","r")}
	 */
	@Test
	public void test_根据条件查询全部${name}记录及关联数据_${jt.tableName?replace(" ","","r")}(){
		${jtCode} ${jtCode?uncap_first} = ${jtCode}MapperTest.gen${jtCode}();
		${jtCode?uncap_first}Mapper.add${jtCode}(${jtCode?uncap_first});
	
		${tab} ${tab?uncap_first} = gen${tab}();
		${tab?uncap_first}.set<@genAttributeByForSetAndGet jt.joinOnColume />(${jtCode?uncap_first}.get<@genAttributeByForSetAndGet jt.joinPrimaryKey />());
		${tab?uncap_first}Mapper.add${tab}(${tab?uncap_first});
		
		${tab?uncap_first}Filter = new ${tab}();
		${tab?uncap_first}Filter.set${joinColSGetR}(${jtCode?uncap_first}.get${joinColSGet}());
		List<${tab}> result = ${tab?uncap_first}Mapper.getAll${tab}BySearchWithJoin(${tab?uncap_first}Filter);
		<#list jt.colume as jtc>
		Assert.assertEquals(result.get(0).get<@genAttributeByForSetAndGet jt.columeAlias[jtc_index] />(),${jtCode?uncap_first}.get<@genAttributeByForSetAndGet jt.columeAlias[jtc_index]?replace("${jt.joinOnColume}","","r") />());
		</#list>
	}
	
	/**
	 * getAll${tab}BySearchKeyWithJoin(${tab} ${tab?uncap_first},List<String> colList)
	 * 根据关键字对指定列进行查询，查询全部<b>${name}</b>记录 ${jt.tableName?replace(" ","","r")}
	 */
	@Test
	public void test_根据关键字对指定列进行查询查询全部${name}记录_${jt.tableName?replace(" ","","r")}(){
		${jtCode} ${jtCode?uncap_first} = ${jtCode}MapperTest.gen${jtCode}();
		${jtCode?uncap_first}Mapper.add${jtCode}(${jtCode?uncap_first});
	
		${tab} ${tab?uncap_first} = gen${tab}();
		<#assign jtCode><@genAttributeByForSetAndGet colName_ABFSAG='${jt.tableCode?replace("^T_","","r")}' /></#assign>
		${tab?uncap_first}.set<@genAttributeByForSetAndGet jt.joinOnColume />(${jtCode?uncap_first}.get<@genAttributeByForSetAndGet jt.joinPrimaryKey />());
		${tab?uncap_first}Mapper.add${tab}(${tab?uncap_first});
		
		List<String> scol = new ArrayList<String>();
		scol.add(${tab?uncap_first}.${tab?uncap_first}Colum.get("${joinColSGetR?uncap_first}"));
		${tab?uncap_first}Filter = new ${tab}();
		<#assign joinColType><@genTypeByColName joinColTmp cols /></#assign>
		<#if joinColType?upper_case?starts_with("INT")>
		${tab?uncap_first}Filter.setSearchKey(String.valueOf(${tab?uncap_first}.get${joinColSGetR}()));
		<#else>
		${tab?uncap_first}Filter.setSearchKey(${tab?uncap_first}.get${joinColSGetR}());
		</#if>
		List<${tab}> result = ${tab?uncap_first}Mapper.getAll${tab}BySearchKeyWithJoin(${tab?uncap_first}Filter,scol);
		<#list jt.colume as jtc>
		Assert.assertEquals(result.get(0).get<@genAttributeByForSetAndGet jt.columeAlias[jtc_index] />(),${jtCode?uncap_first}.get<@genAttributeByForSetAndGet jt.columeAlias[jtc_index]?replace("${jt.joinOnColume}","","r") />());
		</#list>
	}

	</#if>
</#list>
	</#if>
	Logger logger = LogManager.getLogger(${tab}MapperTest.class.getName());
	
	@Autowired
	private ${tab}Mapper ${tab?uncap_first}Mapper;
	
	<#list joinTables as jt>
	<#assign jtCode><@genAttributeByForSetAndGet colName_ABFSAG='${jt.tableCode?replace("^T_","","r")}' /></#assign>
	@Autowired
	private ${jtCode}Mapper ${jtCode?uncap_first}Mapper;
	
	</#list>
	${tab} ${tab?uncap_first}Filter;		//检索过滤条件
	
	List<${tab}> list;				//列表查询返回结果
	
	List<${tab}> addTestList = new ArrayList<${tab}>();		//为检索等作的列表初始数据
	
	List<String> colList;

	ObjectMapper mapper;
	
	int countBefore;		//删除、添加前数据记录条数
	
	int countAfter;			//删除、添加后数据记录条数
	
	/**
	 * 生成一个赋有初始值的对象
	 * @return
	 */
	public static ${tab} gen${tab}(){
		${tab} ${tab?uncap_first} = new ${tab}();
<#list cols as col><#assign colAttr><@genAttributeByForSetAndGet colName_ABFSAG='${col.code}'/></#assign>
<#if (col.inSelfTable)>
	<#if (col.type?upper_case?contains("DECIMAL") || col.type?upper_case?contains("INT"))>
		<#if (col.type?upper_case=="BIGINT")>
		${tab?uncap_first}.set${colAttr}(Long.valueOf(RandomUtils.nextInt()));
		<#else>
		${tab?uncap_first}.set${colAttr}(RandomUtils.nextInt());
		</#if>
	<#elseif (col.type?upper_case?contains('VARCHAR'))>
		<#if (col.primaryKey)>
		${tab?uncap_first}.set${colAttr}(Utils.shortUUIDRandom());
		<#else>
		${tab?uncap_first}.set${colAttr}(Utils.genRandomChar(${col.type?replace('\\D','','r')}));
		</#if>
	<#elseif (col.type?upper_case?contains("DATE"))>
		${tab?uncap_first}.set${colAttr}(new java.util.Date());
	<#else>
		${tab?uncap_first}.set${colAttr}(null);
	</#if>
</#if></#list>
		return ${tab?uncap_first};
	}
	
	/**
	 * 添加列表测试数据
	 * @param size 添加条数
	 */
	public void addTestData(int size){
		addTestList.clear();
		for(int i = 0; i < size ; i++){
			${tab} ${tab?uncap_first} = gen${tab}();
			addTestList.add(${tab?uncap_first});
			${tab?uncap_first}Mapper.add${tab}(${tab?uncap_first});
		}
	}
	
	@Before
	public void init(){
		mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT,true);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
		
		${tab?uncap_first}Filter = new ${tab}();
		
		list = ${tab?uncap_first}Mapper.getAll${tab}BySearch(new ${tab}());
		for(${tab} ${tab?uncap_first}:list)
			${tab?uncap_first}Mapper.del${tab}ByPrimaryKey(new ${tab}(${tab?uncap_first}.get<@compress single_line=true><@getTableKeyForSetGet cols />()</@compress>));
	}

}
