
package org.nsu.vectoreditor;


public class ShapeSelector extends ShapeVisitor {

    public ShapeSelector(Point p) {
        pos = p;
    }

    public void visitLine(Line shape) {
        double dx = pos.x;
        double dy = pos.y;
        double dx1 = shape.getStart().x;
        double dy1 = shape.getStart().y;
        double dx2 = shape.getEnd().x;
        double dy2 = shape.getEnd().y;
        
        double a = (dx - dx1) / (dx2 - dx1);
        double b = (dy - dy1) / (dy2 - dy1);
        
        if(Math.abs(a - b) > 0.01)
            return;
        
        if(Utils.pointInRectangle(pos, shape.getStart(), shape.getEnd()))
            selectedShape = shape;
    }

    public void visitRectangle(Rectangle shape) {
        if(Utils.pointInRectangle(pos, shape.getLeftTop(), shape.getRightBottom()))
            selectedShape = shape;
    }

    public void visitCircle(Circle shape) {
        Point c = shape.getCenter();
        int r = shape.getRadius();
        if((pos.x - c.x) * (pos.x - c.x) + (pos.y - c.y) * (pos.y - c.y) <= r * r)
            selectedShape = shape;
    }

    public void visitConnect(ConnectShape shape) {
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    private Point pos;
    private Shape selectedShape;
}

