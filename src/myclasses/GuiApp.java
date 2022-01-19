/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Book;
import entity.Reader;
import entity.Role;
import entity.User;
import entity.UserRoles;
import facade.BookFacade;
import facade.ReaderFacade;
import facade.RoleFacade;
import facade.UserFacade;
import facade.UserRolesFacade;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import mycomponents.AddBookPanelComponent;
import mycomponents.ButtonComponent;
import mycomponents.CaptionComponent;
import mycomponents.EditBookPanelComponent;
import mycomponents.GuestComponent;
import mycomponents.manager.ManagerComponent;

/**
 *
 * @author user
 */
public class GuiApp extends JFrame{
    public final static int WIDTH_FRAME = 600;
    public final static int HEIGHT_FRAME = 470;
    public GuiApp guiApp = this;
    
    private UserFacade userFacade;
    private UserRolesFacade userRolesFacade;
    private ReaderFacade readerFacade;
    private RoleFacade roleFacade;
    
    private GuestComponent guestComponent;
    private ButtonComponent guestButton;
    private ManagerComponent managerComponent;
   
//    private CaptionComponent captionComponentTabAddBook;
//    private InfoComponent infoComponentTabAddBook;
//    private EditComponent bookNameComponentTabAddBook;
//    private ComboBoxBooksComponent comboBoxBooksComponentTabEditBook;
//    private ListAuthorsComponent listAuthorsComponentTabAddBook;
//    private ListAuthorsComponent listAuthorsComponentTabEditBook;
//    private EditComponent publishedYearComponentTabAddBook;
//    private EditComponent quantityComponentTabAddBook;
//    private ButtonComponent buttonComponentTabAddBook;
//    private CaptionComponent captionComponentTabEditBook;
//    private InfoComponent infoComponentTabEditBook;
//    private EditComponent bookNameComponentTabEditBook;
//    private EditComponent publishedYearComponentTabEditBook;
//    private EditComponent quantityComponentTabEditBook;
//    private ButtonComponent buttonComponentTabEditBook;
//    private BookFacade bookFacade = new BookFacade(Book.class);
//    private DefaultComboBoxModel<Book> defaultComboBoxModel;
//    private Book editBook;
    
    public GuiApp() {
        userFacade = new UserFacade();
        userRolesFacade = new UserRolesFacade();
        roleFacade = new RoleFacade();
        readerFacade = new ReaderFacade(Reader.class);
        addSuperAdmin();
        super.setMinimumSize(new Dimension(GuiApp.WIDTH_FRAME,GuiApp.HEIGHT_FRAME));
        super.setPreferredSize(super.getMaximumSize());
        super.setMaximumSize(super.getMaximumSize());
        initComponents();
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
          this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
          this.add(Box.createRigidArea(new Dimension(0,30)));
          CaptionComponent captionComponent = new CaptionComponent("Книги для библиотеки", GuiApp.WIDTH_FRAME, 27);
          this.add(captionComponent);
          this.add(Box.createRigidArea(new Dimension(0,10)));
          guestComponent = new GuestComponent();
          this.add(guestComponent);
          guestButton = new ButtonComponent("Войти", GuiApp.WIDTH_FRAME, 27, 30, 100);
          this.add(Box.createRigidArea(new Dimension(0,10)));
          this.add(guestButton);
          guestButton.getjButton().addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  //Здесь создаем модальное диалоговое окно JDialog,
                  // где проверяем логин и пароль введенный пользователем.
                  // Если у пользователя есть роль менеджера то показываем 
                  // managerComponent

                guiApp.getContentPane().removeAll();
                managerComponent = new ManagerComponent();
                guiApp.add(managerComponent);
                guiApp.repaint();
                guiApp.revalidate();
              }
          });
        
    }
    
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiApp().setVisible(true);
            }
        });
    }

    private void addSuperAdmin() {
        List<User> users = userFacade.findAll();
        if(!users.isEmpty()){ return; }
        Reader reader = new Reader();
        reader.setFirstname("Juri");
        reader.setLastname("Melnikov");
        reader.setPhone("5654567676");
        readerFacade.create(reader);
        User user = new User();
        user.setLogin("admin");
        user.setPassword("12345");
        user.setReader(reader);
        userFacade.create(user);
        Role role = new Role();
        role.setRoleName("ADMINISTRATOR");
        roleFacade.create(role);
        UserRoles userRoles = new UserRoles();
        userRoles.setUser(user);
        userRoles.setRole(role);
        userRolesFacade.create(userRoles);
        
        role = new Role();
        role.setRoleName("MANAGER");
        roleFacade.create(role);
        userRoles = new UserRoles();
        userRoles.setUser(user);
        userRoles.setRole(role);
        userRolesFacade.create(userRoles);
       
        role = new Role();
        role.setRoleName("READER");
        roleFacade.create(role);
        userRoles = new UserRoles();
        userRoles.setUser(user);
        userRoles.setRole(role);
        userRolesFacade.create(userRoles);
    }

}
