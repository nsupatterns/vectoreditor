
package org.nsu.vectoreditor;

public class ShapeListItem {

    public ShapeListItem(Shape s) {
        shape = s;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape s) {
        shape = s;
    }

    ShapeListItem getNext() {
        return next;
    }

    void setNext(ShapeListItem item) {
        next = item;
    }

    ShapeListItem getPrev() {
        return prev;
    }

    void setPrev(ShapeListItem item) {
        prev = item;
    }

    private Shape shape;
    private ShapeListItem prev;
    private ShapeListItem next;
}

