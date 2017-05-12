package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IrinaIv on 5/11/2017.
 */
public class PointTests {

  @Test
  public void testPoint() {

    Point p1 = new Point(10, 5);
    Point p2 = new Point(6,15);
    Assert.assertEquals(Math.sqrt((Math.pow((p1.p2 - p1.p1), 2)+(Math.pow((p2.p2 - p2.p1), 2)))), 10.295630140987);

  }

  @Test
  public void testPoint1() {

    Point p1 = new Point(2, 6);
    Point p2 = new Point(1,5);
    Assert.assertEquals(Math.sqrt((Math.pow((p1.p2 - p1.p1), 2)+(Math.pow((p2.p2 - p2.p1), 2)))), 5.656854249492381);

  }

  @Test
  public void testPoint2() {

    Point p1 = new Point(4, 8);
    Point p2 = new Point(6,8);
    Assert.assertEquals(Math.sqrt((Math.pow((p1.p2 - p1.p1), 2)+(Math.pow((p2.p2 - p2.p1), 2)))), 4.47213595499958);

  }
}
