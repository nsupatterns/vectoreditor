

package org.nsu.vectoreditor;


abstract class CircleToolState {
    public abstract void draw(java.awt.Graphics canvas);
    public abstract CircleToolState onMouseDown(int x, int y);
    public abstract CircleToolState onMouseUp(int x, int y);
    public abstract CircleToolState onMouseMove(int x, int y);


    protected static Circle makeCircle(int startX, int startY, int endX, int endY) {
        final int width = endX - startX;
        final int height = endY - startY;
        final int dia = Math.min(width, height);
        final int radius = dia / 2;
        final int centerX = startX + (width / 2);
        final int centerY = startY + (height / 2);
        return new Circle(centerX, centerY, radius);
    }
};


class CircleToolStateStart extends CircleToolState {
    public CircleToolStateStart(ShapeList list) {
        shapes = list;
    }

    public void draw(java.awt.Graphics canvas) {}

    public CircleToolState onMouseDown(int x, int y) {
        return new CircleToolStatePressed(shapes, x, y);
    }

    public CircleToolState onMouseUp(int x, int y) { return this; }
    public CircleToolState onMouseMove(int x, int y) { return this; }

    private ShapeList shapes;
};

class CircleToolStatePressed extends CircleToolState {
    public CircleToolStatePressed(ShapeList list, int x, int y) {
        shapes = list;
        startX = x;
        startY = y;
        endX = x;
        endY = y;
    }

    public void draw(java.awt.Graphics canvas) {
        makeCircle(startX, startY, endX, endY).draw(canvas);
    }

    public CircleToolState onMouseDown(int x, int y) {
        return this;
    }

    public CircleToolState onMouseUp(int x, int y) {
        if(x == startX && y == startY) {
            return new CircleToolStateFreeMove(shapes, x, y);
        } else {
            shapes.add(makeCircle(startX, startY, endX, endY));
            return new CircleToolStateStart(shapes);
        }
    }

    public CircleToolState onMouseMove(int x, int y) {
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

class CircleToolStateFreeMove extends CircleToolState {
    public CircleToolStateFreeMove(ShapeList list, int x, int y) {
        shapes = list;
        startX = x;
        startY = y;
        endX = x;
        endY = y;
    }

    public void draw(java.awt.Graphics canvas) {
        makeCircle(startX, startY, endX, endY).draw(canvas);
    }

    public CircleToolState onMouseDown(int x, int y) {
        return this;
    }

    public CircleToolState onMouseUp(int x, int y) {
        shapes.add(makeCircle(startX, startY, endX, endY));
        return new CircleToolStateStart(shapes);
    }

    public CircleToolState onMouseMove(int x, int y) {
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


public class CircleTool extends Tool {

    CircleTool(ShapeList list) {
        shapes = list;
        state = new CircleToolStateStart(shapes);
    }

    public void draw(java.awt.Graphics canvas) {
        state.draw(canvas);
    }

    public void reset() {
        state = new CircleToolStateStart(shapes);
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
    CircleToolState state;
}

