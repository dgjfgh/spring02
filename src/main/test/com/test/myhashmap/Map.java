package com.test.myhashmap;

public interface Map<K,V> {
	
	
	public V put(K k, V v);
	public V get(K k);
	
	public interface Entry<K,V>{
		public K getKey();
		public V getValue();
		
	}
}
