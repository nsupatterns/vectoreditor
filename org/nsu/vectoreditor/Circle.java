
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

    public boolean trySelect(int px, int py) {
        return (px - x) * (px - x) + (py - y) * (py - y) <= radius * radius;
    }

    public int getSelectPointsCount() {
        return 4;
    }
    
    public Point getSelectPoint(int index) {
        switch(index) {
        case 0:
            return new Point(x - radius, y - radius);
        case 1:
            return new Point(x - radius, y + radius);
        case 2:
            return new Point(x + radius, y + radius);
        case 3:
            return new Point(x + radius, y - radius);
        default:
            assert(false);
            return new Point(0, 0);
        }
    }

    public void setPoint(int index, int px, int py) {
        int x1 = x - radius;
        int y1 = y - radius;
        int x2 = x + radius;
        int y2 = y + radius;

        switch(index) {
        case 0:
            x1 = px;
            y1 = py;
            break;
        case 1:
            x1 = px;
            y2 = py;
            break;
        case 2:
            x2 = px;
            y2 = py;
            break;
        case 3:
            x2 = px;
            y1 = py;
            break;
        default:
            assert(false);
        }

        final int width = x2 - x1;
        final int height = y2 - y1;
        final int dia = Math.max(width, height);
        radius = dia / 2;
        x = x1 + (width / 2);
        y = y1 + (height / 2);
    }

    private int x;
    private int y;
    private int radius;
}

