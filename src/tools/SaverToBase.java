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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class SaverToBase implements Keeping{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SPTV20LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public void saveBooks(List<Book> books) {
        tx.begin();
            for (int i = 0; i < books.size(); i++) {
                if(books.get(i).getId() == null){
                    em.persist(books.get(i));
                }else{
                    em.merge(books.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> books = null;
        try {
            books = em.createQuery("SELECT book FROM Book book")
                .getResultList();
        } catch (Exception e) {
            books = new ArrayList<>();
        }
        return books;
    }

    @Override
    public void saveReaders(List<Reader> readers) {
        tx.begin();
            for (int i = 0; i < readers.size(); i++) {
                if(readers.get(i).getId() == null){
                    em.persist(readers.get(i));
                }else{
                    em.merge(readers.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Reader> loadReaders() {
       List<Reader> readers = null;
        try {
            readers = em.createQuery("SELECT reader FROM Reader reader")
                .getResultList();
        } catch (Exception e) {
            readers = new ArrayList<>();
        }
        return readers;
    }

    @Override
    public void saveHistories(List<History> histories) {
        tx.begin();
            for (int i = 0; i < histories.size(); i++) {
                if(histories.get(i).getId() == null){
                    em.persist(histories.get(i));
                }else{
                    em.merge(histories.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<History> loadHistories() {
        List<History> histories = null;
        try {
            histories = em.createQuery("SELECT history FROM History history")
                .getResultList();
        } catch (Exception e) {
            histories = new ArrayList<>();
        }
        return histories;
    }

    @Override
    public void saveAuthors(List<Author> authors) {
        tx.begin();
            for (int i = 0; i < authors.size(); i++) {
                if(authors.get(i).getId() == null){
                    em.persist(authors.get(i));
                }else{
                    em.merge(authors.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Author> loadAuthors() {
        try {
            return em.createQuery("SELECT author FROM Author author")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
}
