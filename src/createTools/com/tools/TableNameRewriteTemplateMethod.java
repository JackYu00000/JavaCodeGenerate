package com.tools;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

public class TableNameRewriteTemplateMethod implements TemplateMethodModel {

	@Override
	public Object exec(List arg0) throws TemplateModelException {
		String temp = arg0.get(0).toString().toLowerCase();
		
		temp = StringUtils.replace(temp, "_", " ");
		if(temp.indexOf("t ")==0){
			temp = StringUtils.substring(temp, 2);
		}
		
		temp = WordUtils.capitalize(temp);
		temp = StringUtils.remove(temp, " ");
		StringBuffer buffer =new StringBuffer();
		if(temp.length()>=2){
			char arr[] = temp.toCharArray();
			int isUpper = 0;
			for(char c :arr){
				if(CharUtils.isAsciiAlphaUpper(c)&&isUpper!=0){
					c = StringUtils.lowerCase(""+c).charAt(0);
					isUpper=0;
				}else if(CharUtils.isAsciiAlphaUpper(c)&&isUpper==0){
					isUpper++;
				}else {
					isUpper=0;
				}
				buffer.append(c);
			}
		}
		return buffer.toString();
	}

}
