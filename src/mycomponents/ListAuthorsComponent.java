/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycomponents;

import entity.Author;
import facade.AuthorFacade;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

/**
 *
 * @author user
 */
public class ListAuthorsComponent extends JPanel{
    private JLabel jLabelTitle;
    private JList<Author> list;

    public ListAuthorsComponent(String title,int widthPanel,int heightPanel,int widthList) {
        initComponent(title, widthPanel, heightPanel, widthList);
    }

    private void initComponent(String title,int widthPanel,int heightList, int widthList) {
        //Установка размера панели компонента и менеджера компановки
        this.setMinimumSize(new Dimension(widthPanel,heightList));
        this.setMaximumSize(getMinimumSize());
        this.setPreferredSize(getMinimumSize());
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
//        this.setBorder(BorderFactory.createLineBorder(Color.yellow));
        //Добавление элементов в панель
        this.addTitleComponent(title,widthPanel);
        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.addListComponent(widthList,heightList);
   }
    
    protected void addListComponent(int widthList,int heightList){
        list = new JList<>();
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HEIGHT);
        list.setModel(createAuthorsListModel());
        JScrollPane jScrollPane = new JScrollPane(list);
        jScrollPane.setPreferredSize(new Dimension(widthList,heightList));
        jScrollPane.setMinimumSize(jScrollPane.getPreferredSize());
        jScrollPane.setMaximumSize(jScrollPane.getPreferredSize());
        jScrollPane.setAlignmentX(LEFT_ALIGNMENT);
        jScrollPane.setAlignmentY(TOP_ALIGNMENT);
        list.setCellRenderer(createAuthorsCellRenderer(widthList));
        this.add(jScrollPane);
    }

    public void addTitleComponent(String title,int widthPanel) {
        this.jLabelTitle = new JLabel(title);
        jLabelTitle.setPreferredSize(new Dimension(widthPanel/3,27));
        jLabelTitle.setMinimumSize(jLabelTitle.getPreferredSize());
        jLabelTitle.setMaximumSize(jLabelTitle.getPreferredSize());
//        jLabelTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jLabelTitle.setHorizontalAlignment(JLabel.RIGHT);
        jLabelTitle.setAlignmentY(TOP_ALIGNMENT);
        this.add(jLabelTitle);
    }

    private ListModel createAuthorsListModel() {
        AuthorFacade authorFacade = new AuthorFacade(Author.class);
        List<Author> authors = authorFacade.findAll();
        DefaultListModel defaultListModel = new DefaultListModel();
        for (Author author : authors) {
            defaultListModel.addElement(author);
        }
        return defaultListModel;
    }

    private ListCellRenderer<? super Author> createAuthorsCellRenderer(int widthList) {
        return new DefaultListCellRenderer(){
            private final Color background = new Color(0, 100, 255, 15);
            private final Color defaultBackground = (Color) UIManager.get("List.background");
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, 
                    Object value, int index,
                    boolean isSelected, 
                    boolean cellHasFocus
            ){
                Component component = super.getListCellRendererComponent(
                        list, 
                        value, 
                        index, 
                        isSelected, 
                        cellHasFocus
                );
                if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                    label.setPreferredSize(new Dimension(widthList-21,27));
                    
                    Author author = (Author) value;
                    label.setText(String.format("%d. %s %s. %d%n"
                            ,author.getId()
                            ,author.getFirstName()
                            ,author.getLastName()
                            ,author.getBirthYear()
                    ));
                    if (!isSelected) {
                        label.setBackground(index % 2 == 0 ? background : defaultBackground);
                    }
                }
                return component;
            }
        };
    }

    public JList<Author> getList() {
        return list;
    }
    
}
