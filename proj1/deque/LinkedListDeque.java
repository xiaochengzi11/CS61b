package deque;

import java.util.ArrayDeque;
import java.util.Comparator;

public class LinkedListDeque<T> implements Deque<T>{
    //Node
    private static class Node<T>{
        T item;
        Node<T> prev;
        Node<T> next;
        Node(T item, Node<T> prev, Node<T> next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }


    private Node<T> sentinel;
    private int size;

    //Creates an empty linked list deque.
    public LinkedListDeque(){
        sentinel = new Node<>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    //add and remove spend "constant time"
    public void addFirst(T item){
        Node<T> node = new Node<T>(item, sentinel, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size++;
    }
    public void addLast(T item){
        Node<T> node = new Node<>(item, sentinel.prev, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
    }
    public T removeFirst(){
        if(!isEmpty()) {
            Node<T> del = sentinel.next;
            T ret = del.item;
            del.item = null;
            sentinel.next = del.next;
            del.next.prev = sentinel;
            del = null;
            size--;
            return ret;
        }
        return null;
    }
    public T removeLast(){
        if (!isEmpty()){
            Node<T> del = sentinel.prev;
            T ret = del.item;
            del.item = null;
            sentinel.prev = del.prev;
            del.prev.next = sentinel;
            del = null;
            --size;
            return ret;
        }
        return null;
    }
    //take constant time
    public int size(){
        return size;
    }
    public void printDeque(){
        Node<T> p = sentinel.next;
        while(p != sentinel){
            System.out.print(p.item);
            if (p.next != sentinel)
                System.out.print("->");
            p = p.next;
        }
    }
    //iterative
    public T get(int index){
        Node<T> p = sentinel.next;
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index!");
        int i = 0;
        while (i != index){
            p = p.next;
            ++i;
        }
        return p.item;
    }
    private T getRecursive(Node<T> node,int index){
        if (index == 0)
            return node.item;
        return getRecursive(node.next, index - 1);
    }
    public T getRecursive(int index){
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index!");
        return getRecursive(sentinel.next, index);
    }
}

