package io.github.celebes.ehcache.test.samples.usagepatterns;

import java.util.Properties;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.CacheWriterFactory;

public class MyCacheWriterFactory extends CacheWriterFactory {

	@Override
	public CacheWriter createCacheWriter(Ehcache cache, Properties properties) {
		return new MyCacheWriter();
	}

}
