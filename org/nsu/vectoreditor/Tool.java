
package org.nsu.vectoreditor;

public abstract class Tool {
    public abstract void draw(java.awt.Graphics canvas);
    public abstract void reset();

    public abstract void onMouseDown(int x, int y);
    public abstract void onMouseUp(int x, int y);
    public abstract void onMouseMove(int x, int y);
}

