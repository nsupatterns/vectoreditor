
package org.nsu.vectoreditor;

public abstract class Shape {
    public abstract void draw(java.awt.Graphics canvas);
    public abstract void move(int dx, int dy);

    public abstract boolean trySelect(int x, int y);
    public abstract int getSelectPointsCount();
    public abstract Point getSelectPoint(int index);
    public abstract void setPoint(int index, int x, int y);

    public abstract int getConnectPointsCount();
    public abstract Point getConnectPoint(int index);
}

