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
}