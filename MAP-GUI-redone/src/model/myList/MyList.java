package model.myList;

import java.util.List;

public interface MyList<V> {
    V get(int position);
    void add(V value);
    void printValues();

    List<V> getContent();
}
