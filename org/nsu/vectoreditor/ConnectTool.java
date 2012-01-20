

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

        ConnectPointsCollector collector = new ConnectPointsCollector();
        shapes.visit(collector);

        java.util.LinkedList<ConnectPoint> points = collector.getPoints();
        java.util.Iterator<ConnectPoint> it = points.iterator();

        while(it.hasNext()) {
            Point p = it.next().getPos();

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
    }

    public ConnectToolState onMouseDown(int x, int y) {
        return this;
    }

    public ConnectToolState onMouseUp(int x, int y) {
        ConnectPointsCollector collector = new ConnectPointsCollector();
        shapes.visit(collector);

        java.util.LinkedList<ConnectPoint> points = collector.getPoints();
        java.util.Iterator<ConnectPoint> it = points.iterator();

        while(it.hasNext()) {
            ConnectPoint cp = it.next();
            Point p = cp.getPos();

            if(Utils.pointInRectangle(new Point(x, y),
                                      new Point(p.x - selectPointSize,
                                                p.y - selectPointSize),
                                      new Point(p.x + selectPointSize,
                                                p.y + selectPointSize))) {
                
                return new ConnectToolStateConnected(shapes, cp);
            }
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
    public ConnectToolStateConnected(ShapeList list, ConnectPoint p1) {
        shapes = list;
        point1 = p1;
        
        Point p = p1.getPos();
        x = p.x;
        y = p.y;
    }

    public void draw(java.awt.Graphics canvas) {
        Point p = point1.getPos();
        (new Line(p.x, p.y, x, y)).draw(canvas);

        canvas.fillRect(p.x - selectPointSize,
                        p.y - selectPointSize,
                        selectPointSize * 2,
                        selectPointSize * 2);

        ConnectPointsCollector collector = new ConnectPointsCollector();
        shapes.visit(collector);

        java.util.LinkedList<ConnectPoint> points = collector.getPoints();
        java.util.Iterator<ConnectPoint> it = points.iterator();

        while(it.hasNext()) {
            ConnectPoint cp = it.next();
            p = cp.getPos();

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
    }

    public ConnectToolState onMouseDown(int x, int y) {
        return this;
    }

    public ConnectToolState onMouseUp(int x, int y) {
        ConnectPointsCollector collector = new ConnectPointsCollector();
        shapes.visit(collector);

        java.util.LinkedList<ConnectPoint> points = collector.getPoints();
        java.util.Iterator<ConnectPoint> it = points.iterator();

        while(it.hasNext()) {
            ConnectPoint cp = it.next();
            Point p = cp.getPos();

            if(Utils.pointInRectangle(new Point(x, y),
                                      new Point(p.x - selectPointSize,
                                                p.y - selectPointSize),
                                      new Point(p.x + selectPointSize,
                                                p.y + selectPointSize))) {
                
                shapes.add(new ConnectShape(point1, cp));
                break;
            }
        }

        return new ConnectToolStateStart(shapes);
    }

    public ConnectToolState onMouseMove(int px, int py) {
        x = px;
        y = py;
        return this;
    }

    private ShapeList shapes;
    private ConnectPoint point1;
    private int x;
    private int y;
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

