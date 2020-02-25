import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;
/**
* RandomizedListArray.java
* Implementing class for the randomized list.
*
*@author Patrick Spafford
*@version 2-28-2019
*@param <T> is the parameter for this class.
*/
public class RandomizedListArray<T> implements RandomizedList<T> {
   private static final int DEFAULT_CAPACITY = 5;
//fields
   private T[] elements;
   private int size = 0;
   private int front = 0;
   private int rear = 0;
//constructor
/**Unavoidable type safety warning.
*
*/
   @SuppressWarnings("unchecked")
   /**
   * Builds a randomized list of the default length.
   *
   */
   public RandomizedListArray() {
      elements = (T[]) new Object[DEFAULT_CAPACITY];
   }
/**
*Returns the size of the randomized list.
*@return integer value of this property.
*/
   public int size() {
      return size;
   }
/**
*@param index is the location of the element.
*@return element of type T at the index-th position.
*/
   public T get(int index) {
      return elements[index];
   }
/**
* Adds an element to the randomized list.
*@param element is the item added to the list.
*/
   public void add(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      if (isFull()) {
         resize((size * 2));
      }
      elements[rear] = element;
      size++;
      rear++;
   }
   /**
   * Removes a random element from the randomized list.
   * @return the item that you removed.
   */
   public T remove() {
      if (isEmpty()) {
         return null;
      }
      size--;
      rear--;
      if (rear == 0) {
         return elements[rear];
      }
      //generate random element to remove
      Random generator = new Random();
      int randomIndex = generator.nextInt(rear);
      T removedElement = elements[randomIndex];
      T result = elements[randomIndex];
      
      elements[randomIndex] = elements[rear];
      // move random element to back element
      elements[rear] = removedElement;
      elements[rear] = null;
      //rear--;
      //size--;
      return result;
   }
   /**
   * Samples a random item from the list.
   * @return a random item in the list.
   */
   public T sample() {
      if (isEmpty()) {
         return null;
      }
      Random generator = new Random();
      int randomIndex = generator.nextInt(rear);
      return elements[randomIndex];
   }
   /**
   *Checks to see if the list is full.
   *@return the truth value of this condition.
   */
   private boolean isFull() {
      return (elements.length - 1 == (rear));
   }
   /**
   *Resizes the array to a new size when full.
   *@param length is the new length.
   */
   @SuppressWarnings("unchecked")
   private void resize(int newLength) {
      T[] resized = (T[]) new Object[newLength];
      for (int i = 0; i < size; i++) {
         resized[i] = elements[i];
      }
      // size = newLength;
      elements = resized;
   }
   /**
   *Checks to see whether the list is empty.
   *@return boolean value of whether it's empty.
   */
   public boolean isEmpty() {
      return (size == 0);
   }
   /**
   *
   *
   */
   public void shuffle() {
      int index;
      T[] array = elements;
      Random random = new Random();
      for (int i = 0; i < size; i++)
      {
      
         index = random.nextInt(rear + 1);
         array[i] = array[index];
         array[index] = array[i];
      }
   }
   /**
   *Returns an iterator for this randomized list.
   *@return the custom iterator built below, in the nested class.
   */
   public Iterator<T> iterator() {
      return new RandomizedArrayIterator<T>(elements);
   }
/**
*Provides a custom iterator for the RandomizedArray class.
*@param <T> is the type that aligns with the iterator's.
*/
   private class RandomizedArrayIterator<T> implements Iterator<T> {
      private RandomizedListArray<T> randomizedlist;
      private int i;
      private int size2;
      /** 
      *Constructor for this iterator.
      */
      public RandomizedArrayIterator(T[] elements) {
         T[] list = elements;
         randomizedlist = new RandomizedListArray<T>();
         i = 0;
         size2 = 0;
         if (list != null) {
            for (int j = 0; j < size; j++) {
               if (list[j] != null) {
                  randomizedlist.add(list[j]);
                  size2++;
               }
            }
         }
         //shuffle here (29)
         randomizedlist.shuffle();
      }
      /** Returns the next item in the list and then points to the next one.
      * @return the next item as described above.
      */ 
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         return randomizedlist.elements[i++];
      }
      /** Checks to see if there are more items in the list.
      * @return a boolean value of this condition.
      */
      public boolean hasNext() {
         return (i < size2);
         // return (randomizedlist.elements[i] != null);
      }
      /** This remove method is unsupported for now.
      *
      */
      public void remove() {
         throw new UnsupportedOperationException();
      } 
             
   }

}
