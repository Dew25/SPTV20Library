/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Author;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author user
 */
public class AuthorFacade extends AbstractFacade<Author>{
       
    @Override
    protected EntityManager getEntityManager() {
        Singleton singleton = Singleton.getInstance();
        return singleton.getEntityManager();
    }
    public AuthorFacade(Class<Author> entityClass) {
        super(entityClass);
    }
    
}
