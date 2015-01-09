package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public static void main(String[] args) {
			Pattern compile = Pattern.compile("(bd).+(fe)");
			Matcher matcher = compile.matcher("tbdeefef");
			boolean matches = matcher.find();
			System.out.println(matches);
			if(matches){
				String group = matcher.group(1);
				
				String group2 = matcher.group(2);
				System.out.println(group+":"+group2);
			}
	}

}
