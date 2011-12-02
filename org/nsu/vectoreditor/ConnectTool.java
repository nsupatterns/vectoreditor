

package org.nsu.vectoreditor;


abstract class ConnectToolState {
    final int selectPointSize = 4;

    public abstract void draw(java.awt.Graphics canvas);
    public abstract ConnectToolState onMouseDown(int x, int y);
    public abstract ConnectToolState onMouseUp(int x, int y);
    public abstract ConnectToolState onMouseMove(int x, int y);
};


class ConnectToolStateStart extends ConnectToolState {
    public ConnectToolStateStart(ShapeList list) {
        shapes = list;
    }

    public void draw(java.awt.Graphics canvas) {

        ShapeListIterator it = shapes.getFirst();
        while(!it.isEnd()) {
            Shape s = it.getShape();

            int npoints = s.getConnectPointsCount();
            for(int i = 0; i < npoints; ++i) {
                Point p = s.getConnectPoint(i);

                if(Utils.pointInRectangle(new Point(x, y),
                                          new Point(p.x - selectPointSize,
                                                    p.y - selectPointSize),
                                          new Point(p.x + selectPointSize,
                                                    p.y + selectPointSize))) {

                    canvas.fillRect(p.x - selectPointSize,
                                    p.y - selectPointSize,
                                    selectPointSize * 2,
                                    selectPointSize * 2);
                    
                    break;
                }

            }

            it = it.getNext();
        }
    }

    public ConnectToolState onMouseDown(int x, int y) {
        return this;
    }

    public ConnectToolState onMouseUp(int x, int y) {
        ShapeListIterator it = shapes.getFirst();
        while(!it.isEnd()) {
            Shape s = it.getShape();

            int npoints = s.getConnectPointsCount();
            for(int i = 0; i < npoints; ++i) {
                Point p = s.getConnectPoint(i);

                if(Utils.pointInRectangle(new Point(x, y),
                                          new Point(p.x - selectPointSize,
                                                    p.y - selectPointSize),
                                          new Point(p.x + selectPointSize,
                                                    p.y + selectPointSize))) {

                    return new ConnectToolStateConnected(shapes, s, i);
                }

            }

            it = it.getNext();
        }

        return this;
    }

    public ConnectToolState onMouseMove(int px, int py) {
        x = px;
        y = py;
        return this;
    }

    private ShapeList shapes;
    int x;
    int y;
};


class ConnectToolStateConnected extends ConnectToolState {
    public ConnectToolStateConnected(ShapeList list, Shape s1, int p1) {
        shapes = list;
        shape1 = s1;
        point1 = p1;
        
        Point p = s1.getConnectPoint(p1);
        x = p.x;
        y = p.y;
    }

    public void draw(java.awt.Graphics canvas) {
        Point p = shape1.getConnectPoint(point1);
        (new Line(p.x, p.y, x, y)).draw(canvas);

        canvas.fillRect(p.x - selectPointSize,
                        p.y - selectPointSize,
                        selectPointSize * 2,
                        selectPointSize * 2);

        ShapeListIterator it = shapes.getFirst();
        while(!it.isEnd()) {
            Shape s = it.getShape();

            int npoints = s.getConnectPointsCount();
            for(int i = 0; i < npoints; ++i) {
                p = s.getConnectPoint(i);

                if(Utils.pointInRectangle(new Point(x, y),
                                          new Point(p.x - selectPointSize,
                                                    p.y - selectPointSize),
                                          new Point(p.x + selectPointSize,
                                                    p.y + selectPointSize))) {

                    canvas.fillRect(p.x - selectPointSize,
                                    p.y - selectPointSize,
                                    selectPointSize * 2,
                                    selectPointSize * 2);
                    
                    break;
                }

            }

            it = it.getNext();
        }
    }

    public ConnectToolState onMouseDown(int x, int y) {
        return this;
    }

    public ConnectToolState onMouseUp(int x, int y) {
        ShapeListIterator it = shapes.getFirst();
        while(!it.isEnd()) {
            Shape s = it.getShape();

            int npoints = s.getConnectPointsCount();
            for(int i = 0; i < npoints; ++i) {
                Point p = s.getConnectPoint(i);

                if(Utils.pointInRectangle(new Point(x, y),
                                          new Point(p.x - selectPointSize,
                                                    p.y - selectPointSize),
                                          new Point(p.x + selectPointSize,
                                                    p.y + selectPointSize))) {

                    shapes.add(new ConnectShape(shape1, point1, s, i));
                    break;
                }

            }

            it = it.getNext();
        }

        return new ConnectToolStateStart(shapes);
    }

    public ConnectToolState onMouseMove(int px, int py) {
        x = px;
        y = py;
        return this;
    }

    private ShapeList shapes;
    private Shape shape1;
    private int point1;
    int x;
    int y;
}


public class ConnectTool extends Tool {

    ConnectTool(ShapeList list) {
        shapes = list;
        state = new ConnectToolStateStart(shapes);
    }

    public void draw(java.awt.Graphics canvas) {
        state.draw(canvas);
    }

    public void reset() {
        state = new ConnectToolStateStart(shapes);
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
    ConnectToolState state;
}

