
package org.nsu.vectoreditor;


class CircleLeftTopSelectPoint extends SelectPoint {

    CircleLeftTopSelectPoint(Circle c) {
        circle = c;
    }

    public Point getPos() {
        Point center = circle.getCenter();
        int radius = circle.getRadius();
        return new Point(center.x - radius, center.y - radius);
    }

    public void setPos(Point newPos) {
    }

    private Circle circle;
}

class CircleRightTopSelectPoint extends SelectPoint {
    CircleRightTopSelectPoint(Circle c) {
        circle = c;
    }

    public Point getPos() {
        Point center = circle.getCenter();
        int radius = circle.getRadius();
        return new Point(center.x + radius, center.y - radius);
    }

    public void setPos(Point newPos) {
    }

    private Circle circle;
}

class CircleLeftBottomSelectPoint extends SelectPoint {
    
    CircleLeftBottomSelectPoint(Circle c) {
        circle = c;
    }

    public Point getPos() {
        Point center = circle.getCenter();
        int radius = circle.getRadius();
        return new Point(center.x - radius, center.y + radius);
    }

    public void setPos(Point newPos) {
    }

    private Circle circle;
}

class CircleRightBottomSelectPoint extends SelectPoint {
    CircleRightBottomSelectPoint(Circle c) {
        circle = c;
    }

    public Point getPos() {
        Point center = circle.getCenter();
        int radius = circle.getRadius();
        return new Point(center.x + radius, center.y + radius);
    }

    public void setPos(Point newPos) {
    }

    private Circle circle;
}


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

    public boolean trySelect(int px, int py) {
        return (px - x) * (px - x) + (py - y) * (py - y) <= radius * radius;
    }

    public int getSelectPointsCount() {
        return 4;
    }
    
    public SelectPoint getSelectPoint(int index) {
        switch(index) {
        case 0:
            return new CircleLeftTopSelectPoint(this);
        case 1:
            return new CircleLeftBottomSelectPoint(this);
        case 2:
            return new CircleRightTopSelectPoint(this);
        case 3:
            return new CircleRightBottomSelectPoint(this);
        default:
            assert(false);
            return null;
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

    public int getConnectPointsCount() {
        return 4;
    }
    
    public Point getConnectPoint(int index) {
        switch(index) {
        case 0:
            return new Point(x - radius, y);
        case 1:
            return new Point(x + radius, y);
        case 2:
            return new Point(x, y + radius);
        case 3:
            return new Point(x, y - radius);
        default:
            assert(false);
            return new Point(0, 0);
        }
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

