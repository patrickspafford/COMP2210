import java.util.Comparator;
/**
 * Point.java. 
 * Models a two dimensional point as a Cartesian coordinate (x, y)
 * in Quadrant I (x >= 0 and y >= 0). This class is designed to be
 * immutable.
 *
 * @author  Patrick Spafford (pas0037@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2-13-2019
 *
 */
 
public final class Point implements Comparable<Point> {

  /** Compare two points with respect to the slope each makes with point.
  *
  *
  */
   public final Comparator<Point> slopeOrder = new ComparePointsBySlope();

   /** x,y coordinates of this point. 
   *
   */
   private final int x;
   private final int y;

   /** 
    * Create a point from the given x and y coordinates. If either x or y is
    * negative, throw an IllegalArgumentException.
    *
    *@param inputx is the input value for the x coordinate.
    *@param inputy is the input value for the y coordinate.
    */
   public Point(int inputx, int inputy) {
      if (inputx < 0 || inputy < 0) {
         throw new IllegalArgumentException();
      }
      this.x = inputx;
      this.y = inputy;
   }

   /** 
    * Return a string representation of this point.
    * 
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   @Override
   public String toString() {
      return "(" + x + ", " + y + ")";
   }

   /**
    * Indicates whether some object is equal to this Point. A Point (x1, y1) is
    * equal to this Point (x0, y0) if and only if x0 == x1 and y0 == y1. All
    * six properties of the equals method specified in the Object class are
    * met.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj == this) {
         return true;
      }
      if (!(obj instanceof Point)) {
         return false;
      }
      Point that = (Point) obj;
      return (this.x == that.x) && (this.y == that.y);
   }
   
   @Override
   public int hashCode() {
      return 0;
   }
   /**
    * Compares this point with the specified point for order. Returns a
    * negative integer, zero, or a positive integer if this point is less
    * than, equal to, or greater than the specified point. Points are ordered
    * first by y value and then by x value. All three properties of compareTo
    * as specified in the Comparable interface are met, and this
    * implementation is consistent with equals.
    */
    
   @Override
   public int compareTo(Point that) {
      if (this == null) {
         throw new NullPointerException();
      }
      if (!this.getClass().equals(that.getClass())) {
         throw new ClassCastException();
      }
      if (this.y != that.y) {
         return this.y - that.y;
      }
      else {
         return this.x - that.x;
      }
   }    

   /**
    * Computes the slope of the line segment between this point (x0, y0) and
    * the specified point (x1, y1). Slope is computed as (y1 - y0) / (x1 - x0),
    * so the direction of the slope is from this point to the specified point.
    * The slope of a horizontal line segment is positive zero; the slope of a
    * vertical line segment is positive infinity; the slope of a degenerate
    * line segment (where this point and the specified point are the same) is
    * negative infinity.
    *@param that is the second point in the comparison.
    *@return slope is the rise over run between points.
    */
   public double slopeTo(Point that) {
      if (this.equals(that)) {
         return Double.NEGATIVE_INFINITY;
      }
      if (this.x == that.x) {
         return Double.POSITIVE_INFINITY;
      }
      double slope = ((double) (that.y - this.y) / (double) (that.x - this.x));
      return slope;
   }
      
   /**
    * Defines a total order for Points based on 
    * the slope that two specified points
    * make with this point.
    */
   private class ComparePointsBySlope implements Comparator<Point> {
   
      /**
       * Compares two specified points p1 and p2 for order. Returns a negative
       * integer, zero, or a positive integer if p1 is less than, equal to, or
       * greater than p2. All three properties of the compare method as
       * specified in the Comparator interface are met. 
       */
      @Override   
      public int compare(Point p1, Point p2) {
         if (p1 == null && p2 != null) {
            return -1;
         }
         if (p1 != null && p2 == null) {
            return 1;
         }
         if (p1 == null && p2 == null) {
            return 0;
         }
         double slopeTop1 = slopeTo(p1);
         double slopeTop2 = slopeTo(p2);
      
         if (slopeTop1 < slopeTop2) {
            return -1;
         }
         if (slopeTop1 > slopeTop2) {
            return 1;
         }         
         else {
            return 0;
         }
      }
   
   }
   
}
