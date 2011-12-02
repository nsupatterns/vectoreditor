
package org.nsu.vectoreditor;


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

    public boolean trySelect(int x, int y) {
        return Utils.pointInRectangle(new Point(x, y), new Point(x1, y1), new Point(x2, y2));
    }

    public int getSelectPointsCount() {
        return 4;
    }
    
    public SelectPoint getSelectPoint(int index) {
        switch(index) {
        case 0:
            return new RectangleLeftTopSelectPoint(this);
        case 1:
            return new RectangleRightTopSelectPoint(this);
        case 2:
            return new RectangleLeftBottomSelectPoint(this);
        case 3:
            return new RectangleRightBottomSelectPoint(this);
        default:
            assert(false);
            return null;
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

    public int getConnectPointsCount() {
        return 4;
    }
    
    public Point getConnectPoint(int index) {
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

