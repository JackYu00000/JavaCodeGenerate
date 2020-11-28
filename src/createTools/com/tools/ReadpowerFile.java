package com.tools;

/**
 * 更新记录
 * 1.2015：12-29 pdm中视图可以通过指定列comment属性、加入关联其他表格列实现扩展查询，但是会再次创建另一个视图，不如直接在创建数据的sql语句中直接查询。
 * 
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester3.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import com.tools.entity.DBColumn;
import com.tools.entity.DBTable;
import com.tools.entity.pdm.DBExtendedDependencyTable;
import com.tools.entity.pdm.DBExtendedDependencyView;
import com.tools.entity.pdm.DBReference;
import com.tools.entity.pdm.DBTPk;
import com.tools.entity.pdm.DBTPkeyId;
import com.tools.entity.pdm.DBTableGroupSymbol;
import com.tools.entity.pdm.DBTableTableSymbol;
import com.tools.entity.pdm.DBView;
import com.tools.entity.pdm.DBViewColumn;
import com.tools.entity.pdm.DBViewReference;

public class ReadpowerFile {
	Log log = LogFactory.getLog(ReadpowerFile.class);
	
	public File power ;

	public File getPower() {
		return power;
	}

	public void setPower(File power) {
		this.power = power;
	}
	@SuppressWarnings({ "unchecked" })
	public List<DBTable>  getTables() throws IOException, SAXException{
	    Digester digester = new Digester();  
	    digester.setLogger(log);
	    digester.setValidating(false);  
        // 从library标签开始解析,并新建一个Library对象做为根对象  
//	    String base ="*/c:Tables";
//	    String base ="*/o:Model";
	    
        digester.addObjectCreate("*/c:Tables", ArrayList.class);  
        digester.addObjectCreate("*/c:Tables/o:Table", DBTable.class);  
        digester.addObjectCreate("*/c:Columns/o:Column", DBColumn.class);  
        digester.addObjectCreate("*/c:Keys/o:Key", DBTPk.class);
        digester.addObjectCreate("*/c:Keys/o:Key/c:Key.Columns/o:Column", DBTPkeyId.class);
//        digester.addObjectCreate("*/c:Key.Columns/o:Column", DBTPk.class);
        
        digester.addSetNext("*/c:Tables/o:Table", "add");  
        digester.addSetNext("*/c:Keys/o:Key", "addPk");  
        digester.addSetNext("*/c:Keys/o:Key/c:Key.Columns/o:Column", "addKeyOid","com.tools.entity.pdm.DBTPk");  
//        digester.addSetNext("*/c:Key.Columns/o:Column", "addPk");  
        digester.addSetNext("*/c:Columns/o:Column", "addCol");  
        
        digester.addSetProperties("*/c:Tables/o:Table", "Id", "oId");
        digester.addBeanPropertySetter("*/o:Table/a:ObjectID", "objectID");  
        digester.addBeanPropertySetter("*/o:Table/a:Name", "name");  
        digester.addBeanPropertySetter("*/o:Table/a:Code", "code"); 
        digester.addBeanPropertySetter("*/o:Table/a:Description", "description"); 
        digester.addSetProperties("*/o:Table/c:AttachedKeywords/o:Keyword", "Ref", "tableKeyWordOid");
        digester.addSetProperties("*/o:Table/c:PrimaryKey/o:Key", "Ref", "tablePrimaryKeyRefOid");
        
        digester.addSetProperties("*/c:Keys/o:Key", "Id", "refId");
        digester.addSetProperties("*/c:Keys/o:Key/c:Key.Columns/o:Column","Ref", "keyid"); 
        
        digester.addSetProperties("*/c:Columns/o:Column", "Id", "oId");
        digester.addBeanPropertySetter("*/o:Column/a:Name", "name");  
        digester.addBeanPropertySetter("*/o:Column/a:Code", "code"); 
        digester.addBeanPropertySetter("*/o:Column/a:DataType", "type"); 
        digester.addBeanPropertySetter("*/o:Column/a:Comment", "comment"); 
        digester.addBeanPropertySetter("*/o:Column/a:Column.Mandatory", "mandatory"); 
        digester.addBeanPropertySetter("*/o:Column/a:DefaultValue", "defaultValue"); 
        
        List<DBTable> parse = (List<DBTable>) digester.parse(power);
		return   parse;
	}
	
	/**
	 * 获取pdm中的视图列表
	 */
	public List<DBView> getViews() throws IOException, SAXException{
		Digester digester = new Digester();  
	    digester.setLogger(log);
	    digester.setValidating(false);  
	    
	    digester.addObjectCreate("*/c:Views", ArrayList.class);
	    digester.addObjectCreate("*/c:Views/o:View", DBView.class);
	    digester.addObjectCreate("*/c:Columns/o:ViewColumn", DBViewColumn.class);
	    
	    digester.addSetNext("*/c:Views/o:View", "add");
	    digester.addSetNext("*/c:Columns/o:ViewColumn", "addCol");
	    
	    digester.addSetProperties("*/c:Views/o:View", "Id", "oId");
	    digester.addBeanPropertySetter("*/o:View/a:Name", "name");
	    digester.addBeanPropertySetter("*/o:View/a:Code", "code");
	    digester.addBeanPropertySetter("*/o:View/a:Comment", "comment");
	    digester.addBeanPropertySetter("*/o:View/a:View.SQLQuery", "viewSqlQuery");
	    
	    digester.addSetProperties("*/c:Columns/o:ViewColumn", "Id", "oId");
	    digester.addBeanPropertySetter("*/o:ViewColumn/a:Name", "name");
	    digester.addBeanPropertySetter("*/o:ViewColumn/a:Code", "code");
	    digester.addBeanPropertySetter("*/o:ViewColumn/a:Comment", "comment");
	    digester.addBeanPropertySetter("*/o:ViewColumn/a:DataType", "type");
	    digester.addBeanPropertySetter("*/o:ViewColumn/a:Displayed", "displayed");
	    
	    List<DBView> parse = (List<DBView>) digester.parse(power);
	    return parse;
	}
	
	/**
	 * 获取字段关联查询其他表字段
	 */
	@SuppressWarnings("unchecked")
	public List<DBExtendedDependencyTable>  getDependencyWithTable() throws IOException, SAXException{
	    Digester digester = new Digester();
	    digester.setLogger(log);
	    digester.setValidating(false);  
        // 从library标签开始解析,并新建一个Library对象做为根对象  
//	    String base ="*/c:Tables";
//	    String base ="*/o:Model";
	    
        digester.addObjectCreate("*/c:ChildTraceabilityLinks", ArrayList.class);  
        digester.addObjectCreate("*/c:ChildTraceabilityLinks/o:ExtendedDependency", DBExtendedDependencyTable.class);
        
        digester.addSetNext("*/c:ChildTraceabilityLinks/o:ExtendedDependency", "add");  
        
        digester.addBeanPropertySetter("*/o:ExtendedDependency/a:Stereotype", "stereotype"); 
        digester.addSetProperties("*/o:ExtendedDependency/c:Object2/o:Column","Ref", "refCol"); 
        digester.addSetProperties("*/o:ExtendedDependency/c:Object1/o:Column","Ref", "refWith"); 
        digester.addSetProperties("*/o:ExtendedDependency/c:Object1/o:Table","Ref", "refWithTable"); 
        List<DBExtendedDependencyTable> parse = (List<DBExtendedDependencyTable>) digester.parse(power);
		return   parse;
	}
	
	
	/**
	 * 获取字段关联查询其他视图字段
	 */
	@SuppressWarnings("unchecked")
	public List<DBExtendedDependencyView>  getDependencyWithView() throws IOException, SAXException{
	    Digester digester = new Digester();
	    digester.setLogger(log);
	    digester.setValidating(false);  
        // 从library标签开始解析,并新建一个Library对象做为根对象  
//	    String base ="*/c:Tables";
//	    String base ="*/o:Model";
	    
        digester.addObjectCreate("*/c:ChildTraceabilityLinks", ArrayList.class);  
        digester.addObjectCreate("*/c:ChildTraceabilityLinks/o:ExtendedDependency", DBExtendedDependencyView.class);
        
        digester.addSetNext("*/c:ChildTraceabilityLinks/o:ExtendedDependency", "add");  
        
        digester.addBeanPropertySetter("*/o:ExtendedDependency/a:Stereotype", "stereotype"); 
        digester.addSetProperties("*/o:ExtendedDependency/c:Object2/o:Column","Ref", "refCol"); 
        digester.addSetProperties("*/o:ExtendedDependency/c:Object1/o:ViewColumn","Ref", "refWith"); 
        digester.addSetProperties("*/o:ExtendedDependency/c:Object1/o:View","Ref", "refWithView"); 
        List<DBExtendedDependencyView> parse = (List<DBExtendedDependencyView>) digester.parse(power);
		return   parse;
	}
	
	/**
	 *	获取父表、子表关联关系 
	 */
	@SuppressWarnings("unchecked")
	public List<DBReference> getReferences() throws IOException, SAXException{
		Digester digester = new Digester();  
		digester.setLogger(log);
	    digester.setValidating(false);
	    
	    digester.addObjectCreate("*/c:References", ArrayList.class);  
	    digester.addObjectCreate("*/c:References/o:Reference", DBReference.class);
	    
	    digester.addSetNext("*/c:References/o:Reference", "add");  
	    digester.addSetProperties("*/c:References/o:Reference", "Id", "oId");
	    digester.addBeanPropertySetter("*/c:References/o:Reference/a:Name", "name"); 
	    digester.addBeanPropertySetter("*/c:References/o:Reference/a:Code", "code"); 
	    digester.addBeanPropertySetter("*/c:References/o:Reference/a:Cardinality", "cardinality"); 
	    digester.addSetProperties("*/c:References/o:Reference/c:ParentTable/o:Table","Ref", "refFatherTable"); 
	    digester.addSetProperties("*/c:References/o:Reference/c:ChildTable/o:Table","Ref", "refChildTable"); 
	    digester.addSetProperties("*/c:References/o:Reference/c:Joins/o:ReferenceJoin/c:Object1/o:Column","Ref", "refFatherColumn"); 
	    digester.addSetProperties("*/c:References/o:Reference/c:Joins/o:ReferenceJoin/c:Object2/o:Column","Ref", "refChildColumn"); 
	    
		List<DBReference> parse = (List<DBReference>) digester.parse(power);
		return parse;
	}
	
	/**
	 * 获取父表、子视图关联关系
	 */
	public List<DBViewReference> getViewReferences() throws IOException, SAXException{
		Digester digester = new Digester();  
		digester.setLogger(log);
	    digester.setValidating(false);
	    
	    digester.addObjectCreate("*/c:ViewReferences", ArrayList.class);  
	    digester.addObjectCreate("*/c:ViewReferences/o:ViewReference", DBViewReference.class);  
	    
	    digester.addSetNext("*/c:ViewReferences/o:ViewReference", "add");
	    digester.addSetProperties("*/c:ViewReferences/o:ViewReference", "Id", "oId");
	    digester.addBeanPropertySetter("*/c:ViewReferences/o:ViewReference/a:Name", "name"); 
	    digester.addBeanPropertySetter("*/c:ViewReferences/o:ViewReference/a:Code", "code"); 
	    digester.addSetProperties("*/c:ViewReferences/o:ViewReference/c:TableView1/o:Table","Ref", "refFatherTable"); 
	    digester.addSetProperties("*/c:ViewReferences/o:ViewReference/c:TableView2/o:View","Ref", "refChildTable"); 
	    digester.addSetProperties("*/c:ViewReferences/o:ViewReference/c:ViewReference.Joins/o:ViewReferenceJoin/c:Column1/o:Column","Ref", "refFatherColumn"); 
	    digester.addSetProperties("*/c:ViewReferences/o:ViewReference/c:ViewReference.Joins/o:ViewReferenceJoin/c:Column2/o:ViewColumn","Ref", "refChildColumn"); 
	    
	    List<DBViewReference> parse = (List<DBViewReference>) digester.parse(power);
		return parse;
	}
	
//	public List<DBTableKeywords> getTableKeyWords() throws IOException, SAXException{
//		Digester digester = new Digester();  
//	    digester.setDebug(1);
//	    digester.setValidating(false);
//	    
//	    digester.addObjectCreate("*/c:Keywords", ArrayList.class);
//	    digester.addObjectCreate("*/c:Keywords/o:Keyword", DBTableKeywords.class);
//	    
//	    digester.addSetNext("*/c:Keywords/o:Keyword", "add");
//	    
//	    digester.addSetProperties("*/c:Keywords/o:Keyword", "Id", "oid");
//	    digester.addBeanPropertySetter("*/c:Keywords/o:Keyword/a:Name", "name");
//	    List<DBTableKeywords> parse = (List<DBTableKeywords>) digester.parse(power);
//	    return parse;
//		
//	}
	
	@SuppressWarnings("unchecked")
	public List<DBTableGroupSymbol> getTableGroups() throws IOException, SAXException{
		Digester digester = new Digester();  
		digester.setLogger(log);
	    digester.setValidating(false);

	    
	    digester.addObjectCreate("*/o:PhysicalDiagram/c:Symbols", ArrayList.class);
	    digester.addObjectCreate("*/o:PhysicalDiagram/c:Symbols/o:GroupSymbol", DBTableGroupSymbol.class);
	    digester.addObjectCreate("*/o:PhysicalDiagram/c:Symbols/o:GroupSymbol/c:Symbols/o:TableSymbol", DBTableTableSymbol.class);
	    
	    digester.addSetNext("*/o:PhysicalDiagram/c:Symbols/o:GroupSymbol", "add");
	    digester.addSetNext("*/o:PhysicalDiagram/c:Symbols/o:GroupSymbol/c:Symbols/o:TableSymbol", "addTs");

	    digester.addSetProperties("*/o:PhysicalDiagram/c:Symbols/o:GroupSymbol", "Id", "id");
	    
	    digester.addSetProperties("*/o:PhysicalDiagram/c:Symbols/o:GroupSymbol/c:Symbols/o:TableSymbol/c:Object/o:Table", "Ref", "tid");
	    List<DBTableGroupSymbol> parse = (List<DBTableGroupSymbol>) digester.parse(power);
	    return parse;
	}
}
