import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleEndedLinkedList<T> implements DoubleEndedList<T> {
   // fields
   public Node front;
   public Node rear;
   public int size;
   //constructor
   public DoubleEndedLinkedList() {
      front = null;
      rear = null;
      size = 0;
   }
   /**
   *
   * Addfirst, addlast methods...
   *
   */
   public void addFirst(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      Node n = new Node(element);
      if (isEmpty()) {
         front = n;
         rear = n;
      }
      else {
         n.next = front; // embed front in n
         front.prev = n; // set front.prev to n
         n.prev = null; // set n.prev to null 
         front = n; // set front Node to Node n.
      }
      size++;
   }
   /**
   *
   */
   public void addLast(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      Node n = new Node(element);
      if (isEmpty()) {
         front = n;
         rear = n;
      }
      else {
         if (size == 1) {
            front.next = n;
            n.prev = front;
            rear = n;
            n.next = null;
         }
         else {
            rear.next = n;
            n.prev = rear;
            rear = n;
            rear.next = null;
         }
      }
      size++;
   }
   /**
   * Removefirst, removelast methods...
   *
   */
   public T removeFirst() {
      if (isEmpty()) {
         return null;
      }
      //store result
      T result = front.element;
      if (size == 1) {
         front = null;
         rear = null;
      } else {
         front = front.next;
         front.prev = null;
      }
      size--;
      return result;
   }
   /**
   *
   */
   public T removeLast() {
      if (isEmpty()) {
         return null;
      }
      T result = rear.element;
      if (size == 1) {
         front = null;
         rear = null;
      } 
      else {
         rear = rear.prev;
         rear.next = null;
         
      
      }
      size--;
      return result;
   }
   /**
   * Other methods
   *
   */
   public int size() {
      return size;
   }
   public boolean isEmpty() {
      return (size == 0);
   }
   public Iterator<T> iterator() {
      return new DoubleEndedLinkedListIterator();
   }
   /**
   *
   * Node class
   *
   *
   */
   public class Node {
   // fields
      public T element;
      public Node next;
      public Node prev;
     //constructors 
      public Node(T e) {
         element = e;
      }
      public Node(T e, Node n) {
         element = e;
         next = n;
      }
   }
   /**
   *
   * Iterator
   *
   *
   */
   private class DoubleEndedLinkedListIterator implements Iterator<T> {
      Node current = front;
      public DoubleEndedLinkedListIterator() {
           
      }
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         } 
         T result = current.element;
         current = current.next;
         return result;
      }
      public boolean hasNext() {
         return (current != null);
      }
      public void remove() {
         throw new UnsupportedOperationException();
      }
   
   }

}