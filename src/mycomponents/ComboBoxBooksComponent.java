/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycomponents;

import entity.Book;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mycomponents.renderers.ComboBoxBooksRenderer;

/**
 *
 * @author user
 */
public class ComboBoxBooksComponent extends JPanel{
    private JLabel jLabelTitle;
    private JComboBox<Book> comboBoxBooks;
    public ComboBoxBooksComponent(String title, int widthPanel, int heightPanel,int left,  int widthEdit) {
        initComponent(title, widthPanel, heightPanel, left,  widthEdit);
    }

    private void initComponent(String title,int widthPanel,int heightPanel, int left, int widthEdit) {
        setMinimumSize(new Dimension(widthPanel,heightPanel));
        setMaximumSize(getMinimumSize());
        setPreferredSize(getMinimumSize());
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
//        setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.jLabelTitle = new JLabel(title);
        jLabelTitle.setPreferredSize(new Dimension(left,27));
        jLabelTitle.setMinimumSize(jLabelTitle.getPreferredSize());
        jLabelTitle.setMaximumSize(jLabelTitle.getPreferredSize());
//        jLabelTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jLabelTitle.setHorizontalAlignment(JLabel.RIGHT);
        this.add(jLabelTitle);
        this.add(Box.createRigidArea(new Dimension(5,0)));
        comboBoxBooks = new JComboBox<>();
        comboBoxBooks.setPreferredSize(new Dimension(widthEdit,27));
        comboBoxBooks.setMinimumSize(comboBoxBooks.getPreferredSize());
        comboBoxBooks.setMaximumSize(comboBoxBooks.getPreferredSize());
        comboBoxBooks.setRenderer(new ComboBoxBooksRenderer());
        comboBoxBooks.setSelectedItem(-1);
        this.add(comboBoxBooks);
    }

    public JComboBox getComboBoxBooks() {
        return comboBoxBooks;
    }

    
    
}
