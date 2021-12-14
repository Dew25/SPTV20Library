/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycomponents;

import entity.Book;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;
import mycomponents.renderers.ComboBoxBooksRenderer;

/**
 *
 * @author user
 */
public class ComboBoxBooksComponent extends EditComponent{
    private JComboBox<Book> comboBoxBooks;
    public ComboBoxBooksComponent(String title, int widthPanel, int heightPanel, int widthEdit) {
        super(title, widthPanel, heightPanel, widthEdit);
    }
    @Override
    protected void setEdit(int widthEdit){
        comboBoxBooks = new JComboBox<>();
        comboBoxBooks.setPreferredSize(new Dimension(widthEdit,27));
        comboBoxBooks.setMinimumSize(comboBoxBooks.getPreferredSize());
        comboBoxBooks.setMaximumSize(comboBoxBooks.getPreferredSize());
        comboBoxBooks.setRenderer(new ComboBoxBooksRenderer());
        this.add(comboBoxBooks);
    }

    public JComboBox getComboBoxBooks() {
        return comboBoxBooks;
    }

    
    
}
