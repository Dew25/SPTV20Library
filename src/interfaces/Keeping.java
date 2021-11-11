/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import java.util.List;

/**
 *
 * @author user
 */
public interface Keeping {
    public void saveBooks(List<Book> books);
    public List<Book> loadBooks();
    public void saveAuthors(List<Author> authors);
    public List<Author> loadAuthors();
    public void saveReaders(List<Reader> readers);
    public List<Reader> loadReaders();
    public void saveHistories(List<History> histories);
    public List<History> loadHistories();
}
