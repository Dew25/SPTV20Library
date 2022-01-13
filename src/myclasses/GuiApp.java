/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import entity.Author;
import entity.Book;
import facade.BookFacade;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import mycomponents.AddBookPanelComponent;
import mycomponents.ButtonComponent;
import mycomponents.CaptionComponent;
import mycomponents.ComboBoxBooksComponent;
import mycomponents.EditBookPanelComponent;
import mycomponents.EditComponent;
import mycomponents.InfoComponent;
import mycomponents.ListAuthorsComponent;

/**
 *
 * @author user
 */
public class GuiApp extends JFrame{
    private final int WIDTH_FRAME = 600;
    private final int HEIGHT_FRAME = 470;
//    private CaptionComponent captionComponentTabAddBook;
    private InfoComponent infoComponentTabAddBook;
    private EditComponent bookNameComponentTabAddBook;
    private ComboBoxBooksComponent comboBoxBooksComponentTabEditBook;
    private ListAuthorsComponent listAuthorsComponentTabAddBook;
    private ListAuthorsComponent listAuthorsComponentTabEditBook;
    private EditComponent publishedYearComponentTabAddBook;
    private EditComponent quantityComponentTabAddBook;
//    private ButtonComponent buttonComponentTabAddBook;
//    private CaptionComponent captionComponentTabEditBook;
    private InfoComponent infoComponentTabEditBook;
    private EditComponent bookNameComponentTabEditBook;
    private EditComponent publishedYearComponentTabEditBook;
    private EditComponent quantityComponentTabEditBook;
//    private ButtonComponent buttonComponentTabEditBook;
    private BookFacade bookFacade = new BookFacade(Book.class);
    private DefaultComboBoxModel<Book> defaultComboBoxModel;
//    private Book editBook;
    
    public GuiApp() {
        setDefaultComboBoxModel();
        super.setMinimumSize(new Dimension(WIDTH_FRAME,HEIGHT_FRAME));
        super.setPreferredSize(super.getMaximumSize());
        super.setMaximumSize(super.getMaximumSize());
        initComponents();
        
        
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        
    }
    
    private void initComponents() {
        JTabbedPane managerTabbedPane = new JTabbedPane();
        managerTabbedPane.setMinimumSize(new Dimension(WIDTH_FRAME,HEIGHT_FRAME));
        managerTabbedPane.setPreferredSize(managerTabbedPane.getMaximumSize());
        managerTabbedPane.setMaximumSize(managerTabbedPane.getMaximumSize());
        managerTabbedPane.addTab("Добавление книги", new AddBookPanelComponent(WIDTH_FRAME, HEIGHT_FRAME));
        managerTabbedPane.addTab("Редактирование книги", new EditBookPanelComponent(WIDTH_FRAME, HEIGHT_FRAME));
        this.add(managerTabbedPane);
        managerTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(managerTabbedPane.getSelectedIndex() == 1){
                    setDefaultComboBoxModel();
//                    comboBoxBooksComponentTabEditBook.getComboBoxBooks().setModel(getDefaultComboBoxModel());
//                    bookNameComponentTabEditBook.getEditor().setText("");
//                    publishedYearComponentTabEditBook.getEditor().setText("");
//                    quantityComponentTabEditBook.getEditor().setText("");
//                    listAuthorsComponentTabEditBook.getList().clearSelection();
//                    comboBoxBooksComponentTabEditBook.getComboBoxBooks().setSelectedIndex(-1);
                }
            }
        });
                
        
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiApp().setVisible(true);
            }
        });
    }

    private ActionListener createAddBookButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Book book = new Book();
                if(bookNameComponentTabAddBook.getEditor().getText().isEmpty()){
                    infoComponentTabAddBook.getjLabelInfo().setForeground(Color.red);
                    infoComponentTabAddBook.getjLabelInfo().setText("Введите название книги");
                    return;
                }
                book.setBookName(bookNameComponentTabAddBook.getEditor().getText());
                List<Author> authorsBook = listAuthorsComponentTabAddBook.getList().getSelectedValuesList();
                if(authorsBook.isEmpty()){
                    infoComponentTabAddBook.getjLabelInfo().setForeground(Color.red);
                    infoComponentTabAddBook.getjLabelInfo().setText("Выберите авторов книги");
                    return;
                }
                book.setAuthors(authorsBook);
                try {
                    book.setReleaseYear(Integer.parseInt(publishedYearComponentTabAddBook.getEditor().getText()));
                } catch (Exception ex) {
                    infoComponentTabAddBook.getjLabelInfo().setForeground(Color.red);
                    infoComponentTabAddBook.getjLabelInfo().setText("Введите год издания книги цифрами");
                    return;
                }
                try {
                    book.setQuantity(Integer.parseInt(quantityComponentTabAddBook.getEditor().getText()));
                    book.setCount(book.getQuantity());
                } catch (Exception ex) {
                    infoComponentTabAddBook.getjLabelInfo().setForeground(Color.red);
                    infoComponentTabAddBook.getjLabelInfo().setText("Введите количество книг цифрами");
                    return;
                }
                try {
                    bookFacade.create(book);
                    infoComponentTabAddBook.getjLabelInfo().setForeground(Color.blue);
                    infoComponentTabAddBook.getjLabelInfo().setText("Книга успешно добавлена в базу");
                } catch (Exception ex) {
                    infoComponentTabAddBook.getjLabelInfo().setForeground(Color.red);
                    infoComponentTabAddBook.getjLabelInfo().setText("Книгу добавить не удалось :(");
                }
            }
        };
    }

    private ComboBoxModel getDefaultComboBoxModel() {
        return defaultComboBoxModel;
    }

    private void setDefaultComboBoxModel() {
        defaultComboBoxModel = new DefaultComboBoxModel<>();
        List<Book> books = bookFacade.findAll();
        for (Book book : books) {
            defaultComboBoxModel.addElement(book);
        }
    }

    private ActionListener editBookActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book book = (Book) comboBoxBooksComponentTabEditBook.getComboBoxBooks().getSelectedItem();
                if(bookNameComponentTabEditBook.getEditor().getText().isEmpty()){
                    infoComponentTabEditBook.getjLabelInfo().setForeground(Color.red);
                    infoComponentTabEditBook.getjLabelInfo().setText("Введите название книги");
                    return;
                }
                book.setBookName(bookNameComponentTabEditBook.getEditor().getText());
                List<Author> authorsBook = listAuthorsComponentTabEditBook.getList().getSelectedValuesList();
                if(authorsBook.isEmpty()){
                    infoComponentTabEditBook.getjLabelInfo().setForeground(Color.red);
                    infoComponentTabEditBook.getjLabelInfo().setText("Выберите авторов книги");
                    return;
                }
                book.setAuthors(authorsBook);
                try {
                    book.setReleaseYear(Integer.parseInt(publishedYearComponentTabEditBook.getEditor().getText()));
                } catch (Exception ex) {
                    infoComponentTabEditBook.getjLabelInfo().setForeground(Color.red);
                    infoComponentTabEditBook.getjLabelInfo().setText("Введите год издания книги цифрами");
                    return;
                }
                try {
                    book.setQuantity(Integer.parseInt(quantityComponentTabEditBook.getEditor().getText()));
                    book.setCount(book.getQuantity());
                } catch (Exception ex) {
                    infoComponentTabEditBook.getjLabelInfo().setForeground(Color.red);
                    infoComponentTabEditBook.getjLabelInfo().setText("Введите количество книг цифрами");
                    return;
                }
                try {
                    bookFacade.edit(book);
                    infoComponentTabEditBook.getjLabelInfo().setForeground(Color.blue);
                    infoComponentTabEditBook.getjLabelInfo().setText("Книга успешно добавлена в базу");
                    comboBoxBooksComponentTabEditBook.getComboBoxBooks().setSelectedIndex(-1);
                } catch (Exception ex) {
                    infoComponentTabEditBook.getjLabelInfo().setForeground(Color.red);
                    infoComponentTabEditBook.getjLabelInfo().setText("Книгу добавить не удалось :(");
                }
            }
        };
                
    }
    

    
}
