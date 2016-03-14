package com.netscout.devops.autobuild.bigbird;
import java.util.Arrays;
import java.util.*;

// Sample
//PackageDeps(GETTEXT,       BISON LIBUNISTRING ZLIB NCURSES)

public class SeaLocalDep extends ValueObjectHandler{
	public static final String format = "PackageDeps(%s, %s)";
	
	public SeaLocalDep() {}
	public SeaLocalDep(String line) {
		List<String> item = Arrays.asList(line.trim().split(","));
		this.alias = item.get(0).trim();
		this.alias = alias.substring((alias.indexOf('(') + 1));
		String tmpFilename = item.get(1).trim();
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
	public SeaLocalDep(String alias, String filename) {
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
		buffer.append("SeaLocalDep:PackageDeps: Alias [" + alias + "] Dependencies [");
		for(String x : dependencies){
			buffer.append(x + " ");
		}
		return buffer.toString().trim() + "]";
	}
	
	public static void main(String args[]){
		new SeaLocalDep("PackageDeps(GETTEXT,       BISON LIBUNISTRING ZLIB NCURSES)");
	}
}