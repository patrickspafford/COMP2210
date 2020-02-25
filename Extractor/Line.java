import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
/**.
 * Line.java
 * Models a line segment as a sorted set of points.
 *
 * @author Patrick Spafford (pas0037@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2-14-2019
 *
 */
 
public class Line implements Comparable<Line>, Iterable<Point> {
 
   SortedSet<Point> line;
   
   /** 
    * Creates a new line containing no points.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Line() {
      line = new TreeSet<Point>();
   }
   
   /**.
    * Creates a new line containing all distinct collinear points in the
    *@param c is the Collection of points.
    */
   public Line(Collection<Point> c) {
      line = new TreeSet<Point>();
      Iterator<Point> itr = c.iterator();
      Point a = itr.next();
      Point b = itr.next();
      line.add(a);
      line.add(b);
      double slope = a.slopeTo(b);
      while (itr.hasNext()) {
         Point pointi = itr.next();
         if (pointi.slopeTo(b) == slope) {
            if (!line.contains(pointi)) {
               this.line.add(pointi);
            }
         }
      }     
   }
 
   /** 
    * Adds the point p to this line if p is collinear with all points already
    * in the line and p itself is not already in the line. Returns true if this
    * line is changed as a result, false otherwise.
    *@param p is the point to be added.
    *@return boolen value.
    */
   public boolean add(Point p) {
      if (line == null || this.length() == 0) {
         line.add(p);
         return true;
      }
      if (line.size() == 1 && !line.contains(p)) {
         line.add(p);
         return true;
      }
      if (p == null) {
         return false;
      }
      double slopeOfLine = line.first().slopeTo(line.last());
      double slopeToPoint1 = line.first().slopeTo(p);
      double slopeToPoint2 = p.slopeTo(line.first());
      if ((slopeToPoint1 == slopeOfLine) && !line.contains(p)) {
         line.add(p);
         return true;
      }
      if ((slopeToPoint2 == slopeOfLine) && !line.contains(p)) {
         line.add(p);
         return true;
      }
      else {
         return false;
      }
   }
   
   /** 
    * Returns the first (minimum) point in this line or null if this line
    * contains no points.
    *@return the first item in the line.
    */
   public Point first() {
      if (line.size() == 0 || this == null || line == null) {
         return null;
      }
      return line.first();
   }
   
   /** 
    * Returns the last (maximum) point in this line or null if this line
    * contains no points.
    *@return the last item in the line.
    */
   public Point last() {
      if (line.size() == 0 || this == null || line == null) {
         return null;
      }
      return line.last();
   }
   
   /** 
    * Returns the number of points in this line.
    *@return the size of the line.
    */
   public int length() {
      if (this == null) {
         return 0;
      }
      return line.size();
   }

   /**
    * Compares this line with the specified line for order. Returns a negative
    * integer, zero, or a positive integer if this line is less than, equal to,
    * or greater than the specified line. Lines are ordered first by their
    * first point then by their last point. An empty line is less than any
    * non-empty line, and all empty lines are equal. All three properties of
    * compareTo as specified in the Comparable interface are met, and this
    * implementation is consistent with equals.
    */
   @Override
   public int compareTo(Line that) {
      if (this.length() == 0 && that.length() != 0) {
         return -1;
      }
      if (this.length() != 0 && that.length() == 0) {
         return 1;
      }
      if (this.length() == 0 && that.length() == 0) {
         return 0;
      }
      if (!this.first().equals(that.first())) {
         return this.first().compareTo(that.first());
      }
      else {
         return this.last().compareTo(that.last());
      }
   }

   /** 
    * Provide an iterator over all the points in this line. The order in which
    * points are returned must be ascending natural order.
    */
   @Override
   public Iterator<Point> iterator() {
      return line.iterator();
   }
   
   /** 
    * Return true if this line's first and last points are equal to the
    * parameter's first and last points. Empty lines are equal to each other
    * but are not equal to any non-empty line.
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
      if (!(obj instanceof Line)) {
         return false;
      }
      Line that = (Line) obj;
      if ((this.length() == 0) && (that.length() == 0)) {
         return true;
      }
      if ((this.length() == 0) && (that.length() != 0)) {
         return false;
      }
      if ((this.length() != 0) && (that.length() == 0)) {
         return false;
      }
      return (this.first().equals(that.first())) 
         && (this.last().equals(that.last()));
   }
 /**.
 *Obligatory override.
 *
 */
 
   @Override
   public int hashCode() {
      return 0;
   }
   /** 
    * Return a string representation of this line.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
    
   @Override
   public String toString() {
      if (length() == 0) {
         return "";
      }
      StringBuilder s = new StringBuilder();
      for (Point p : line) {
         s.append(p + " -> ");
      }
      s = s.delete(s.length() - 4, s.length());
      return s.toString();
   }
 
}
