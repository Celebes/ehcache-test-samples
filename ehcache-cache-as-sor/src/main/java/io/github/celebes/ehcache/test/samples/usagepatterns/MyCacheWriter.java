package io.github.celebes.ehcache.test.samples.usagepatterns;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import net.sf.ehcache.CacheEntry;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.writebehind.operations.SingleOperationType;

/*
 * pozwala cache na skorzystanie z wzorców write-through oraz write-behind
 */

public class MyCacheWriter implements CacheWriter {
	private EntityManager em;
	
	@Override
	public CacheWriter clone(Ehcache cache) throws CloneNotSupportedException {
		throw new CloneNotSupportedException(); 
	}

	@Override
	public void init() {}

	@Override
	public void dispose() throws CacheException {}

	@Override
	public void write(Element element) throws CacheException {
		System.out.println("MyCacheWriter -> write()");
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.persist((Book)element.getObjectValue());
	    tx.commit();
	}

	@Override
	public void writeAll(Collection<Element> elements) throws CacheException {
		for (Element element : elements) {
			write(element);
		}
	}

	@Override
	public void delete(CacheEntry entry) throws CacheException {}

	@Override
	public void deleteAll(Collection<CacheEntry> entries) throws CacheException {}

	@Override
	public void throwAway(Element element, SingleOperationType operationType, RuntimeException e) {}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
