package com.netscout.devops.autobuild.bigbird;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerCode {
	public static final int KEY = 0;
	public static final int FILENAME = 1;
	public static final int DEP = 2;
	public int tokenCount = -1;
	public String imakeClass = "";
	public String alias = "";
	public String filename = "";
	public List<String> dependencies = new ArrayList<String>();
	public String dependency = "";
	
	public PowerCode(String line){
		List<String> item = Arrays.asList(line.trim().split(","));
		tokenCount = item.size();
		
		String tokenOne = item.get(0).trim();
		imakeClass = tokenOne.substring(0,tokenOne.indexOf('(')).trim();
		this.alias = tokenOne.substring((tokenOne.indexOf('(') + 1)).trim();
		
		String tokenTwo = item.get(0).trim();
		
		
	}
	
	public void parseAlias(String val){
		if(val.indexOf(')') > 0){
			val = val.substring(0,val.indexOf(')'));
		}
		this.filename = val.trim();
	}

	public void parseMultiValue(String tmpval){
		tmpval = tmpval.substring(0, tmpval.indexOf(')'));
		this.dependencies = Arrays.asList(tmpval.trim().split("\\s+"));
		StringBuffer buffer = new StringBuffer();
		for (String dep : dependencies) {
			buffer.append(dep + " ");
		}
		this.dependency = buffer.toString().trim();
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
	public List<String> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<String> depsval) {
		StringBuffer buffer = new StringBuffer();
		for (String dep : depsval) {
			buffer.append(dep.trim() + " ");
			this.dependencies.add(dep.trim());
		}
		this.dependency = buffer.toString().trim();
	}
	public String getDependency() {
		return dependency;
	}
	public void setDependency(String depval) {
		this.dependencies = Arrays.asList(depval.trim().split("\\s+"));
		StringBuffer buffer = new StringBuffer();
		for (String dep : this.dependencies) {
			buffer.append(dep.trim() + " ");
		}
		this.dependency = buffer.toString().trim();
	}
	
	public String fetchByKey(int key){
		String thevalue = null;
		if(key == KEY)
			thevalue = alias;
		else if (key == FILENAME)
			thevalue = filename;
		else if (key == DEP)
			thevalue = dependency;
		return thevalue;
	}

}
