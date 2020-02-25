import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
/**
*@author Patrick Spafford
*@version 01-16-2019
*
*/
public class SelectorTest {
   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   /**.
   *
   */
   // @Test
   // public void minTest() {
      // int [] a = {2, 5, 1, 5, 9, 27, 6, 12, 10, 8, 3};
      // int expected = 1;
      // int actual = Selector.min(a);
      // Assert.assertEquals(expected, actual);
   // }
//    /**.
//    *
//    */
   // @Test
   // public void maxTest() {
      // int [] a = {2, 5, 1, 5, 9, 27, 6, 12, 10, 8, 3};
      // int expected = 27;
      // int actual = Selector.max(a);
      // Assert.assertEquals(expected, actual);
   // }
//    /**.
//    *
//    */
   @Test
   public void kminTest() {
      int [] a = {3, 7, 3, 3, 1, 9, 1, 1, 5};
      int expected = 1;
      int actual = Selector.kmin(a, 5);
      Assert.assertEquals(expected, actual);
   }
   /**.
   *
   */
   @Test
   public void kmaxTest() {
      int [] a = {3, 7, 3, 3, 1, 9, 1, 1, 5};
      int expected = 9;
      int actual = Selector.kmax(a, 5);
      Assert.assertEquals(expected, actual);
   }
//    /**.
//    *
//    */
   // @Test
   // public void rangeTest() {
      // int [] a = {2, 5, 1, 5, 9, 27, 6, 12, 10, 8, 3};
      // int[] expected = {2, 3, 5, 5, 6, 8, 9, 10};
      // int[] actual = Selector.range(a, 2, 11);
      // Arrays.sort(expected);
      // Arrays.sort(actual);
      // Assert.assertArrayEquals(expected, actual);
   // }
//    /**.
//    *
//    */
   // @Test
   // public void ceilingTest() {
      // int [] a = {2, 5, 1, 5, 9, 27, 6, 12, 10, 8, 3};
      // int expected = 10;
      // int actual = Selector.ceiling(a, 10);
      // Assert.assertEquals(expected, actual);
   // }
//    /**.
//    *
//    */
   // @Test 
   // public void floorTest() {
      // int [] a = {2, 5, 1, 5, 9, 27, 6, 12, 10, 8, 3};
      // int expected = 6;
      // int actual = Selector.floor(a, 7);
      // Assert.assertEquals(expected, actual);
   // }
}
