
package org.nsu.vectoreditor;

public abstract class Shape {
    public abstract void draw(java.awt.Graphics canvas);

    public Shape prev;
    public Shape next;
}

