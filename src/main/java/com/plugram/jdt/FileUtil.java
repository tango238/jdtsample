package com.plugram.jdt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {

	private FileUtil(){
	}
	
	public static char[] getClassSource(String className){
		byte[] bytes = getClassBytes(className);
		String str = new String(bytes);
		return str.toCharArray();
	}
	
	public static byte[] getClassBytes(String className) {
		return getBytes(Main.SOURCE_DIR +  className.replace(".", "/") + ".java");
	}
	
	public static byte[] getBytes(String path) {
		byte[] bytes = null;

		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
			byte[] buf = new byte[8192];
			StringBuilder sb = new StringBuilder();
            while (bis.read(buf, 0, buf.length) != -1) {
            	sb.append(new String(buf));
            }
            String str = sb.toString();
            bytes = str.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}
}
