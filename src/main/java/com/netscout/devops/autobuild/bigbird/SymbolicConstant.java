package com.netscout.devops.autobuild.bigbird;

import java.util.Arrays;
import java.util.List;

// Sample
// #define X86_SOL   x86-sunos-solaris-%
public class SymbolicConstant extends ValueObjectHandler{
	public static final String format = "#define%s%s%s%s";

	public SymbolicConstant() {}
	public SymbolicConstant(String line) {
		List<String> item = Arrays.asList(line.trim().split("\\s+"));
		this.alias = item.get(1);
		this.filename = item.get(2);
//		System.out.println(this.toString());
	}
	public SymbolicConstant(String alias, String filename) {
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
		return "Constant:#define: Alias [" + alias + "] Reference [" + filename + "]";
	}
	
	public static void main(String args[]){
		new SymbolicConstant("#define 		X86_SOL   x86-sunos-solaris-%");
	}
	
}
