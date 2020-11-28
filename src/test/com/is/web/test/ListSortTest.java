package com.is.web.test;


import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.tools.ReadpowerFile;
import com.tools.entity.DBTable;


public class ListSortTest {
	static String webname = "ytzx";
	static String pdmname = "../../create/pdm/zhixuan.pdm";
	static String packagename="com.is.web." + webname;
	static String mapperpackagename="mappers.is.web." + webname;
	
	@Test
	public void test() throws IOException, SAXException {
		String filePath = ListSortTest.class.getResource("/").getPath().replace("/bin/", "/");
		File f = new File(filePath + pdmname);
		ReadpowerFile power = new ReadpowerFile();
		power.setPower(f);
		//所有表格信息
		List<DBTable> pdmTables = power.getTables(); 
		
		CollectionUtils.collect(pdmTables, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				DBTable t = (DBTable) arg0;
				t.setPackname(packagename);
				t.setMapperpackatename(mapperpackagename);
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
				if(StringUtils.isBlank(o1.getTableGroupOid()))
					return -1;
				else if(StringUtils.isBlank(o2.getTableGroupOid()))
					return 1;
				else if(StringUtils.equals(o1.getTableGroupOid(), o2.getTableGroupOid()))
					return 0;
				else{
//					System.out.println(o1.getTableGroupOid() + " - " + o2.getTableGroupOid());
					return o1.getTableGroupOid().compareTo(o2.getTableGroupOid());
				}
			}
			
		});
	}

}
