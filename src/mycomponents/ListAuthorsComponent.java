/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycomponents;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author user
 */
public class ListAuthorsComponent extends JPanel{
    private JLabel jLabelTitle;
    private JList list;

    public ListAuthorsComponent(String title,int widthPanel,int heightPanel,int widthList) {
        initComponent(title, widthPanel, heightPanel, widthList);
    }

    private void initComponent(String title,int widthPanel,int heightList, int widthList) {
        setMinimumSize(new Dimension(widthPanel,heightList));
        setMaximumSize(getMinimumSize());
        setPreferredSize(getMinimumSize());
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
//        setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.setTitle(title,widthPanel);
        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.setList(widthList,heightList);
        
      
        
   }
    protected void setList(int widthEdit,int heightList){
        list = new JList();
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HEIGHT);
        JScrollPane jScrollPane = new JScrollPane(list);
        jScrollPane.setPreferredSize(new Dimension(widthEdit,heightList));
        jScrollPane.setMinimumSize(jScrollPane.getPreferredSize());
        jScrollPane.setMaximumSize(jScrollPane.getPreferredSize());
        jScrollPane.setAlignmentX(LEFT_ALIGNMENT);
        jScrollPane.setAlignmentY(TOP_ALIGNMENT);
        this.add(jScrollPane);
    }

    public void setTitle(String title,int widthPanel) {
        this.jLabelTitle = new JLabel(title);
        jLabelTitle.setPreferredSize(new Dimension(widthPanel/3,27));
        jLabelTitle.setMinimumSize(jLabelTitle.getPreferredSize());
        jLabelTitle.setMaximumSize(jLabelTitle.getPreferredSize());
//        jLabelTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jLabelTitle.setHorizontalAlignment(JLabel.RIGHT);
        jLabelTitle.setAlignmentY(TOP_ALIGNMENT);
        this.add(jLabelTitle);
    }
    
}
