package com.netscout.devops.autobuild.bigbird;

import java.util.Arrays;
import java.util.List;

// Sample
//DefinePerlModule(TEXT_REFORM,         Text-Reform-1.11)

public class PerlModule extends ValueObjectHandler{	
	public static final String format = "DefinePerlModule(%s, %s)";
	
	public PerlModule() {}
	public PerlModule(String line) {
		List<String> item = Arrays.asList(line.trim().split(","));
		String tmpAlias = item.get(0).trim();
		this.alias = tmpAlias.substring((tmpAlias.indexOf('(') + 1));
		String tmpRef = item.get(1).trim();
		this.filename = tmpRef.substring(0,tmpRef.indexOf(')')).trim();
		
//		this.alias = item.get(1);
//		this.filename = item.get(2);
		System.out.println(this.toString());
//		for(String x : item){
//			System.out.println(x);
//		}
	}
	public PerlModule(String alias, String filename) {
		this.alias = alias;
		this.filename = filename;
	}

	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String toString(){
		return "PerlModule:DefinePerlModule: Alias [" + alias + "] Reference [" + filename + "]";
	}
	
	public static void main(String args[]){
		new PerlModule("DefinePerlModule(TEXT_REFORM,         Text-Reform-1.11)");
	}
	
}
