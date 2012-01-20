

package org.nsu.vectoreditor;


public class ConnectShape extends Shape {
    ConnectShape(ConnectPoint p1, ConnectPoint p2) {
        point1 = p1;
        point2 = p2;
    }

    public void draw(java.awt.Graphics canvas) {
        Point p1 = point1.getPos();
        Point p2 = point2.getPos();

        (new Line(p1.x, p1.y, p2.x, p2.y)).draw(canvas);
    }

    public void visit(ShapeVisitor visitor) {
        visitor.visitConnect(this);
    }

    public void move(int dx, int dy) {}

    public int getConnectPointsCount() { return 0; }
    public Point getConnectPoint(int index) { return new Point(0, 0); }

    private ConnectPoint point1;
    private ConnectPoint point2;
}

