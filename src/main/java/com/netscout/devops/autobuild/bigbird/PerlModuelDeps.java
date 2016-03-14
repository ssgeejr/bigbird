package com.netscout.devops.autobuild.bigbird;

import java.util.Arrays;
import java.util.*;

// Sample
//PackageDeps(NET_SSH_PERL,     MATH_GMP DIGEST_SHA1 DIGEST_HMAC CRYPT_DSA CRYPT_DH CONVERT_PEM)

public class PerlModuelDeps extends ValueObjectHandler{
	public static final String format = "PackageDeps(%s, %s)";
	public String alias = "";
	public String filename = "";
	public List<String> dependencies = new ArrayList<String>();

	public PerlModuelDeps() {
	}

	public PerlModuelDeps(String line) {
		List<String> item = Arrays.asList(line.trim().split(","));
		this.alias = item.get(0).trim();
		this.alias = alias.substring((alias.indexOf('(') + 1));
		parseMultiValue(item.get(1));
	}

	public PerlModuelDeps(String alias, String filename) {
		this.alias = alias;
		this.filename = filename;
	}
	public String toString() {
		return "PerlModuelDeps:PackageDeps: Alias [" + alias + "] Dependencies [" + dependency + "]";
	}

	public static void main(String args[]) {
		new PerlModuelDeps("PackageDeps(NET_SSH_PERL,     MATH_GMP DIGEST_SHA1 DIGEST_HMAC CRYPT_DSA CRYPT_DH CONVERT_PEM)");
	}
}