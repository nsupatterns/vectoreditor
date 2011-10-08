
package org.nsu.vectoreditor;

public class ShapeList {

    public void add(Shape shape) {

        PrivateShapeListItem item = new PrivateShapeListItem(shape);

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

    public void addBefore(Shape s, ShapeListItem bef) {

        PrivateShapeListItem before = bef.getPrivateItem();
        PrivateShapeListItem newItem = new PrivateShapeListItem(s);

        newItem.setNext(before);

        if(before.getPrev() != null) {
            before.getPrev().setNext(newItem);
            newItem.setPrev(before.getPrev());
        } else {
            first = newItem;
        }

        before.setPrev(newItem);
    }

    public void remove(ShapeListItem i) {
        PrivateShapeListItem item = i.getPrivateItem();

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

    public ShapeListItem getFirst() {
        if(first == null)
            return null;

        return new ShapeListItem(first);
    }

    public ShapeListItem getLast() {
        if(last == null)
            return null;

        return new ShapeListItem(last);
    }

    private PrivateShapeListItem first;
    private PrivateShapeListItem last;
};

