

package org.nsu.vectoreditor;


abstract class LineToolState {
    public abstract void draw(java.awt.Graphics canvas);
    public abstract LineToolState onMouseDown(int x, int y);
    public abstract LineToolState onMouseUp(int x, int y);
    public abstract LineToolState onMouseMove(int x, int y);
};


class LineToolStateStart extends LineToolState {
    public LineToolStateStart(ShapeList list) {
        shapes = list;
    }

    public void draw(java.awt.Graphics canvas) {}

    public LineToolState onMouseDown(int x, int y) {
        return new LineToolStatePressed(shapes, x, y);
    }

    public LineToolState onMouseUp(int x, int y) { return this; }
    public LineToolState onMouseMove(int x, int y) { return this; }

    private ShapeList shapes;
};

class LineToolStatePressed extends LineToolState {
    public LineToolStatePressed(ShapeList list, int x, int y) {
        shapes = list;
        startX = x;
        startY = y;
        endX = x;
        endY = y;
    }

    public void draw(java.awt.Graphics canvas) {
        (new Line(startX, startY, endX, endY)).draw(canvas);
    }

    public LineToolState onMouseDown(int x, int y) {
        return this;
    }

    public LineToolState onMouseUp(int x, int y) {
        if(x == startX && y == startY) {
            return new LineToolStateFreeMove(shapes, x, y);
        } else {
            shapes.add(new Line(startX, startY, x, y));
            return new LineToolStateStart(shapes);
        }
    }

    public LineToolState onMouseMove(int x, int y) {
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

class LineToolStateFreeMove extends LineToolState {
    public LineToolStateFreeMove(ShapeList list, int x, int y) {
        shapes = list;
        startX = x;
        startY = y;
        endX = x;
        endY = y;
    }

    public void draw(java.awt.Graphics canvas) {
        (new Line(startX, startY, endX, endY)).draw(canvas);
    }

    public LineToolState onMouseDown(int x, int y) {
        return this;
    }

    public LineToolState onMouseUp(int x, int y) {
        shapes.add(new Line(startX, startY, x, y));
        return new LineToolStateStart(shapes);
    }

    public LineToolState onMouseMove(int x, int y) {
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


public class LineTool extends Tool {

    LineTool(ShapeList list) {
        shapes = list;
        state = new LineToolStateStart(shapes);
    }

    public void draw(java.awt.Graphics canvas) {
        state.draw(canvas);
    }

    public void reset() {
        state = new LineToolStateStart(shapes);
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
    LineToolState state;
}

