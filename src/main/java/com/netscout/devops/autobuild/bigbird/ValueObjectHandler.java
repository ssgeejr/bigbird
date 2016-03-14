package com.netscout.devops.autobuild.bigbird;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Hashtable;

public class ValueObjectHandler implements ImakefileInterface {
	/*
	 * DefineCorePackage :: DefineCorePackage(ARCH_NAME, arch-name, ALL) //
	 * Internal tek script DefinePackage :: DefinePackage(ANT, apache-ant-1.8.3,
	 * ALL) // Did not upgrade PackageDeps :: PackageDeps(GTK1, GLIB1)
	 * DefineCorePerlModule :: DefineCorePerlModule(POD_SIMPLE, Pod-Simple-3.07)
	 * DefinePerlModule :: DefinePerlModule(ARCHIVE_ZIP, Archive-Zip-1.30)
	 */

	public static final int KEY = 0;
	public static final int FILENAME = 1;
	public static final int DEP = 2;
	private String imakeClass = "";
	private String alias = "";
	private String filename = "";
	private List<String> dependencies = new ArrayList<String>();
	private String dependency = "";
	private String comment = "";
	
	public ValueObjectHandler() { }

	public ValueObjectHandler(String line) {
		List<String> item = Arrays.asList(line.trim().split(","));
		String token = item.get(0).trim();
		imakeClass = token.substring(0, token.indexOf('(')).trim();
		alias = token.substring((token.indexOf('(') + 1)).trim();
		if (imakeClass.equals("DefineCorePackage") || imakeClass.equals("DefinePackage")) {
			parseFilename(item.get(1).trim());
			token = item.get(2).trim();
			parseDeps(item.get(2).trim());
		} else if (imakeClass.equals("PackageDeps")) {
			token = item.get(1).trim();
			parseDeps(token);
		} else {
			token = item.get(1).trim();
			parseFilename(token);
		}
		checkComment(token);
	}

	public void checkComment(String token){
		int commentLocation = token.indexOf("//");
		if(commentLocation > 0){
			System.out.println(token.substring(commentLocation));
		}
	}
	public void parseFilename(String token) {
		if(token.indexOf(')') > 0){
			token = token.substring(0, token.indexOf(')'));
		}		
		filename = token.trim();
	}
	
	public void parseDeps(String token) {
		if(token.indexOf(')') > 0){
			token = token.substring(0, token.indexOf(')'));
		}
		dependencies = Arrays.asList(token.trim().split("\\s+"));
		StringBuffer buffer = new StringBuffer();
		for (String dep : dependencies) {
			buffer.append(dep + " ");
		}
		dependency = buffer.toString().trim();
	}

	public String getComment(){return comment;}
	
	public String getAlias() {
		return alias;
	}

	public String getFilename() {
		return filename;
	}

	public List<String> getDependencies() {
		return dependencies;
	}

	public String getDependency() {
		return dependency;
	}

	public String fetchByKey(int key) {
		String thevalue = null;
		if (key == KEY)
			thevalue = alias;
		else if (key == FILENAME)
			thevalue = filename;
		else if (key == DEP)
			thevalue = dependency;
		return thevalue;
	}

}
