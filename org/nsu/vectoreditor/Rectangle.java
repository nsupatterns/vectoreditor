
package org.nsu.vectoreditor;

public class Rectangle extends Shape {

    public Rectangle(int px1, int py1, int px2, int py2) {
        x1 = px1;
        y1 = py1;
        x2 = px2;
        y2 = py2;
    }

    public void draw(java.awt.Graphics canvas) {
        canvas.drawLine(x1, y1, x2, y1);
        canvas.drawLine(x2, y1, x2, y2);
        canvas.drawLine(x2, y2, x1, y2);
        canvas.drawLine(x1, y2, x1, y1);
    }

    public void move(int dx, int dy) {
        x1 += dx;
        x2 += dx;
        y1 += dy;
        y2 += dy;
    }

    public void visit(ShapeVisitor visitor) {
        visitor.visitRectangle(this);
    }

    public Point getLeftTop() {
        return new Point(x1, y1);
    }

    public void setLeftTop(Point p) {
        x1 = p.x;
        y1 = p.y;
    }

    public Point getRightBottom() {
        return new Point(x2, y2);
    }

    public void setRightBottom(Point p) {
        x2 = p.x;
        y2 = p.y;
    }

    int x1;
    int y1;
    int x2;
    int y2;
}

