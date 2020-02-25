import org.junit.Assert;
import java.util.Iterator;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class RandomizedListArrayTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
/**
*
*
*/
   @Test public void constructorTest() {
      RandomizedListArray<String> rla = new RandomizedListArray<String>();
      int expected = 0;
      int actual = rla.size();
      Assert.assertEquals(expected, actual);
   }
/**
*
*
*/
   @Test public void addTest() {
      RandomizedListArray<String> rla = new RandomizedListArray<String>();
      rla.add("bcd");
      rla.add("cde");
      rla.add("bcd");
      rla.add("cde");
      rla.add("bcd");
      rla.add("cde");
      rla.add("bcd");
      rla.add("cde");
      rla.add("abc");
      rla.add("bcd");
      rla.add("cde");
      rla.add("bcd");
      rla.add("cde");
      rla.add("bcd");
      rla.add("cde");
      rla.add("bcd");
      rla.add("cde");
      rla.add("abc");
      rla.remove();
      int expected = 17;
      int actual = rla.size();
   }
/**
*
*
*/
   @Test public void removeTest() {
      RandomizedListArray<String> rla = new RandomizedListArray<String>();
      rla.add("abc");
      rla.add("abc");
      rla.add("abc");
      rla.add("abc");
      rla.remove();
      rla.remove();
      // rla.remove();
      String actual = rla.remove();
      String expected = "abc";
      Assert.assertEquals(expected, actual);
   }
/**
*
*
*/
   @Test public void sampleTest() {
      RandomizedListArray<String> rla = new RandomizedListArray<String>();
      rla.add("abc");
      rla.add("abc");
      String expected = "abc";
      String actual = rla.sample();
      Assert.assertEquals(expected, actual);
   
   }
/**
*
*
*/
   @Test public void iteratorTest() {
      RandomizedListArray<Integer> rla = new RandomizedListArray<Integer>();
      int size = 0;
      rla.add(0);
      rla.add(1);
      rla.add(2);
      rla.add(3);
      boolean[] values = new boolean[4];
      Iterator<Integer> itr = rla.iterator();
      while (itr.hasNext()) {
         values[itr.next()] = true;
         size++;
      }
      boolean actual = true;
      for (int i = 0; i < values.length; i++) {
         if (values[i] == false) {
            actual = false;
         }
      }       
      boolean expected = true;
      Assert.assertEquals(expected, actual);
   }
/**
*
*
*/
   @Test public void emptyTest() {
      RandomizedListArray<String> rla = new RandomizedListArray<String>();
      String actual = rla.sample();
      String expected = null;
      Assert.assertEquals(expected, actual);
   }
   @Test public void emptyTest1() {
      RandomizedListArray<String> rla = new RandomizedListArray<String>();
      String actual = rla.remove();
      String expected = null;
      Assert.assertEquals(expected, actual);
   }

   
}
