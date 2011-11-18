
package org.nsu.vectoreditor;

public class Circle extends Shape {

    public Circle(int px, int py, int pradius) {
        x = px;
        y = py;
        radius = pradius;
    }

    public void draw(java.awt.Graphics canvas) {
        canvas.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    private int x;
    private int y;
    private int radius;
}

