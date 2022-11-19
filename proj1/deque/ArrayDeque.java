package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>{


    /*The starting size of your array should be 8*/
    T[] items;
    int size;
    //head指向第一个元素,tail指向尾后元素
    int head, tail;

    // Creates an empty array deque
    @SuppressWarnings("all")
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        head = tail = 0;
    }

    //helper
    private int getIndex(int i){
        return (head + i) % items.length;
    }
    @SuppressWarnings("all")
    private void resize(int capacity){
        T[] copy = (T[]) new Object[capacity];
        for(int i = 0; i < size; ++i)
            copy[i] = items[getIndex(i)];
        head = 0;
        tail = size;
        items = copy;
    }
    //take constant time
    public void addFirst(T item){
        if (size == items.length){
            resize(size * 2);
        }
        head = (--head + items.length) % items.length;
        items[head] = item;
        size++;
    }
    public void addLast(T item){
        if (size == items.length)
            resize(size * 2);
        items[tail] = item;
        tail = (++tail) % items.length;
        size++;
    }
    public T removeFirst(){
        if (isEmpty())
            return null;
        T ret = items[head];
        items[head] = null;
        head = (++head) % items.length;
        --size;
        if (size * 1.0 / items.length <= 0.25)
            resize(items.length / 2);
        return ret;
    }
    public T removeLast(){
        if (isEmpty())
            return null;
        tail = (--tail + items.length) % items.length;
        T ret = items[tail];
        items[tail] = null;
        --size;
        if (size * 1.0 / items.length <= 0.25)
            resize(items.length / 2);
        return ret;
    }

    // take constant time
    public T get(int index){
        return items[getIndex(index)];
    }
    public int size(){
        return size;
    }

    public void printDeque(){
        int p = head;
        while (p != tail){
            System.out.print(items[p]);
            p = ++p % items.length;
            if (p != tail)
                System.out.print("->");
        }
    }
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (this.getClass() != o.getClass())
            return false;
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (this.size != other.size)
            return false;
        for (int i = 0; i < size; ++i)
            if (this.get(i) != other.get(i))
                return false;
        return true;
    }

    private class ArrayDequeIterator implements Iterator<T>{

        int cursor;
        public ArrayDequeIterator() {cursor = 0;}
        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            return get(cursor++);
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
}
