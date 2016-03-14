package com.netscout.devops.autobuild.bigbird;

import java.util.Arrays;
import java.util.List;

// Sample
//DefineCorePackage(CHRPATH,     chrpath-0.13,        ALL)

public class MasterCorePackage extends ValueObjectHandler{
	public static final String format = "DefineCorePackage(%s, %s, %s)";
	
	public MasterCorePackage(String line) {
		List<String> item = Arrays.asList(line.trim().split(","));
		String tmpAlias = item.get(0).trim();
		this.alias = tmpAlias.substring((tmpAlias.indexOf('(') + 1));
		this.filename = item.get(1).trim();
		this.parseMultiValue(item.get(2));
	}

	public String toString(){
		return "MasterCorePackage:DefineCorePackage: Alias [" + alias + "] Reference [" + filename + "] Version [" + dependencies + "]";
	}
	
	public static void main(String args[]){
		new MasterCorePackage("DefineCorePackage(CHRPATH,     chrpath-0.13,        ALL)");
	}
	
}
