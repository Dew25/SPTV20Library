/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Book;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author user
 */
public class ListBooksPanel extends JPanel{

    public ListBooksPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JList<Book> jListBooks = new JList<>();
        JLabel jLabelReaders = new JLabel("Книги");
        this.add(jLabelReaders);
        jListBooks.setPreferredSize(new Dimension(600,100));
        jListBooks.setMaximumSize(jListBooks.getPreferredSize());
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.add(jListBooks);
        jScrollPane.setPreferredSize(jListBooks.getPreferredSize());
        this.add(jListBooks
        );
    }
    
}
