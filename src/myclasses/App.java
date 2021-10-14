/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author user
 */
public class App {
    public void run(){
       // System.out.println("Hello");
        Book book = new Book();
        book.setBookName("Voina i mir");
        book.setReleaseYear(2010);
        Author[] authors = new Author[1];
        Author author = new Author();
        author.setFirstName("Lev");
        author.setLastName("Tolstoy");
        author.setBirthYear(1828);
        authors[0] = author;
        book.setAuthors(authors);
        System.out.printf("Создана книга: %s, автор: %s %s, год издания: %d%n",
                            book.getBookName(),
                            book.getAuthors()[0].getFirstName(),
                            book.getAuthors()[0].getLastName(),
                            book.getReleaseYear()
        );
        Reader reader = new Reader();
        reader.setFirstname("Ivan");
        reader.setLastname("Ivanov");
        reader.setPhone("54545454545");
        System.out.printf("Создан пользователь: %s %s, с телефоном: %s%n"
                ,reader.getFirstname()
                ,reader.getLastname()
                ,reader.getPhone()
        );
        History history = new History();
        history.setBook(book);
        history.setReader(reader);
        Calendar c = new GregorianCalendar();
        history.setGivenBook(c.getTime());
        System.out.printf("Читатель %s %s взял читать книгу \"%s\", %s%n"
                ,history.getReader().getFirstname()
                ,history.getReader().getLastname()
                ,history.getBook().getBookName()
                ,history.getGivenBook()
        );
        c = new GregorianCalendar();
        history.setReturnBook(c.getTime());
        System.out.printf("Читатель %s %s вернул книгу \"%s\", %s%n"
                ,history.getReader().getFirstname()
                ,history.getReader().getLastname()
                ,history.getBook().getBookName()
                ,history.getReturnBook()
        );
    }
}
