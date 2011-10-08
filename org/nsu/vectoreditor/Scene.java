
package org.nsu.vectoreditor;

public class Scene extends java.awt.Component {

    public Scene() {
        shapes = new ShapeList();
    }

    public void paint(java.awt.Graphics graphics) {
        ShapeListIterator it = shapes.getFirst();
        while(!it.isEnd()) {
            it.getShape().draw(graphics);
            it = it.getNext();
        }
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }


    public void addShapeBefore(Shape s, Shape before) {

        ShapeListIterator it = shapes.getFirst();
        while(!it.isEnd()) {
            if(it.getShape() == before) {
                shapes.addBefore(s, it);
                break;
            }

            it = it.getNext();
        }
    }

    public void removeShape(Shape s) {

        ShapeListIterator it = shapes.getFirst();
        while(!it.isEnd()) {
            if(it.getShape() == s) {
                shapes.remove(it);
            }

            it = it.getNext();
        }
    }

    ShapeList shapes;
}

