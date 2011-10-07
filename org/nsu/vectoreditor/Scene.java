
package org.nsu.vectoreditor;

public class Scene extends java.awt.Component {

    public void paint(java.awt.Graphics graphics) {
        ShapeListItem item = first;
        while(item != null) {
            item.shape.draw(graphics);
            item = item.nextItem;
        }
    }

    public void addShape(Shape shape) {

        ShapeListItem item = new ShapeListItem();
        item.shape = shape;
        item.nextItem = null;

        if(first == null) {
            // not items in the list
            first = item;
            last = item;
        } else {
            last.nextItem = item;
            last = item;
        }
    }

    ShapeListItem first;
    ShapeListItem last;
}

