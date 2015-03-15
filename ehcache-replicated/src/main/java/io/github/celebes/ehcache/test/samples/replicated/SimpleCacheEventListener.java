package io.github.celebes.ehcache.test.samples.replicated;

import io.github.celebes.ehcache.test.samples.replicated.model.Foo;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

public class SimpleCacheEventListener implements CacheEventListener {
	@Override
	public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
		System.out.println("\nElement removed: " + element.getObjectValue().getClass());
	}

	@Override
	public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
		System.out.println("\nElement added: " + element.getObjectValue().getClass());
		
		if(element.getObjectValue() instanceof Foo) {
			System.out.println("Event, added: " + (Foo)element.getObjectValue());
		}
	}

	@Override
	public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
		System.out.println("\nElement updated: " + element.getObjectValue().getClass());
		
		if(element.getObjectValue() instanceof Foo) {
			System.out.println("Event, updated: " + (Foo)element.getObjectValue());
		}
	}

	@Override
	public void notifyElementExpired(Ehcache cache, Element element) {}

	@Override
	public void dispose() {}
	
	public Object clone() {
		return null;
	}

	@Override
	public void notifyElementEvicted(Ehcache arg0, Element arg1) {}

	@Override
	public void notifyRemoveAll(Ehcache arg0) {}
}
