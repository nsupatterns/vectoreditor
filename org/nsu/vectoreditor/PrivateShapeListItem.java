
package org.nsu.vectoreditor;

public class PrivateShapeListItem {

    public PrivateShapeListItem(Shape s) {
        shape = s;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape s) {
        shape = s;
    }

    PrivateShapeListItem getNext() {
        return next;
    }

    void setNext(PrivateShapeListItem item) {
        next = item;
    }

    PrivateShapeListItem getPrev() {
        return prev;
    }

    void setPrev(PrivateShapeListItem item) {
        prev = item;
    }

    private Shape shape;
    private PrivateShapeListItem prev;
    private PrivateShapeListItem next;
}

