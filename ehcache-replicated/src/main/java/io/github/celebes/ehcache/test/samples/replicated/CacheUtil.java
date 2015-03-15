package io.github.celebes.ehcache.test.samples.replicated;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

public class CacheUtil {

	private static CacheManager cacheManager = null;
	private static Ehcache cache = null;
	
	public static String CACHE_NAME = "events";
	
	public static Ehcache getCache() {
		if(cacheManager == null){
			System.out.println("Cache manager not initialized");
		}
			
		if(cache == null){
			cache = cacheManager.getEhcache(CACHE_NAME);
		}
		
		return cache;
	}
	
	public static void shutdownCacheManager() {
		cacheManager.shutdown();
	}

	public static void initializeCacheManager(int number) {
		String fileUrl = "src/main/resources/ehcache" + number + ".xml";
		cacheManager = CacheManager.newInstance(fileUrl);
		cache = cacheManager.getCache(CACHE_NAME);
	}
	
}
