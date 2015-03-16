package io.github.celebes.ehcache.test.samples.usagepatterns;

import javax.persistence.EntityManager;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.SelfPopulatingCache;

public class BookDaoImpl implements BookDao {
	private final Ehcache cache;
	
	public BookDaoImpl(Ehcache cache, EntityManager em) {
		MyCacheWriter myCacheWriter = new MyCacheWriter();
		myCacheWriter.setEm(em);
		
		MyCacheEntryFactory myCacheEntryFactory = new MyCacheEntryFactory();
		myCacheEntryFactory.setEm(em);
		
		cache.registerCacheWriter(myCacheWriter);
		this.cache = new SelfPopulatingCache(cache, myCacheEntryFactory);
	}

	@Override
	public Book findById(Long id) {
		return (Book)cache.get(id).getObjectValue();
	}

	@Override
	public void save(Book book) {
	    cache.putWithWriter(new Element(1L, book));
	}
}
