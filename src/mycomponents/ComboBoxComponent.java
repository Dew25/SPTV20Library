/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycomponents;

import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class ComboBoxComponent extends EditComponent{
    private JComboBox edit;
    public ComboBoxComponent(String title, int widthPanel, int heightPanel, int widthEdit) {
        super(title, widthPanel, heightPanel, widthEdit);
    }
    @Override
    protected void setEdit(int widthEdit){
        edit = new JComboBox();
        edit.setPreferredSize(new Dimension(widthEdit,27));
        edit.setMinimumSize(edit.getPreferredSize());
        edit.setMaximumSize(edit.getPreferredSize());
        this.add(edit);
    }

    public JComboBox getEdit() {
        return edit;
    }
    
}
