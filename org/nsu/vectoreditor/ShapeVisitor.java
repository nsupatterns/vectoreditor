
package org.nsu.vectoreditor;

public abstract class ShapeVisitor {
    public abstract void visitLine(Line shape);
    public abstract void visitRectangle(Rectangle shape);
    public abstract void visitCircle(Circle shape);
    public abstract void visitConnect(ConnectShape shape);
}

