
package org.nsu.vectoreditor;

public class Rectangle extends Shape {

    public Rectangle(int px1, int py1, int px2, int py2) {
        x1 = px1;
        y1 = py1;
        x2 = px2;
        y2 = py2;
    }

    public void draw(java.awt.Graphics canvas) {
        canvas.drawLine(x1, y1, x2, y1);
        canvas.drawLine(x2, y1, x2, y2);
        canvas.drawLine(x2, y2, x1, y2);
        canvas.drawLine(x1, y2, x1, y1);
    }

    int x1;
    int y1;
    int x2;
    int y2;
}

