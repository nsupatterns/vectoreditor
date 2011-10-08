
package org.nsu.vectoreditor;

public class ShapeListItem {

    public ShapeListItem(PrivateShapeListItem i) {
        item = i;
    }

    public Shape getShape() {
        return item.getShape();
    }

    public ShapeListItem getNext() {
        if(item.getNext() == null)
            return null;

        return new ShapeListItem(item.getNext());
    }

    // for internal use only
    public PrivateShapeListItem getPrivateItem() {
        return item;
    }

    private PrivateShapeListItem item;
};

