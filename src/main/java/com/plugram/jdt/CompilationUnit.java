package com.plugram.jdt;

import java.util.StringTokenizer;

import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;

public class CompilationUnit implements ICompilationUnit {

    final private String clazzName;
    final private String fileName;
    final private char[] typeName;
    final private char[][] packageName;

    public CompilationUnit(String className) {
        clazzName = className;
        if (className.contains("$")) {
            className = className.substring(0, className.indexOf("$"));
        }
        fileName = className.replace('.', '/') + ".java";
        int dot = className.lastIndexOf('.');
        if (dot > 0) {
            typeName = className.substring(dot + 1).toCharArray();
        } else {
            typeName = className.toCharArray();
        }
        StringTokenizer tokenzr = new StringTokenizer(className, ".");
        packageName = new char[tokenzr.countTokens() - 1][];
        for (int i = 0; i < packageName.length; i++) {
            packageName[i] = tokenzr.nextToken().toCharArray();
        }
    }

    public char[] getFileName() {
        return fileName.toCharArray();
    }

    public char[] getContents() {
        //return applicationClasses.getApplicationClass(clazzName).javaSource.toCharArray();
        return FileUtil.getClassSource(clazzName);
    }

    public char[] getMainTypeName() {
        return typeName;
    }

    public char[][] getPackageName() {
        return packageName;
    }
}
