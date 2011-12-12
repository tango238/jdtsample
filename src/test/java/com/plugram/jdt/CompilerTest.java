package com.plugram.jdt;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.StringTokenizer;

import org.junit.Test;

public class CompilerTest {

	@Test
	public void testName() throws Exception {
		StringTokenizer izer = new StringTokenizer("java.util.HashMap", ".");
		char[][] packageName = new char[izer.countTokens() - 1][];
        for (int i = 0; i < packageName.length; i++) {
            packageName[i] = izer.nextToken().toCharArray();
        }
        assertThat("java", is(new String(packageName[0])));
        assertThat("util", is(new String(packageName[1])));
	}
}
