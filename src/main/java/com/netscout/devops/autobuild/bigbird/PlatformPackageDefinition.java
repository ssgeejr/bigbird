package com.netscout.devops.autobuild.bigbird;

import java.util.Arrays;
import java.util.*;

// Sample
//DefinePackage(NCURSES,         ncurses-5.9,             SPARC_SOL LINUX)

public class PlatformPackageDefinition extends ValueObjectHandler{
	public static final String format = "DefinePackage(%s, %s, %s)";
	
	public PlatformPackageDefinition() {}
	public PlatformPackageDefinition(String line) {
		List<String> item = Arrays.asList(line.trim().split(","));
		this.alias = item.get(0).trim();
		this.alias = alias.substring((alias.indexOf('(') + 1));
		this.filename = item.get(1).trim();
		String tmpFilename = item.get(2).trim();
//		System.out.println("TV: " + tmpFilename);
		tmpFilename = tmpFilename.substring(0,tmpFilename.indexOf(')'));
		this.dependencies = Arrays.asList(tmpFilename.trim().split("\\s+"));
//		this.alias = item.get(1);
//		this.filename = item.get(2);
		System.out.println(this.toString());
//		for(String x : item){
//			System.out.println(x);
//		}
	}
	public PlatformPackageDefinition(String alias, String filename) {
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
		StringBuffer buffer = new StringBuffer();
		buffer.append("PlatformPackageDefinition:DefinePackage: Alias [" + alias + "] Reference [" + filename + "] Version [");
		for(String x : dependencies){
			buffer.append(x + " ");
		}
		return buffer.toString().trim() + "]";
	}
	
	public static void main(String args[]){
		new PlatformPackageDefinition("DefinePackage(NCURSES,         ncurses-5.9,             SPARC_SOL LINUX)");
	}

}
