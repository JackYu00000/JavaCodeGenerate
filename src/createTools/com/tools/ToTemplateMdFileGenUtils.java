package com.tools;

import com.tools.entity.CreateUtils;
import com.tools.entity.DBColumn;
import com.tools.entity.DBTable;
import com.tools.entity.ftl.FtlJoinTable;
import com.tools.entity.ftl.JoinTable;
import org.apache.commons.lang3.StringUtils;

public class ToTemplateMdFileGenUtils {

	public static void genTableStructure(StringBuffer sbContent,
										 DBTable table) {
		sbContent.append("#### 表: ").append(table.getCode()).append("  ").append(table.getName()).append("\n");
		sbContent.append("##### 数据说明").append("\n");
		sbContent.append("| Name           | Code   | Data Type   | Mandatory | Default | Comment |").append("\n");
		sbContent.append("|----------------|--------|--------------|--------|--------|---------|").append("\n");

		/**
		 * 数据表
		 */
		genTable(sbContent, table);
		sbContent.append("\n");
		sbContent.append("##### 查询sql").append("\n");

		StringBuffer selSqlSB = new StringBuffer();
		selSqlSB.append("> select \r").append(" ");
		int dbcRow = 0;
		for(DBColumn dbc:table.getCols()){
			if(dbc.isInSelfTable()){
				if(dbcRow>0) {
					selSqlSB.append(",");
				}
				selSqlSB.append(dbc.getCode());
				dbcRow += 1;
			}
		}
		selSqlSB.append("\r from ").append(table.getCode());

		sbContent.append(selSqlSB.toString()).append("\n");
		sbContent.append("\n").append("\n").append("\n").append("\n");
		/**
		 * 扩展字段
		 */
//		genTableExpend(sbContent, table);
		
		/**
		 * ref 关联字段
		 */
//		genTableRef(sbContent,table);
		/**
		 * 生成视图
		 */
//		genTableView(sbContent,table);

	}

	/**
	 * database api 中使用
	 * @param sbContent
	 * @param table
	 */
	public static void genTableExpend(StringBuffer sbContent, DBTable table) {
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_expend\">");
		sbContent.append("<table class=\"table table-bordered table-striped\">");
		sbContent.append("<thead><tr><th style=\"width: 100px;\">名称</th><th style=\"width: 50px;\">字段名</th><th style=\"width: 80px;\">接口字段名</th><th style=\"width: 50px;\">类型</th><th>描述</th></tr></thead>");
		sbContent.append("<tbody>");
		for(DBColumn colume:table.getCols()){
			if(!colume.isInSelfTable() && colume.isInTableColumn()){
				sbContent.append("<tr>");
				sbContent.append("<td>").append(colume.getName()).append("</td>");
				sbContent.append("<td>").append(colume.getCode()).append("</td>");
				sbContent.append("<td>").append(CreateUtils.getCol(colume.getCode())).append("</td>");
				sbContent.append("<td>").append(colume.getType()).append("</td>");
				sbContent.append("<td>").append(colume.getComment()).append("</td>");
				sbContent.append("</tr>");
			}
		}
		for(DBColumn colume:table.getChildCols()){
			sbContent.append("<tr>");
			sbContent.append("<td>").append(colume.getName()).append("</td>");
			sbContent.append("<td>").append(CreateUtils.getCol(colume.getCode())).append("</td>");
			sbContent.append("<td><a href=\"#").append(colume.getType().toLowerCase()).append("\">").append(colume.getType()).append("</a></td>");
			sbContent.append("<td>").append(colume.getComment()).append("</td>");
			sbContent.append("</tr>");
		}
		sbContent.append("</tbody>");
		sbContent.append("</table></div>");
	}

	/**
	 * database api 中使用
	 * @param sbContent
	 * @param table
	 */
	public static void genTableRef(StringBuffer sbContent, DBTable table) {
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_ref\">");
		sbContent.append("<table class=\"table table-bordered table-striped\">");
		sbContent.append("<thead><tr><th style=\"width: 100px;\">名称</th><th style=\"width: 80px;\">REF 字段名</th><th style=\"width: 50px;\">类型</th><th>描述</th></tr></thead>");
		sbContent.append("<tbody>");
		for(DBColumn colume:table.getRefCols()){
			if(colume.isInSelfTable() && !colume.isPrimaryKey()){
				sbContent.append("<tr>");
				sbContent.append("<td>").append(colume.getName()).append("</td>");
				sbContent.append("<td>").append(CreateUtils.getCol(colume.getCode())).append("</td>");
				sbContent.append("<td>").append(colume.getType()).append("</td>");
				sbContent.append("<td>").append(colume.getComment()).append("</td>");
				sbContent.append("</tr>");
			}
		}
//		for(DBColumn colume:table.getChildCols()){
//			sbContent.append("<tr>");
//			sbContent.append("<td>").append(colume.getName()).append("</td>");
//			sbContent.append("<td>").append(CreateUtils.getCol(colume.getCode())).append("</td>");
//			sbContent.append("<td><a href=\"#").append(colume.getType().toLowerCase()).append("\">").append(colume.getType()).append("</a></td>");
//			sbContent.append("<td>").append(colume.getComment()).append("</td>");
//			sbContent.append("</tr>");
//		}
		sbContent.append("</tbody>");
		sbContent.append("</table></div>");
	}

	/**
	 * database api 中使用
	 * @param sbContent
	 * @param table
	 */
	public static void genTableView(StringBuffer sbContent,DBTable table){
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_view\">");
		if(null!=table.getJoinTables() && table.getJoinTables().size()>0){
			sbContent.append("视图名称:").append("VW_" + CreateUtils.getTab(table.getCode()).toUpperCase()).append("<br/>");
			sbContent.append("关联数据表:").append("<br/>");
			sbContent.append("<ul>");
			for(FtlJoinTable jt:table.getJoinTables()){
				sbContent.append("<li>");
				if(StringUtils.startsWith(jt.getTableCode(), "SQLVW_") || StringUtils.startsWith(jt.getTableCode(), "SQLVVW_")){
					sbContent.append("<a href=\"/pages/docs/databaseapi_view.html").append(CreateUtils.getTab(jt.getTableCode()).toLowerCase()).append("' />\">").append(jt.getTableName()).append("</a>").append("<br/>");
				}else{
					sbContent.append("<a href=\"#").append(CreateUtils.getTab(jt.getTableCode()).toLowerCase()).append("\">").append(jt.getTableName()).append("</a>").append("<br/>");
				}
				sbContent.append("检索列表:").append(jt.getColumeName()).append("<br/>");
				sbContent.append("</li>");
			}
			sbContent.append("</ul>");
			sbContent.append("</div>");
		}else{
			sbContent.append("视图名称:无").append("<br/>");
			sbContent.append("关联数据表:无").append("<br/>");
			sbContent.append("</div>");
			
		}
	}
	/**
	 * database api 中使用
	 * @param sbContent
	 * @param table
	 */
	public static void genTable(StringBuffer sbContent, DBTable table) {

		for(DBColumn colume:table.getCols()){
			if(colume.isInSelfTable()){
				sbContent.append("|").append(colume.getName());
				sbContent.append("|").append(colume.getCode());
				sbContent.append("|").append(colume.getType());
//				sbContent.append("|").append(CreateUtils.getCol(colume.getCode()));
				if(colume.isPrimaryKey() || colume.getMandatory().equalsIgnoreCase("1")) {
                    sbContent.append("|").append("**必填**");
                } else {
					sbContent.append("|").append("");
                }
				sbContent.append("|").append(colume.getDefaultValue());
				if(StringUtils.startsWithIgnoreCase(colume.getComment(), "RADIO") ||
						StringUtils.startsWithIgnoreCase(colume.getComment(), "CHECKBOX")){
					sbContent.append("|").append("枚举赋值：*").append(DBPdmUtils.getKey(table.getCode() + "_" + colume.getCode())).append("*\n").append(colume.getComment().replace("RADIO_", "").replace("CHECKBOX_", "").replace(":", "&nbsp;:&nbsp;").replace(",", ",\n"));
				}else if(StringUtils.startsWithIgnoreCase(colume.getComment(), "OPTION")){
					if(null!=colume.getJoinTables()){
						for(JoinTable jt:colume.getJoinTables()){
							sbContent.append("|").append("字典值字段：").append(jt.getJoinTableCode());
						}
					}

				}else{
					sbContent.append("|").append(colume.getComment());
				}
				sbContent.append("|").append("\n");
			}
		}
		for(DBColumn colume:table.getChildCols()){
			if(colume.isInSelfTable()){

				sbContent.append("|").append(colume.getName());
				sbContent.append("|").append(colume.getCode());
				sbContent.append("|").append(colume.getType());
//				sbContent.append("|").append(CreateUtils.getCol(colume.getCode()));
				if(colume.isPrimaryKey() || colume.getMandatory().equalsIgnoreCase("1")) {
                    sbContent.append("|").append("**必填**");
                } else {
					sbContent.append("|").append("");
                }
				sbContent.append("|").append(colume.getDefaultValue());
				if(StringUtils.startsWithIgnoreCase(colume.getComment(), "RADIO") ||
						StringUtils.startsWithIgnoreCase(colume.getComment(), "CHECKBOX")){
					sbContent.append("|").append("枚举赋值：*").append(DBPdmUtils.getKey(table.getCode() + "_" + colume.getCode())).append("*\n").append(colume.getComment().replace("RADIO_", "").replace("CHECKBOX_", "").replace(":", "&nbsp;:&nbsp;").replace(",", ",\n"));
				}else if(StringUtils.startsWithIgnoreCase(colume.getComment(), "OPTION")){
					for(JoinTable jt:colume.getJoinTables()){
						sbContent.append("|").append("字典值字段：").append(jt.getJoinTableCode());
					}
				}else{
					sbContent.append("|").append(colume.getComment());
				}
				sbContent.append("|").append("\n");
			}
		}

	}

	/**
	 * database api 中使用
	 * @param sbContent
	 * @param table
	 * @param tabEntityName
	 */
	public static void genJavaCodeMd(StringBuffer sbContent, DBTable table,
								   String tabEntityName) {
		int i;
		sbContent.append("## 表: ").append(table.getCode()).append("  对象:").append(CreateUtils.getTab(table.getCode())).append("  ").append(table.getName()).append("\n");
		sbContent.append("#### 引用\n");
		sbContent.append("```\n");
		sbContent.append(CreateUtils.getTab(table.getCode())).append(" ").append(tabEntityName).append(" = new ").append(CreateUtils.getTab(table.getCode())).append("();\n");
		sbContent.append("\n");
		sbContent.append("Service\n");
		sbContent.append("@Resource\n");
		sbContent.append("private ").append(CreateUtils.getTab(table.getCode())).append("Service ").append(tabEntityName).append("Svc;\n");
		sbContent.append("\n");
		sbContent.append("Mapper\n");
		sbContent.append("@Resource\n");
		sbContent.append("private ").append(CreateUtils.getTab(table.getCode())).append("Mapper ").append(tabEntityName).append("Mapper;\n");
		sbContent.append("```\n");

		sbContent.append("排序\n");
		sbContent.append("```\n");
		sbContent.append("代码实现\n");
		sbContent.append(tabEntityName);
		sbContent.append(".setOrderBys(Utils.checkOrderBys(new LinkedHashMap<String, String>(){ \n");
		sbContent.append("	private static final long serialVersionUID = 1L;\n{\n");
		for(DBColumn colume:table.getCols()){
			if(colume.isInTableColumn()){
				sbContent.append("	put(\"");
				sbContent.append(CreateUtils.getCol(colume.getCode()));
				sbContent.append("\",\"desc\");\n");
			}
		}
		sbContent.append("}},");
		sbContent.append(tabEntityName);
		sbContent.append(".");
		sbContent.append(tabEntityName);
		sbContent.append("Colum));\n");
		sbContent.append("\n");
		sbContent.append("页面传参\n");
		sbContent.append("if(null != ").append(tabEntityName).append(".getOrderBys() && ").append(tabEntityName).append(".getOrderBys().size()>0){\n");
		sbContent.append("	").append(tabEntityName).append(".setOrderBys(Utils.checkOrderBys(").append(tabEntityName).append(".getOrderBys(),").append(tabEntityName).append(".").append(tabEntityName).append("Colum));\n");
		sbContent.append("}\n");
		sbContent.append("```\n");

		sbContent.append("分组\n");
		sbContent.append("```\n");
		sbContent.append(tabEntityName);
		sbContent.append(".setGroupBys(StringUtils.join(\n");
		sbContent.append("	new String[]{\n");
		i=0;
		for(DBColumn colume:table.getCols()){
			if(colume.isInTableColumn()){
				sbContent.append("		");
				if(i>0) {
					sbContent.append(",");
				}
				sbContent.append(tabEntityName);
				sbContent.append(".");
				sbContent.append(tabEntityName);
				if(colume.inSelfTable) {
					sbContent.append("Colum.get(\"");
				} else {
					sbContent.append("ColumJoin.get(\"");
				}
				sbContent.append(CreateUtils.getCol(colume.getCode()));
				sbContent.append("\")\n");
				i++;
			}
		}
		sbContent.append("		},\",\"));");
		sbContent.append("\n```\n");

		sbContent.append("检索列\n");
		sbContent.append("```\n");
		sbContent.append("List<String> searchColList = new ArrayList<String>();\n");
		for(DBColumn colume:table.getCols()){
			if(colume.isInTableColumn()){
				sbContent.append("searchColList.add(");
				sbContent.append(tabEntityName);
				sbContent.append(".");
				sbContent.append(tabEntityName);
				if(colume.inSelfTable)
					sbContent.append("Colum.get(\"");
				else
					sbContent.append("ColumJoin.get(\"");
				sbContent.append(CreateUtils.getCol(colume.getCode()));
				sbContent.append("\"));\n");
			}
		}
		sbContent.append(tabEntityName);
		sbContent.append(".setSearchCols(searchColList);");
		sbContent.append("\n```\n");

		sbContent.append("查询列\n");
		sbContent.append("```\n");
		sbContent.append("List<String> selectColList = new ArrayList<String>();\n");
		for(DBColumn colume:table.getCols()){
			if(colume.isInTableColumn()){
				sbContent.append("selectColList.add(");
				sbContent.append(tabEntityName);
				sbContent.append(".");
				sbContent.append(tabEntityName);
				if(colume.inSelfTable)
					sbContent.append("Colum.get(\"");
				else
					sbContent.append("ColumJoin.get(\"");
				sbContent.append(CreateUtils.getCol(colume.getCode()));
				sbContent.append("\"));\n");
			}
		}
		sbContent.append(tabEntityName);
		sbContent.append(".setSearchCols(selectColList);");
		sbContent.append("\n```\n");
	}
}
