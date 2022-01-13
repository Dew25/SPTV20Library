/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycomponents;

import entity.Author;
import entity.Book;
import facade.BookFacade;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.ListModel;

/**
 *
 * @author user
 */
public class EditBookPanelComponent extends JPanel{
    private CaptionComponent captionComponentTabEditBook;
    private InfoComponent infoComponentTabEditBook;
    private ComboBoxBooksComponent comboBoxBooksComponentTabEditBook;
    private DefaultComboBoxModel defaultComboBoxModel;
    private EditComponent bookNameComponentTabEditBook;
    private EditComponent publishedYearComponentTabEditBook;
    private EditComponent quantityComponentTabEditBook;
    private ButtonComponent buttonComponentTabEditBook;
    private ListAuthorsComponent listAuthorsComponentTabEditBook;
    
    private final BookFacade bookFacade;
    
    public EditBookPanelComponent(int widthPanel, int heightPanel) {
        this.bookFacade = new BookFacade(Book.class);
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(heightPanel, heightPanel);
        super.setPreferredSize(new Dimension(heightPanel,heightPanel));
        super.setMinimumSize(super.getPreferredSize());
        super.setMaximumSize(super.getPreferredSize());
        setDefaultComboBoxModel();
        initComponents(widthPanel, heightPanel);
        
    }

    private void initComponents(int widthPanel, int heightPanel) {
        this.setMinimumSize(new Dimension(widthPanel,500));
        this.setPreferredSize(this.getMaximumSize());
        this.setMaximumSize(this.getMaximumSize());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponentTabEditBook = new CaptionComponent("Изменение данных книги ", this.getWidth(), 30);
        this.add(captionComponentTabEditBook);
        infoComponentTabEditBook = new InfoComponent("", this.getWidth(), 30);
        this.add(infoComponentTabEditBook);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        comboBoxBooksComponentTabEditBook = new ComboBoxBooksComponent("Книги", this.getWidth(), 27, 300);
        comboBoxBooksComponentTabEditBook.getComboBoxBooks().setSelectedIndex(-1);
        comboBoxBooksComponentTabEditBook.getComboBoxBooks().setModel(getDefaultComboBoxModel());
        this.add(comboBoxBooksComponentTabEditBook);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        bookNameComponentTabEditBook= new EditComponent("Новое название книги", this.getWidth(), 30, 300);
        this.add(bookNameComponentTabEditBook);
        listAuthorsComponentTabEditBook = new ListAuthorsComponent("Авторы", this.getWidth(), 120, 300);
        this.add(listAuthorsComponentTabEditBook);
        publishedYearComponentTabEditBook = new EditComponent("Новый год издания", this.getWidth(), 30, 100);
        this.add(publishedYearComponentTabEditBook);
        quantityComponentTabEditBook = new EditComponent("Другое количество книг", this.getWidth(), 30, 50);
        this.add(quantityComponentTabEditBook);
        buttonComponentTabEditBook = new ButtonComponent("Изменить данные книги", this.getWidth(), 40, 240, 180);
        this.add(buttonComponentTabEditBook);
        buttonComponentTabEditBook.getjButton().addActionListener(editBookActionListener());
        
        comboBoxBooksComponentTabEditBook.getComboBoxBooks().addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                infoComponentTabEditBook.getjLabelInfo().setText("");
                Book editBook = (Book) e.getItem();
                if(editBook == null) {
                    bookNameComponentTabEditBook.getEditor().setText("");
                    publishedYearComponentTabEditBook.getEditor().setText("");
                    quantityComponentTabEditBook.getEditor().setText("");
                    listAuthorsComponentTabEditBook.getList().clearSelection();
                    return;
                }
                bookNameComponentTabEditBook.getEditor().setText(editBook.getBookName());
                Integer releaseYear= editBook.getReleaseYear();
                publishedYearComponentTabEditBook.getEditor().setText(releaseYear.toString());
                quantityComponentTabEditBook.getEditor().setText(((Integer)editBook.getQuantity()).toString());
                listAuthorsComponentTabEditBook.getList().clearSelection();
                ListModel<Author> listModel = listAuthorsComponentTabEditBook.getList().getModel();
                for (int i=0;i<listModel.getSize();i++) {
                    if(editBook.getAuthors().contains(listModel.getElementAt(i))){
                        listAuthorsComponentTabEditBook.getList().getSelectionModel().addSelectionInterval(i, i);
                    }
                }
            }
            
        });
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
                    infoComponentTabEditBook.getjLabelInfo().setText("Книга изменена");
                    bookNameComponentTabEditBook.getEditor().setText("");
                    listAuthorsComponentTabEditBook.getList().clearSelection();
                    publishedYearComponentTabEditBook.getEditor().setText("");
                    quantityComponentTabEditBook.getEditor().setText("");
                    comboBoxBooksComponentTabEditBook.getComboBoxBooks().setSelectedIndex(-1);
                    
                } catch (Exception ex) {
                    infoComponentTabEditBook.getjLabelInfo().setForeground(Color.red);
                    infoComponentTabEditBook.getjLabelInfo().setText("Книгу изменить не удалось :(");
                }
            }
        };
                
    }
    private DefaultComboBoxModel getDefaultComboBoxModel() {
        return defaultComboBoxModel;
    }

    private void setDefaultComboBoxModel() {
        defaultComboBoxModel = new DefaultComboBoxModel<>();
        List<Book> books = bookFacade.findAll();
        for (Book book : books) {
            defaultComboBoxModel.addElement(book);
        }
    }
    
}
