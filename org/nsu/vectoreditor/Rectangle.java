
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

    public boolean trySelect(int x, int y) {
        return Utils.pointInRectangle(new Point(x, y), new Point(x1, y1), new Point(x2, y2));
    }

    public int getSelectPointsCount() {
        return 4;
    }
    
    public Point getSelectPoint(int index) {
        switch(index) {
        case 0:
            return new Point(x1, y1);
        case 1:
            return new Point(x1, y2);
        case 2:
            return new Point(x2, y2);
        case 3:
            return new Point(x2, y1);
        default:
            assert(false);
            return new Point(0, 0);
        }
    }

    public void setPoint(int index, int x, int y) {
        switch(index) {
        case 0:
            x1 = x;
            y1 = y;
            break;
        case 1:
            x1 = x;
            y2 = y;
            break;
        case 2:
            x2 = x;
            y2 = y;
            break;
        case 3:
            x2 = x;
            y1 = y;
            break;
        default:
            assert(false);
        }
    }

    int x1;
    int y1;
    int x2;
    int y2;
}

