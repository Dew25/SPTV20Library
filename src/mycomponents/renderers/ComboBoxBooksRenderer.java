/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycomponents.renderers;

import entity.Author;
import entity.Book;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

/**
 *
 * @author user
 */
public class ComboBoxBooksRenderer extends JLabel implements ListCellRenderer<Book>{
    private final Color background = new Color(0, 100, 255, 15);
    private final Color defaultBackground = (Color) UIManager.get("List.background");
    public ComboBoxBooksRenderer() {
        super.setOpaque(true);
        super.setHorizontalAlignment(LEFT);
        super.setVerticalAlignment(TOP);
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends Book> list, Book book, int index, boolean isSelected, boolean cellHasFocus) {
//        int selectedIndex = book.getId().intValue();
        StringBuilder sb = new StringBuilder();
        for(Author author : book.getAuthors()){
           sb.append(author.getFirstName())
                   .append(" ")
                   .append(author.getLastName())
                   .append(". ");
        }
        this.setText(String.format("%d. %s. %s %d"
                         ,book.getId()
                         ,book.getBookName()
                         ,sb.toString()
                         ,book.getReleaseYear()
                ));
            if (!isSelected){setBackground(index % 2 == 0 ? background : defaultBackground);}
        return this;
    }
}
