/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import mycomponents.ButtonComponent;
import mycomponents.CaptionComponent;
import mycomponents.ComboBoxComponent;
import mycomponents.EditComponent;
import mycomponents.InfoComponent;
import mycomponents.ListAuthorsComponent;

/**
 *
 * @author user
 */
public class GuiApp extends JFrame{
    
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent bookNameComponent;
//    private ComboBoxComponent authorsComponent;
    private ListAuthorsComponent listAuthorsComponent;
    private EditComponent publishedYearComponent;
    private EditComponent quantityComponent;
    private ButtonComponent buttonComponent;
    
    public GuiApp() {
        initComponents();
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        this.setMinimumSize(new Dimension(600,430));
        this.setPreferredSize(this.getMaximumSize());
        this.setMaximumSize(this.getMaximumSize());
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponent = new CaptionComponent("Добавление новой книги ", this.getWidth(), 30);
        this.getContentPane().add(captionComponent);
        infoComponent = new InfoComponent("Это инфо компонент", this.getWidth(), 30);
        this.getContentPane().add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        bookNameComponent=new EditComponent("Название книги ", this.getWidth(), 30, 300);
        this.getContentPane().add(bookNameComponent);
//        authorsComponent=new ComboBoxComponent("Авторы ", this.getWidth(), 30, 250);
//        this.getContentPane().add(authorsComponent);
        listAuthorsComponent = new ListAuthorsComponent("Авторы", this.getWidth(), 120, 300);
        this.getContentPane().add(listAuthorsComponent);
        publishedYearComponent=new EditComponent("Год публикации книги ", this.getWidth(), 30, 80);
        this.getContentPane().add(publishedYearComponent);
        quantityComponent=new EditComponent("Количество экземпляров ", this.getWidth(), 30, 40);
        this.getContentPane().add(quantityComponent);
        buttonComponent=new ButtonComponent("Добавить книгу", this.getWidth(), 50, 210, 195);
        this.getContentPane().add(buttonComponent);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiApp().setVisible(true);
            }
        });
    }

    
}
