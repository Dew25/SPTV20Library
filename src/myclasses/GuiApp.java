/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import mycomponents.ButtonComponent;
import mycomponents.CaptionComponent;
import mycomponents.ComboBoxBooksComponent;
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
    private CaptionComponent captionComponentTabAddBook;
    private InfoComponent infoComponentTabAddBook;
    private EditComponent bookNameComponentTabAddBook;
    private ComboBoxBooksComponent comboBoxBooksComponentTabEditBook;
    private ListAuthorsComponent listAuthorsComponentTabAddBook;
    private ListAuthorsComponent listAuthorsComponentTabEditBook;
    private EditComponent publishedYearComponentTabAddBook;
    private EditComponent quantityComponentTabAddBook;
    private ButtonComponent buttonComponentTabAddBook;
    private CaptionComponent captionComponentTabEditBook;
    private InfoComponent infoComponentTabEditBook;
    private EditComponent bookNameComponentTabEditBook;
    private EditComponent publishedYearComponentTabEditBook;
    private EditComponent quantityComponentTabEditBook;
    private ButtonComponent buttonComponentTabEditBook;
    private BookFacade bookFacade = new BookFacade(Book.class);
    private DefaultComboBoxModel<Book> defaultComboBoxModel;
    private Book editBook;
    
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
        this.add(managerTabbedPane);
        
        JPanel addBookPanel = new JPanel();
        addBookPanel.setMinimumSize(new Dimension(WIDTH_FRAME,HEIGHT_FRAME));
        addBookPanel.setPreferredSize(addBookPanel.getMaximumSize());
        addBookPanel.setMaximumSize(addBookPanel.getMaximumSize());
        
        managerTabbedPane.addTab("Добавление книги", addBookPanel);
        
        addBookPanel.setLayout(new BoxLayout(addBookPanel, BoxLayout.Y_AXIS));
        addBookPanel.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponentTabAddBook = new CaptionComponent("Добавление новой книги ", this.getWidth(), 30);
        addBookPanel.add(captionComponentTabAddBook);
        infoComponentTabAddBook = new InfoComponent("", this.getWidth(), 30);
        addBookPanel.add(infoComponentTabAddBook);
        addBookPanel.add(Box.createRigidArea(new Dimension(0,10)));
        bookNameComponentTabAddBook=new EditComponent("Название книги ", this.getWidth(), 30, 300);
        addBookPanel.add(bookNameComponentTabAddBook);
        listAuthorsComponentTabAddBook = new ListAuthorsComponent("Авторы", this.getWidth(), 120, 300);
        addBookPanel.add(listAuthorsComponentTabAddBook);
        publishedYearComponentTabAddBook=new EditComponent("Год публикации книги ", this.getWidth(), 30, 80);
        addBookPanel.add(publishedYearComponentTabAddBook);
        quantityComponentTabAddBook=new EditComponent("Количество экземпляров ", this.getWidth(), 30, 40);
        addBookPanel.add(quantityComponentTabAddBook);
        buttonComponentTabAddBook=new ButtonComponent("Добавить книгу", this.getWidth(), 50, 210, 195);
        addBookPanel.add(buttonComponentTabAddBook);
        buttonComponentTabAddBook.getjButton().addActionListener(createAddBookButtonActionListener());
        
        
        JPanel editBookPanel = new JPanel();
        managerTabbedPane.addTab("Редактирование книги", editBookPanel);
        editBookPanel.setMinimumSize(new Dimension(WIDTH_FRAME,500));
        editBookPanel.setPreferredSize(editBookPanel.getMaximumSize());
        editBookPanel.setMaximumSize(editBookPanel.getMaximumSize());
        editBookPanel.setLayout(new BoxLayout(editBookPanel, BoxLayout.Y_AXIS));
        editBookPanel.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponentTabEditBook = new CaptionComponent("Изменение данных книги ", this.getWidth(), 30);
        editBookPanel.add(captionComponentTabEditBook);
        infoComponentTabEditBook = new InfoComponent("", this.getWidth(), 30);
        editBookPanel.add(infoComponentTabEditBook);
        editBookPanel.add(Box.createRigidArea(new Dimension(0,10)));
        comboBoxBooksComponentTabEditBook = new ComboBoxBooksComponent("Книги", this.getWidth(), 27, 300);
        editBookPanel.add(comboBoxBooksComponentTabEditBook);
        comboBoxBooksComponentTabEditBook.getComboBoxBooks().addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                editBook = (Book) comboBoxBooksComponentTabEditBook.getComboBoxBooks().getSelectedItem();
                bookNameComponentTabEditBook.getEditor().setText(editBook.getBookName());
                Integer releaseYear= editBook.getReleaseYear();
                publishedYearComponentTabEditBook.getEditor().setText(releaseYear.toString());
                quantityComponentTabEditBook.getEditor().setText(((Integer)editBook.getQuantity()).toString());
            }
            
        });
        comboBoxBooksComponentTabEditBook.getComboBoxBooks().setModel(getDefaultComboBoxModel());
        bookNameComponentTabEditBook= new EditComponent("Новое название книги", this.getWidth(), 30, 300);
        editBookPanel.add(bookNameComponentTabEditBook);
        listAuthorsComponentTabEditBook = new ListAuthorsComponent("Авторы", this.getWidth(), 120, 300);
        editBookPanel.add(listAuthorsComponentTabEditBook);
        publishedYearComponentTabEditBook = new EditComponent("Новый год издания", this.getWidth(), 30, 300);
        editBookPanel.add(publishedYearComponentTabEditBook);
        quantityComponentTabEditBook = new EditComponent("Другое количество книг", this.getWidth(), 30, 300);
        editBookPanel.add(quantityComponentTabEditBook);
        buttonComponentTabEditBook = new ButtonComponent("Изменить данные книги", this.getWidth(), 40, 240, 180);
        editBookPanel.add(buttonComponentTabEditBook);
        buttonComponentTabAddBook.getjButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

    
}
