import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
//import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 *@author  Patrick Spafford (pas0037@auburn.edu)
 *@author  Dean Hendrix (dh@auburn.edu)
 *@version 2-14-2019
 *
 */
 
public class Extractor {
   
   /** raw data: all (x,y) points from source data. 
   *
   */
   private Point[] points;
   
   /** lines identified from raw data. 
   *
   */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename.
    *@param filename is the input file. 
    *@throws FileNotFoundException if the file does not exist.
    */
   public Extractor(String filename) throws FileNotFoundException {
      Collection<Point> pcoll = new TreeSet<Point>();
      Scanner scanFile = new Scanner(new File(filename));
      int numberOfLines = Integer.parseInt(scanFile.nextLine());
      int counter = 0;
      while (scanFile.hasNext()) {
         String currentLine = scanFile.nextLine();
         Scanner scanLine = new Scanner(currentLine);
         int xvalue = Integer.parseInt(scanLine.next());
         int yvalue = Integer.parseInt(scanLine.next());
         Point p = new Point(xvalue, yvalue);
         pcoll.add(p);
         scanLine.close();
         counter++;
      
      }
      scanFile.close();
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    *  THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    *@param pcoll is the collection of points.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    *@return collection of lines.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>(); 
      for (int i = 0; i < points.length; i++) {
         for (int j = i + 1; j < points.length; j++) {
            for (int k = j + 1; k < points.length; k++) {
               for (int l = k + 1; l < points.length; l++) {
                  Collection<Point> pcoll = new ArrayList<Point>();
                  pcoll.add(points[i]);
                  pcoll.add(points[j]);
                  pcoll.add(points[k]);
                  pcoll.add(points[l]);
                  Line line = new Line(pcoll);
                  if (line.length() == 4) {
                     lines.add(line);
                  }
                                          
               }
            }
         }
      }
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    *
    *@return the collection of lines discovered.
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      Arrays.sort(points); 
      for (int i = 0; i < points.length; i++) {
         Point[] pointsBySlope = Arrays.<Point>copyOf(points, points.length); 
         Arrays.<Point>sort(pointsBySlope, points[i].slopeOrder);
         Collection<Point> pcoll = new ArrayList<Point>();
         Point firstPoint = pointsBySlope[0];
         pcoll.add(firstPoint);
         for (int j = 1; j < pointsBySlope.length - 1; j++) {
         //abstractions
            double currentSlope = firstPoint.slopeTo(pointsBySlope[j]);
            double nextSlope = firstPoint.slopeTo(pointsBySlope[j + 1]);
            Point current = pointsBySlope[j];
            Point next = pointsBySlope[j + 1];
            
            pcoll.add(current);
            if (currentSlope == nextSlope) {
               pcoll.add(next);
            }
            else {
               Line line = new Line(pcoll);
               if (line.length() >= 4) {
                  lines.add(line);
               }
               Collection<Point> pcoll2 = new ArrayList<Point>();
               pcoll = pcoll2;
               pcoll.add(firstPoint); 
               pcoll.add(next); 
            } 
            if (j == (pointsBySlope.length - 2)) {
               Line line = new Line(pcoll);
               if (line.length() >= 4) {
                  lines.add(line);
               }
            }                
         } 
      }
      return lines;
   }
   
}
