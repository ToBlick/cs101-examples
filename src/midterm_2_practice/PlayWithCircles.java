package midterm_2_practice;

class Circle {
  private float radius;

  public Circle(float radius) {
    if (radius <= 0) {
      radius = 1;
    }
    this.radius = radius;
  }

  public float getRadius() {
    return this.radius;
  }

  public int compareTo(Circle c) {
    if (this.radius < c.getRadius()) {
      return -1;
    } else if (this.radius > c.getRadius()) {
      return 1;
    } else {
      return 0;
    }
  }
}

public class PlayWithCircles {

  void sortCircles(Circle[] circles) {
    for (int i = 0; i < circles.length - 1; i++) {
      Circle currentMin = circles[i];
      int currentMinIndex = i;
      for (int j = i + 1; j < circles.length; j++) {
        if (currentMin.getRadius() > circles[j].getRadius()) {
          currentMin = circles[j];
          currentMinIndex = j;
        }
      }
      if (currentMinIndex != i) {
        circles[currentMinIndex] = circles[i];
        circles[i] = currentMin;
      }
    }
  }
}