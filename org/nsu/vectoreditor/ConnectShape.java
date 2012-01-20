

package org.nsu.vectoreditor;


public class ConnectShape extends Shape {
    ConnectShape(Shape s1, int p1, Shape s2, int p2) {
        shape1 = s1;
        shape2 = s2;
        point1 = p1;
        point2 = p2;
    }

    public void draw(java.awt.Graphics canvas) {
        Point p1 = shape1.getConnectPoint(point1);
        Point p2 = shape2.getConnectPoint(point2);

        (new Line(p1.x, p1.y, p2.x, p2.y)).draw(canvas);
    }

    public void visit(ShapeVisitor visitor) {
        visitor.visitConnect(this);
    }

    public void move(int dx, int dy) {}

    public int getConnectPointsCount() { return 0; }
    public Point getConnectPoint(int index) { return new Point(0, 0); }

    private Shape shape1;
    private Shape shape2;
    private int point1;
    private int point2;
}

