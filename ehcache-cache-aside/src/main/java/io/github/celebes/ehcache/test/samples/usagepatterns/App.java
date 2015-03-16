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
        CacheManager manager = CacheManager.newInstance();
        
        // utworz Cache
        manager.addCache("test");
        
        // dodaj Elementy (klucz-wartosc)
        Cache cache = manager.getCache("test");
	    
        // zapisz obiekt typu Book
        BookDaoImpl bookDao = new BookDaoImpl(cache, em);
        bookDao.save(book);
        
        // pobierz Book (pobierze z bazy danych i zapisze do cache)
        Book result = bookDao.findById(1L);
        
        // pobierz Book (pobierze tym razem z cache)
        Book result2 = bookDao.findById(1L);

        System.out.println("######### " + result2.getDescription());

	    // Pozamykaj co trzeba
        manager.shutdown();
	    em.close();
	    emf.close();
	}
}

