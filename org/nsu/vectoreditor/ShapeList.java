
package org.nsu.vectoreditor;

public class ShapeList {

    public ShapeListItem getFirst() {
        return first;
    }

    public void setFirst(ShapeListItem item) {
        first = item;
    }

    public ShapeListItem getLast() {
        return last;
    }

    public void setLast(ShapeListItem item) {
        last = item;
    }

    private ShapeListItem first;
    private ShapeListItem last;
};

