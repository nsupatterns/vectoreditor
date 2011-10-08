
package org.nsu.vectoreditor;

public class ShapeListItem {

    public ShapeListItem(PrivateShapeListItem i) {
        item = i;
    }

    public Shape getShape() {
        assert(!isEnd());
        return item.getShape();
    }

    public ShapeListItem getNext() {
        assert(!isEnd());
        return new ShapeListItem(item.getNext());
    }

    public boolean isEnd() {
        return item == null;
    }

    // for internal use only
    public PrivateShapeListItem getPrivateItem() {
        return item;
    }

    private PrivateShapeListItem item;
};

