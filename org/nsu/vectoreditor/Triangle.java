
package org.nsu.vectoreditor;

public class Triangle extends Figure {

    public Triangle(int px1, int py1, int px2, int py2, int px3, int py3) {
        x1 = px1;
        y1 = py1;
        x2 = px2;
        y2 = py2;
        x3 = px3;
        y3 = py3;
    }

    public void draw(java.awt.Graphics canvas) {
        canvas.drawLine(x1, y1, x2, y2);
        canvas.drawLine(x2, y2, x3, y3);
        canvas.drawLine(x3, y3, x1, y1);
    }

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int x3;
    private int y3;
}

