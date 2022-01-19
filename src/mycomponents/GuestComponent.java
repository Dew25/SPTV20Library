/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycomponents;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import myclasses.GuiApp;

/**
 *
 * @author user
 */
public class GuestComponent extends JPanel{
    private ListBooksComponent listBooksComponent;
    public GuestComponent() {
        super.setPreferredSize(new Dimension(GuiApp.WIDTH_FRAME,300));
        super.setMinimumSize(getPreferredSize());
        super.setMaximumSize(getPreferredSize());
        super.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        initComponent();
    }

    private void initComponent() {
        listBooksComponent = new ListBooksComponent("", GuiApp.WIDTH_FRAME, 300,0, GuiApp.WIDTH_FRAME);
        this.add(listBooksComponent);
    }
    
}
