
package org.nsu.vectoreditor;

public class ShapeList {

    public void add(Shape shape) {

        PrivateShapeListItem item = new PrivateShapeListItem(shape);

        if(getFirst() == null) {
            // no items in the list
            setFirst(item);
            setLast(item);
        } else {
            getLast().setNext(item);
            item.setPrev(getLast());
            setLast(item);
        }
    }

    public void addBefore(Shape s, PrivateShapeListItem before) {

        PrivateShapeListItem newItem = new PrivateShapeListItem(s);

        newItem.setNext(before);

        if(before.getPrev() != null) {
            before.getPrev().setNext(newItem);
            newItem.setPrev(before.getPrev());
        } else {
            setFirst(newItem);
        }

        before.setPrev(newItem);
    }

    public void remove(PrivateShapeListItem item) {
        if(item == getFirst() && item == getLast()) {
            setFirst(null);
            setLast(null);
        } else if(item == getFirst()) {
            setFirst(item.getNext());
            item.getNext().setPrev(null);
        } else if(item == getLast()) {
            setLast(item.getPrev());
            item.getPrev().setNext(null);
        } else {
            item.getPrev().setNext(item.getNext());
            item.getNext().setPrev(item.getPrev());
        }
    }

    public PrivateShapeListItem getFirst() {
        return first;
    }

    public PrivateShapeListItem getLast() {
        return last;
    }

    private void setFirst(PrivateShapeListItem item) {
        first = item;
    }

    private void setLast(PrivateShapeListItem item) {
        last = item;
    }

    private PrivateShapeListItem first;
    private PrivateShapeListItem last;
};

