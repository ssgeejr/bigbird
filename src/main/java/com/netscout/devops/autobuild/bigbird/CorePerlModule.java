package com.netscout.devops.autobuild.bigbird;

import java.util.Arrays;
import java.util.List;

// Sample
//DefineCorePerlModule(TIME_DATE,       TimeDate-1.16)

public class CorePerlModule extends ValueObjectHandler{
	public static final String format = "DefineCorePerlModule(%s, %s)";
	
	public CorePerlModule() {}
	public CorePerlModule(String line) {
		List<String> item = Arrays.asList(line.trim().split(","));
		String tmpAlias = item.get(0).trim();
		this.alias = tmpAlias.substring((tmpAlias.indexOf('(') + 1)).trim();
	}

	public CorePerlModule(String alias, String filename) {
		this.alias = alias;
		this.filename = filename;
	}

	public String toString(){
		return "CorePerlModule:DefineCorePerlModule: Alias [" + alias + "] Reference [" + filename + "]";
	}
	
	public static void main(String args[]){
		new CorePerlModule("DefineCorePerlModule(TIME_DATE,       TimeDate-1.16)");
	}
	
}
