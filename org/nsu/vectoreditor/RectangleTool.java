

package org.nsu.vectoreditor;


abstract class RectangleToolState {
    public abstract void draw(java.awt.Graphics canvas);
    public abstract RectangleToolState onMouseDown(int x, int y);
    public abstract RectangleToolState onMouseUp(int x, int y);
    public abstract RectangleToolState onMouseMove(int x, int y);
};


class RectangleToolStateStart extends RectangleToolState {
    public RectangleToolStateStart(ShapeList list) {
        shapes = list;
    }

    public void draw(java.awt.Graphics canvas) {}

    public RectangleToolState onMouseDown(int x, int y) {
        return new RectangleToolStatePressed(shapes, x, y);
    }

    public RectangleToolState onMouseUp(int x, int y) { return this; }
    public RectangleToolState onMouseMove(int x, int y) { return this; }

    private ShapeList shapes;
};

class RectangleToolStatePressed extends RectangleToolState {
    public RectangleToolStatePressed(ShapeList list, int x, int y) {
        shapes = list;
        startX = x;
        startY = y;
        endX = x;
        endY = y;
    }

    public void draw(java.awt.Graphics canvas) {
        (new Rectangle(startX, startY, endX, endY)).draw(canvas);
    }

    public RectangleToolState onMouseDown(int x, int y) {
        return this;
    }

    public RectangleToolState onMouseUp(int x, int y) {
        if(x == startX && y == startY) {
            return new RectangleToolStateFreeMove(shapes, x, y);
        } else {
            shapes.add(new Rectangle(startX, startY, x, y));
            return new RectangleToolStateStart(shapes);
        }
    }

    public RectangleToolState onMouseMove(int x, int y) {
        endX = x;
        endY = y;
        return this;
    }

    private ShapeList shapes;
    int startX;
    int startY;
    int endX;
    int endY;
};

class RectangleToolStateFreeMove extends RectangleToolState {
    public RectangleToolStateFreeMove(ShapeList list, int x, int y) {
        shapes = list;
        startX = x;
        startY = y;
        endX = x;
        endY = y;
    }

    public void draw(java.awt.Graphics canvas) {
        (new Rectangle(startX, startY, endX, endY)).draw(canvas);
    }

    public RectangleToolState onMouseDown(int x, int y) {
        return this;
    }

    public RectangleToolState onMouseUp(int x, int y) {
        shapes.add(new Rectangle(startX, startY, x, y));
        return new RectangleToolStateStart(shapes);
    }

    public RectangleToolState onMouseMove(int x, int y) {
        endX = x;
        endY = y;
        return this;
    }

    private ShapeList shapes;
    int startX;
    int startY;
    int endX;
    int endY;
};


public class RectangleTool extends Tool {

    RectangleTool(ShapeList list) {
        shapes = list;
        state = new RectangleToolStateStart(shapes);
    }

    public void draw(java.awt.Graphics canvas) {
        state.draw(canvas);
    }

    public void reset() {
        state = new RectangleToolStateStart(shapes);
    }

    public void onMouseDown(int x, int y) {
        state = state.onMouseDown(x, y);
    }

    public void onMouseUp(int x, int y) {
        state = state.onMouseUp(x, y);
    }

    public void onMouseMove(int x, int y) {
        state = state.onMouseMove(x, y);
    }

    ShapeList shapes;
    RectangleToolState state;
}

