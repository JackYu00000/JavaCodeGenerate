package com.tools.test;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.junit.Test;

public class JxlTest {

//	@Test
	public void test() throws IOException, RowsExceededException, WriteException {
		//创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(new File("output.xls"));
        //创建新的一页
		WritableSheet sheet = workbook.createSheet("用户", 0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
		Label label = new Label(0, 2, "0-2"); 
		sheet.addCell(label); 
		Label label2 = new Label(3, 4, "3-4"); 
		sheet.addCell(label2);
		
		workbook.write(); 
		workbook.close();
	}
	
	@Test
	public void test与运算(){
		boolean isSelf = true;
		boolean inSelfTable = true;
		
		System.out.println(isSelf ^ inSelfTable);
	}

}
