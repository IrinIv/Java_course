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

    Assert.assertEquals(p1.distanceTo(p2), 10.770329614269007);

  }

  @Test
  public void testPoint1() {

    Point p1 = new Point(2, 6);
    Point p2 = new Point(1,5);
    Assert.assertEquals(p1.distanceTo(p2), 1.4142135623730951);

  }

  @Test
  public void testPoint2() {

    Point p1 = new Point(4, 8);
    Point p2 = new Point(6,8);
    Assert.assertEquals(p1.distanceTo(p2), 2.0);

  }
}
