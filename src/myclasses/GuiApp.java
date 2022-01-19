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
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import mycomponents.AddBookPanelComponent;
import mycomponents.EditBookPanelComponent;

/**
 *
 * @author user
 */
public class GuiApp extends JFrame{
    private UserFacade userFacade;
    private UserRolesFacade userRolesFacade;
    private ReaderFacade readerFacade;
    private RoleFacade roleFacade;
    private final int WIDTH_FRAME = 600;
    private final int HEIGHT_FRAME = 470;
    private EditBookPanelComponent editBookPanelComponent;
    private AddBookPanelComponent addBookPanelComponent;
    private JTabbedPane managerTabbedPane;
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
        super.setMinimumSize(new Dimension(WIDTH_FRAME,HEIGHT_FRAME));
        super.setPreferredSize(super.getMaximumSize());
        super.setMaximumSize(super.getMaximumSize());
        initComponents();
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        managerTabbedPane = new JTabbedPane();
        managerTabbedPane.setMinimumSize(new Dimension(WIDTH_FRAME,HEIGHT_FRAME));
        managerTabbedPane.setPreferredSize(managerTabbedPane.getMaximumSize());
        managerTabbedPane.setMaximumSize(managerTabbedPane.getMaximumSize());
        addBookPanelComponent = new AddBookPanelComponent(WIDTH_FRAME, HEIGHT_FRAME);
        editBookPanelComponent = new EditBookPanelComponent(WIDTH_FRAME, HEIGHT_FRAME);
        managerTabbedPane.addTab("Добавление книги", addBookPanelComponent);
        managerTabbedPane.addTab("Редактирование книги", editBookPanelComponent);
        this.add(managerTabbedPane);
        managerTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                editBookPanelComponent.getComboBoxBooksComponentTabEditBook().getComboBoxBooks().setSelectedItem(-1);
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
