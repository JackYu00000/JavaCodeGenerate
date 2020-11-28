package com.tools.web.pbase;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class InterfaceExclusionStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes field) {
		// TODO Auto-generated method stub
		if(field.getName().endsWith("GreaterThan") ||
				field.getName().endsWith("Mults") ||
				field.getName().endsWith("LessThan") ||
				field.getName().endsWith("BetweenType") ||
				field.getName().endsWith("Chnval") ||
				field.getName().equalsIgnoreCase("orderBys") ||
				field.getName().equalsIgnoreCase("groupBys") ||
				field.getName().equalsIgnoreCase("searchKey") ||
				field.getName().equalsIgnoreCase("searchCols") ||
				field.getName().endsWith("Ids") ||
				field.getName().endsWith("Colum") ||
				field.getName().endsWith("ColumJoin") ||
				field.getName().endsWith("ForRequest")
				)
			return true;
		return false;
	}

}
