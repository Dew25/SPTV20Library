/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Reader;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class ListReadersPanel extends JPanel{

    public ListReadersPanel() {
        this.setLayout(new FlowLayout());
        JLabel jLabelReaders = new JLabel("Читатели");
        this.add(jLabelReaders);
        JComboBox<Reader> jComboBoxReaders = new JComboBox<>();
        jComboBoxReaders.setPreferredSize(new Dimension(300, 27));
        jComboBoxReaders.setMaximumSize(jComboBoxReaders.getPreferredSize());
        this.add(jComboBoxReaders);
    }
    
}
