package com.tools;

import com.google.gson.Gson;
import com.tools.entity.CreateUtils;
import com.tools.entity.DBColumn;
import com.tools.entity.DBTable;
import com.tools.entity.ftl.FtlJoinTable;
import com.tools.entity.ftl.JoinTable;
import com.tools.entity.pdm.DBView;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class ToTemplateFileGenUtils {
	
	/**
	 * 生成table 说明excel字段
	 * @param titleNoBold
	 * @param sheetMap
	 * @param table
	 * @param isSelf
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	static void genTableExcelSheet(WritableCellFormat titleNoBold,
			Map<String, WritableSheet> sheetMap, DBTable table,boolean isSelf)
			throws WriteException, RowsExceededException {
		WritableSheet sheet;
		if(StringUtils.isBlank(table.getTableGroupOid())){
			sheet = sheetMap.get(table.getObjectID());
		}else{
			sheet = sheetMap.get(table.getTableGroupOid());
		}
		int beginRow = sheet.getRows();
		int beginColumn = 0;
		int rowSpan = 0;
		if(isSelf){
			rowSpan = DBPdmUtils.getDBTableSelfColumnSize(table);
		}else{
			rowSpan = DBPdmUtils.getDBTableInTableColumnSize(table);
		}
		sheet.mergeCells(0, beginRow, 0, beginRow + rowSpan);
		
		sheet.addCell(new Label(beginColumn,beginRow,table.getCode() + "\r" + table.getName(),titleNoBold));
		StringBuffer jtSB = new StringBuffer();
		for(FtlJoinTable jt:table.getJoinTables()){
			if(!isSelf){
				jtSB.append(jt.getTableCode() + ":" + jt.getTableName() + "\r");
			}else{
				if(StringUtils.startsWith(jt.getTableCode(), Start.pdmDBTablePrefixStr)){
					jtSB.append(jt.getTableCode() + ":" + jt.getTableName() + "\r");
				}
			}
		}
		sheet.mergeCells(beginColumn + 1, beginRow, beginColumn + 1, beginRow + rowSpan);
		sheet.addCell(new Label(beginColumn + 1,beginRow,jtSB.toString(),titleNoBold));
		int dbcRow = 0;
		StringBuffer selSqlSB = new StringBuffer();
		selSqlSB.append("select \r").append(" ");
		for(DBColumn dbc:table.getCols()){
			if(dbc.isInSelfTable()){
				if(dbcRow>0)
					selSqlSB.append(",");
				selSqlSB.append(dbc.getCode());
				sheet.addCell(new Label(beginColumn + 2,beginRow + dbcRow,dbc.getCode(),titleNoBold));
				sheet.addCell(new Label(beginColumn + 3,beginRow + dbcRow,dbc.getName(),titleNoBold));
				sheet.addCell(new Label(beginColumn + 4,beginRow + dbcRow,dbc.getType(),titleNoBold));
				sheet.addCell(new Label(beginColumn + 5,beginRow + dbcRow,dbc.getComment(),titleNoBold));
				dbcRow += 1;
			}else{
				if(!isSelf && dbc.isInTableColumn()){
					if(dbcRow>0)
						selSqlSB.append(",");
					selSqlSB.append(dbc.getCode());
					sheet.addCell(new Label(beginColumn + 2,beginRow + dbcRow,dbc.getCode(),titleNoBold));
					sheet.addCell(new Label(beginColumn + 3,beginRow + dbcRow,dbc.getName(),titleNoBold));
					sheet.addCell(new Label(beginColumn + 4,beginRow + dbcRow,dbc.getType(),titleNoBold));
					sheet.addCell(new Label(beginColumn + 5,beginRow + dbcRow,dbc.getComment(),titleNoBold));
					dbcRow += 1;
				}
			}
		}
		selSqlSB.append("\r from ").append(table.getCode());
		sheet.mergeCells(beginColumn + 6, beginRow, beginColumn + 6, beginRow + rowSpan);
		
		sheet.addCell(new Label(beginColumn + 6,beginRow,selSqlSB.toString(),titleNoBold));
	}
	
	/**
	 * 生成创建视图sql语句
	 * @param view
	 * @return
	 */
	static String genPdmViewSql(DBView view){
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("drop VIEW if exists ");
		sbSql.append(view.getCode());
		sbSql.append(";\n");
		sbSql.append("CREATE VIEW ");
		sbSql.append(view.getCode());
		sbSql.append(" AS ");
		sbSql.append(view.getViewSqlQuery());
		sbSql.append(";\n");
		return sbSql.toString();
	}
	
	/**
	 * 生成主键的json数据格式
	 * @param table
	 * @return
	 */
	public static String getDBTabelPrimaryKeyJsonString(DBTable table){
		StringBuffer pkeyJson = new StringBuffer();
		for(DBColumn dbc :table.getCols()){
			if(dbc.isPrimaryKey()){
				pkeyJson.append("\"").append(CreateUtils.getCol(dbc.getCode()));
				if(StringUtils.equalsIgnoreCase(dbc.getType(), "INT") ||
						StringUtils.equalsIgnoreCase(dbc.getType(), "BIGINT") ||
						StringUtils.equalsIgnoreCase(dbc.getType(), "SMALLINT") ||
						StringUtils.equalsIgnoreCase(dbc.getType(), "MEDIUMINT") ||
						StringUtils.equalsIgnoreCase(dbc.getType(), "BOOL") ||
						StringUtils.equalsIgnoreCase(dbc.getType(), "DECIMAL") ||
						StringUtils.equalsIgnoreCase(dbc.getType(), "TINYINT")) {
                    pkeyJson.append("\":\"" + dbc.getName() + "," + dbc.getType()  + "\"");
                } else {
                    pkeyJson.append("\":\"" + dbc.getName() + "," + dbc.getType() + "\"");
                }
				break;
			}
		}
		return pkeyJson.toString();
	}
	
	public static void writeSection(StringBuffer sbContent,
			Gson gson, DBTable table) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		String tabEntityName = CreateUtils.getTab(table.getCode()).replaceFirst(CreateUtils.getTab(table.getCode()).substring(0, 1),CreateUtils.getTab(table.getCode()).substring(0, 1).toLowerCase());
		sbContent.append("<section id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("\">");
		/**
		 *  Header 开始
		 */
		genHeader(sbContent, table);
		
		/**
		 * 导航栏开始
		 */
		genTabNav(sbContent, table);
		
		/**
		 * tab 内容
		 */
		sbContent.append("<div class=\"tab-content\">");
		/**
		 * 数据表
		 */
		genTable(sbContent, table);
		
		/**
		 * 扩展字段
		 */
		genTableExpend(sbContent, table);
		
		/**
		 * ref 关联字段
		 */
		genTableRef(sbContent,table);
		/**
		 * 生成视图
		 */
		genTableView(sbContent,table);
		/**
		 * 查询参数
		 */
		genSearch(sbContent, table);
		
		/**
		 * Json 示例
		 */
		genJson(sbContent, table,gson);
		
		/**
		 * URL 地址
		 */
		genUrl(sbContent, table);
		
		/**
		 * Example Code
		 */
		genJavaCode(sbContent, table, tabEntityName);
		
		sbContent.append("</div>");
		sbContent.append("</section>\n");
	}

	public static void writeNavMenu(StringBuffer sbDict, DBTable table) {
		sbDict.append("<li");
		sbDict.append("><a href=\"#");
		sbDict.append(CreateUtils.getTab(table.getCode()).toLowerCase());
		sbDict.append("\"><i class=\"icon-remove\"></i><i class=\"icon-chevron-right\"></i> ");
		sbDict.append(table.getName());
		sbDict.append("</a></li>\n");
	}
	/**
	 * database api 中使用
	 * @param sbContent
	 * @param table
	 */
	public static void genHeader(StringBuffer sbContent, DBTable table) {
		sbContent.append("<div class=\"page-header\"><h1>").append(table.getName()).append("说明 <small>[").append(table.getCode()).append("]</small></h1></div>");
		sbContent.append("<h3>数据测试地址</h3>");
		sbContent.append("<ul>");
		sbContent.append("<li>").append("添加一条数据：<a target=\"_blank\" href=\"/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new\">点击添加</a></li>");
		sbContent.append("<li>").append("查看数据列表：<a target=\"_blank\" href=\"/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("\">点击访问</a></li>");
		sbContent.append("</ul>");
	}

	/**
	 * database api 中使用
	 * @param sbContent
	 * @param table
	 * @param tabEntityName
	 */
	public static void genJavaCode(StringBuffer sbContent, DBTable table,
			String tabEntityName) {
		int i;
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_epcode_init\">");
		sbContent.append("<pre class=\"prettyprint linenums\">");
		sbContent.append("Entity<br/>");
		sbContent.append(CreateUtils.getTab(table.getCode())).append(" ").append(tabEntityName).append(" = new ").append(CreateUtils.getTab(table.getCode())).append("();<br/>");
		sbContent.append("<br/>");
        sbContent.append("Service<br/>");
        sbContent.append("@Resource<br/>");
        sbContent.append("private ").append(CreateUtils.getTab(table.getCode())).append("Service ").append(tabEntityName).append("Svc;<br/>");
		sbContent.append("<br/>");
		sbContent.append("Mapper<br/>");
        sbContent.append("@Resource<br/>");
        sbContent.append("private ").append(CreateUtils.getTab(table.getCode())).append("Mapper ").append(tabEntityName).append("Mapper;<br/>");
		sbContent.append("</pre>");
		sbContent.append("</div>");
		
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_epcode_order\">");
		sbContent.append("排序");
		sbContent.append("<pre class=\"prettyprint linenums\">");
		sbContent.append("代码实现<br/>");
		sbContent.append(tabEntityName);
		sbContent.append(".setOrderBys(Utils.checkOrderBys(new LinkedHashMap&lt;String, String&gt;(){ <br/>");
		sbContent.append("	private static final long serialVersionUID = 1L;<br/>{<br/>");
		for(DBColumn colume:table.getCols()){
			if(colume.isInTableColumn()){
				sbContent.append("	put(\"");
				sbContent.append(CreateUtils.getCol(colume.getCode()));
				sbContent.append("\",\"desc\");<br/>");
			}
		}
		sbContent.append("}},");
		sbContent.append(tabEntityName);
		sbContent.append(".");
		sbContent.append(tabEntityName);
		sbContent.append("Colum));<br/>");
		sbContent.append("<br/>");
		sbContent.append("页面传参<br/>");
		sbContent.append("if(null != ").append(tabEntityName).append(".getOrderBys() && ").append(tabEntityName).append(".getOrderBys().size()>0){<br/>");
		sbContent.append("	").append(tabEntityName).append(".setOrderBys(Utils.checkOrderBys(").append(tabEntityName).append(".getOrderBys(),").append(tabEntityName).append(".").append(tabEntityName).append("Colum));<br/>");
		sbContent.append("}");
		sbContent.append("</pre>");
		sbContent.append("</div>");
            
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_epcode_group\">");
		sbContent.append("分组");
		sbContent.append("<pre class=\"prettyprint linenums\">");
		sbContent.append(tabEntityName);
		sbContent.append(".setGroupBys(StringUtils.join(<br/>");
		sbContent.append("	new String[]{<br/>");
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
				sbContent.append("\")<br/>");
				i++;
			}
		}
		sbContent.append("		},\",\"));");
		sbContent.append("</pre>");
		sbContent.append("</div>");
            
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_epcode_skcol\">");
		sbContent.append("检索列");
		sbContent.append("<pre class=\"prettyprint linenums\">");
		sbContent.append("List&lt;String&gt; searchColList = new ArrayList&lt;String&gt;();<br/>");
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
				sbContent.append("\"));<br/>");
			}
		}
		sbContent.append(tabEntityName);
		sbContent.append(".setSearchCols(searchColList);");
		sbContent.append("</pre>");
		sbContent.append("</div>");
		
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_epcode_selcol\">");
		sbContent.append("查询列");
		sbContent.append("<pre class=\"prettyprint linenums\">");
		sbContent.append("List&lt;String&gt; selectColList = new ArrayList&lt;String&gt;();<br/>");
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
				sbContent.append("\"));<br/>");
			}
		}
		sbContent.append(tabEntityName);
		sbContent.append(".setSearchCols(selectColList);");
		sbContent.append("</pre>");
		sbContent.append("</div>");
	}

	/**
	 * database api 中使用
	 * @param sbContent
	 * @param table
	 */
	public static void genUrl(StringBuffer sbContent, DBTable table) {
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_url_view\">");
		sbContent.append("浏览端");
		sbContent.append("<table class=\"table table-bordered table-striped\">");
		sbContent.append("<thead><tr><th style=\"width: 100px;\">功能</th><th style=\"width: 100px;\">地址</th><th style=\"width: 100px;\">JSP</th><th style=\"width: 50px;\">页面</th></tr></thead>");
		sbContent.append("<tbody>");
		sbContent.append("<tr><td>列表</td>");
		sbContent.append("<td>").append("/view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append(" </td>");
		sbContent.append("<td> &lt;c:url value='/view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("' /&gt;</td>");
		sbContent.append("<td> /view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("</td></tr>");
		sbContent.append("<tr><td>添加</td>");
		sbContent.append("<td>").append("/view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new </td>");
		sbContent.append("<td> &lt;c:url value='/view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new' /&gt;</td>");
		sbContent.append("<td> /view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new</td></tr>");
		sbContent.append("<tr><td>展示</td>");
		sbContent.append("<td>").append("/view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show </td>");
		sbContent.append("<td> &lt;c:url value='/view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show' /&gt;</td>");
		sbContent.append("<td> /view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show</td></tr>");
		sbContent.append("<tr><td>修改</td>");
		sbContent.append("<td>").append("/view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify </td>");
		sbContent.append("<td> &lt;c:url value='/view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify' /&gt;</td>");
		sbContent.append("<td> /view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify</td></tr>");
		sbContent.append("<tr><td>保存</td>");
		sbContent.append("<td>").append("/view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save </td>");
		sbContent.append("<td> &lt;c:url value='/view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save' /&gt;</td>");
		sbContent.append("<td> /view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save</td></tr>");
		sbContent.append("<tr><td>删除</td>");
		sbContent.append("<td>").append("/view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del </td>");
		sbContent.append("<td> &lt;c:url value='/view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del' /&gt;</td>");
		sbContent.append("<td> /view/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del</td></tr>");
		sbContent.append("</tbody>");
		sbContent.append("</table>");
		sbContent.append("</div>");
		
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_url_mg\">");
		sbContent.append("管理端");
		sbContent.append("<table class=\"table table-bordered table-striped\">");
		sbContent.append("<thead><tr><th style=\"width: 100px;\">功能</th><th style=\"width: 100px;\">地址</th><th style=\"width: 100px;\">JSP</th><th style=\"width: 50px;\">页面</th></tr></thead>");
		sbContent.append("<tbody>");
		sbContent.append("<tr><td>列表</td>");
		sbContent.append("<td>").append("/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append(" </td>");
		sbContent.append("<td> &lt;c:url value='/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("' /&gt;</td>");
		sbContent.append("<td> /mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("</td></tr>");
		sbContent.append("<tr><td>添加</td>");
		sbContent.append("<td>").append("/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new </td>");
		sbContent.append("<td> &lt;c:url value='/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new' /&gt;</td>");
		sbContent.append("<td> /mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new</td></tr>");
		sbContent.append("<tr><td>展示</td>");
		sbContent.append("<td>").append("/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show </td>");
		sbContent.append("<td> &lt;c:url value='/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show' /&gt;</td>");
		sbContent.append("<td> /mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show</td></tr>");
		sbContent.append("<tr><td>修改</td>");
		sbContent.append("<td>").append("/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify </td>");
		sbContent.append("<td> &lt;c:url value='/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify' /&gt;</td>");
		sbContent.append("<td> /mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify</td></tr>");
		sbContent.append("<tr><td>保存</td>");
		sbContent.append("<td>").append("/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save </td>");
		sbContent.append("<td> &lt;c:url value='/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save' /&gt;</td>");
		sbContent.append("<td> /mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save</td></tr>");
		sbContent.append("<tr><td>删除</td>");
		sbContent.append("<td>").append("/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del </td>");
		sbContent.append("<td> &lt;c:url value='/mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del' /&gt;</td>");
		sbContent.append("<td> /mg/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del</td></tr>");
		sbContent.append("</tbody>");
		sbContent.append("</table>");
		sbContent.append("</div>");
		
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_url_interface_json\">");
		sbContent.append("接口端-JSON");
		sbContent.append("<table class=\"table table-bordered table-striped\">");
		sbContent.append("<thead><tr><th style=\"width: 100px;\">功能</th><th style=\"width: 100px;\">地址</th><th style=\"width: 100px;\">JSP</th><th style=\"width: 50px;\">页面</th></tr></thead>");
		sbContent.append("<tbody>");
		sbContent.append("<tr><td>列表</td>");
		sbContent.append("<td>").append("/apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append(" </td>");
		sbContent.append("<td> &lt;c:url value='/apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("' /&gt;</td>");
		sbContent.append("<td> /apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("</td></tr>");
		sbContent.append("<tr><td>添加</td>");
		sbContent.append("<td>").append("/apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new </td>");
		sbContent.append("<td> &lt;c:url value='/apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new' /&gt;</td>");
		sbContent.append("<td> /apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new</td></tr>");
		sbContent.append("<tr><td>展示</td>");
		sbContent.append("<td>").append("/apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show </td>");
		sbContent.append("<td> &lt;c:url value='/apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show' /&gt;</td>");
		sbContent.append("<td> /apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show</td></tr>");
		sbContent.append("<tr><td>修改</td>");
		sbContent.append("<td>").append("/apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify </td>");
		sbContent.append("<td> &lt;c:url value='/apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify' /&gt;</td>");
		sbContent.append("<td> /apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify</td></tr>");
		sbContent.append("<tr><td>保存</td>");
		sbContent.append("<td>").append("/apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save </td>");
		sbContent.append("<td> &lt;c:url value='/apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save' /&gt;</td>");
		sbContent.append("<td> /apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save</td></tr>");
		sbContent.append("<tr><td>删除</td>");
		sbContent.append("<td>").append("/apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del </td>");
		sbContent.append("<td> &lt;c:url value='/apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del' /&gt;</td>");
		sbContent.append("<td> /apijson/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del</td></tr>");
		sbContent.append("</tbody>");
		sbContent.append("</table>");
		sbContent.append("</div>");
		
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_url_interface_mobi\">");
		sbContent.append("接口端-MOBI");
		sbContent.append("<table class=\"table table-bordered table-striped\">");
		sbContent.append("<thead><tr><th style=\"width: 100px;\">功能</th><th style=\"width: 100px;\">地址</th><th style=\"width: 100px;\">JSP</th><th style=\"width: 50px;\">页面</th></tr></thead>");
		sbContent.append("<tbody>");
		sbContent.append("<tr><td>列表</td>");
		sbContent.append("<td>").append("/apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append(" </td>");
		sbContent.append("<td> &lt;c:url value='/apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("' /&gt;</td>");
		sbContent.append("<td> /apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("</td></tr>");
		sbContent.append("<tr><td>添加</td>");
		sbContent.append("<td>").append("/apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new </td>");
		sbContent.append("<td> &lt;c:url value='/apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new' /&gt;</td>");
		sbContent.append("<td> /apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new</td></tr>");
		sbContent.append("<tr><td>展示</td>");
		sbContent.append("<td>").append("/apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show </td>");
		sbContent.append("<td> &lt;c:url value='/apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show' /&gt;</td>");
		sbContent.append("<td> /apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show</td></tr>");
		sbContent.append("<tr><td>修改</td>");
		sbContent.append("<td>").append("/apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify </td>");
		sbContent.append("<td> &lt;c:url value='/apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify' /&gt;</td>");
		sbContent.append("<td> /apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify</td></tr>");
		sbContent.append("<tr><td>保存</td>");
		sbContent.append("<td>").append("/apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save </td>");
		sbContent.append("<td> &lt;c:url value='/apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save' /&gt;</td>");
		sbContent.append("<td> /apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save</td></tr>");
		sbContent.append("<tr><td>删除</td>");
		sbContent.append("<td>").append("/apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del </td>");
		sbContent.append("<td> &lt;c:url value='/apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del' /&gt;</td>");
		sbContent.append("<td> /apimobi/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del</td></tr>");
		sbContent.append("</tbody>");
		sbContent.append("</table>");
		sbContent.append("</div>");
		
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_url_interface_rest\">");
		sbContent.append("接口端-REST");
		sbContent.append("<table class=\"table table-bordered table-striped\">");
		sbContent.append("<thead><tr><th style=\"width: 100px;\">功能</th><th style=\"width: 100px;\">地址</th><th style=\"width: 100px;\">JSP</th><th style=\"width: 50px;\">页面</th></tr></thead>");
		sbContent.append("<tbody>");
		sbContent.append("<tr><td>列表</td>");
		sbContent.append("<td>").append("/apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append(" </td>");
		sbContent.append("<td> &lt;c:url value='/apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("' /&gt;</td>");
		sbContent.append("<td> /apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("</td></tr>");
		sbContent.append("<tr><td>添加</td>");
		sbContent.append("<td>").append("/apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new </td>");
		sbContent.append("<td> &lt;c:url value='/apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new' /&gt;</td>");
		sbContent.append("<td> /apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/new</td></tr>");
		sbContent.append("<tr><td>展示</td>");
		sbContent.append("<td>").append("/apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show </td>");
		sbContent.append("<td> &lt;c:url value='/apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show' /&gt;</td>");
		sbContent.append("<td> /apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/show</td></tr>");
		sbContent.append("<tr><td>修改</td>");
		sbContent.append("<td>").append("/apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify </td>");
		sbContent.append("<td> &lt;c:url value='/apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify' /&gt;</td>");
		sbContent.append("<td> /apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/modify</td></tr>");
		sbContent.append("<tr><td>保存</td>");
		sbContent.append("<td>").append("/apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save </td>");
		sbContent.append("<td> &lt;c:url value='/apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save' /&gt;</td>");
		sbContent.append("<td> /apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/save</td></tr>");
		sbContent.append("<tr><td>删除</td>");
		sbContent.append("<td>").append("/apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del </td>");
		sbContent.append("<td> &lt;c:url value='/apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del' /&gt;</td>");
		sbContent.append("<td> /apirest/").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("/del</td></tr>");
		sbContent.append("</tbody>");
		sbContent.append("</table>");
		sbContent.append("</div>");
	}

	/**
	 * database api 中使用
	 * @param sbContent
	 * @param table
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static void genJson(StringBuffer sbContent,
			DBTable table,Gson gson) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_json_one\">");
		sbContent.append("单条");
		sbContent.append("<pre class=\"prettyprint linenums\">");
		
		sbContent.append(DBPdmUtils.genTableJson(table, true));
		sbContent.append("</pre>");
		sbContent.append("</div>");
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_json_list\">");
		sbContent.append("分页");
		sbContent.append("<pre class=\"prettyprint linenums\">");
		sbContent.append("{").append("\r\t")
					.append("\"pageSize\":10,").append("\r\t")
					.append("\"pageNo\":1,").append("\r\t")
					.append("\"data\":[").append("\r\t")
					.append(DBPdmUtils.genTableJson(table, true))
					.append("],").append("\r\t")
					.append("\"totalCount\":0,").append("\r\t")
					.append("\"totalPage\":0").append("\r\t")
					.append("}");
		sbContent.append("</pre>");
		sbContent.append("</div>");
	}

	/**
	 * database api 中使用
	 * @param sbContent
	 * @param table
	 */
	public static void genSearch(StringBuffer sbContent, DBTable table) {
		sbContent.append("<div class=\"tab-pane fade in\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_filter\">");
		sbContent.append("<table class=\"table table-bordered table-striped\">");
		sbContent.append("<thead><tr><th style=\"width: 100px;\">名称</th><th style=\"width: 80px;\">接口字段名</th><th style=\"width: 50px;\">类型</th><th>描述</th></tr></thead>");
		sbContent.append("<tbody>");
		for(DBColumn colume:table.getCols()){
			if(!colume.isInSelfTable() && 
					(colume.getType().equalsIgnoreCase("DECIMAL")
							|| colume.getType().equalsIgnoreCase("int"))){
				sbContent.append("<tr>");
				sbContent.append("<td>").append(colume.getName()).append("</td>");
				sbContent.append("<td>").append(CreateUtils.getCol(colume.getCode())).append("</td>");
				sbContent.append("<td>").append(colume.getType()).append("</td>");
				sbContent.append("<td>").append(colume.getComment()).append("</td>");
				sbContent.append("</tr>");
			}
		}
		sbContent.append("<tr>");
		sbContent.append("<td>").append("关键字 ").append("</td>");
		sbContent.append("<td>").append("searchKey").append("</td>");
		sbContent.append("<td>").append("String").append("</td>");
		sbContent.append("<td>").append("全文检索关键字").append("</td>");
		sbContent.append("</tr>");
		sbContent.append("<tr>");
		sbContent.append("<td>").append("检索字段 ").append("</td>");
		sbContent.append("<td>").append("searchCols").append("</td>");
		sbContent.append("<td>").append("List&lt;String&gt;").append("</td>");
		sbContent.append("<td>").append("全文检索字段列表:<列名>").append("</td>");
		sbContent.append("</tr>");
		sbContent.append("<tr>");
		sbContent.append("<td>").append("排序字段 ").append("</td>");
		sbContent.append("<td>").append("orderBys").append("</td>");
		sbContent.append("<td>").append("LinkedHashMap&lt;String, String&gt;").append("</td>");
		sbContent.append("<td>").append("排序字段:<列名,desc|asc>").append("</td>");
		sbContent.append("</tr>");
		sbContent.append("<tr>");
		sbContent.append("<td>").append("每页规定显示记录数 ").append("</td>");
		sbContent.append("<td>").append("pageSize").append("</td>");
		sbContent.append("<td>").append("int").append("</td>");
		sbContent.append("<td>").append("每页规定显示记录数").append("</td>");
		sbContent.append("</tr>");
		sbContent.append("<tr>");
		sbContent.append("<td>").append("页数").append("</td>");
		sbContent.append("<td>").append("pageNo").append("</td>");
		sbContent.append("<td>").append("int").append("</td>");
		sbContent.append("<td>").append("页数").append("</td>");
		sbContent.append("</tr>");
		sbContent.append("</tbody>");
		sbContent.append("</table></div>");
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
		sbContent.append("<div class=\"tab-pane fade in active\" id=\"").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_base\">");
		sbContent.append("<table class=\"table table-bordered table-striped\">");
		sbContent.append("<thead><tr><th style=\"width: 100px;\">名称</th><th style=\"width: 50px;\">字段名</th><th style=\"width: 80px;\">接口字段名</th><th style=\"width: 50px;\">类型</th><th style=\"width: 50px;\">默认值</th><th>描述</th></tr></thead>");
		sbContent.append("<tbody>");
		for(DBColumn colume:table.getCols()){
			if(colume.isInSelfTable()){
				sbContent.append("<tr>");
				sbContent.append("<td>").append(colume.getName()).append("</td>");
				sbContent.append("<td>").append(colume.getCode()).append("</td>");
				sbContent.append("<td>").append(CreateUtils.getCol(colume.getCode())).append("</td>");
				sbContent.append("<td>").append(colume.getType()).append("</td>");
				if(colume.isPrimaryKey() || colume.getMandatory().equalsIgnoreCase("1")) {
                    sbContent.append("<td>").append("<b>必填</b>").append("</td>");
                } else {
                    sbContent.append("<td>").append(colume.getDefaultValue()).append("</td>");
                }
				
				if(StringUtils.startsWithIgnoreCase(colume.getComment(), "RADIO") ||		
						StringUtils.startsWithIgnoreCase(colume.getComment(), "CHECKBOX")){
					sbContent.append("<td>").append("枚举赋值：<i>").append(DBPdmUtils.getKey(table.getCode() + "_" + colume.getCode())).append("</i><br/>").append(colume.getComment().replace("RADIO_", "").replace("CHECKBOX_", "").replace(":", "&nbsp;:&nbsp;").replace(",", ",<br/>")).append("</td>");
				}else if(StringUtils.startsWithIgnoreCase(colume.getComment(), "OPTION")){
					if(null!=colume.getJoinTables()){
						for(JoinTable jt:colume.getJoinTables()){
							sbContent.append("<td>").append("字典值字段：").append(jt.getJoinTableCode()).append("</td>");
						}
					}
					
				}else{
					sbContent.append("<td>").append(colume.getComment()).append("</td>");
				}
				sbContent.append("</tr>");
			}
		}
		for(DBColumn colume:table.getChildCols()){
			if(colume.isInSelfTable()){
				sbContent.append("<tr>");
				sbContent.append("<td>").append(colume.getName()).append("</td>");
				sbContent.append("<td>").append(colume.getCode()).append("</td>");
				sbContent.append("<td>").append(CreateUtils.getCol(colume.getCode())).append("</td>");
				sbContent.append("<td>").append(colume.getType()).append("</td>");
				if(colume.isPrimaryKey() || colume.getMandatory().equalsIgnoreCase("1")) {
                    sbContent.append("<td>").append("<b>必填</b>").append("</td>");
                } else {
                    sbContent.append("<td>").append(colume.getDefaultValue()).append("</td>");
                }
				
				if(StringUtils.startsWithIgnoreCase(colume.getComment(), "RADIO") ||		
						StringUtils.startsWithIgnoreCase(colume.getComment(), "CHECKBOX")){
					sbContent.append("<td>").append("枚举赋值：<i>").append(DBPdmUtils.getKey(table.getCode() + "_" + colume.getCode())).append("</i><br/>").append(colume.getComment().replace("RADIO_", "").replace("CHECKBOX_", "").replace(":", "&nbsp;:&nbsp;").replace(",", ",<br/>")).append("</td>");
				}else if(StringUtils.startsWithIgnoreCase(colume.getComment(), "OPTION")){
					for(JoinTable jt:colume.getJoinTables()){
						sbContent.append("<td>").append("字典值字段：").append(jt.getJoinTableCode()).append("</td>");
					}
				}else{
					sbContent.append("<td>").append(colume.getComment()).append("</td>");
				}
				sbContent.append("</tr>");
			}
		}
		sbContent.append("</tbody>");
		sbContent.append("</table></div>");
	}

	/**
	 * database api 中使用
	 * @param sbContent
	 * @param table
	 */
	public static void genTabNav(StringBuffer sbContent, DBTable table) {
		sbContent.append("<h3>说明</h3>");
		sbContent.append("<ul class=\"nav nav-tabs\">");
		sbContent.append("<li class=\"active\"><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_base\" data-toggle=\"tab\">基本数据</a></li>");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_expend\" data-toggle=\"tab\">扩展数据</a></li>");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_ref\" data-toggle=\"tab\">Ref 数据</a></li>");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_view\" data-toggle=\"tab\">视图</a></li>");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_filter\" data-toggle=\"tab\">查询数据</a></li>");
		
		sbContent.append("<li class=\"dropdown\">");
		sbContent.append("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Json 格式 <b class=\"caret\"></b></a>");
		sbContent.append("<ul class=\"dropdown-menu\">");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_json_one\" data-toggle=\"tab\">单例</a></li>");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_json_list\" data-toggle=\"tab\">多例</a></li>");
		sbContent.append("</ul>");
		sbContent.append("</li>");
		
		sbContent.append("<li class=\"dropdown\">");
		sbContent.append("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">接口地址 <b class=\"caret\"></b></a>");
		sbContent.append("<ul class=\"dropdown-menu\">");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_url_view\" data-toggle=\"tab\">浏览端</a></li>");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_url_mg\" data-toggle=\"tab\">管理端</a></li>");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_url_interface_json\" data-toggle=\"tab\">接口端-JSON</a></li>");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_url_interface_mobi\" data-toggle=\"tab\">接口端-MOBI</a></li>");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_url_interface_rest\" data-toggle=\"tab\">接口端-REST</a></li>");
		sbContent.append("</ul>");
		sbContent.append("</li>");
		
		sbContent.append("<li class=\"dropdown\">");
		sbContent.append("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Example Code <b class=\"caret\"></b></a>");
		sbContent.append("<ul class=\"dropdown-menu\">");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_epcode_init\" data-toggle=\"tab\">定义</a></li>");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_epcode_order\" data-toggle=\"tab\">排序</a></li>");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_epcode_group\" data-toggle=\"tab\">分组</a></li>");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_epcode_skcol\" data-toggle=\"tab\">检索列</a></li>");
		sbContent.append("<li><a href=\"#").append(CreateUtils.getTab(table.getCode()).toLowerCase()).append("_epcode_selcol\" data-toggle=\"tab\">查询列</a></li>");
		sbContent.append("</ul>");
		sbContent.append("</li>");
		
		sbContent.append("</ul>");
	}
}
