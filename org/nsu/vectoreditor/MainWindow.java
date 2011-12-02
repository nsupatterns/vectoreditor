
package org.nsu.vectoreditor;

public class MainWindow extends java.awt.Frame {
    public MainWindow(final Scene scene, final ShapeList shapes) {
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

        java.awt.Button selectButton = new java.awt.Button("Select");
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                scene.selectTool(new SelectTool(shapes));
            }
        });

        java.awt.Button lineButton = new java.awt.Button("Line");
        lineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                scene.selectTool(new LineTool(shapes));
            }
        });

        java.awt.Button rectangleButton = new java.awt.Button("Rectangle");
        rectangleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                scene.selectTool(new RectangleTool(shapes));
            }
        });

        java.awt.Button circleButton = new java.awt.Button("Circle");
        circleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                scene.selectTool(new CircleTool(shapes));
            }
        });

        buttonPanel.add(selectButton);
        buttonPanel.add(lineButton);
        buttonPanel.add(rectangleButton);
        buttonPanel.add(circleButton);
        buttonPanel.add(javax.swing.Box.createVerticalGlue());
    }
}

