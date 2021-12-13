/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycomponents;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class InfoComponent extends JPanel{
    private JLabel jLabelInfo;

    public InfoComponent(String caption,int widthPanel,int heightPanel) {
        initComponent(caption,widthPanel,heightPanel);
    }

    private void initComponent(String info,int widthPanel,int heightPanel) {
        jLabelInfo = new JLabel(info);
        jLabelInfo.setPreferredSize(new Dimension(widthPanel,heightPanel));
        jLabelInfo.setFont(new Font("Tahoma",0,12));
        setMinimumSize(new Dimension(widthPanel,heightPanel));
        setMaximumSize(getMinimumSize());
        setPreferredSize(getMinimumSize());
        setLayout(new FlowLayout());
        add(jLabelInfo);
        jLabelInfo.setHorizontalAlignment(JLabel.CENTER);
    }

    public JLabel getjLabelInfo() {
        return jLabelInfo;
    }
    
}
