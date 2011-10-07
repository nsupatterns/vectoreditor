
package org.nsu.vectoreditor;

public class Scene extends java.awt.Component {

    public void paint(java.awt.Graphics graphics) {
        FigureListItem item = first;
        while(item != null) {
            item.figure.draw(graphics);
            item = item.nextItem;
        }
    }

    public void addFigure(Figure figure) {

        FigureListItem item = new FigureListItem();
        item.figure = figure;
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

    FigureListItem first;
    FigureListItem last;
}

