package com.hjh.chapter05;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CacheManager {
    private Map<File, byte[]> caches = new HashMap<>();

    public byte[] put(File key, byte[] value) {
        return caches.put(key, value);
    }

    public byte[] get(File key) {
        return caches.get(key);
    }

    public byte[] evict(File key) {
        return caches.remove(key);
    }
}
