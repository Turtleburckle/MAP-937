package model.myDictionary;

import java.util.Hashtable;
import java.util.Map;

public interface MyDictionary<K,V>{
    MyDictionary<K,V> clone();
    V get(K key);
    void put(K key, V value);
    boolean isDefined(K key);
    void setContent(Map<K,V> map);
    Map<K,V> getContent();
    void remove(K key);
}
