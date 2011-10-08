
package org.nsu.vectoreditor;

public class Scene extends java.awt.Component {

    public void paint(java.awt.Graphics graphics) {
        ShapeListItem item = first;
        while(item != null) {
            item.shape.draw(graphics);
            item = item.next;
        }
    }

    public void addShape(Shape shape) {

        ShapeListItem item = new ShapeListItem();
        item.shape = shape;

        if(first == null) {
            // no items in the list
            first = item;
            last = item;
        } else {
            last.next = item;
            item.prev = last;
            last = item;
        }
    }


    public void addShapeBefore(Shape s, Shape before) {

        ShapeListItem newItem = new ShapeListItem();
        newItem.shape = s;

        ShapeListItem item = first;
        while(item != null) {
            if(item.shape == before) {

                newItem.next = item;

                if(item.prev != null) {
                    item.prev.next = newItem;
                    newItem.prev = item.prev;
                } else {
                    first = newItem;
                }

                item.prev = newItem;
                break;
            }

            item = item.next;
        }
    }

    public void removeShape(Shape s) {

        ShapeListItem item = first;
        while(item != null) {

            if(item.shape == s) {
                if(item == first && item == last) {
                    first = null;
                    last = null;
                } else if(item == first) {
                    first = item.next;
                    item.next.prev = null;
                } else if(item == last) {
                    last = item.prev;
                    item.prev.next = null;
                } else {
                    item.prev.next = item.next;
                    item.next.prev = item.prev;
                }
            }

            item = item.next;
        }
    }


    ShapeListItem first;
    ShapeListItem last;
}

