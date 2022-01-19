/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Reader;
import entity.User;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author user
 */
public class UserFacade extends AbstractFacade<User>{
    
    @Override
    protected EntityManager getEntityManager() {
        Singleton singleton = Singleton.getInstance();
        return singleton.getEntityManager();
    }
    public UserFacade() {
        super(User.class);
    }
    
}
