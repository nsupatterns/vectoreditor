

package org.nsu.vectoreditor;


public class LineTool extends Tool {

    private enum State {
        START,
        FIRST_PRESSED,
        FREE_MOVE
    }

    LineTool(ShapeList list) {
        shapes = list;
        state = State.START;
    }

    public void draw(java.awt.Graphics canvas) {

        if(state == State.START) {
            return;
        }

        (new Line(startX, startY, endX, endY)).draw(canvas);
    }

    public void reset() {
        state = State.START;
    }

    public void onMouseDown(int x, int y) {
        switch(state) {
        case START:
            startX = x;
            startY = y;
            endX = x;
            endY = y;
            state = State.FIRST_PRESSED;
            break;

        case FIRST_PRESSED:
            break;

        case FREE_MOVE:
            break;
        }
    }

    public void onMouseUp(int x, int y) {
        switch(state) {
        case START:
            break;

        case FIRST_PRESSED:
            if(startX == x && startY == y) {
                state = State.FREE_MOVE;
            } else {
                shapes.add(new Line(startX, startY, x, y));
                state = State.START;
            }
            break;

        case FREE_MOVE:
            shapes.add(new Line(startX, startY, x, y));
            state = State.START;
            break;
        }
    }

    public void onMouseMove(int x, int y) {
        switch(state) {
        case START:
            break;

        case FIRST_PRESSED:
        case FREE_MOVE:
            endX = x;
            endY = y;
            break;
        }
        endX = x;
        endY = y;
    }

    ShapeList shapes;
    State state;
    int startX;
    int startY;
    int endX;
    int endY;
}

