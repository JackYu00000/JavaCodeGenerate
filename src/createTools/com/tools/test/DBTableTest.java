package com.tools.test;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tools.entity.DBTable;


public class DBTableTest {

	DBTable tab ;
	@Before
	public void setUp() throws Exception {
		tab = new DBTable();
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void test全小写() {
		tab.setCode("abc");
		assertEquals("Abc", tab.getTab());
		
	}
	@Test
	public void test全大写() {
		tab.setCode("ABC");
		assertEquals("Abc", tab.getTab());
		
	}
	@Test
	public void test有空格() {
		tab.setCode("ABC DB");
		assertEquals("AbcDb", tab.getTab());
		
	}
	@Test
	public void test有下划线() {
		tab.setCode("ABC_DB");
		assertEquals("AbcDb", tab.getTab());
		
	}
	@Test
	public void test有多下划线() {
		tab.setCode("ABC_DB_Sp");
		assertEquals("AbcDbSp", tab.getTab());
		
	}
	@Test
	public void test有混合() {
		tab.setCode("ABC_DB Sp");
		assertEquals("AbcDbSp", tab.getTab());
		
	}
	@Test
	public void test有T() {
		tab.setCode("T_DB Sp");
		assertEquals("DbSp", tab.getTab());
		
	}
	@Test
	public void test有前二个单词不能同时为大写() {
		tab.setCode("T_D_Sp");
		assertEquals("Dsp", tab.getTab());
		
	}
}
