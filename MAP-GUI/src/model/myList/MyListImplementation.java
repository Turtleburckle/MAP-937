package model.myList;

import java.util.ArrayList;
import java.util.List;

public class MyListImplementation<V> implements MyList<V>{

    private List<V> list = new ArrayList<>();

    public MyListImplementation(List<V> programstates) {
        this.list = programstates;
    }
    public MyListImplementation() {
    }

    @Override
    public V get(int position) {return list.get(position);}

    @Override
    public void add(V value) {list.add(value);}

    @Override
    public void printValues(){list.forEach(System.out::println);}

    @Override
    public List<V> getContent() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(V v : list) {
            stringBuilder.append(v);
        }
        return stringBuilder.toString();
    }
}
