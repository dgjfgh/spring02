package com.test.hash;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


public final class KetamaNodeLocator {
	
	private TreeMap<Long, String> ketamaStrings;
	private HashAlgorithm hashAlg;
	private int numReps = 160;
	
    public KetamaNodeLocator(List<String> Strings, HashAlgorithm alg, int StringCopies) {
		hashAlg = alg;
		ketamaStrings=new TreeMap<Long, String>();
		
        numReps= StringCopies;
        
		for (String node : Strings) {
			for (int i = 0; i < numReps / 4; i++) {
				byte[] digest = hashAlg.computeMd5(node + i);
				for(int h = 0; h < 4; h++) {
					long m = hashAlg.hash(digest, h);
					
					ketamaStrings.put(m, node);
				}
			}
		}
    }

	public String getPrimary(final String k) {
		byte[] digest = hashAlg.computeMd5(k);
		String rv=getStringForKey(hashAlg.hash(digest, 0));
		return rv;
	}

	String getStringForKey(long hash) {
		final String rv;
		Long key = hash;
		if(!ketamaStrings.containsKey(key)) {
			SortedMap<Long, String> tailMap=ketamaStrings.tailMap(key);
			if(tailMap.isEmpty()) {
				key=ketamaStrings.firstKey();
			} else {
				key=tailMap.firstKey();
			}
			//For JDK1.6 version
//			key = ketamaStrings.ceilingKey(key);
//			if (key == null) {
//				key = ketamaStrings.firstKey();
//			}
		}
		
		
		rv=ketamaStrings.get(key);
		return rv;
	}
}
