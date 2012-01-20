

package org.nsu.vectoreditor;


abstract class SelectToolState {
    final int selectPointSize = 4;

    public abstract void draw(java.awt.Graphics canvas);
    public abstract SelectToolState onMouseDown(int x, int y);
    public abstract SelectToolState onMouseUp(int x, int y);
    public abstract SelectToolState onMouseMove(int x, int y);
};


class SelectToolStateStart extends SelectToolState {
    public SelectToolStateStart(ShapeList list) {
        shapes = list;
    }

    public void draw(java.awt.Graphics canvas) {}

    public SelectToolState onMouseDown(int x, int y) {
        return new SelectToolStatePressed(shapes, x, y);
    }

    public SelectToolState onMouseUp(int x, int y) { return this; }
    public SelectToolState onMouseMove(int x, int y) { return this; }

    private ShapeList shapes;
};

class SelectToolStatePressed extends SelectToolState {
    public SelectToolStatePressed(ShapeList list, int x, int y) {
        shapes = list;
        startX = x;
        startY = y;
        endX = x;
        endY = y;
    }

    public void draw(java.awt.Graphics canvas) {
        (new Rectangle(startX, startY, endX, endY)).draw(canvas);
    }

    public SelectToolState onMouseDown(int x, int y) {
        return this;
    }

    public SelectToolState onMouseUp(int x, int y) {
        if(startX == x && startY == y) {
            ShapeSelector selector = new ShapeSelector(new Point(x, y));
            shapes.visit(selector);

            if(selector.getSelectedShape() != null) {
                ShapeList selList = new ShapeList();
                selList.add(selector.getSelectedShape());
                return new SelectToolStateSelected(shapes, selList);
            } else 
                return new SelectToolStateStart(shapes);
        }

        return new SelectToolStateStart(shapes);
    }

    public SelectToolState onMouseMove(int x, int y) {
        endX = x;
        endY = y;
        return this;
    }

    private ShapeList shapes;
    int startX;
    int startY;
    int endX;
    int endY;
}


class SelectToolStateSelected extends SelectToolState {

    SelectToolStateSelected(ShapeList list, ShapeList selList) {
        shapes = list;
        selectedShapes = selList;
    }

    public void draw(java.awt.Graphics canvas) {
        java.util.LinkedList<SelectPoint> points = getSelectedPoints();
        java.util.Iterator<SelectPoint> it = points.iterator();
        while(it.hasNext()) {
            SelectPoint p = it.next();

            canvas.fillRect(p.getPos().x - selectPointSize,
                            p.getPos().y - selectPointSize,
                            selectPointSize * 2,
                            selectPointSize * 2);
        }
    }

    public SelectToolState onMouseDown(int x, int y) {
        java.util.LinkedList<SelectPoint> points = getSelectedPoints();
        java.util.Iterator<SelectPoint> it = points.iterator();
        while(it.hasNext()) {
            SelectPoint p = it.next();

            if(Utils.pointInRectangle(new Point(x, y),
                                      new Point(p.getPos().x - selectPointSize, p.getPos().y - selectPointSize),
                                      new Point(p.getPos().x + selectPointSize, p.getPos().y + selectPointSize))) {

                return new SelectToolStateMovePoint(shapes,
                                                    selectedShapes,
                                                    p);
            }
        }

        ShapeSelector selector = new ShapeSelector(new Point(x, y));
        selectedShapes.visit(selector);
        if(selector.getSelectedShape() != null)
            return new SelectToolStateMove(shapes, selectedShapes, x, y);

        return new SelectToolStatePressed(shapes, x, y);
    }

    public SelectToolState onMouseUp(int x, int y) {
        return this;
    }

    public SelectToolState onMouseMove(int x, int y) {
        return this;
    }

    private java.util.LinkedList<SelectPoint> getSelectedPoints() {
        SelectPointsCollector collector = new SelectPointsCollector();
        selectedShapes.visit(collector);
        return collector.getPoints();
    }

    private ShapeList shapes;
    private ShapeList selectedShapes;
}


class SelectToolStateMovePoint extends SelectToolState {

    final int selectPointSize = 4;

    SelectToolStateMovePoint(ShapeList list,
                             ShapeList selList,
                             SelectPoint spoint) {
        shapes = list;
        selectedShapes = selList;
        point = spoint;
    }

    public void draw(java.awt.Graphics canvas) {
        SelectPointsCollector collector = new SelectPointsCollector();
        selectedShapes.visit(collector);
        java.util.LinkedList<SelectPoint> points = collector.getPoints();

        java.util.Iterator<SelectPoint> it = points.iterator();
        while(it.hasNext()) {
            SelectPoint p = it.next();

            canvas.fillRect(p.getPos().x - selectPointSize,
                            p.getPos().y - selectPointSize,
                            selectPointSize * 2,
                            selectPointSize * 2);
        }
    }

    public SelectToolState onMouseDown(int x, int y) {
        return this;
    }

    public SelectToolState onMouseUp(int x, int y) {
        return new SelectToolStateSelected(shapes, selectedShapes);
    }

    public SelectToolState onMouseMove(int x, int y) {
        point.setPos(new Point(x, y));
        return this;
    }

    private ShapeList shapes;
    private ShapeList selectedShapes;
    private SelectPoint point;
}


class SelectToolStateMove extends SelectToolState {

    final int selectPointSize = 4;

    SelectToolStateMove(ShapeList list,
                        ShapeList selList,
                        int x,
                        int y) {
        shapes = list;
        selectedShapes = selList;
        startX = x;
        startY = y;
    }

    public void draw(java.awt.Graphics canvas) {
        SelectPointsCollector collector = new SelectPointsCollector();
        selectedShapes.visit(collector);
        java.util.LinkedList<SelectPoint> points = collector.getPoints();

        java.util.Iterator<SelectPoint> it = points.iterator();
        while(it.hasNext()) {
            SelectPoint p = it.next();

            canvas.fillRect(p.getPos().x - selectPointSize,
                            p.getPos().y - selectPointSize,
                            selectPointSize * 2,
                            selectPointSize * 2);
        }
    }

    public SelectToolState onMouseDown(int x, int y) {
        return this;
    }

    public SelectToolState onMouseUp(int x, int y) {
        doMove(x, y);
        return new SelectToolStateSelected(shapes, selectedShapes);
    }

    public SelectToolState onMouseMove(int x, int y) {
        doMove(x, y);
        return this;
    }

    private void doMove(int x, int y) {
        ShapeListIterator it = selectedShapes.getFirst();
        while(!it.isEnd()) {
            Shape s = it.getShape();
            s.move(x - startX, y - startY);
            it = it.getNext();
        }

        startX = x;
        startY = y;
    }

    private ShapeList shapes;
    private ShapeList selectedShapes;
    private int startX;
    private int startY;
}


public class SelectTool extends Tool {

    SelectTool(ShapeList list) {
        shapes = list;
        state = new SelectToolStateStart(shapes);
    }

    public void draw(java.awt.Graphics canvas) {
        state.draw(canvas);
    }

    public void reset() {
        state = new SelectToolStateStart(shapes);
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
    SelectToolState state;
}

