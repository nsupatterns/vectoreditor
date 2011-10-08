
package org.nsu.vectoreditor;

public class ShapeListIterator {

    public ShapeListIterator(ShapeListNode i) {
        node = i;
    }

    public Shape getShape() {
        assert(!isEnd());
        return node.getShape();
    }

    public ShapeListIterator getNext() {
        assert(!isEnd());
        return new ShapeListIterator(node.getNext());
    }

    public boolean isEnd() {
        return node == null;
    }

    // for internal use only
    public ShapeListNode getNode() {
        return node;
    }

    private ShapeListNode node;
};

