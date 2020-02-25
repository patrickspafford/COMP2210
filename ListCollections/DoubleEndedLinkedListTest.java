import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;


public class DoubleEndedLinkedListTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   @Test public void addFirstTest() {
      DoubleEndedLinkedList<String> dell = 
         new DoubleEndedLinkedList<String>();
      dell.addFirst("Element 1");
      dell.addFirst("Element 2");
      dell.addFirst("Element 3");
      String expected = dell.rear.element;
      String actual = dell.removeLast();
      Assert.assertEquals(expected, actual);
   }
   @Test public void addLastTest() {
      DoubleEndedLinkedList<String> dell 
         = new DoubleEndedLinkedList<String>();
      dell.addLast("Element 1");
      dell.addLast("Element 2");
      dell.addLast("Element 3");
      String expected = dell.front.element;
      String actual = dell.removeFirst();
      Assert.assertEquals(expected, actual);
   }
   @Test public void removeFirstTest() {
      DoubleEndedLinkedList<String> dell = 
         new DoubleEndedLinkedList<String>();
      dell.addFirst("Element 1");
      dell.addLast("Element 2");
      dell.addFirst("Element 3");
      dell.addLast("Element 4");
      dell.addFirst("Element 5");
      dell.addLast("Element 6");
      String expected = "Element 5";
      String actual = dell.removeFirst();
      Assert.assertEquals(expected, actual);
   }
   @Test public void removeLastTest() {
      DoubleEndedLinkedList<String> dell = 
         new DoubleEndedLinkedList<String>();
      dell.addFirst("Element 1");
      dell.addFirst("Element 2");
      dell.addFirst("Element 3");
      dell.addLast("Element 4");
      dell.addLast("Element 5");
      dell.addLast("Element 6");
      String expected = "Element 6";
      String actual = dell.removeLast();
   
   }
   @Test public void iteratorTest() {
      DoubleEndedLinkedList<String> dell = 
         new DoubleEndedLinkedList<String>();
      dell.addFirst("Element 1");
      dell.addFirst("Element 2");
      dell.addFirst("Element 3");
      dell.addLast("Element 4");
      dell.addLast("Element 5");
      dell.addLast("Element 6");
      Iterator<String> itr = dell.iterator();
      String actual = null;
      while (itr.hasNext()) {
         actual = itr.next();
      }
      String expected = "Element 6";
      Assert.assertEquals(expected, actual);
   }
   @Test public void rigorousTest() {
      DoubleEndedLinkedList<String> dell = 
         new DoubleEndedLinkedList<String>();
      dell.addLast("Element 1");
      dell.addFirst("Element 2");
      dell.addLast("Element 3");
      dell.addLast("Element 4");
      dell.addLast("Element 5");
      dell.addLast("Element 6");
      dell.addFirst("Element 1");
      dell.addFirst("Element 2");
      dell.addFirst("Element 3");
      dell.addLast("Element 4");
      dell.addLast("Element 5");
      dell.addLast("Element 6");
      dell.removeFirst();
      dell.removeLast();
      dell.removeFirst();
      dell.removeLast();
      String expected = "Element 4";
      String actual = dell.removeLast();
      Assert.assertEquals(expected, actual);
   }
   @Test public void iterator2Test() {
   
   }



}
