
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

    public boolean trySelect(int x, int y) {
        double dx = x;
        double dy = y;
        double dx1 = x1;
        double dy1 = y1;
        double dx2 = x2;
        double dy2 = y2;

        double a = (dx - dx1) / (dx2 - dx1);
        double b = (dy - dy1) / (dy2 - dy1);

        if(Math.abs(a - b) > 0.01)
            return false;

        return Utils.pointInRectangle(new Point(x, y), new Point(x1, y1), new Point(x2, y2));
    }

    public int getSelectPointsCount() {
        return 2;
    }
    
    public Point getSelectPoint(int index) {
        if(index == 0)
            return new Point(x1, y1);
        else
            return new Point(x2, y2);
    }

    public void setPoint(int index, int x, int y) {
        if(index == 0) {
            x1 = x;
            y1 = y;
        } else {
            x2 = x;
            y2 = y;
        }
    }


    private int x1;
    private int y1;
    private int x2;
    private int y2;
}

