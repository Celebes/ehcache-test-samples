package io.github.celebes.ehcache.test.samples.usagepatterns;

import javax.persistence.EntityManager;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.blocking.CacheEntryFactory;

/*
 * pozwala cache na skorzystanie z wzorca read-through
 */

public class MyCacheEntryFactory implements CacheEntryFactory {
	private EntityManager em;
	
	@Override
	public Object createEntry(Object key) throws Exception {
		System.out.println("MyCacheEntryFactory -> createEntry");
		System.out.println("KEY = " + key);
		Long id = (Long)key;
		System.out.println("ID = " + id);
		return em.createNamedQuery("findBookById", Book.class).setParameter("id", id).getSingleResult();
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
}
