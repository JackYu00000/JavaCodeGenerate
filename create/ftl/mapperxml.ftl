<#include "function.ftl">
<?xml version="1.0" encoding="UTF-8" ?> 
    <!DOCTYPE mapper 
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#assign tableCols=0>
<#list cols as col>
		<#if (col.inSelfTable)><#assign tableCols=tableCols+1 ></#if>
</#list>   
<#assign tableKeyParamStr><@compress><@getTableKeyParam cols/></@compress></#assign>
<#assign tableKeyStr><@compress><@getTableKeyColFirstCode cols/></@compress></#assign>
<#assign tablePrimaryKeyColumnType><@getTableKeyType cols /></#assign>
<#assign colInteger>${checkColTypeIsToJavaNotString(tablePrimaryKeyColumnType)?string}</#assign>
<#if (!isView)>
<mapper namespace="${packname}.mapper.${tab}Mapper">
<#if (!code?starts_with("SQLVW_") && !code?starts_with("SQLVVW_"))>	
	<insert id="add${tab}" parameterType="${packname}.entity.${tab}"<#if (colInteger=='true')> useGeneratedKeys="true" keyProperty="${tab?uncap_first}.<@getTableKey columns_TK=cols />"</#if>>
		insert into <include refid="table" />(<#list cols as col>
			<#if col.inSelfTable><#if !col.primaryKey ><if test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=null"><#if (col_index>0)>,</#if><@rewriteColInMysql colCode_RCIM='${col.code}'/></if><#else><#if (col_index>0)>,</#if><@rewriteColInMysql colCode_RCIM='${col.code}'/></#if><#else><#break></#if></#list>
		)values(<#list cols as col>
			<#if col.inSelfTable><#if !col.primaryKey><if test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=null"><#if (col_index>0)>,</#if>${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DECIMAL">,jdbcType=DECIMAL<#elseif col.type?upper_case=="DOUBLE">,jdbcType=DOUBLE<#else></#if>}</if><#else><#if (col_index>0)>,</#if>${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}</#if><#else><#break></#if></#list>
		)
	</insert>
	
<!--	<delete id="del${tab}ByPrimaryKey" parameterType="<@getTableKeyJavaType cols />">
		delete from <include refid="table" />
		where 
		<#list cols as col>
			<#if (col.primaryKey && col.inSelfTable) >
				<#if (col_index=0)>
			${col.code}=<#noparse>#{</#noparse><#list cols as colEntity><#if (colEntity_index=col_index)><@genAttributeByCol colName_ABC='${colEntity.code}'/></#if></#list>}
				<#else>
<!--			 and ${col.code}=<#noparse>#{</#noparse><#list cols as colEntity><#if (colEntity_index=col_index)><@genAttributeByCol colName_ABC='${colEntity.code}'/></#if></#list>} -->
			</#if>
			</#if>
		</#list>
	</delete> -->	
	
	<update id="upd${tab}ByPrimaryKey" parameterType="${packname}.entity.${tab}">
		update <include refid="table" />	TA
		<include refid="update" /> 
		where 
		<#list cols as col>
			<#if (col.primaryKey && col.inSelfTable) >
				<#if (col_index=0)>
		${col.code}=${"#"}{${tab?uncap_first}.<#list cols as colEntity><#if (colEntity_index=col_index)><@genAttributeByCol colName_ABC='${colEntity.code}'/></#if></#list>}
				<#else>
<!--		 and ${col.code}=${"#"}{${tab?uncap_first}.<#list cols as colEntity><#if (colEntity_index=col_index)><@genAttributeByCol colName_ABC='${colEntity.code}'/></#if></#list>} -->
					</#if>
			</#if>
		</#list>
	</update>
	
</#if>	

	<select id="getResultForSelectParamBySearch" parameterType="String" resultType="Map">
		select <#noparse>$</#noparse>{selectParam} TA
		<where> 
		<include refid="qualification" />
		</where>
		<include refid="groupbys" />
		<include refid="orderbys" />	
	</select>
	
	<select id="getResultForSelectParam" parameterType="String" resultType="Map">
		select <#noparse>$</#noparse>{selectParam} 
	</select>
	
	<select id="get${tab}ByPrimaryKey" parameterType="${packname}.entity.${tab}" resultMap="${tab?lower_case}Mapper">
		<include refid="selectSql" />
		where 
		<#list cols as col>
			<#if (col.primaryKey && col.inSelfTable) >
				<#if (col_index=0)>
					TA.${col.code}=${"#"}{${tab?uncap_first}.<#list cols as colEntity><#if (colEntity_index=col_index)><@genAttributeByCol colName_ABC='${colEntity.code}'/></#if></#list>}
				</#if>
			</#if>
		</#list>
	</select>
		
	<select id="getAll${tab}BySearch" parameterType="${packname}.entity.${tab}" resultMap="${tab?lower_case}Mapper">
		<include refid="selectSql" /> 
		<where> 
		<include refid="qualification" />
		</where>
		<include refid="groupbys" />
		<include refid="orderbys" />
	</select>

	<select id="getAll${tab}BySearchKey" parameterType="List" resultMap="${tab?lower_case}Mapper">
		<include refid="${tab}WithSearchKeyByPage" />
		<include refid="groupbys" />
		<include refid="orderbys" />
	</select>
	
	<select id="getAll${tab}ByPage" parameterType="${packname}.entity.${tab}" resultMap="${tab?lower_case}Mapper">
		<include refid="selectSql" /> 
		<where> 
		<include refid="qualification" />
		</where>
		<include refid="groupbys" />
		<include refid="orderbys" />
		<include refid="pageLimit" />
	</select>
	
	<select id="getCountForPage" parameterType="${packname}.entity.${tab}" resultType="int">
		select count(${tableKeyStr}) from <include refid="table" /> TA 
		<where> 
		<include refid="qualification" />
		</where>
	</select>
	
	<select id="getAll${tab}WithSearchKeyByPage" parameterType="List" resultMap="${tab?lower_case}Mapper">
		<include refid="${tab}WithSearchKeyByPage" />
		<include refid="groupbys" />
		<include refid="orderbys" />
		<include refid="pageLimit" />
	</select>
	
	<select id="getCountWithSearchKeyForPage" parameterType="List" resultType="int">
		select count(${tableKeyStr}) from (
			<include refid="${tab}WithSearchKeyByPage" />
		) TSUM
	</select>
	
	<resultMap id="${tab?lower_case}Mapper" type="${packname}.entity.${tab}">
        <#list cols as col>
        <#assign colShow>${checkColWriteToMapper(col.code)?string}</#assign>
		<#if (colShow=='true' && col.inSelfTable && col.inTableColumn)>
        <#if !col.primaryKey><result property="<@genAttributeByCol colName_ABC='${col.code}'/>" column="${col.code}" />		<!-- ${col.name} -->
        <#else><#if (col.primaryKey && col_index==0)><id property="<@genAttributeByCol colName_ABC='${col.code}'/>" column="${col.code}" />		<!-- ${col.name} --></#if>
        </#if>
        </#if>
        </#list>
    </resultMap>
    
    <sql id="${tab}WithSearchKeyByPage">
		<foreach item="${tab?uncap_first}" index="index" collection="${tab?uncap_first}List" separator="UNION">
		<include refid="selectSql" /> 
		<where> 
			<include refid="qualification" />
		</where>
		</foreach>
	</sql>
	
	<sql id="table">
		${code}
	</sql>

	<sql id="selectColum">
		<choose>
		<when test="${tab?uncap_first}.selectCols!=null and ${tab?uncap_first}.selectCols.size>0">
		<foreach item="item" index="index" collection="${tab?uncap_first}.selectCols" open="" separator="," close=""><#noparse>${</#noparse>item}</foreach>
		</when>
		<otherwise>
			<#list cols as col><#if col.inSelfTable><#if (col_index>0)>,</#if><@rewriteColInMysql colCode_RCIM='${col.code}'/></#if></#list>
		</otherwise>
		</choose>
	</sql>
	
	<sql id="selectSql">
		<#if (code?starts_with("SQLVW_") || code?starts_with("SQLVVW_"))>
		select <include refid="selectColum" /> from <include refid="table" /> TA
		<!--
		select <include refid="selectColum" /> from (${viewSqlQuery}) TA
		  -->
		<#else>	
		select <include refid="selectColum" /> 
		<#list cols as col><#if (!col.primaryKey)><#if (col.comment!?index_of("RADIO")>-1 || col.comment!?index_of("CHECKBOX")>-1)>
<!--			,<include refid="${col.code}_CHN" />	-->
			</#if></#if></#list>
		from <include refid="table" /> TA
	</#if>
	</sql>
	
	<sql id="qualification">
	<if test="null!=${tab?uncap_first}">
	<#list cols as col><#if (col.inSelfTable)>
<#if ((!col.primaryKey && col.inTableColumn) || (col.primaryKey && col.inSelfTable))><#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
<#if (col_index=0 && !(code?starts_with("SQLVW_") || code?starts_with("SQLVVW_")))><!-- </#if><#if col.type?upper_case?contains("VARCHAR") || col.type?upper_case?contains("TEXT")>
		<if test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=null and ${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=''"> and  
			<choose>
				<#if (col.comment!?index_of("RADIO")>-1 || col.comment!?index_of("CHECKBOX")>-1 || col.comment!?index_of("OPTION")>-1)>
				<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.indexOf(',')>=0">
        			TA.${col.code} in <foreach item="item" index="index" collection="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.split(',')" open="(" separator="," close=")"><#noparse>#{item}</#noparse></foreach>
        		</when>
				<#else>
				<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.indexOf(' ')>=0">
        			<foreach item="item" index="index" collection="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.split(' ')" open="(" separator=" " close=")"><if test="index>0"> or </if> TA.${col.code} like concat('%',<#noparse>${</#noparse>item},'%') </foreach>
        		</when>
        		<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.indexOf('+')>=0">
        			TA.${col.code} like concat('%','<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.replace("+", "%")}','%')
        		</when>
				</#if>
        		<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.startsWith('!')">
        			TA.${col.code} !='<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.replace("!", "")}'
        		</when>
				<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.startsWith('=')">
        			TA.${col.code} ='<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.replace("=", "")}'
        		</when>
        		<otherwise>
        			TA.${col.code} like concat('%','<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.replace("%", "")}','%')
        		</otherwise>
			</choose>
		</if>
		<#elseif colInteger=='true' || col.type?upper_case?contains("DATE")>
			<if test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=null or 
					${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>BetweenType!=null or
    				${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan!=null or
    				${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan!=null"> and  
    				<choose>
    					<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan!=null
	        					and ${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan!=null
	        					and ${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>BetweenType!=null">
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse><</#noparse> TA.${col.code} ]]>
	        				<choose><when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>BetweenType=='and'"> and </when><otherwise> or </otherwise></choose>
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse>></#noparse> TA.${col.code}]]>
	        			</when>
    					<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan!=null
	        					and ${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan!=null">
	        				<choose>
	        				<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case?contains("DATE")>.equal(${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan)<#else>==${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan</#if>">
	        					TA.${col.code} =${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case=="DECIMAL">,jdbcType=DECIMAL<#elseif col.type?upper_case=="DOUBLE">,jdbcType=DOUBLE<#elseif col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#else></#if>}
	        				</when>
	        				<otherwise>
		        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse><</#noparse> TA.${col.code} ]]>
	        				<choose><when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case?contains("DATE")>.after(${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan)<#else>>${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan</#if>"> or </when><otherwise> and </otherwise></choose>
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse>></#noparse> TA.${col.code}]]>
	        				</otherwise>
	        				</choose>
	        			</when>
    					<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan!=null">
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse><</#noparse> TA.${col.code}]]>
	        			</when>
    					<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan!=null">
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse>></#noparse> TA.${col.code}]]>
	        			</when>
	        			<otherwise>
	        				TA.${col.code} =${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#if col.type?upper_case=="DECIMAL">,jdbcType=DECIMAL<#elseif col.type?upper_case=="DOUBLE">,jdbcType=DOUBLE<#elseif col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#else></#if>}
	        			</otherwise>
    				</choose>
			</if>
			<if test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Mults!=null and ${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Mults.length>0"> and  
	        		TA.${col.code} in <foreach item="item" index="index" collection="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Mults" open="(" separator="," close=")"><#noparse>#{</#noparse>item<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DECIMAL">,jdbcType=DECIMAL<#elseif col.type?upper_case=="DOUBLE">,jdbcType=DOUBLE<#else></#if>}</foreach>
			</if>
			<if test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Str!=null"> and  
	        		TA.${col.code} like concat('%','<#noparse>#</#noparse>{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Str}','%')
			</if>
		<#else>
		<if test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=null"> and  
        		TA.${col.code} =${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DECIMAL">,jdbcType=DECIMAL<#elseif col.type?upper_case=="DOUBLE">,jdbcType=DOUBLE<#else></#if>}
		</if>
		</#if>
<#if (col_index=0 && !(code?starts_with("SQLVW_") || code?starts_with("SQLVVW_")))> --></#if><#else></#if>
</#if></#list>
	</if>
	
	<include refid="isNullColum" />
	</sql>
	
	<sql id="isNullColum">
		<if test="${tab?uncap_first}.isNullCols!=null and ${tab?uncap_first}.isNullCols.size>0">
		<foreach item="item" index="index" collection="${tab?uncap_first}.isNullCols" open="" separator=" " close=""> and (<#noparse>${</#noparse>item} IS NULL || <#noparse>${</#noparse>item}='')</foreach>
		</if>
	</sql>
	
	<sql id="update">
	<set>
<#list cols as col><#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
	<#if (col.primaryKey && col.inSelfTable)>
		<!-- 主键不作为查询条件传入不更新,
		TA.${col.code}=${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}, 
		-->
	<#else>
		<#if col.inSelfTable>
		<if test="null!=${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>"> 
		<#if colInteger=='true'>
			<choose>
			<when test="null!=${tab?uncap_first}.updateInSqlColType.<@genAttributeByCol colName_ABC='${col.code}'/>">
				TA.${col.code}=TA.${col.code}
				<choose>
					<when test="${tab?uncap_first}.updateInSqlColType.<@genAttributeByCol colName_ABC='${col.code}'/>.equalsIgnoreCase('/')">/</when>
					<when test="${tab?uncap_first}.updateInSqlColType.<@genAttributeByCol colName_ABC='${col.code}'/>.equalsIgnoreCase('-')">-</when>
					<when test="${tab?uncap_first}.updateInSqlColType.<@genAttributeByCol colName_ABC='${col.code}'/>.equalsIgnoreCase('*')">*</when>
					<otherwise>+</otherwise>
				</choose>
				${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}
			</when>
			<otherwise>
				TA.${col.code}=${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}
			</otherwise>
			</choose>, 
		</if>
		<#else>
			TA.${col.code}=${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#else></#if>}, 
		</if>
		</#if>
		<#else><#break>
		</#if>
	</#if>
</#list>
	</set>
	</sql>
</#if>

<#if (isView)>
<#if (code?starts_with("SQLVW_") || code?starts_with("SQLVVW_"))>
<mapper namespace="${packname}.mapper.${tab}Mapper">
	<select id="getResultForSelectParamBySearch" parameterType="String" resultType="Map">
		select <#noparse>$</#noparse>{selectParam} TA
		<where> 
		<include refid="qualification" />
		</where>
		<include refid="groupbys" />
		<include refid="orderbys" />	
	</select>
	
	<select id="getResultForSelectParam" parameterType="String" resultType="Map">
		select <#noparse>$</#noparse>{selectParam} 
	</select>
	
	<select id="get${tab}ByPrimaryKey" parameterType="${packname}.entity.${tab}" resultMap="${tab?lower_case}Mapper">
		<include refid="selectSql" />
		where 
		<#list cols as col>
			<#if (col.primaryKey && col.inSelfTable) >
				<#if (col_index=0)>
					TA.${col.code}=${"#"}{${tab?uncap_first}.<#list cols as colEntity><#if (colEntity_index=col_index)><@genAttributeByCol colName_ABC='${colEntity.code}'/></#if></#list>}
				</#if>
			</#if>
		</#list>
	</select>
		
	<select id="getAll${tab}BySearch" parameterType="${packname}.entity.${tab}" resultMap="${tab?lower_case}Mapper">
		<include refid="selectSql" /> 
		<where> 
		<include refid="qualification" />
		</where>
		<include refid="groupbys" />
		<include refid="orderbys" />
	</select>

	<select id="getAll${tab}BySearchKey" parameterType="List" resultMap="${tab?lower_case}Mapper">
		<include refid="${tab}WithSearchKeyByPage" />
		<include refid="groupbys" />
		<include refid="orderbys" />
	</select>
	
	<select id="getAll${tab}ByPage" parameterType="${packname}.entity.${tab}" resultMap="${tab?lower_case}Mapper">
		<include refid="selectSql" /> 
		<where> 
		<include refid="qualification" />
		</where>
		<include refid="groupbys" />
		<include refid="orderbys" />
		<include refid="pageLimit" />
	</select>
	
	<select id="getCountForPage" parameterType="${packname}.entity.${tab}" resultType="int">
		select count(${tableKeyStr}) from <include refid="table" /> TA 
		<where> 
		<include refid="qualification" />
		</where>
	</select>
	
	<select id="getAll${tab}WithSearchKeyByPage" parameterType="List" resultMap="${tab?lower_case}Mapper">
		<include refid="${tab}WithSearchKeyByPage" />
		<include refid="groupbys" />
		<include refid="orderbys" />
		<include refid="pageLimit" />
	</select>
	
	<select id="getCountWithSearchKeyForPage" parameterType="List" resultType="int">
		select count(${tableKeyStr}) from (
			<include refid="${tab}WithSearchKeyByPage" />
		) TSUM
	</select>
	
	<resultMap id="${tab?lower_case}Mapper" type="${packname}.entity.${tab}">
        <#list cols as col>
        <#assign colShow>${checkColWriteToMapper(col.code)?string}</#assign>
		<#if (colShow=='true' && col.inSelfTable && col.inTableColumn)>
        <#if !col.primaryKey><result property="<@genAttributeByCol colName_ABC='${col.code}'/>" column="${col.code}" />		<!-- ${col.name} -->
        <#else><#if (col.primaryKey && col_index==0)><id property="<@genAttributeByCol colName_ABC='${col.code}'/>" column="${col.code}" />		<!-- ${col.name} --></#if>
        </#if>
        </#if>
        </#list>
    </resultMap>
    
    <sql id="${tab}WithSearchKeyByPage">
		<foreach item="${tab?uncap_first}" index="index" collection="${tab?uncap_first}List" separator="UNION">
		<include refid="selectSql" /> 
		<where> 
			<include refid="qualification" />
		</where>
		</foreach>
	</sql>
	
	<sql id="table">
		${code}
	</sql>

	<sql id="selectColum">
		<choose>
		<when test="${tab?uncap_first}.selectCols!=null and ${tab?uncap_first}.selectCols.size>0">
		<foreach item="item" index="index" collection="${tab?uncap_first}.selectCols" open="" separator="," close=""><#noparse>${</#noparse>item}</foreach>
		</when>
		<otherwise>
			<#list cols as col><#if col.inSelfTable><#if (col_index>0)>,</#if><@rewriteColInMysql colCode_RCIM='${col.code}'/></#if></#list>
		</otherwise>
		</choose>
	</sql>
	
	<sql id="selectSql">
		<#if (code?starts_with("SQLVW_") || code?starts_with("SQLVVW_"))>
		select <include refid="selectColum" /> from <include refid="table" /> TA
		<!--
		select <include refid="selectColum" /> from (${viewSqlQuery}) TA
		  -->
		<#else>	
		select <include refid="selectColum" /> 
		<#list cols as col><#if (!col.primaryKey)><#if (col.comment!?index_of("RADIO")>-1 || col.comment!?index_of("CHECKBOX")>-1)>
<!--			,<include refid="${col.code}_CHN" />	-->
			</#if></#if></#list>
		from <include refid="table" /> TA
	</#if>
	</sql>
	
	<sql id="qualification">
	<if test="null!=${tab?uncap_first}">
	<#list cols as col><#if (col.inSelfTable)>
<#if ((!col.primaryKey && col.inTableColumn) || (col.primaryKey && col.inSelfTable))><#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
<#if (col_index=0 && !(code?starts_with("SQLVW_") || code?starts_with("SQLVVW_")))><!-- </#if><#if col.type?upper_case?contains("VARCHAR") || col.type?upper_case?contains("TEXT")>
		<if test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=null and ${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=''"> and  
			<choose>
				<#if (col.comment!?index_of("RADIO")>-1 || col.comment!?index_of("CHECKBOX")>-1 || col.comment!?index_of("OPTION")>-1)>
				<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.indexOf(',')>=0">
        			TA.${col.code} in <foreach item="item" index="index" collection="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.split(',')" open="(" separator="," close=")"><#noparse>#{item}</#noparse></foreach>
        		</when>
				<#else>
				<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.indexOf(' ')>=0">
        			<foreach item="item" index="index" collection="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.split(' ')" open="(" separator=" " close=")"><if test="index>0"> or </if> TA.${col.code} like concat("%",<#noparse>#{</#noparse>item},"%") </foreach>
        		</when>
        		<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.indexOf('+')>=0">
        			TA.${col.code} like concat('%','<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.replace("+", "%")}','%')
        		</when>
				</#if>
        		<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.startsWith('!')">
        			TA.${col.code} !='<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.replace("!", "")}'
        		</when>
				<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.startsWith('=')">
        			TA.${col.code} ='<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.replace("=", "")}'
        		</when>
        		<otherwise>
        			TA.${col.code} like concat('%','<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.replace("%", "")}','%')
        		</otherwise>
			</choose>
		</if>
		<#elseif colInteger=='true' || col.type?upper_case?contains("DATE")>
			<if test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=null or 
					${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>BetweenType!=null or
    				${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan!=null or
    				${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan!=null"> and  
    				<choose>
    					<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan!=null
	        					and ${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan!=null
	        					and ${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>BetweenType!=null">
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse><</#noparse> TA.${col.code} ]]>
	        				<choose><when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>BetweenType=='and'"> and </when><otherwise> or </otherwise></choose>
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse>></#noparse> TA.${col.code}]]>
	        			</when>
    					<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan!=null
	        					and ${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan!=null">
	        				<choose>
	        				<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case?contains("DATE")>.equal(${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan)<#else>==${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan</#if>">
	        					TA.${col.code} =${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case=="DECIMAL">,jdbcType=DECIMAL<#elseif col.type?upper_case=="DOUBLE">,jdbcType=DOUBLE<#elseif col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#else></#if>}
	        				</when>
	        				<otherwise>
		        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse><</#noparse> TA.${col.code} ]]>
	        				<choose><when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case?contains("DATE")>.after(${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan)<#else>>${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan</#if>"> or </when><otherwise> and </otherwise></choose>
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse>></#noparse> TA.${col.code}]]>
	        				</otherwise>
	        				</choose>
	        			</when>
    					<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan!=null">
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse><</#noparse> TA.${col.code}]]>
	        			</when>
    					<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan!=null">
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse>></#noparse> TA.${col.code}]]>
	        			</when>
	        			<otherwise>
	        				TA.${col.code} =${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#if col.type?upper_case=="DECIMAL">,jdbcType=DECIMAL<#elseif col.type?upper_case=="DOUBLE">,jdbcType=DOUBLE<#elseif col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#else></#if>}
	        			</otherwise>
    				</choose>
			</if>
			<if test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Mults!=null and ${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Mults.length>0"> and  
	        		TA.${col.code} in <foreach item="item" index="index" collection="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Mults" open="(" separator="," close=")"><#noparse>#{</#noparse>item<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DECIMAL">,jdbcType=DECIMAL<#elseif col.type?upper_case=="DOUBLE">,jdbcType=DOUBLE<#else></#if>}</foreach>
			</if>
			<if test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Str!=null"> and  
	        		TA.${col.code} like concat('%','<#noparse>#</#noparse>{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Str}','%')
			</if>
		<#else>
		<if test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=null"> and  
        		TA.${col.code} =${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DECIMAL">,jdbcType=DECIMAL<#elseif col.type?upper_case=="DOUBLE">,jdbcType=DOUBLE<#else></#if>}
		</if>
		</#if>
<#if (col_index=0 && !(code?starts_with("SQLVW_") || code?starts_with("SQLVVW_")))> --></#if><#else></#if>
</#if></#list>
	</if>
	
	<include refid="isNullColum" />
	</sql>
	
	<sql id="isNullColum">
		<if test="${tab?uncap_first}.isNullCols!=null and ${tab?uncap_first}.isNullCols.size>0">
		<foreach item="item" index="index" collection="${tab?uncap_first}.isNullCols" open="" separator=" " close=""> and (<#noparse>${</#noparse>item} IS NULL || <#noparse>${</#noparse>item}='')</foreach>
		</if>
	</sql>
	
	<sql id="update">
	<set>
<#list cols as col><#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
	<#if (col.primaryKey && col.inSelfTable)>
		<!-- 主键不作为查询条件传入不更新,
		TA.${col.code}=${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}, 
		-->
	<#else>
		<#if col.inSelfTable>
		<if test="null!=${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>"> 
		<#if colInteger=='true'>
			<choose>
			<when test="null!=${tab?uncap_first}.updateInSqlColType.<@genAttributeByCol colName_ABC='${col.code}'/>">
				TA.${col.code}=TA.${col.code}
				<choose>
					<when test="${tab?uncap_first}.updateInSqlColType.<@genAttributeByCol colName_ABC='${col.code}'/>.equalsIgnoreCase('/')">/</when>
					<when test="${tab?uncap_first}.updateInSqlColType.<@genAttributeByCol colName_ABC='${col.code}'/>.equalsIgnoreCase('-')">-</when>
					<when test="${tab?uncap_first}.updateInSqlColType.<@genAttributeByCol colName_ABC='${col.code}'/>.equalsIgnoreCase('*')">*</when>
					<otherwise>+</otherwise>
				</choose>
				${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}
			</when>
			<otherwise>
				TA.${col.code}=${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}
			</otherwise>
			</choose>, 
		</if>
		<#else>
			TA.${col.code}=${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#else></#if>}, 
		</if>
		</#if>
		<#else><#break>
		</#if>
	</#if>
</#list>
	</set>
	</sql>	
<#else>
<mapper namespace="${mapperpackatename}.${tab}ExtendViewMapper">

	<select id="get${tab}ByPrimaryKeyWithJoin" parameterType="${packname}.entity.extend.${tab}Extend" resultMap="${tab?lower_case}ExtendMapper">
		<include refid="selectWithJoinSql" />
		where 
		<#list cols as col>
			<#if (col.primaryKey && col.inSelfTable)>
				<#if (col_index=0)>
					TA.${col.code}=<#noparse>#{</#noparse>${tab?uncap_first}.<#list cols as col><#if (col.primaryKey && col.inSelfTable)><@genAttributeByCol colName_ABC='${col.code}'/><#break></#if></#list>}
				</#if>
			</#if>
		</#list>
	</select>

	<select id="getAll${tab}BySearchWithJoin" parameterType="${packname}.entity.extend.${tab}Extend" resultMap="${tab?lower_case}ExtendMapper">
		<include refid="selectWithJoinSql" />
		<where> 
		<include refid="qualification" />
		</where>
		<include refid="groupbys" />
		<include refid="orderbys" />
	</select>
	
	<select id="getAll${tab}BySearchKeyWithJoin" parameterType="List" resultMap="${tab?lower_case}ExtendMapper">
		<include refid="${tab}ByPageAndSearchKeyWithJoin" />
		<include refid="groupbys" />
		<include refid="orderbys" />
	</select>

	<select id="getAll${tab}ByPageWithJoin" parameterType="${packname}.entity.extend.${tab}Extend" resultMap="${tab?lower_case}ExtendMapper">
		<include refid="selectWithJoinSql" />
		<where> 
		<include refid="qualification" />
		</where>
		<include refid="groupbys" />
		<include refid="orderbys" />
		<include refid="pageLimit" />
	</select>

	<select id="getCountForPageWithJoin" parameterType="${packname}.entity.extend.${tab}Extend" resultType="int">
		select count(${tableKeyStr}) from <include refid="view" /> TA
		<where> 
		<include refid="qualification" />
		</where>
	</select>

	<sql id="${tab}ByPageAndSearchKeyWithJoin">
		<foreach item="${tab?uncap_first}" index="index" collection="${tab?uncap_first}ExtendList" separator="UNION">
		<include refid="selectWithJoinSql" />
		<where> 
			<include refid="qualification" />
		</where>
		</foreach>
	</sql>
	
	<select id="getAll${tab}ByPageAndSearchKeyWithJoin" parameterType="List" resultMap="${tab?lower_case}ExtendMapper">
		<include refid="${tab}ByPageAndSearchKeyWithJoin" />
		<include refid="groupbys" />
		<include refid="orderbys" />
		<include refid="pageLimit" />
	</select>
	
	<select id="getCountForPageAndSearchKeyWithJoin" parameterType="List" resultType="int">
		select count(${tableKeyStr}) from (
		<include refid="${tab}ByPageAndSearchKeyWithJoin" />
		) TSUM
	</select>

	<sql id="selectColum">
		<choose>
		<when test="${tab?uncap_first}.selectCols!=null and ${tab?uncap_first}.selectCols.size>0">
		<foreach item="item" index="index" collection="${tab?uncap_first}.selectCols" open="" separator="," close=""><#noparse>${</#noparse>item}</foreach>
		</when>
		<otherwise>
			<#list cols as col><#if col.inSelfTable><#if (col_index>0)>,</#if><@rewriteColInMysql colCode_RCIM='${col.code}'/></#if></#list>
			<#assign tmpViewTable="">
		<#list joinTables as jt>
			<include refid="${jt.joinOnColume?lower_case}_JOIN_${jt.tableCode}_SELECT_SHOW" />
			<#assign viewTable><#if (jt_index>0)>,</#if>${jt.tableName}</#assign>
			<#assign tmpViewTable=tmpViewTable + viewTable>
		</#list>
		</otherwise>
		</choose>
	</sql>
	
	<sql id="selectWithJoinSql">
		select <include refid="selectColum" />
 		<!-- ${name} 关联 ${tmpViewTable} 联合查询 -->  
 		 from <include refid="view" />  TA
	</sql>

	<!-- 输出创建视图sql语句 -->	
<#list joinTableSql?keys as testKey>
	<sql id="${testKey}">	<!-- ${joinTableSql[testKey].name} -->	
		${joinTableSql[testKey].sql}
	</sql>
</#list>

<#list joinTables as jt>
	<!-- BEGIN ${jt.joinOnColume?lower_case}_JOIN_${jt.tableCode} -->
	<sql id="${jt.joinOnColume?lower_case}_JOIN_${jt.tableCode}_SELECT_SHOW">	
		<#list jt.colume as jtc>
		,${jt.columeAlias[jtc_index]}
		</#list>	 
	</sql>
    <!-- END ${jt.joinOnColume?lower_case}_JOIN_${jt.tableCode} -->
    
</#list>
	<!-- END 字段关联检索 -->

<#if (hasExtendColumn && joinTables!?size>0)>	
	<sql id="qualification">
	<if test="null!=${tab?uncap_first}">
		<#list cols as col>
<#if ((!col.primaryKey && col.inTableColumn) || (col.primaryKey && col.inSelfTable))><#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
<#if (col_index=0 && !(code?starts_with("SQLVW_") || code?starts_with("SQLVVW_")))><!-- </#if><#if col.type?upper_case?contains("VARCHAR") || col.type?upper_case?contains("TEXT")>
		<if test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=null and ${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=''"> and  
			<choose>
				<#if (col.comment!?index_of("RADIO")>-1 || col.comment!?index_of("CHECKBOX")>-1 || col.comment!?index_of("OPTION")>-1)>
				<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.indexOf(',')>=0">
        			TA.${col.code} in <foreach item="item" index="index" collection="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.split(',')" open="(" separator="," close=")"><#noparse>#{item}</#noparse></foreach>
        		</when>
				<#else>
				<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.indexOf(' ')>=0">
        			<foreach item="item" index="index" collection="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.split(' ')" open="(" separator=" " close=")"><if test="index>0"> or </if> TA.${col.code} like concat('%','<#noparse>#{</#noparse>item}','%')</foreach>
        		</when>
        		<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.indexOf('+')>=0">
        			TA.${col.code} like concat('%','<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.replace("+", "%")}','%')
        		</when>
				</#if>
        		<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.startsWith('!')">
        			TA.${col.code} !='<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.replace("!", "")}'
        		</when>
				<when test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.startsWith('=')">
        			TA.${col.code} ='<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.replace("=", "")}'
        		</when>
        		<otherwise>
        			TA.${col.code} like concat('%','<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>.replace("%", "")}','%')
        		</otherwise>
			</choose>
		</if>
		<#elseif colInteger=='true' || col.type?upper_case?contains("DATE")>
			<if test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=null or 
					${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>BetweenType!=null or
    				${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan!=null or
    				${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan!=null"> and  
    				<choose>
    					<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan!=null
	        					and ${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan!=null
	        					and ${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>BetweenType!=null">
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse><</#noparse> TA.${col.code} ]]>
	        				<choose><when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>BetweenType=='and'"> and </when><otherwise> or </otherwise></choose>
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse>></#noparse> TA.${col.code}]]>
	        			</when>
    					<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan!=null
	        					and ${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan!=null">
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse><</#noparse> TA.${col.code} ]]>
	        				<choose><when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>BetweenType>${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan"> or </when><otherwise> and </otherwise></choose>
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse>></#noparse> TA.${col.code}]]>
	        			</when>
    					<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan!=null">
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>GreaterThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse><</#noparse> TA.${col.code}]]>
	        			</when>
    					<when test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan!=null">
	        				<![CDATA[${"#"}{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>LessThan<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP</#if>} <#noparse>></#noparse> TA.${col.code}]]>
	        			</when>
	        			<otherwise>
	        				TA.${col.code} =${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#if col.type?upper_case=="DECIMAL">,jdbcType=DECIMAL<#elseif col.type?upper_case=="DOUBLE">,jdbcType=DOUBLE<#elseif col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#else></#if>}
	        			</otherwise>
    				</choose>
			</if>
			<if test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Mults!=null and ${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Mults.length>0"> and  
	        		TA.${col.code} in <foreach item="item" index="index" collection="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Mults" open="(" separator="," close=")"><#noparse>#{</#noparse>item<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DECIMAL">,jdbcType=DECIMAL<#elseif col.type?upper_case=="DOUBLE">,jdbcType=DOUBLE<#else></#if>}</foreach>
			</if>
			<if test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>MultsNot!=null and ${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>MultsNot.length>0"> and  
	        		TA.${col.code} not in <foreach item="item" index="index" collection="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>MultsNot" open="(" separator="," close=")"><#noparse>#{</#noparse>item<#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DECIMAL">,jdbcType=DECIMAL<#elseif col.type?upper_case=="DOUBLE">,jdbcType=DOUBLE<#else></#if>}</foreach>
			</if>
			<if test="${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Str!=null"> and  
	        		TA.${col.code} like concat('%','<#noparse>#</#noparse>{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Str}','%')
			</if>
		<#else>
		<if test="${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=null"> and  
        		TA.${col.code} =${"#"}{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#if col.type?upper_case=="DATE">,jdbcType=DATE<#elseif col.type?upper_case=="TIMESTAMP">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DATETIME">,jdbcType=TIMESTAMP<#elseif col.type?upper_case=="DECIMAL">,jdbcType=DECIMAL<#elseif col.type?upper_case=="DOUBLE">,jdbcType=DOUBLE<#else></#if>}
		</if>
		</#if>
<#if (col_index=0 && !(code?starts_with("SQLVW_") || code?starts_with("SQLVVW_")))> --></#if><#else></#if>
</#list>
	</if>
	</sql>
</#if>

	<sql id="view">
		${viewCode}
		<!--
		${viewSqlQuery!}
		-->
		<!--
		${viewSqlQueryDetail!}
		-->
	</sql>
	
	<!-- BEGIN 字段关联检索 -->
	<resultMap id="${tab?lower_case}ExtendMapper" type="${packname}.entity.extend.${tab}Extend">
        <#list cols as col>
        <#assign colShow>${checkColWriteToMapper(col.code)?string}</#assign>
		<#if (colShow=='true' && col.inTableColumn)>
        <#if !col.primaryKey><result property="<@genAttributeByCol colName_ABC='${col.code}'/>" column="${col.code}" />		<!-- ${col.name} -->
        <#else><#if (col.primaryKey && col.inSelfTable && col_index==0)><id property="<@genAttributeByCol colName_ABC='${col.code}'/>" column="${col.code}" />		<!-- ${col.name} --></#if>
        </#if>
        </#if>
        </#list>
    </resultMap>
</#if> 
</#if> 

	<sql id="orderbys">
		<if test="null!= ${tab?uncap_first} and null!= ${tab?uncap_first}.orderBys and ${tab?uncap_first}.orderBys.size>0">
			order by
				<#noparse>${</#noparse>${tab?uncap_first}.orderBys.TABLEORDERBYSTR}
		</if>
	</sql>
	
	<sql id="groupbys">
		<if test="null!= ${tab?uncap_first} and null!= ${tab?uncap_first}.groupBys and ${tab?uncap_first}.groupBys!=''">
			group by
				<#noparse>${</#noparse>${tab?uncap_first}.groupBys}
		</if>
	</sql>
	
	<!-- BEGIN 字典字段转换检索 -->
	<#list cols as col><#if (!col.primaryKey)><#if (col.comment!?index_of("RADIO")>-1 || col.comment!?index_of("CHECKBOX")>-1)><#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
	<sql id="${col.code}_CHN"><#assign tcomment="${col.comment!?replace('.*RADIO_','','r')}" /><#assign tcomment="${tcomment!?replace('.*CHECKBOX_','','r')}" /><#assign tcomment="${tcomment!?replace('.*OPTION_','','r')}" />
        <#if colInteger=='true'>
        <![CDATA[CASE TA.${col.code}<#list tcomment?split(",") as s><#if (s?contains(":"))> WHEN ${s?split(":")?first} THEN '${s?split(":")?last}' <#else> ELSE '${s?trim}' </#if></#list>END AS ${col.code}_CHNVAL]]>
        <#else>
        <![CDATA[CASE TA.${col.code}<#list tcomment?split(",") as s><#if (s?contains(":"))> WHEN "${s?split(":")?first}" THEN '${s?split(":")?last}' <#else> ELSE '${s?trim}' </#if></#list>END AS ${col.code}_CHNVAL]]>
        </#if>
	</sql>
	</#if></#if></#list>
	<!-- END 字典字段转换检索 -->
	
	<sql id="startSql">
		select * from (
	</sql>

	<sql id="endSql">
		 ) b <include refid="pageLimit" />
	</sql>

	<sql id="pageLimit">
		<![CDATA[ limit ${"$"}{startIndex},${"$"}{endIndex}]]>
	</sql>

</mapper>