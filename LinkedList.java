package linkedListPractice;
import java.util.NoSuchElementException;

/**
 * Class: LinkedList
 * 
 * @author Robert Zank
 * @version 1.0 Course : CSE 274 Fall 2023 Written: September 28, 2023
 *
 *          This class can be used to make a linked List. 
 *
 *          Purpose: – This class can be used to make a LinkedList for
 *          generic objects.
 * @param <T>
 */

public class LinkedList<T> {
    
    private Node<T> head;
    private Node<T> tail;
    private int size;   
    
    /*
     * Creates an empty list.
     */
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    /*
     * Returns a space-separated list of the elements in the list.
     * If the list contains no elements, return an empty string ""
     */
    public String toString() {
        if (size == 0) {
            return "";
        } else {
            String result = "";
            Node<T> current = head;
            while (current != null) {
                result = result + current.data + " ";
                current = current.next;
            }
            return result;
        }
    }
    
    
    /*
     * Returns the first item in the list. If the list is empty,
     * throw a NoSuchElementException.
     */
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.getAt(0);
    }
    
    /*
     * Returns the last item in the list. If the list is empty,
     * throw a NoSuchElementException.
     */
    public Object getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.getAt(size - 1);
    }
    
    /*
     * Returns the item at the specified index. If the index
     * is not valid, throw an IndexOutOfBoundsException.
     */
    public T getAt(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return (T) head.data;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.data;
        }
    }
    
    /*
     * Inserts an item at the beginning of the list.
     */
    public void insertFirst(T temp) {
        Node<T> hold = new Node<T>(temp);
        if (size == 0) {
            head = hold;
            tail = hold;
        } else {
            hold.next = head;
            head = hold;
        }
        size++;
    }
    
    /*
     * Inserts an item at the end of the list.
     */
    public void insertLast(T temp) {
        Node<T> hold = new Node<T>(temp);
        if (size == 0) {
            size = 1;
            head = hold;
            tail = hold;
        } else {
            tail.next = hold;
            tail = hold;
            size++;
        }
    }
    
    /*
     * Removes and returns the first element from the list.  If the 
     * list is empty, throw a NoSuchElementException.
     */
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } 
        if (size == 1) {
           T firstData = head.data;
           head = null;
           tail = null;
           size--;
           return firstData;
        }
        else {
            T firstData = head.data;
            head = head.next;
            size--;
            return firstData;
        }
        
    }
    
    /*
     * Removes and returns the last element from the list.  If the 
     * list is empty, throw a NoSuchElementException.
     */
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T lastData = tail.data;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            //find the node that refers to tail
            Node<T> current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        size--;
        return lastData;
    }
    
    
    
    /*
     * Returns the number of elements in the list.
     */
    public int getSize() {
        return size;
    }
    
    /*
     * Returns true if the list is empty, and false otherwise.
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    
    // A private Node class.  By making it an inner class, 
    // the outer class can access it easily, but the client cannot. 
    @SuppressWarnings("hiding")
    private class Node<T> {
        private T data;
        private Node<T> next;

        // Constructs a new node with the specified data
        private Node(T temp) {
            this.data = temp;
            this.next = null;
        }
    }
}
