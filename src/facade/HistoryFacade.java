/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.History;

/**
 *
 * @author user
 */
public class HistoryFacade extends AbstractFacade<History>{
    
    public HistoryFacade(Class<History> entityClass) {
        super(entityClass);
    }
    
}
