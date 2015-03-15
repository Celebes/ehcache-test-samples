package io.github.celebes.ehcache.test.samples.usagepatterns;


public interface BookDao {
	public Book findById(Long id);
	public void save(Book b);
}
