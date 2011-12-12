package com.plugram.jdt;

import java.util.ArrayList;
import java.util.List;

public enum StringCache {

	PACKAGE_NAME_CACHE, CLASS_NAME_CACHE;
	
	private List<String> cache = new ArrayList<String>();
	
	/**
	 * キャッシュされているか
	 * 
	 * @param value
	 * @return
	 */
	public boolean isCached(String value){
		return cache.contains(value);
	}
	
	/**
	 * キャッシュに保存する
	 * 
	 * @param value
	 */
	public void store(String value){
		cache.add(value);
	}
}
