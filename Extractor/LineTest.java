import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collection;

public class LineTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   
   @Test public void lineConstructorTest() {
      Collection<Point> coll = new ArrayList<Point>();
      Point p1 = new Point(0, 0);
      Point p2 = new Point(3, 3);
      Point p3 = new Point(3, 4);
      Point p4 = new Point(0, 0);
      
      coll.add(p1);
      coll.add(p2);
      coll.add(p3);
      coll.add(p4);
      Line line = new Line(coll);
      int expected = 2;
      int actual = line.length(); 
      Assert.assertEquals(expected, actual); 
   }
   @Test public void lastTest() {
      Collection<Point> coll = new ArrayList<Point>();
      Point p1 = new Point(1, 2);
      Point p2 = new Point(2, 3);
      Point p3 = new Point(3, 4);
      Point p4 = new Point(0, 0);
      //Point p5 = new Point(, 3);
      coll.add(p1);
      coll.add(p2);
      coll.add(p3);
      coll.add(p4);
      //coll.add(p5);
      Line line = new Line(coll);
      Point expected = p3;
      Point actual = line.last();
      Assert.assertEquals(expected, actual);
   }
   @Test public void iteratorTest() {
   
   }

}
