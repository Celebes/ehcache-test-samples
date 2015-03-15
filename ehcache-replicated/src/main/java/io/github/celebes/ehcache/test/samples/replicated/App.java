package io.github.celebes.ehcache.test.samples.replicated;

import io.github.celebes.ehcache.test.samples.replicated.model.Foo;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class App {
	public static void main(String[] args) throws InterruptedException {
		String cacheNumber = args[0];
		int number = Integer.valueOf(cacheNumber);
		
		CacheUtil.initializeCacheManager(number);
		
		if(number == 1) {
			Ehcache cache = CacheUtil.getCache();
			int i=0;
			
			System.out.println("Nadawca");
			
			while(true) {
				Foo foo = new Foo();
				foo.setId(i);
				foo.setFooName("Foo_" + i);
				Element e = new Element(foo.getFooName(), foo);
				cache.put(e);
				i++;
				Thread.sleep(1000);
			}
		} else {
			System.out.println("Odbiorca");
			
			while(true) {
				Thread.sleep(100);
			}
		}
	}
}