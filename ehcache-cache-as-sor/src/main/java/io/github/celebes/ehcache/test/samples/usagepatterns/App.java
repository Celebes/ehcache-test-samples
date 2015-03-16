package io.github.celebes.ehcache.test.samples.usagepatterns;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class App {
	public static void main(String[] args) {
	    // Utworz obiekt typu Book
	    Book book = new Book("H2G2", "The Hitchhiker's Guide to the Galaxy", 12.5F, "1-84023-742-2", 354, false);

	    // Pozyskaj entity managera
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter04PU");
	    EntityManager em = emf.createEntityManager();
	    
	    // utworz CacheManager
        CacheManager manager = CacheManager.newInstance("src/main/resources/ehcache.xml");
        
        // pobierz cache
        Cache cache = manager.getCache("sor");
	    
        // zapisz obiekt typu Book
        System.out.println("######### SAVING...");
        BookDaoImpl bookDao = new BookDaoImpl(cache, em);
        bookDao.save(book);
        
        // pobierz Book
        System.out.println("######### LOADING...");
        Book resultThatIsInCache = bookDao.findById(book.getId());
        Book resultThatIsNotInCache = bookDao.findById(1000L);

        System.out.println("######### " + resultThatIsInCache.getDescription());
        System.out.println("######### " + resultThatIsNotInCache.getDescription());

	    // Pozamykaj co trzeba
        manager.shutdown();
	    em.close();
	    emf.close();
	}
}

