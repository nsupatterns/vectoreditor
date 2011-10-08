
package org.nsu.vectoreditor;

public class Line extends Shape {

    public Line(int px1, int py1, int px2, int py2) {
        x1 = px1;
        y1 = py1;
        x2 = px2;
        y2 = py2;
    }

    public void draw(java.awt.Graphics canvas) {
        canvas.drawLine(x1, y1, x2, y2);
    }

    private int x1;
    private int y1;
    private int x2;
    private int y2;
}

