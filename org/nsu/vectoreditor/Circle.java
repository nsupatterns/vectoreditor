
package org.nsu.vectoreditor;


public class Circle extends Shape {

    public Circle(int px, int py, int pradius) {
        x = px;
        y = py;
        radius = pradius;
    }

    public void draw(java.awt.Graphics canvas) {
        canvas.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void visit(ShapeVisitor visitor) {
        visitor.visitCircle(this);
    }

    public Point getCenter() {
        return new Point(x, y);
    }

    public void setCenter(Point p) {
        x = p.x;
        y = p.y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int r) {
        radius = r;
    }

    private int x;
    private int y;
    private int radius;
}

