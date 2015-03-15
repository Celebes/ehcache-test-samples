package io.github.celebes.ehcache.test.samples.usagepatterns;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class BookDaoImpl implements BookDao {
	private final Cache cache;
	private final EntityManager em;
	
	public BookDaoImpl(Cache cache, EntityManager em) {
		this.cache = cache;
		this.em = em;
	}

	@Override
	public Book findById(Long id) {
		Element element;
		
		long startTime = System.nanoTime();
		
		// sprawdz czy w cache znajduje sie szukana wartosc, jesli tak to ja zwroc
		if((element = cache.get(id.toString())) != null) {
			System.out.println("Pobrano wartość z cache w czasie " + (System.nanoTime() - startTime)/1000000 + " [ms]");
			return (Book)element.getObjectValue();
		}
		
		// w przeciwnym przypadku pobierz wartosc z bazy danych
		Book book = em.createNamedQuery("findBookById", Book.class).setParameter("id", id).getSingleResult();
		System.out.println("Pobrano wartość z bazy danych w czasie " + (System.nanoTime() - startTime)/1000000 + " [ms]");
		// jesli znajduje sie w bazie danych to zaktualizuj cache
		if(book != null) {
			cache.put(new Element(id.toString(), book));
		}
		
		// zwroc pobrana z bazy danych wartosc
		return book;
	}

	@Override
	public void save(Book book) {
		// zapisz do bazy danych
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.persist(book);
	    tx.commit();
	    
	    // zapisz do cache
	    cache.put(new Element(book.getId(), book));
	}
}
