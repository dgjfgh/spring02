package com.test.myhashmap;


import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V> implements Map<K, V> {

    private static int defaltLength = 16;
    private static double defaltLoader = 0.75d;
    private Entry<K, V> table[] = null;
    private int size = 0;

    public HashMap() {
        this(defaltLength, defaltLoader);
    }

    public HashMap(int length, double loader) {
        HashMap.defaltLength = length;
        HashMap.defaltLoader = loader;
        table = new Entry[defaltLength];
        //TODO
    }

    public int hash(K k) {
        int m = defaltLength;
        return k.hashCode() % m;
    }

    public V get(K k) {
        int index = hash( k);
        Entry<K, V> entry = table[index];
        if (null == entry) {
            return null;
        }
        return findValueByEqualKey(k, entry);
    }

    private V findValueByEqualKey(K k, Entry<K, V> entry) {
        if (k == entry.getKey() || k.equals(entry.getKey())) {
            return entry.getValue();
        } else {
            if (entry.next != null) {
                return findValueByEqualKey(k, entry.next);
            }
        }
        return null;
    }

    public V put(K k, V v) {
        if (size >= defaltLength * defaltLoader) {
            up2size();
        }

        int index = hash(k);
        Entry<K, V> entry = table[index];
        if (null == entry) {
            table[index] = new Entry<K, V>(k, v, null);
            size++;
        } else {
            table[index] = new Entry<K, V>(k, v, entry);

        }
        return table[index].getValue();
    }

    /**
     * 扩容
     */
    private void up2size() {
        Entry<K, V>[] newTable = new Entry[defaltLength * 2];
        //再次hash
        againHash(newTable);
    }

    private void againHash(Entry<K, V>[] newTable) {
        List<Entry<K, V>> list = new ArrayList<Entry<K, V>>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            } else {
                findEntryByNext(table[i], list);
            }
        }

        if (!list.isEmpty()) {
            size = 0;
            defaltLength = defaltLength * 2;
            table = newTable;
            for (Entry<K, V> entry : list) {
                if (null != entry.next) {
                    entry.next = null;
                }
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    private void findEntryByNext(Entry entry, List<Entry<K, V>> list) {
        if (entry != null && entry.next != null) {
            list.add(entry);
            findEntryByNext(entry.next, list);
        } else {
            list.add(entry);

        }
    }

    public int size() {
        return size;
    }

    class Entry<K, V> implements Map.Entry<K, V> {

        K k;
        V v;
        Entry<K,V> next;

        public Entry(K k, V v, Entry next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }

    }

}
