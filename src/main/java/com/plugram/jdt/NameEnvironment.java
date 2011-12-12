package com.plugram.jdt;

import org.eclipse.jdt.internal.compiler.classfmt.ClassFileReader;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFormatException;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;


public class NameEnvironment implements INameEnvironment {

	@Override
	public void cleanup() {
	}

	@Override
	public NameEnvironmentAnswer findType(char[][] compoundTypeName) {
		final StringBuffer result = new StringBuffer();
		for (int i = 0; i < compoundTypeName.length; i++) {
			if (i != 0) {
				result.append('.');
			}
			result.append(compoundTypeName[i]);
		}
		return findType(result.toString());
	}

	@Override
	public NameEnvironmentAnswer findType(char[] typeName, char[][] packageName) {
		final StringBuffer result = new StringBuffer();
		for (int i = 0; i < packageName.length; i++) {
			result.append(packageName[i]);
			result.append('.');
		}
		result.append(typeName);
		return findType(result.toString());
	}
	
	private NameEnvironmentAnswer findType(final String name) {
		if (name.startsWith("java.") || name.startsWith("javax.")) {
			return null;
		}
		
		ClassFileReader classFileReader;
		try {
			classFileReader = new ClassFileReader(FileUtil.getClassBytes(name), name.toCharArray(), true);
			return new NameEnvironmentAnswer(classFileReader, null);
		} catch (ClassFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public boolean isPackage(char[][] parentPackageName, char[] packageName) {
		
		StringBuilder sb = new StringBuilder();
		if (parentPackageName != null) {
			for (char[] p : parentPackageName) {
				sb.append(new String(p));
				sb.append(".");
			}
		}
		sb.append(new String(packageName));
		String name = sb.toString();

		// キャッシュされたパッケージ名に存在する
		if (StringCache.PACKAGE_NAME_CACHE.isCached(name)) {
			return true;
		}
		// キャッシュされたクラス名に存在する
		if (StringCache.CLASS_NAME_CACHE.isCached(name)) {
			return false;
		}
		StringCache.PACKAGE_NAME_CACHE.store(name);
		return true;
	}

}
