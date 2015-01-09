package com.celula.tmtool.util;

import java.util.Comparator;

public class TableComparater implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		int result = 0;
		if(o1 instanceof Integer && o2 instanceof Integer){
			result= (int)o1 - (int)o2;
		}else if(o1 instanceof String && o2 instanceof String){
			result = ((String)o1).compareTo((String)o2);
		}
		return result;
	}

}
