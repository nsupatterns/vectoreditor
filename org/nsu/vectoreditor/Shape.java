
package org.nsu.vectoreditor;

public abstract class Shape {
    public abstract void draw(java.awt.Graphics canvas);
    public abstract void move(int dx, int dy);

    public abstract void visit(ShapeVisitor visitor);

    public abstract boolean trySelect(int x, int y);
    public abstract int getSelectPointsCount();
    public abstract SelectPoint getSelectPoint(int index);

    public abstract int getConnectPointsCount();
    public abstract Point getConnectPoint(int index);
}

