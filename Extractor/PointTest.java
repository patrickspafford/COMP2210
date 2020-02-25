import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Comparator;


public class PointTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   @Test public void compareTest() {
      Point point1 = new Point(3, 4);
      Point point2 = new Point(2, 3);
      Point point3 = new Point(0, 0);
      Comparator<Point> slopeOrder = new ComparePointsBySlope<Point>();
      double refSlope = point1.slopeTo(point2);
      
   }

}
