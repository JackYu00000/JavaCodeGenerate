package com.tools.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tools.entity.CreateUtils;
import com.tools.entity.DBColumn;

public class DBColumeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test全小写() {
		DBColumn col = new DBColumn();
		col.setCode("abc");
		assertEquals("abc", CreateUtils.getCol(col.getCode()));
		
	}
	@Test
	public void test全大写() {
		DBColumn col = new DBColumn();
		col.setCode("ABC");
		assertEquals("abc", CreateUtils.getCol(col.getCode()));
		
	}
	@Test
	public void test有空格() {
		DBColumn col = new DBColumn();
		col.setCode("ABC DB");
		assertEquals("abcDb", CreateUtils.getCol(col.getCode()));
		
	}
	@Test
	public void test有下划线() {
		DBColumn col = new DBColumn();
		col.setCode("ABC_DB");
		assertEquals("abcDb", CreateUtils.getCol(col.getCode()));
		
	}
	@Test
	public void test有多下划线() {
		DBColumn col = new DBColumn();
		col.setCode("ABC_DB_Sp");
		assertEquals("abcDbSp", CreateUtils.getCol(col.getCode()));
		
	}
	@Test
	public void test有混合() {
		DBColumn col = new DBColumn();
		col.setCode("ABC_DB Sp");
		assertEquals("abcDbSp", CreateUtils.getCol(col.getCode()));
		
	}

}
