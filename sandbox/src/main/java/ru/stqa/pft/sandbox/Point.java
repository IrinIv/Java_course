package ru.stqa.pft.sandbox;

/**
 * Created by IrinaIv on 5/11/2017.
 */
public class Point {
 public double x;
 public double y;



public Point(double x, double y) {
  this.x = x;
  this.y = y;
  }

  public double dot1() {
    return (this.y - this.x) * (this.y - this.x);
  }

  public double dot2() {
    return (this.y - this.x) * (this.y - this.x);
  }


  public static void main(String[] args) {

    Point p1 = new Point(10, 5);
    // System.out.println(dot1(p1));

    Point p2 = new Point(6,15);
    // System.out.println(dot2(p2));

    System.out.println(distance(p1, p2));
  }


  public static double distance(Point p1, Point p2) {

    return Math.sqrt(((p1.y - p1.x) * (p1.y - p1.x))+((p2.y - p2.x) * (p2.y - p2.x)));
  }
}
