
package org.nsu.vectoreditor;


/////////////////////////////////////////////////////////////
// Line

class LineStartSelectPoint extends SelectPoint {
    public LineStartSelectPoint(Line l) {
        line = l;
    }

    public Point getPos() {
        return line.getStart();
    }

    public void setPos(Point newPos) {
        line.setStart(newPos);
    }

    private Line line;
}

class LineEndSelectPoint extends SelectPoint {
    public LineEndSelectPoint(Line l) {
        line = l;
    }

    public Point getPos() {
        return line.getEnd();
    }

    public void setPos(Point newPos) {
        line.setEnd(newPos);
    }

    private Line line;
}


/////////////////////////////////////////////////////////////
// Rectangle

class RectangleLeftTopSelectPoint extends SelectPoint {
    public RectangleLeftTopSelectPoint(Rectangle r) {
        rect = r;
    }

    public Point getPos() {
        return rect.getLeftTop();
    }

    public void setPos(Point newPos) {
        rect.setLeftTop(newPos);
    }

    private Rectangle rect;
}

class RectangleRightTopSelectPoint extends SelectPoint {
    public RectangleRightTopSelectPoint(Rectangle r) {
        rect = r;
    }

    public Point getPos() {
        Point leftTop = rect.getLeftTop();
        Point rightBottom = rect.getRightBottom();

        return new Point(rightBottom.x, leftTop.y);
    }

    public void setPos(Point newPos) {
        Point leftTop = rect.getLeftTop();
        Point rightBottom = rect.getRightBottom();

        rect.setLeftTop(new Point(leftTop.x, newPos.y));
        rect.setRightBottom(new Point(newPos.x, rightBottom.y));
    }

    private Rectangle rect;
}

class RectangleLeftBottomSelectPoint extends SelectPoint {
    public RectangleLeftBottomSelectPoint(Rectangle r) {
        rect = r;
    }

    public Point getPos() {
        Point leftTop = rect.getLeftTop();
        Point rightBottom = rect.getRightBottom();

        return new Point(leftTop.x, rightBottom.y);
    }

    public void setPos(Point newPos) {
        Point leftTop = rect.getLeftTop();
        Point rightBottom = rect.getRightBottom();

        rect.setLeftTop(new Point(newPos.x, leftTop.y));
        rect.setRightBottom(new Point(rightBottom.x, newPos.y));
    }

    private Rectangle rect;
}


class RectangleRightBottomSelectPoint extends SelectPoint {
    public RectangleRightBottomSelectPoint(Rectangle r) {
        rect = r;
    }

    public Point getPos() {
        return rect.getRightBottom();
    }

    public void setPos(Point newPos) {
        rect.setRightBottom(newPos);
    }

    private Rectangle rect;
}


/////////////////////////////////////////////////////////////
// Circle

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



public class SelectPointsCollector extends ShapeVisitor {

    public SelectPointsCollector() {
        points = new java.util.LinkedList<SelectPoint>();
    }

    public void visitLine(Line shape) {
        points.add(new LineStartSelectPoint(shape));
        points.add(new LineEndSelectPoint(shape));
    }

    public void visitRectangle(Rectangle shape) {
        points.add(new RectangleLeftTopSelectPoint(shape));
        points.add(new RectangleRightTopSelectPoint(shape));
        points.add(new RectangleLeftBottomSelectPoint(shape));
        points.add(new RectangleRightBottomSelectPoint(shape));
    }

    public void visitCircle(Circle shape) {
        points.add(new CircleLeftTopSelectPoint(shape));
        points.add(new CircleRightTopSelectPoint(shape));
        points.add(new CircleLeftBottomSelectPoint(shape));
        points.add(new CircleRightBottomSelectPoint(shape));
    }

    public void visitConnect(ConnectShape shape) {
    }

    public java.util.LinkedList<SelectPoint> getPoints() {
        return points;
    }

    private java.util.LinkedList<SelectPoint> points;
}

