
package org.nsu.vectoreditor;

public class Scene extends java.awt.Component {

    public Scene() {
        shapes = new ShapeList();
    }

    public void paint(java.awt.Graphics graphics) {
        ShapeListItem item = shapes.getFirst();
        while(item != null) {
            item.getShape().draw(graphics);
            item = item.getNext();
        }
    }

    public void addShape(Shape shape) {

        ShapeListItem item = new ShapeListItem(shape);

        if(shapes.getFirst() == null) {
            // no items in the list
            shapes.setFirst(item);
            shapes.setLast(item);
        } else {
            shapes.getLast().setNext(item);
            item.setPrev(shapes.getLast());
            shapes.setLast(item);
        }
    }


    public void addShapeBefore(Shape s, Shape before) {

        ShapeListItem newItem = new ShapeListItem(s);

        ShapeListItem item = shapes.getFirst();
        while(item != null) {
            if(item.getShape() == before) {

                newItem.setNext(item);

                if(item.getPrev() != null) {
                    item.getPrev().setNext(newItem);
                    newItem.setPrev(item.getPrev());
                } else {
                    shapes.setFirst(newItem);
                }

                item.setPrev(newItem);
                break;
            }

            item = item.getNext();
        }
    }

    public void removeShape(Shape s) {

        ShapeListItem item = shapes.getFirst();
        while(item != null) {

            if(item.getShape() == s) {
                if(item == shapes.getFirst() && item == shapes.getLast()) {
                    shapes.setFirst(null);
                    shapes.setLast(null);
                } else if(item == shapes.getFirst()) {
                    shapes.setFirst(item.getNext());
                    item.getNext().setPrev(null);
                } else if(item == shapes.getLast()) {
                    shapes.setLast(item.getPrev());
                    item.getPrev().setNext(null);
                } else {
                    item.getPrev().setNext(item.getNext());
                    item.getNext().setPrev(item.getPrev());
                }
            }

            item = item.getNext();
        }
    }

    ShapeList shapes;
}

