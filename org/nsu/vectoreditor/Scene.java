
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

        if(first == null) {
            // not items in the list
            first = item;
            last = item;
        } else {
            last.nextItem = item;
            item.prevItem = last;
            last = item;
        }
    }


    public void addShapeBefore(Shape shape, Shape before) {

        ShapeListItem newItem = new ShapeListItem();
        newItem.shape = shape;

        ShapeListItem item = first;
        while(item != null) {
            if(item.shape == before) {

                newItem.nextItem = item;

                if(item.prevItem != null) {
                    item.prevItem.nextItem = newItem;
                    newItem.prevItem = item.prevItem;
                } else {
                    first = newItem;
                }

                item.prevItem = newItem;
                break;
            }

            item = item.nextItem;
        }
    }

    public void removeShape(Shape shape) {
        ShapeListItem item = first;
        while(item != null) {

            if(item.shape == shape) {
                if(item == first && item == last) {
                    first = null;
                    last = null;
                } else if(item == first) {
                    first = item.nextItem;
                    item.nextItem.prevItem = null;
                } else if(item == last) {
                    last = item.prevItem;
                    item.prevItem.nextItem = null;
                } else {
                    item.prevItem.nextItem = item.nextItem;
                    item.nextItem.prevItem = item.prevItem;
                }
            }

            item = item.nextItem;
        }
    }


    ShapeListItem first;
    ShapeListItem last;
}

