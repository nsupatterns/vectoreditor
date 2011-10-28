
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

        setLayout(new java.awt.BorderLayout());
        add(scene, java.awt.BorderLayout.CENTER);

        java.awt.Panel buttonPanel = new java.awt.Panel();
        add(buttonPanel, java.awt.BorderLayout.WEST);
        buttonPanel.setLayout(new javax.swing.BoxLayout(buttonPanel, javax.swing.BoxLayout.Y_AXIS));
        buttonPanel.add(new java.awt.Button("Line"));
        buttonPanel.add(new java.awt.Button("Rectangle"));
        buttonPanel.add(new java.awt.Button("Circle"));
        buttonPanel.add(javax.swing.Box.createVerticalGlue());
    }
}

