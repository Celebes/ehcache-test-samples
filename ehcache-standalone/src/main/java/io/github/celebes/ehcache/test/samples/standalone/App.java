package io.github.celebes.ehcache.test.samples.standalone;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/*
 * W tym przykladzie nie jest potrzebny ehcache.xml, poniewaz w przypadku jego braku
 * zostanie zaladowana domyslna konfiguracja (ehcache-failsafe.xml)
 */

public class App {
    public static void main(String[] args) {
        // utworz CacheManager
        CacheManager manager = CacheManager.newInstance();
        
        // utworz Cache
        manager.addCache("test");
        
        // dodaj Elementy (klucz-wartosc)
        Cache cache = manager.getCache("test");
        cache.put(new Element("tarzan", "Jane"));
        cache.put(new Element("kermit", "Piggy"));
        
        // pobierz wartosc dla wybranego klucza
        Element elt = cache.get("tarzan");
        String sPartner = (elt == null ? null : elt.getObjectValue().toString());
        System.out.println(sPartner);

        String[] cacheNames = manager.getCacheNames();
        System.out.println("ilosc cache: " + cacheNames.length);
        System.out.println("nazwa pierwszego cache: " + cacheNames[cacheNames.length-1]);
        
        manager.shutdown();
    }
}
