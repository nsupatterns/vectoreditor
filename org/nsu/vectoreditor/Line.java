
package org.nsu.vectoreditor;

public class Line extends Shape {

    public Line(int px1, int py1, int px2, int py2) {
        x1 = px1;
        y1 = py1;
        x2 = px2;
        y2 = py2;
    }

    public void draw(java.awt.Graphics canvas) {
        canvas.drawLine(x1, y1, x2, y2);
    }

    public void move(int dx, int dy) {
        x1 += dx;
        x2 += dx;
        y1 += dy;
        y2 += dy;
    }

    public void visit(ShapeVisitor visitor) {
        visitor.visitLine(this);
    }

    public int getConnectPointsCount() {
        return 2;
    }

    public Point getConnectPoint(int index) {
        if(index == 0)
            return new Point(x1, y1);
        else
            return new Point(x2, y2);
    }


    public Point getStart() {
        return new Point(x1, y1);
    }

    public void setStart(Point p) {
        x1 = p.x;
        y1 = p.y;
    }

    public Point getEnd() {
        return new Point(x2, y2);
    }

    public void setEnd(Point p) {
        x2 = p.x;
        y2 = p.y;
    }


    private int x1;
    private int y1;
    private int x2;
    private int y2;
}

