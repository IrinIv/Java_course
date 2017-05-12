package ru.stqa.pft.sandbox;

/**
 * Created by IrinaIv on 5/11/2017.
 */
public class Point {
 public double p1;
 public double p2;



public Point(double p1, double p2) {
  this.p1 = p1;
  this.p2 = p2;
  }

  public double dot1()
  {
    return Math.pow((this.p2 - this.p1), 2);
  }

  public double dot2() {

    return Math.pow((this.p2 - this.p1), 2);

  }


  public static void main(String[] args) {

    Point p1 = new Point(10, 5);
    // System.out.println(dot1(p1));

    Point p2 = new Point(6,15);
    // System.out.println(dot2(p2));

    System.out.println(distance(p1, p2));
  }


  public static double distance(Point p1, Point p2) {

    return Math.sqrt((Math.pow((p1.p2 - p1.p1), 2)+(Math.pow((p2.p2 - p2.p1), 2))));
  }
}
