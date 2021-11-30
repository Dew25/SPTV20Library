/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Book;
import entity.History;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author user
 */
public class HistoryFacade extends AbstractFacade<History>{
       
    @Override
    protected EntityManager getEntityManager() {
        Singleton singleton = Singleton.getInstance();
        return singleton.getEntityManager();
    }
    public HistoryFacade(Class<History> entityClass) {
        super(entityClass);
    }

    public History findHistoryByBook(Book book) {
        try {
            return (History) getEntityManager().createQuery("SELECT h FROM History h WHERE h.book = :book")
                    .setParameter("book", book)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<History> findListGiverBooks() {
        try {
            return getEntityManager().createQuery("SELECT h FROM History h WHERE h.returnBook = null")
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    
}
