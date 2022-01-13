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
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class AddBookPanelComponent extends JPanel{
    private CaptionComponent captionComponentTabAddBook;
    private InfoComponent infoComponentTabAddBook;
    private EditComponent bookNameComponentTabAddBook;
    private ListAuthorsComponent listAuthorsComponentTabAddBook;
    private EditComponent publishedYearComponentTabAddBook;
    private EditComponent quantityComponentTabAddBook;
    private ButtonComponent buttonComponentTabAddBook;
    
    private final BookFacade bookFacade;
    
    public AddBookPanelComponent(int widthPanel, int heightPanel) {
        this.bookFacade = new BookFacade(Book.class);
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(heightPanel, heightPanel);
        super.setPreferredSize(new Dimension(heightPanel,heightPanel));
        super.setMinimumSize(super.getPreferredSize());
        super.setMaximumSize(super.getPreferredSize());
        initComponents();
        
    }

    private void initComponents() {
        this.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponentTabAddBook = new CaptionComponent("Добавление новой книги ", this.getWidth(), 30);
        this.add(captionComponentTabAddBook);
        infoComponentTabAddBook = new InfoComponent("", this.getWidth(), 30);
        this.add(infoComponentTabAddBook);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        bookNameComponentTabAddBook=new EditComponent("Название книги ", this.getWidth(), 30, 300);
        this.add(bookNameComponentTabAddBook);
        listAuthorsComponentTabAddBook = new ListAuthorsComponent("Авторы", this.getWidth(), 120, 300);
        this.add(listAuthorsComponentTabAddBook);
        publishedYearComponentTabAddBook=new EditComponent("Год публикации книги ", this.getWidth(), 30, 80);
        this.add(publishedYearComponentTabAddBook);
        quantityComponentTabAddBook=new EditComponent("Количество экземпляров ", this.getWidth(), 30, 40);
        this.add(quantityComponentTabAddBook);
        buttonComponentTabAddBook=new ButtonComponent("Добавить книгу", this.getWidth(), 50, 210, 195);
        this.add(buttonComponentTabAddBook);
        buttonComponentTabAddBook.getjButton().addActionListener(createAddBookButtonActionListener());
        
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
    
}
