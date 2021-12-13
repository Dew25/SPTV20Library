/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycomponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class EditComponent extends JPanel{
    private JLabel jLabelTitle;
    private JTextField edit;

    public EditComponent(String title,int widthPanel,int heightPanel,int widthEdit) {
        initComponent(title, widthPanel, heightPanel, widthEdit);
    }

    private void initComponent(String title,int widthPanel,int heightPanel, int widthEdit) {
        setMinimumSize(new Dimension(widthPanel,heightPanel));
        setMaximumSize(getMinimumSize());
        setPreferredSize(getMinimumSize());
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
//        setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.jLabelTitle = new JLabel(title);
        jLabelTitle.setPreferredSize(new Dimension(widthPanel/3,27));
        jLabelTitle.setMinimumSize(jLabelTitle.getPreferredSize());
        jLabelTitle.setMaximumSize(jLabelTitle.getPreferredSize());
//        jLabelTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jLabelTitle.setHorizontalAlignment(JLabel.RIGHT);
        this.add(jLabelTitle);
        this.add(Box.createRigidArea(new Dimension(5,0)));
        if(widthEdit==0){
            this.setEdit(widthPanel-widthPanel/3 - 40);
        }else{
            this.setEdit(widthEdit);
        }
        
   }
    protected void setEdit(int widthEdit){
        edit = new JTextField();
        edit.setPreferredSize(new Dimension(widthEdit,27));
        edit.setMinimumSize(edit.getPreferredSize());
        edit.setMaximumSize(edit.getPreferredSize());
        this.add(edit);
    }

    public JTextField getEditor() {
        return edit;
    }
    
}
