
package org.nsu.vectoreditor;

public class ShapeListNode {

    public ShapeListNode(Shape s) {
        shape = s;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape s) {
        shape = s;
    }

    ShapeListNode getNext() {
        return next;
    }

    void setNext(ShapeListNode item) {
        next = item;
    }

    ShapeListNode getPrev() {
        return prev;
    }

    void setPrev(ShapeListNode item) {
        prev = item;
    }

    private Shape shape;
    private ShapeListNode prev;
    private ShapeListNode next;
}

