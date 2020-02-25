import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   
   @Test 
   public void minTest() {
      //Collection<Integer> collection = new 
         //ArrayList<Integer>(Arrays.asList(new Integer[] {2, 8, 7, 3, 4}));
      Collection<Integer> collection = new ArrayList<Integer>();
      Integer actual = Selector.<Integer>min(collection);
   }
   @Test
   public void maxTest() {
   
   }
   @Test
   public void kminTest() {
   
   }
   @Test
   public void kmaxTest() {
   
   }
   @Test
   public void rangeTest() {
   
   }
   @Test
   public void ceilingTest() {
   
   }
   @Test
   public void floorTest() {
   
   }

}
