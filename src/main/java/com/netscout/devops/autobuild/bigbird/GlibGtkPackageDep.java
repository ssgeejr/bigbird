package com.netscout.devops.autobuild.bigbird;

import java.util.Arrays;
import java.util.*;

// Sample
//PackageDeps(WXGTK,         EXPAT GLIB1 GTK1 LIBPNG ZLIB)

public class GlibGtkPackageDep extends ValueObjectHandler{
	public static final String format = "PackageDeps(%s, %s)";
	
	public GlibGtkPackageDep() {}
	public GlibGtkPackageDep(String line) {
		List<String> item = Arrays.asList(line.trim().split(","));
		this.alias = item.get(0).trim();
		this.alias = alias.substring((alias.indexOf('(') + 1));
		String tmpFilename = item.get(1).trim();
		tmpFilename = tmpFilename.substring(0,tmpFilename.indexOf(')'));
		this.dependencies = Arrays.asList(tmpFilename.trim().split("\\s+"));
	}
	public GlibGtkPackageDep(String alias, String filename) {
		this.alias = alias;
		this.filename = filename;
	}

	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("GlibGtkPackageDep:PackageDeps: Alias [" + alias + "] Dependencies [");
		for(String x : dependencies){
			buffer.append(x + " ");
		}
		return buffer.toString().trim() + "]";
	}
	
	public static void main(String args[]){
		new GlibGtkPackageDep("PackageDeps(WXGTK,         EXPAT GLIB1 GTK1 LIBPNG ZLIB)");
	}
}