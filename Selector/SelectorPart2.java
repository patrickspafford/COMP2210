import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Patrick Spafford (pas0037@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  01-16-2019
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    *
    *@param a , which is an array of integers.
    *@return int, which is the minimum
    *@throws IllegalArgumentException if not acceptable.
    */
   public static int min(int[] a) throws IllegalArgumentException {
      int minimum = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] < minimum) {
            minimum = a[i];
         }
      }
      return minimum;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    *
    *@param a , which is an array of integers.
    *@return int, which is the maximum
    *@throws IllegalArgumentException if not acceptable.
    */
   public static int max(int[] a) throws IllegalArgumentException {
      int maximum = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] > maximum) {
            maximum = a[i];
         }
      }
      return maximum;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    *
    *@param a , which is an array of integers.
    *@param k , which denotes the kth smallest value.
    *@return int, which is the kth minimum
    *@throws IllegalArgumentException if not acceptable.
    */
   public static int kmin(int[] a, int k) throws IllegalArgumentException {
      Arrays.sort(a);
      return a[k - 1];
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    *@param a , which is an array of integers.
    *@param k , which denotes the kth max value.
    *@return int, which is the kth maximum.
    *@throws IllegalArgumentException if not acceptable.
    */
   public static int kmax(int[] a, int k) throws IllegalArgumentException {
      Arrays.sort(a);
      return a[a.length - k];
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    *
    *@param a is the array.
    *@param low is the min value in the range.
    *@param high is the max value in the range.
    *@return array of ints.
    *@throws IllegalArgumentException .
    */
   public static int[] range(int[] a, int low, int high) 
      throws IllegalArgumentException {
      int[] rangeArray = new int[0];
      int counter = 0;
      for (int i = 0; i < a.length; i++) {
         if (low <= a[i] && a[i] <= high) {
            int[] newRangeArray = Arrays.copyOf(rangeArray, 
               rangeArray.length + 1);
            newRangeArray[counter] = a[i];
            rangeArray = Arrays.copyOf(newRangeArray, newRangeArray.length);
            counter++;
         } 
      }
      return rangeArray;
   }
   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    *@param a is the array.
    *@param key is the lower limit.
    *@return int.
    *@throws IllegalArgumentException 
    */
   public static int ceiling(int[] a, int key) throws IllegalArgumentException {
      int[] newArray = new int[0];
      int counter = 0;
      for (int i = 0; i < a.length; i++) {
         if (key <= a[i]) {
            int[] ceilingArray = Arrays.copyOf(newArray, newArray.length + 1);
            ceilingArray[counter] = a[i];
            newArray = Arrays.copyOf(ceilingArray, ceilingArray.length);
            counter++;
         } 
      }
      int minimumCeiling = newArray[0];
      for (int i = 0; i < newArray.length; i++) {
         if (newArray[i] <= minimumCeiling) {
            minimumCeiling = newArray[i];
         }
      }
      return minimumCeiling;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    *@param a is the array.
    *@param key is the upper limit.
    *@return int.
    *@throws IllegalArgumentException .
    */
   public static int floor(int[] a, int key) throws IllegalArgumentException {
      int[] newArray = new int[0];
      int counter = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= key) {
            
            int[] floorArray = Arrays.copyOf(newArray, newArray.length + 1);
            floorArray[counter] = a[i];
            newArray = Arrays.copyOf(floorArray, floorArray.length);
            counter++;
         } 
      }
      int maxFloor = newArray[0];
      for (int i = 0; i < newArray.length; i++) {
         if (maxFloor <= newArray[i]) {
            maxFloor = newArray[i];
         }
      }
      return maxFloor;
   
   }

}
