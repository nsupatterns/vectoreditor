
package org.nsu.vectoreditor;


/////////////////////////////////////////////////////////////
// Line

class LineStartConnectPoint extends ConnectPoint {
    public LineStartConnectPoint(Line l) {
        line = l;
    }

    public Point getPos() {
        return line.getStart();
    }

    private Line line;
}

class LineEndConnectPoint extends ConnectPoint {
    public LineEndConnectPoint(Line l) {
        line = l;
    }

    public Point getPos() {
        return line.getEnd();
    }

    private Line line;
}


/////////////////////////////////////////////////////////////
// Rectangle

class RectangleLeftTopConnectPoint extends ConnectPoint {
    public RectangleLeftTopConnectPoint(Rectangle r) {
        rect = r;
    }

    public Point getPos() {
        return rect.getLeftTop();
    }

    private Rectangle rect;
}

class RectangleRightTopConnectPoint extends ConnectPoint {
    public RectangleRightTopConnectPoint(Rectangle r) {
        rect = r;
    }

    public Point getPos() {
        Point leftTop = rect.getLeftTop();
        Point rightBottom = rect.getRightBottom();

        return new Point(rightBottom.x, leftTop.y);
    }

    private Rectangle rect;
}

class RectangleLeftBottomConnectPoint extends ConnectPoint {
    public RectangleLeftBottomConnectPoint(Rectangle r) {
        rect = r;
    }

    public Point getPos() {
        Point leftTop = rect.getLeftTop();
        Point rightBottom = rect.getRightBottom();

        return new Point(leftTop.x, rightBottom.y);
    }

    private Rectangle rect;
}


class RectangleRightBottomConnectPoint extends ConnectPoint {
    public RectangleRightBottomConnectPoint(Rectangle r) {
        rect = r;
    }

    public Point getPos() {
        return rect.getRightBottom();
    }

    private Rectangle rect;
}


/////////////////////////////////////////////////////////////
// Circle

class CircleLeftConnectPoint extends ConnectPoint {

    CircleLeftConnectPoint(Circle c) {
        circle = c;
    }

    public Point getPos() {
        Point center = circle.getCenter();
        int radius = circle.getRadius();
        return new Point(center.x - radius, center.y);
    }

    private Circle circle;
}

class CircleRightConnectPoint extends ConnectPoint {
    CircleRightConnectPoint(Circle c) {
        circle = c;
    }

    public Point getPos() {
        Point center = circle.getCenter();
        int radius = circle.getRadius();
        return new Point(center.x + radius, center.y);
    }

    private Circle circle;
}

class CircleTopConnectPoint extends ConnectPoint {
    
    CircleTopConnectPoint(Circle c) {
        circle = c;
    }

    public Point getPos() {
        Point center = circle.getCenter();
        int radius = circle.getRadius();
        return new Point(center.x, center.y + radius);
    }

    private Circle circle;
}

class CircleBottomConnectPoint extends ConnectPoint {
    CircleBottomConnectPoint(Circle c) {
        circle = c;
    }

    public Point getPos() {
        Point center = circle.getCenter();
        int radius = circle.getRadius();
        return new Point(center.x, center.y - radius);
    }

    private Circle circle;
}



public class ConnectPointsCollector extends ShapeVisitor {

    public ConnectPointsCollector() {
        points = new java.util.LinkedList<ConnectPoint>();
    }

    public void visitLine(Line shape) {
        points.add(new LineStartConnectPoint(shape));
        points.add(new LineEndConnectPoint(shape));
    }

    public void visitRectangle(Rectangle shape) {
        points.add(new RectangleLeftTopConnectPoint(shape));
        points.add(new RectangleRightTopConnectPoint(shape));
        points.add(new RectangleLeftBottomConnectPoint(shape));
        points.add(new RectangleRightBottomConnectPoint(shape));
    }

    public void visitCircle(Circle shape) {
        points.add(new CircleLeftConnectPoint(shape));
        points.add(new CircleRightConnectPoint(shape));
        points.add(new CircleTopConnectPoint(shape));
        points.add(new CircleBottomConnectPoint(shape));
    }

    public void visitConnect(ConnectShape shape) {
    }

    public java.util.LinkedList<ConnectPoint> getPoints() {
        return points;
    }

    private java.util.LinkedList<ConnectPoint> points;
}

