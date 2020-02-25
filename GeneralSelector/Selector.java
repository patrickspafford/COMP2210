import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Patrick Spafford (pas0037@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 1-30-2019
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
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @param <T>     is the data type.
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      List<T> list = (List<T>) coll;
      T min = list.get(0);
      for (int i = 0; i < list.size(); i++) {
         if (comp.compare(list.get(i), min) < 0) {
            min = list.get(i);
         }
      }
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @param <T>     is the data type.
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      List<T> list = (List<T>) coll;
      T max = list.get(0);
      for (int i = 0; i < list.size(); i++) {
         if (comp.compare(list.get(i), max) > 0) {
            max = list.get(i);
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @param <T>     is the data type.
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.size() == 0 || k > coll.size() || k < 1) {
         throw new NoSuchElementException();
      } 
      Iterator<T> itr = coll.iterator();
      ArrayList<T> list = new ArrayList<T>();
      while (itr.hasNext()) {
         T value = itr.next();
         list.add(value);
      }
      java.util.Collections.sort(list, comp);
      int distinctValues = list.size();
      for (int i = 0; i < list.size() - 1; i++) {
         if (list.get(i).equals(list.get(i + 1))) {
            list.remove(i + 1);
            i--;
            distinctValues--;
         }
      }
      if (distinctValues < k) {
         throw new NoSuchElementException();
      }
      return list.get(k - 1);
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @param <T>     is the data type.
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.size() == 0 || k > coll.size() || k < 1) {
         throw new NoSuchElementException();
      }
      Iterator<T> itr = coll.iterator();
      ArrayList<T> list = new ArrayList<T>();
      while (itr.hasNext()) {
         T value = itr.next();
         list.add(value);
      }
      java.util.Collections.sort(list, comp);
      int distinctValues = list.size();
      for (int i = 0; i < list.size() - 1; i++) {
         if (list.get(i).equals(list.get(i + 1))) {
            list.remove(i + 1);
            i--;
            distinctValues--;
         }
      }
      if (distinctValues < k) {
         throw new NoSuchElementException();
      }
      return list.get(list.size() - k);
   
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @param <T>     is the data type.
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if (comp == null || coll == null) {
         throw new IllegalArgumentException();
      }  
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      Iterator<T> itr = coll.iterator();
      Collection<T> collection = new ArrayList<T>(); 
      while (itr.hasNext()) {
         T value = itr.next();
         if ((comp.compare(value, low) >= 0) 
            && (comp.compare(value, high) <= 0)) {
            collection.add(value);
         }
      } 
      if (collection.size() == 0) {
         throw new NoSuchElementException();
      }                               
      return collection;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @param <T>     is the data type.
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      Iterator<T> iter = coll.iterator();
      List<T> ceilingValues = new ArrayList<T>();
      while (iter.hasNext()) {
         T value = iter.next();
         if ((comp.compare(key, value) <= 0)) {
            ceilingValues.add(value);  
         }
      }
      if (ceilingValues.size() == 0) {
         throw new NoSuchElementException();
      }
      T ceiling = ceilingValues.get(0);
      for (T element : ceilingValues) {
         if (comp.compare(element, ceiling) <= 0) {
            ceiling = element;
         }
      }
      return ceiling;
   
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @param <T>     is the data type.
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      Iterator<T> iter = coll.iterator();
      List<T> floorValues = new ArrayList<T>();
      while (iter.hasNext()) {
         T value = iter.next();
         if ((comp.compare(key, value) >= 0)) {
            floorValues.add(value);  
         }
      }
      if (floorValues.size() == 0) {
         throw new NoSuchElementException();
      }
      T floor = floorValues.get(0);
      for (T element : floorValues) {
         if (comp.compare(element, floor) >= 0) {
            floor = element;
         }
      }
      return floor;
   }

}
