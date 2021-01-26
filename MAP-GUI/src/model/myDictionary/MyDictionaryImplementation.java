package model.myDictionary;

import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

public class MyDictionaryImplementation<K,V> implements MyDictionary<K,V> {
    private Map<K,V> dictionary = new Hashtable<>();

    @Override
    public MyDictionary<K,V> clone(){
        Map<K, V> collect = dictionary.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        MyDictionary<K,V> myDictionary = new MyDictionaryImplementation<>();
        myDictionary.setContent(collect);
        return myDictionary;
    }

    @Override
    //Returns the value stored on the key
    public V get(K key){return dictionary.get(key);}

    @Override
    public Map<K,V> getContent(){return dictionary;}

    @Override
    //Puts a value on a key
    public void put(K key,V value){dictionary.put(key,value);}

    @Override
    //Returns true if the key exists
    public boolean isDefined(K key){return dictionary.containsKey(key);}

    @Override
    public void setContent(Map<K, V> map) {
        this.dictionary = map;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(K key : dictionary.keySet()){
            stringBuilder.append("Key: ").append(key).append(" --> Value: ").append(dictionary.get(key)).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void remove(K key){
        dictionary.remove(key);
    }
}
