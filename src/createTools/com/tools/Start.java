package com.tools;

import com.tools.entity.CreateUtils;
import com.tools.entity.DBColumn;
import com.tools.entity.DBTable;
import com.tools.entity.ftl.FtlJoinTable;
import com.tools.entity.ftl.FtlTableEnum;
import com.tools.entity.ftl.JoinTable;
import com.tools.entity.ftl.JoinTableSql;
import com.tools.entity.pdm.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * 根据pdm 生成
 */
public class Start {
	static int doType = 1;		//1 生成 0清理
	@SuppressWarnings("serial")
	static Set<String> renameFolderExpects = new HashSet<String>(){{add("common");add("thirdpart");}};
	public static String pdmDBTablePrefixStr = "T_";
	
//	public static String pmanRequestHost = "http://{{host}}";
	public static String pmanRequestHost = "http://api.awdxbbs.infosince.com";

	static String webname = "JavaCode";
	static String sys_module=webname.concat("-sys");
	static String sys_module_path = "./" + sys_module + "/src/main/java/com/" + webname;
	static String core_module=webname.concat("-core");
	static String web_module=webname.concat("-web");

	static String pdmname = "../../create/pdm/JavaCode.pdm";

	static String packagepath = "./" + sys_module + "/src/main/java/com/" + webname;
	static String mapperpath =  "/mapper";
	static String mappertestpath = "./src/test/mappers/";

	static String packagename="com." + webname;

	static String jsppath = "./WebContent/pages/";


	static String managerJspPath = jsppath + "manager";
	static String databaseJspPath = jsppath + "docs";
	static String postJsonJSONPath = jsppath + "pm/json";

	static String replacePackageTextRegx = "com\\.is\\.web\\.(\\w*?)\\.";
	
	@SuppressWarnings("unused")
	private static ArrayList<String> filelist = new ArrayList<String>();
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		if(doType==0) {
			DBPdmUtils.renameBaseFolder(TemplateFile.realPathBuild("","",packagepath));
		}
		
		String filePath = Start.class.getResource("/").getPath().replace("/bin/", "/");
		File f = new File(filePath + pdmname);
		ReadpowerFile power = new ReadpowerFile();
		power.setPower(f);
		//所有表格信息
		List<DBTable> pdmTables = power.getTables(); 
		
		/**
		 * 1.获取所有表格中列的依赖其他表格显示信息
		 * 2.移除掺入的view引用信息
		 */
		List<DBExtendedDependencyTable> dbDependencysTable = power.getDependencyWithTable();
		List<DBExtendedDependencyTable> dbTRemove = new ArrayList<DBExtendedDependencyTable>();
		if(null!=dbDependencysTable){
			for(DBExtendedDependencyTable ddt:dbDependencysTable){
				if(StringUtils.isBlank(ddt.getRefWith()) && StringUtils.isBlank(ddt.getRefWithTable())){
					dbTRemove.add(ddt);
				}
			}
			dbDependencysTable.removeAll(dbTRemove);
		}
		
		/**
		 * 1.获取所有表格中列的依赖其他视图显示信息
		 * 2.移除掺入的table引用信息
		 */
		List<DBExtendedDependencyView> dbDependencysView = power.getDependencyWithView();
		List<DBExtendedDependencyView> dbVRemove = new ArrayList<DBExtendedDependencyView>();
		if(null!=dbDependencysView){
			for(DBExtendedDependencyView ddv:dbDependencysView){
				if(StringUtils.isBlank(ddv.getRefWith()) && StringUtils.isBlank(ddv.getRefWithView())){
					dbVRemove.add(ddv);
				}
			}
			dbDependencysView.removeAll(dbVRemove);
		}
		
//		//所有表格的关键字
//		List<DBTableKeywords> tableKeywords = power.getTableKeyWords();
		//所有表格分组
		List<DBTableGroupSymbol> dbTableGroups = power.getTableGroups();
		//所有表-表关联关系
		List<DBReference> dbReferences = power.getReferences();
		//所有表-视图关联关系
		List<DBViewReference> dbViewReferences = power.getViewReferences();
		//所有视图
		List<DBView> pdmViews = power.getViews();
		
		/**
		 * 把表-视图关联转换为表-表关联
		 */
		if(null!=dbViewReferences){
			for(DBViewReference dbvRef:dbViewReferences){
				DBReference tmpDbr = new DBReference();
				tmpDbr.setoId(dbvRef.getoId());
				tmpDbr.setName(dbvRef.getName());
				tmpDbr.setCode(dbvRef.getCode());
				tmpDbr.setCardinality("1..*");
				tmpDbr.setRefFatherTable(dbvRef.getRefFatherTable());
				tmpDbr.setRefFatherColumn(dbvRef.getRefFatherColumn());
				tmpDbr.setRefChildTable(dbvRef.getRefChildTable());
				tmpDbr.setRefChildColumn(dbvRef.getRefChildColumn());
				dbReferences.add(tmpDbr);
			}
		}
		/**
		 * 把视图转换为表格进行处理
		 */
		if(null!=pdmViews){
			for(DBView dbv:pdmViews){
				DBTable dbt = new DBTable();
				List<DBColumn> tmpCols = new ArrayList<DBColumn>();
				for(DBViewColumn iVl:dbv.getColumns()){
					DBColumn ncol = new DBColumn();
					ncol.setTableOId(dbv.getoId());
					ncol.setoId(iVl.getoId());
					ncol.setName(iVl.getName());
					ncol.setCode(iVl.getCode());
					ncol.setType(iVl.getType());
					ncol.setComment(iVl.getName());
					if(StringUtils.equals(iVl.getDisplayed(), "0")){
						ncol.setPrimaryKey(true);
					}else{
						ncol.setPrimaryKey(false);
					}
					ncol.setInSelfTable(true);
					ncol.setInTableColumn(true);
					tmpCols.add(ncol);
				}
				dbt.setoId(dbv.getoId());
				dbt.setObjectID(UUID.randomUUID().toString());
				dbt.setName(dbv.getName());
				dbt.setCode(dbv.getCode());
				dbt.setDescription(dbv.getComment());
				dbt.setViewSqlQuery(dbv.getViewSqlQuery());
				dbt.setCols(tmpCols);
				dbt.setIsView(true);
				pdmTables.add(dbt);
			}
		}
		
		/**
		 * 把对视图的关联，转化为对表格的关联
		 */
		if(null!=dbDependencysView){
			for(DBExtendedDependencyView dpv :dbDependencysView){
				DBExtendedDependencyTable dpt = new DBExtendedDependencyTable();
				dpt.setRefCol(dpv.getRefCol());
				dpt.setRefWith(dpv.getRefWith());
				dpt.setRefWithTable(dpv.getRefWithView());
				dpt.setStereotype(dpv.getStereotype());
				dbDependencysTable.add(dpt);
			}
		}
		
		List<FtlTableEnum> tableEnums = initTableData(pdmTables, dbDependencysTable,dbReferences,dbTableGroups);
		
		CollectionUtils.collect(pdmTables, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				DBTable t = (DBTable) arg0;
				t.setPackname(packagename);
				t.setMapperpackatename(packagename);
				return t;
			}
		});
		CollectionUtils.filter(pdmTables, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				return arg0!=null;
			}
		});

		Collections.sort(pdmTables,new Comparator<DBTable>(){
			@Override
			public int compare(DBTable o1, DBTable o2) {
				if(StringUtils.isBlank(o1.getTableGroupOid())) {
					return -1;
				} else if(StringUtils.isBlank(o2.getTableGroupOid())) {
					return 1;
				} else if(StringUtils.equals(o1.getTableGroupOid(), o2.getTableGroupOid())) {
					return 0;
				} else{
					return o1.getTableGroupOid().compareTo(o2.getTableGroupOid());
				}
			}
			
		});
		if(doType==0) {
			clear(pdmTables,tableEnums);
		} else{
			create(pdmTables,tableEnums,pdmViews);
		}
	}

	public static void create(List<DBTable> tables,List<FtlTableEnum> tableEnums,List<DBView> pdmViews) throws Exception{
		ToTemplateFile tofile = new ToTemplateFile();
		
		tofile.tabelEntity(new File(sys_module_path  + "/entity"), tables);
//		tofile.tabelEntityVojo(new File(sys_module_path + "/entity/vojo"), tables);
//		tofile.tableRefEnum(new File(sys_module_path + "/entity/refenum"), tables);
//		tofile.tabelExtend(new File(sys_module_path + "/entity/extend"), tables);
//		tofile.tableCompareColEnum(new File(sys_module_path + "/entity/comparecolsenum"), tables);
//		tofile.tableUpdateInSqlColEnum(new File(packagepath + webname + "/entity/updateinsqlenum"), tables);
//		tofile.tabelEnum(new File(sys_module_path + "/entity/entityenum"), tableEnums);

		
//		tofile.genViewSql(new File(databaseJspPath), tables,pdmViews);
//		tofile.xmlMapper(new File(sys_module_path + "/mapper"), tables);
//		tofile.interMapperTest(new File(mappertestpath + webname + "/sql"), tables);
//
//		tofile.svc(new File(packagepath  + "/service"), tables);
//		tofile.svcImpl(new File(packagepath + "/service/impl"), tables);
		
//		tofile.viewController(new File(packagepath + webname + "/controller/view"), tables);
//		tofile.managerController(new File(packagepath + webname + "/controller/manager"), tables,managerJspPath);
//
//		tofile.interfaceJson(new File(packagepath + webname + "/controller/interfacewithjson"), tables);
//		tofile.interJsonContent(new File(databaseJspPath), tables);
//		tofile.interJsonPostMan(new File(databaseJspPath), tables,webname);
//		tofile.interJsonExcel(new File(databaseJspPath), tables);
//
//		tofile.interfaceMobi(new File(packagepath + webname + "/controller/interfacewithmobi"), tables);
//		tofile.interMobiContent(new File(databaseJspPath), tables);
//		tofile.interMobiPostMan(new File(databaseJspPath), tables,webname);
//		tofile.interMobiExcel(new File(databaseJspPath), tables);
//
//		tofile.interfaceRest(new File(packagepath + webname + "/controller/interfacewithrest"), tables);
//
//		tofile.jspmodify(new File(managerJspPath), tables);
//		tofile.jspshow(new File(managerJspPath), tables);
//		tofile.jspadd(new File(managerJspPath), tables);
//		tofile.jsplist_with_colname(new File(managerJspPath), tables);
//
//		tofile.databaseApiContent(new File(databaseJspPath), tables);
//		tofile.databaseExcel(new File(databaseJspPath), tables);
//		tofile.databaseMdContent(new File(databaseJspPath), tables);
//		tofile.javaExampleMd(new File(databaseJspPath), tables);
//
//		tofile.applicationContextContent(new File(webinf), packagename);
//		tofile.webXmlContent(new File(web_xml), webname);
		System.out.println("run finished.");
	}

	public static void clear(List<DBTable> tables,List<FtlTableEnum> tableEnums) throws Exception{
		CleanTemplateFile clFile = new CleanTemplateFile();
		clFile.tabelEntity(packagepath + webname + "/entity/sql", tables);
		clFile.tabelEnum(new File(packagepath + webname + "/entityentityenum"), tableEnums);
		
		clFile.interMapperTest(new File(mappertestpath + webname + "/sql"), tables);
		
		clFile.xmlMapper(new File(mapperpath + webname + "/sql"), tables);
		clFile.svcMapper(packagepath + webname + "/services", tables);
		
		clFile.controllerMapper(packagepath + webname + "/controller", tables);

		clFile.jsp(new File(managerJspPath), tables);
		System.out.println("run finished.");
	}
	
	/**
	 * 表，join展示列，关联字段
	 * @param tables	表格
	 * @param dependencysTable	join 其他表格列
	 * @param references	关联查询字段
	 * @param tableGroups	表格分组
	 * @return
	 */
	private static List<FtlTableEnum> initTableData(List<DBTable> tables,
			List<DBExtendedDependencyTable> dependencysTable
			,List<DBReference> references
			,List<DBTableGroupSymbol> tableGroups
			) {
		/**
		 * 初始化表格分组信息
		 * 表格oid:分组oid,{o87=o77}
		 */
		Map<String,String> dbTableGroupIdMap = new HashMap<String,String>();
		for(DBTableGroupSymbol t : tableGroups){
			for(DBTableTableSymbol dts:t.gettSymbol()){
				dbTableGroupIdMap.put(dts.getTid(), t.getId());
			}
		}
		
		/**
		 * 构建全部表格中列字段信息
		 * oid:colume,{o189=com.tools.entity.DBColumn}
		 * 用以列信息检索
		 */
		Map<String,DBColumn> fullDBColumns = new HashMap<String,DBColumn>();
		
		/**
		 * 构建全部表格的信息
		 * oid:table,{o87=com.tools.entity.DBTable}
		 * 用以判断引用类型是否是表格
		 */
		Map<String,DBTable> fullDBTables = new HashMap<String,DBTable>();
		
		/**
		 * 构建全部sql生成视图sql语句
		 * 视图名称:创建sql语句
		 */
		Map<String,String> fullSQLView = new HashMap<String,String>();
		
//		Map<String,FtlTable> fullFtlTables = new HashMap<String,FtlTable>();
		
		for(DBTable t : tables){
			//把SQLVW，SQLVVW写入到fullSQLView中
			if(StringUtils.isNotBlank(t.getViewSqlQuery())){
				fullSQLView.put(t.getCode(), t.getViewSqlQuery().replace(";", ""));
			}
//			FtlTable ftlTable = new FtlTable();
			//把表所属分组，写入到表属性中
			if(StringUtils.isBlank(t.getViewSqlQuery())){
				if(dbTableGroupIdMap.containsKey(t.getoId())) {
					t.setTableGroupOid(dbTableGroupIdMap.get(t.getoId()));
				} else {
					t.setTableGroupOid(t.getoId());
				}
			}else{
				t.setTableGroupOid("v1");
			}
			List<DBTPk> pks = t.getPks();
			List <DBColumn> cols = t.getCols();
			for(DBColumn dbc:cols){
				dbc.setTableOId(t.getoId());
				/**
				 * 判断列是否是主键
				 */
				for(DBTPk dbtpk:pks){
					/**
					 * 2016-01-20
					 * 由于获取的keys中，包含主键和非主键
					 * 用于区分出，keys中的主键与非主键
					 */
					if(StringUtils.equalsIgnoreCase(dbtpk.getRefId(), t.getTablePrimaryKeyRefOid())){
						for(DBTPkeyId keyId: dbtpk.getKeyIds()){
							if (StringUtils.equalsIgnoreCase(keyId.getKeyid(), dbc.getoId())) {
								dbc.setPrimaryKey(true);
								break;
							}else{
								dbc.setPrimaryKey(false);
							}
						}
					}
				}
				fullDBColumns.put(dbc.getoId(), dbc);
			}
			fullDBTables.put(t.getoId(), t);
		}

		Pattern ptn = compile(" *SQLVW_.+? ");
		for(String fsv:fullSQLView.keySet()) {
			if (StringUtils.contains(fullSQLView.get(fsv).toUpperCase(), " SQLVW_")) {
				String sql = fullSQLView.get(fsv).toUpperCase();
				Matcher mch = ptn.matcher(sql);
				while (mch.find()) {
					sql = sql.replace(mch.group(), " (" + fullSQLView.get(mch.group().trim()) + ") ");
				}
				fullSQLView.put(fsv, sql);
			}
		}
		/**
		 * 把主表中关联关系子表加入到字段中
		 * 1..1 1..*
		 */
		if(null!=references){
			for(DBReference reference:references){
				DBTable fatherTable = fullDBTables.get(reference.getRefFatherTable());
				DBColumn fatherColunm = (DBColumn) fullDBColumns.get(reference.getRefFatherColumn());
				DBTable childTable = fullDBTables.get(reference.getRefChildTable());
				DBColumn childColumn = (DBColumn) fullDBColumns.get(reference.getRefChildColumn());
				
				DBColumn colSearchKeyCols = new DBColumn();
				colSearchKeyCols.setRefChildTable(childTable);
				colSearchKeyCols.setoId(UUID.randomUUID().toString());
				colSearchKeyCols.setName(reference.getName());
				colSearchKeyCols.setCode(reference.getCode());
				colSearchKeyCols.setType(CreateUtils.getTab(childTable.getCode()));
				colSearchKeyCols.setRefFatherColumn(fatherColunm.getCode());
				colSearchKeyCols.setRefChildColumn(childColumn.getCode());
				colSearchKeyCols.setRefChildColumnPrimaryKey(childColumn.isPrimaryKey());
				
				if(StringUtils.equals(reference.getCardinality(), "1..1")){
					colSearchKeyCols.setComment(reference.getName() + "，关联：" + CreateUtils.getCol(fatherColunm.getCode()) + "-"+ CreateUtils.getCol(childColumn.getCode()) + "，一对一");
				}else if(StringUtils.equals(reference.getCardinality(), "1..*")){
					colSearchKeyCols.setTypeExpend("List");
					colSearchKeyCols.setComment(reference.getName() + "，关联：" + CreateUtils.getCol(fatherColunm.getCode()) + "-"+ CreateUtils.getCol(childColumn.getCode()) + "，一对多");
				}
				colSearchKeyCols.setPrimaryKey(false);
				colSearchKeyCols.setInSelfTable(false);
				colSearchKeyCols.setInTableColumn(false);
				fatherTable.getRefCols().add(colSearchKeyCols);
			}
		}
		
		/**
		 * left join
		 * {o543={										//主表中扩展外部表格,视图的列
		 * 		o173=[refCol=o614,refShow=[o615]], 		//第一个扩展=[扩展检索列，[展示列1,展示列2]
		 * 		o175=[refCol=o616,refShow=[o617]], 		//第二个扩展=[扩展检索列，[展示列1,展示列2]
		 * 		o177=[refCol=o618,refShow=[o619, o620]]	//第三个扩展=[扩展检索列，[展示列1,展示列2]
		 * 		}
		 * }
		 */
		Map<String,Map<String,JoinVojo>> joinVojos = new HashMap<String,Map<String,JoinVojo>>();
		if(null!=dependencysTable){
			Map<String,JoinVojo> joinMap = null;
			for(DBExtendedDependencyTable dep:dependencysTable){
				if(!joinVojos.containsKey(dep.getRefCol())){
					joinMap = new HashMap<String,JoinVojo>();
					joinVojos.put(dep.getRefCol(), joinMap);
				}else{
					joinMap = joinVojos.get(dep.getRefCol());
				}
				if(StringUtils.isNotBlank(dep.getRefWithTable())){
					if(!joinMap.containsKey(dep.getRefWithTable())){
						joinMap.put(dep.getRefWithTable(), new JoinVojo());
					}
				}
				if(StringUtils.isNotBlank(dep.getRefWith())){
					DBColumn vcol = fullDBColumns.get(dep.getRefWith());
					String vid = vcol.getTableOId();
					if(!joinMap.containsKey(vid)){
						joinMap.put(vid, new JoinVojo());
					}
					JoinVojo tmpRv = joinMap.get(vid);
					if(vcol.isPrimaryKey()){
						tmpRv.setJoinCol(vcol.getoId());
					}else{
						List<String> shows = null==tmpRv.getJoinShow()?new ArrayList<String>():tmpRv.getJoinShow();
						shows.add(vcol.getoId());
					}
				}
			}
		}
		
		List<FtlTableEnum> tableEnums = new ArrayList<FtlTableEnum>();
		
		for(DBTable t : tables){
			List<DBColumn> cols = t.getCols();
			List<DBColumn> newColFromJoinSel = new ArrayList<DBColumn>();
			int ifc=0;
			for(DBColumn dbc : cols){
				if(StringUtils.startsWithIgnoreCase(dbc.getComment(), "RADIO") ||
						StringUtils.startsWithIgnoreCase(dbc.getComment(), "CHECKBOX")){
					DBColumn nldbc = new DBColumn();
					nldbc.setCode(dbc.getCode() + "_CHNVAL");
					nldbc.setName(dbc.getName() + "_字典值");
					nldbc.setComment(dbc.getName() + "_字典值");
					nldbc.setoId(dbc.getoId() + "_CHNVAL");
					nldbc.setType("VARCHAR");
					nldbc.setPrimaryKey(false);
					nldbc.setInSelfTable(false);
					nldbc.setInTableColumn(false);
					newColFromJoinSel.add(nldbc);
				}
				
				//对选择类型字段做枚举处理
				if(StringUtils.startsWithIgnoreCase(dbc.getComment(), "RADIO") ||		
						StringUtils.startsWithIgnoreCase(dbc.getComment(), "CHECKBOX")){
					FtlTableEnum dte = new FtlTableEnum();
					dte.setKey(t.getCode() + "_" + dbc.getCode());
					dte.setTableName(t.getName());
					dte.setCol(dbc);
					dte.setPackname(packagename);
					tableEnums.add(dte);
				}
				if(joinVojos.containsKey(dbc.getoId())){
					Map<String, JoinVojo> joinVojo = joinVojos.get(dbc.getoId());
					List<JoinTable> joinTables = new ArrayList<JoinTable>();
					int ift = 0;
					boolean needSuffix = false;		//是否是一个列有多个表格视图的left join
					if(joinVojo.size()>1){
						needSuffix = true;
					}
					for(String joinTOid : joinVojo.keySet()){
						final FtlJoinTable jTable = new FtlJoinTable();
						LinkedList<String> colume = new LinkedList<String>();
						LinkedList<String> columeAlias = new LinkedList<String>();
						LinkedList<String> columeNames = new LinkedList<String>();
						JoinVojo jv = joinVojo.get(joinTOid);
						StringBuffer joinShow = new StringBuffer();
						boolean joinIsView = false;
						//生成join 后显示的列
						for(int lc=0;lc<jv.getJoinShow().size();lc++){
							String joinShowOid = jv.getJoinShow().get(lc);
							DBColumn dbcs[] = {dbc};
							genJoinColumn(fullDBColumns, fullDBTables,
									newColFromJoinSel,dbcs, needSuffix,
									joinTOid, colume, columeAlias, columeNames,
									joinShow, joinShowOid);
							//如果引用的列，对外还有引用
							if(joinVojos.containsKey(joinShowOid)){
								joinIsView = true;
								Map<String, JoinVojo> joinVojoIn = joinVojos.get(joinShowOid);
								for(String joinTOidIn : joinVojoIn.keySet()){
									JoinVojo jvIn = joinVojoIn.get(joinTOidIn);
									DBColumn dbcIn = fullDBColumns.get(joinShowOid);
									for(int llc=0;llc<jvIn.getJoinShow().size();llc++){
										String joinShowOidIn = jvIn.getJoinShow().get(llc);
										DBColumn dbcsIn[] = {dbc,dbcIn};
										genJoinColumn(fullDBColumns, fullDBTables,
												newColFromJoinSel, dbcsIn, needSuffix,
												joinTOidIn, colume, columeAlias, columeNames,
												joinShow, joinShowOidIn);
									}
								}
							}
						}
						JoinTable dJt = new JoinTable();
						dJt.setJoinTable(fullDBTables.get(joinTOid));
						if(joinIsView){
							dJt.setJoinTableCode(CreateUtils.getTab(fullDBTables.get(joinTOid).getViewCode()));
						}else{
							dJt.setJoinTableCode(CreateUtils.getTab(fullDBTables.get(joinTOid).getCode()));
						}
						String jcol = jv.getJoinCol();
						String fcode = fullDBColumns.get(jcol).getCode();
						String cCol = CreateUtils.getCol(fcode);
						dJt.setJoinTableKey(cCol);
						dJt.setJoinTableShow(joinShow.toString());
						joinTables.add(dJt);
						/**
						 * 拼凑数据库中列字段关联查询对应的sql语句
						 */
						jTable.setOid(joinTOid);
						if(joinIsView){
							jTable.setTableCode(fullDBTables.get(joinTOid).getViewCode());
						}else{
							jTable.setTableCode(fullDBTables.get(joinTOid).getCode());
						}
						if(needSuffix){
							jTable.setTabelAliasCode("T" + ifc + ift);
						}else{
							jTable.setTabelAliasCode("T" + ifc);
						}
						jTable.setJoinPrimaryKey(fullDBColumns.get(jv.getJoinCol()).getCode());
						jTable.setJoinOnColume(dbc.getCode().toString().toUpperCase());
						jTable.setTableName(fullDBTables.get(joinTOid).getName());
						jTable.setColume(colume);
						jTable.setColumeAlias(columeAlias);
						jTable.setColumeName(columeNames);
						t.getJoinTables().add(jTable);
						ift += 1;
					}
					dbc.setJoinTables(joinTables);
				}
				ifc += 1;
			}
			//	1.把left join 的列单独存储
			List<DBColumn> tmpNCFJS = new ArrayList<DBColumn>();
			for(int i=0;i<newColFromJoinSel.size();i++){
				tmpNCFJS.add(newColFromJoinSel.get(i));
			}
			//	2.生成本表的扩展查询条件
			for(DBColumn dbc : cols){
				if(DBPdmUtils.checkColumnCanCompare(dbc)){
					t.setHasCompareColumn(true);
					DBPdmUtils.genIntegerMultsCol(newColFromJoinSel, dbc);
				}
			}
			//	3.生成left join 的列的扩展查询条件			
			for(DBColumn dbc : tmpNCFJS){
				if(DBPdmUtils.checkColumnCanCompare(dbc)){
					t.setHasCompareColumn(true);
					DBPdmUtils.genIntegerMultsCol(newColFromJoinSel, dbc);
				}
			}
			
			for(int ic = 0;ic<newColFromJoinSel.size();ic++){
				t.getCols().add(newColFromJoinSel.get(ic));
				t.setHasExtendColumn(true);
			}
			
		}
		/**
		 * 初始表格数据中，jointable的表格，
		 * 是否有外链join字段
		 */
		for(DBTable t : tables){
			for(FtlJoinTable jtItem: t.getJoinTables()){
				for(DBTable tSub : tables){
					if(StringUtils.equalsIgnoreCase(jtItem.getOid(), tSub.getoId())){
						if(null!=tSub.getRefCols() && tSub.getJoinTables().size()>0){
							jtItem.setTableHaveChildColum(1);
							break;
						}
					}
				}
			}
		}
		/**
		 * 判断 mapper方法是否有WithJoin方法可以调用
		 */
		for(DBTable t : tables){
			for(DBColumn dbc:t.getRefCols()){
				if(dbc.getRefChildTable().getJoinTables().size()>0){
					dbc.setFatherTableHasJoin(true);
				}
			}
			for(FtlJoinTable fjt:t.getJoinTables()){
				if(StringUtils.startsWith(fjt.getTableCode(), "SQLVW_") || StringUtils.startsWith(fjt.getTableCode(), "SQLVVW_")){
					JoinTableSql jts = new JoinTableSql();
					jts.setName(fjt.getTableName());
					jts.setSql(fullSQLView.get(fjt.getTableCode()));
					t.getJoinTableSql().put(fjt.getTableCode(), jts);
//					System.out.println(t.getName() + "-" + fjt.getTableCode() + "-" + fullSQLView.get(fjt.getTableCode()));
				}
			}
		}
		return tableEnums;
	}

	/**
	 * 生成join列数据
	 * @param joinShowOid	扩展查询的列oid
	 * @param dbc	扩展查询的原列
	 * @param joinTOid	扩展查询的目标表	
	 * @param fullDBColumns	全部列	oid，列
	 * @param fullDBTables	全部表	oid，表
	 * @param needSuffix	是否需要加前缀一个列，join多个表时，需要有前缀
	 * @param newColFromJoinSel	返回的，新生成的查询列集合
	 * @param colume	返回的，sql查询列名集合字符串
	 * @param columeAlias	返回的，sql查询的列的别名
	 * @param columeNames	返回的，sql查询的列的中文名称
	 * @param joinShow	返回的，列属性名
	 */
	private static void genJoinColumn(Map<String, DBColumn> fullDBColumns,
			Map<String, DBTable> fullDBTables,
			List<DBColumn> newColFromJoinSel, DBColumn[] dbc, boolean needSuffix,
			String joinTOid, LinkedList<String> colume,
			LinkedList<String> columeAlias, LinkedList<String> columeNames,
			StringBuffer joinShow, String joinShowOid) {
		if(joinShow.length()>0){
			joinShow.append(",");
		}
		DBColumn ldbc = (DBColumn) fullDBColumns.get(joinShowOid);
		DBColumn nldbc = new DBColumn();
		StringBuffer dbcCodeBuffer = new StringBuffer();
		StringBuffer dbcNameBuffer = new StringBuffer();
		StringBuffer dbcAliaBuffer = new StringBuffer();
		for(int dbcI=0;dbcI<dbc.length;dbcI++){
			DBColumn dbcF = dbc[dbcI];
			if(dbcI>0){
				dbcCodeBuffer.append("_");
				dbcNameBuffer.append("_");
				dbcAliaBuffer.append("_");
			}
			dbcCodeBuffer.append(dbcF.getCode());
			dbcNameBuffer.append(dbcF.getName());
			dbcAliaBuffer.append(fullDBColumns.get(dbcF.getoId()).getCode());
		}
		
		if(needSuffix){
			nldbc.setCode(dbcCodeBuffer.toString() + "_" + fullDBTables.get(joinTOid).getCode() + "_" + ldbc.getCode());
			nldbc.setComment(dbcNameBuffer.toString() + "_" + "扩展_" + fullDBTables.get(joinTOid).getName() + "_" + ldbc.getName());
			
			columeNames.add(fullDBTables.get(joinTOid).getName() + "_" + ldbc.getName());
			columeAlias.add(dbcAliaBuffer.toString() + "_" + fullDBTables.get(joinTOid).getCode() + "_" + ldbc.getCode());
		}else{
			nldbc.setCode(dbcCodeBuffer.toString() +"_" + ldbc.getCode());
			nldbc.setComment(dbcNameBuffer.toString() + "_" + "扩展_" + ldbc.getName());
			
			columeNames.add(dbcNameBuffer.toString() + "_" + "扩展_" + ldbc.getName());
			columeAlias.add(dbcCodeBuffer.toString() + "_" + ldbc.getCode());
		}
		/**
		 * 生成sql 查询语句
		 * 扩展列查询时，查询视图中的列，不加前缀
		 */
		if(dbc.length>1){
			colume.add(dbc[dbc.length-1].getCode() +"_" + ldbc.getCode());
		}else{
			colume.add(ldbc.getCode());
		}
		
		if(ldbc.isPrimaryKey()){
			nldbc.setName(dbcNameBuffer.toString());
			nldbc.setPrimaryKey(true);
		}else{
			nldbc.setName(ldbc.getName());
			nldbc.setPrimaryKey(false);
		}
		nldbc.setoId(ldbc.getoId());
		nldbc.setType(ldbc.getType());
		nldbc.setInSelfTable(false);
		nldbc.setInTableColumn(true);
		newColFromJoinSel.add(nldbc);
		joinShow.append(CreateUtils.getCol(ldbc.getCode()));
	}

}
