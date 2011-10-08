
package org.nsu.vectoreditor;

public class Scene extends java.awt.Component {

    public void paint(java.awt.Graphics graphics) {
        ShapeListItem item = first;
        while(item != null) {
            item.getShape().draw(graphics);
            item = item.getNext();
        }
    }

    public void addShape(Shape shape) {

        ShapeListItem item = new ShapeListItem(shape);

        if(first == null) {
            // no items in the list
            first = item;
            last = item;
        } else {
            last.setNext(item);
            item.setPrev(last);
            last = item;
        }
    }


    public void addShapeBefore(Shape s, Shape before) {

        ShapeListItem newItem = new ShapeListItem(s);

        ShapeListItem item = first;
        while(item != null) {
            if(item.getShape() == before) {

                newItem.setNext(item);

                if(item.getPrev() != null) {
                    item.getPrev().setNext(newItem);
                    newItem.setPrev(item.getPrev());
                } else {
                    first = newItem;
                }

                item.setPrev(newItem);
                break;
            }

            item = item.getNext();
        }
    }

    public void removeShape(Shape s) {

        ShapeListItem item = first;
        while(item != null) {

            if(item.getShape() == s) {
                if(item == first && item == last) {
                    first = null;
                    last = null;
                } else if(item == first) {
                    first = item.getNext();
                    item.getNext().setPrev(null);
                } else if(item == last) {
                    last = item.getPrev();
                    item.getPrev().setNext(null);
                } else {
                    item.getPrev().setNext(item.getNext());
                    item.getNext().setPrev(item.getPrev());
                }
            }

            item = item.getNext();
        }
    }


    ShapeListItem first;
    ShapeListItem last;
}

