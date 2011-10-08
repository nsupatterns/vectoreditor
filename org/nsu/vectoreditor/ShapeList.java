
package org.nsu.vectoreditor;

public class ShapeList {

    public void add(Shape shape) {

        ShapeListItem item = new ShapeListItem(shape);

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

    public void addBefore(Shape s, ShapeListItem before) {

        ShapeListItem newItem = new ShapeListItem(s);

        newItem.setNext(before);

        if(before.getPrev() != null) {
            before.getPrev().setNext(newItem);
            newItem.setPrev(before.getPrev());
        } else {
            setFirst(newItem);
        }

        before.setPrev(newItem);
    }

    public void remove(ShapeListItem item) {
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

    public ShapeListItem getFirst() {
        return first;
    }

    public ShapeListItem getLast() {
        return last;
    }

    private void setFirst(ShapeListItem item) {
        first = item;
    }

    private void setLast(ShapeListItem item) {
        last = item;
    }

    private ShapeListItem first;
    private ShapeListItem last;
};

