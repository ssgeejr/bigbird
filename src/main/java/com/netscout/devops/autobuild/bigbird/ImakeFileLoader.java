package com.netscout.devops.autobuild.bigbird;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ImakeFileLoader {
	private ArrayList<SymbolicConstant> constantArray = new ArrayList<SymbolicConstant>();
	private Hashtable<String, String> outputFormat = new Hashtable<String, String>();
	
	public ImakeFileLoader() {
		try{
			outputFormat.put("DefineCorePackage", "DefineCorePackage(ALIAS,SPACER0FILENAME,SPACER1DEPS)COMMENTS");
			outputFormat.put("DefinePackage", "DefinePackage(ALIAS,SPACER0FILENAME,SPACER1DEPS)COMMENTS");
			outputFormat.put("PackageDeps", "PackageDeps(ALIAS,SPACER0DEPS)COMMENTS");
			outputFormat.put("DefineCorePackage", "DefineCorePerlModule(ALIAS,SPACER0FILENAME)COMMENTS");
			outputFormat.put("DefineCorePackage", "DefinePerlModule(ALIAS,SPACER0FILENAME)COMMENTS");
			parseFile();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void parseFile() throws Exception{
		BufferedReader input = null;
		List<String> list = new ArrayList<String>();
		List<String> zipper = new ArrayList<String>();
		try{
        	input = new BufferedReader(new FileReader("C:\\development\\projects\\bigbird\\src\\test\\resources\\Imakefile"));
            String line;
            while ( (line = input.readLine()) != null ) {
            	if( line.startsWith("Package") || line.startsWith("Define") ){
            		String data = line.substring(0,line.indexOf('(')).trim();
            		if(!list.contains(data)){
            			list.add(data);
            			zipper.add(line);
            		}
            	}
//            	if(line.trim().startsWith("#define")){
//            		constantArray.add(new SymbolicConstant(line));
//            		System.out.println("<<CONSTANT>> [" + line + "]");
//            	}
//                System.out.println(line);
            } 
           
            int id = 0;
            for (String dep : list) {
				System.out.println(dep + " :: " + zipper.get(id));
				;
				id++;
			}
   
		}finally{
			try{input.close();}catch(Exception ex){}
		}
		
		int maxAliasLen = 0;
		for(SymbolicConstant sc : constantArray){
			if (sc.getAlias().length() > maxAliasLen) maxAliasLen = sc.getAlias().length();
		}
		int maxReferenceLen = 0;
		for(SymbolicConstant sc : constantArray){
			if (sc.getFilename().length() > maxReferenceLen) maxReferenceLen = sc.getFilename().length();
		}
		
		String aliasSpacer = "";
		for (int i = 0; i < maxAliasLen; i++) {
			aliasSpacer += " ";
		}
		String referenceSpacer = "";
		for (int i = 0; i < maxAliasLen; i++) {
			referenceSpacer += " ";
		}
		
		for(SymbolicConstant sc : constantArray){
			System.out.println(sc.toString());
			System.out.format(sc.format, aliasSpacer, sc.getAlias(), aliasSpacer, sc.getFilename());
		}
		
		aliasSpacer = "";
		for (int i = 0; i < maxAliasLen; i++) {
			aliasSpacer += " ";
		}		
		
	}
/*
	constantsArray
	#define
	
	// Define Core Packages
	// These are built first and in the order listed	
	masterCorePackageArray
	DefineCorePackage	
	
	// Packages built for all platforms
	// These are built in any order based on dependencies
	platformDefineArray
	DefinePackage	

	// GLIB/GTK+ V1.x and everything that depends on it.
	glibGtkDefineArray
	DefinePackage
	
	GlibGtkPackageDep
	PackageDeps
	
	// Package Dependencies
	//  $(TOP)/$(INSTALL_ARCH)/sea-local.deps
	packageDependenciesArray
	PackageDeps
	
	// Define Perl modules -- these will be built after PERL
	// POD::Simple is required by nearly every PERL module -- force it to build first
	// Time modules are also a baseline requirement
	corePerlModuleArray
	DefineCorePerlModule
	
	PerlModule
	DefinePerlModule
	
	// The remainder are built in parallel using the perl module dependencies below
	perlModuleArray
	DefinePerlModule
	
	
	// Perl Module Deps
	perlModuleDependenciesArray
	PackageDeps
	
*/	
	public static void main(String args[]){
		new ImakeFileLoader();
	}
}
