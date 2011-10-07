
package org.nsu.vectoreditor;

public class MainWindow extends java.awt.Frame {
    public MainWindow(Scene scene) {
        super("Vector editor");

        // handle window close event
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent event) {
                System.exit(0);
            }
        });

        add("Center", scene);
    }
}

