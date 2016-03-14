package com.netscout.devops.autobuild.bigbird;

import java.util.List;
import java.util.Arrays;

public class ImakefileParser {
	public ImakefileParser() {}
	public List<String> parse(String in){
		return Arrays.asList(in.trim().split("\\s+"));
	}
}
