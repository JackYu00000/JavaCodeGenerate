package com.tools.entity.runtime.Interfaces;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.write.WriteException;

import com.tools.entity.DBTable;

public abstract class InterfaceFileBase {
	
	public abstract void interExcel(File path ,List<DBTable> tables) throws WriteException, IOException;
	
	public abstract void interPostMan(File path ,List<DBTable> tables,String webname) throws Exception ;
	
	public abstract void interJsp(File path ,List<DBTable> tables) throws Exception ;

}
