/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycomponents.manager;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import myclasses.GuiApp;
import mycomponents.AddBookPanelComponent;
import mycomponents.EditBookPanelComponent;

/**
 *
 * @author user
 */
public class ManagerComponent extends JPanel{
    private JTabbedPane managerTabbedPane;
    private AddBookPanelComponent addBookPanelComponent;
    private EditBookPanelComponent editBookPanelComponent;
    public ManagerComponent() {
        initComponent();
    }

    private void initComponent() {
        managerTabbedPane = new JTabbedPane();
        managerTabbedPane.setPreferredSize(new Dimension(GuiApp.WIDTH_FRAME,GuiApp.HEIGHT_FRAME));
        managerTabbedPane.setMinimumSize(managerTabbedPane.getPreferredSize());
        managerTabbedPane.setMaximumSize(managerTabbedPane.getPreferredSize());
        addBookPanelComponent = new AddBookPanelComponent(GuiApp.WIDTH_FRAME, GuiApp.HEIGHT_FRAME);
        editBookPanelComponent = new EditBookPanelComponent(GuiApp.WIDTH_FRAME, GuiApp.HEIGHT_FRAME);
        managerTabbedPane.addTab("Добавление книги", addBookPanelComponent);
        managerTabbedPane.addTab("Редактирование книги", editBookPanelComponent);
        this.add(managerTabbedPane);
    }
    
}
