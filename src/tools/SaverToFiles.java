/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import interfaces.Keeping;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class SaverToFiles implements Keeping{

    @Override
    public void saveBooks(List<Book> books) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("books");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(books);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет файла books", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("books");
            ois = new ObjectInputStream(fis);
            books = (List<Book>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "books еше не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка чтения", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        }
        return books;
    }

    @Override
    public void saveReaders(List<Reader> readers) {
       FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("readers");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(readers);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет файла readers", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }

    @Override
    public List<Reader> loadReaders() {
        List<Reader> readers = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("readers");
            ois = new ObjectInputStream(fis);
            readers = (List<Reader>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "readers еше не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка чтения", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        }
        return readers;
    }

    @Override
    public void saveHistories(List<History> histories) {
         FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("histories");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(histories);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет файла histories", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }

    @Override
    public List<History> loadHistories() {
        List<History> histories = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("histories");
            ois = new ObjectInputStream(fis);
            histories = (List<History>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "histories еше не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка чтения", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        }
        return histories;
    }

    @Override
    public void saveAuthors(List<Author> authors) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("authors");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(authors);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет файла authors", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }

    @Override
    public List<Author> loadAuthors() {
        List<Author> authors = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("authors");
            ois = new ObjectInputStream(fis);
            authors = (List<Author>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "authors еше не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка чтения", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        }
        return authors;
    }
    
}
