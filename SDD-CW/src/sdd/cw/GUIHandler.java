package sdd.cw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * The GUIHandler class is responsible for rendering graphics to the screen
 * which allows the user to interact with the database. The role of this class is
 * to show the user the Graphical User Interface which has the input fields
 * and buttons which handles user requests and display the
 * search result or export specified records.
 *
 * @author faagbede
 */
public class GUIHandler extends JFrame implements ActionListener {

    /**
     * the six final ints below set the size of the Graphical User Interface elements, 
     * specifically the display height and width of the frame, the label and the button
     */
    private final int width = 1000;
    private final int height = 800;
    private final int labelWidth = 800;
    private final int labelHeight = 100;
    private final int buttonWidth = 125;
    private final int buttonHeight = 45;
    
    /**
     * The frame implement JFrame 
     * Label1 and label2 are instances of JLabel
     * findButtton and exportButton are instances of JButton
     * panel is an instance of JPanel
     * table is an instance of JTable
     */
    private final JFrame frame ;
    private final JLabel label1;
    private final JLabel label2;
    private final JButton findButton;
    private final JButton exportButton;
    private JPanel panel;
    private JTable table;
    
    /**
     * The constructor for GUIHandler
     * It creates a new frame, labels, button and panel
     * It set the width and height of the frame, labels, button and panel
     * It set the location of the button and labels with specified x and y coordinate
     * 
     */

    public GUIHandler() {
        frame = new JFrame("Car Accident Search Engine"); //instantiate a new frame
        label1 = new JLabel(); //instantiate a new label-1
        label2 = new JLabel(); //instantiate a new label-2
        
        findButton = new JButton("FIND RECORD"); //instantiate a new button to find error value for engine capacity
        exportButton = new JButton("EXPORT RECORD");//instantiate a new button to export records of error value for engine capacity
        panel = new JPanel(); //instantiate panel to add text feild, label and button 

        //set size
        frame.setSize(width, height); //set the width and height of the frame
        label1.setSize(labelWidth, labelHeight);// set the width and height of label-1 
        label2.setSize(labelWidth, labelHeight);// set the width and height of label-2
        findButton.setSize(buttonWidth, buttonHeight);//set width and height of find record button
        exportButton.setSize(135, buttonHeight);//set width and height of export button
        panel.setSize(width, height);  //set width and height of the panel  
                
        //set location
        findButton.setLocation(550, 30); //set find button location, param: specified the x and y coordinate 
        exportButton.setLocation(550, 100); //set export button location
        label1.setLocation(100, 0);//Set location for label-1
        label2.setLocation(100, 75);//Set location for label-2  
        frame.setLocationRelativeTo(null); //set window in the middle of the screen

        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stop program from running once the window is closed
        frame.setResizable(false);// does not allowed windows to be resizable 
        panel.setBackground(Color.CYAN); // set background colour of the panel to cyan
        panel.setLayout(null); //do not set layout of the panel
        label1.setText("TO FIND DATA FOR ENGINE CAPACITY ERROR, CLICK Find Record =>");//add text to label-1
        label2.setText("TO EXPORT DATA FOR ENGINE CAPACITY ERROR, CLICK EXPORT Record =>");//add text to label-2
        

        //Add all elements
        frame.add(label1);//add label-1 to the panel
        frame.add(label2);//add label-1 to the pane-2        
        frame.add(findButton);//add find button 
        frame.add(exportButton);//add export button 
        frame.add(panel); //add panel to the frame 
        
        
        findButton.addActionListener(this); //Adds an ActionListener to the findButton. param - the ActionListener to be added
        exportButton.addActionListener(this); //Adds an ActionListener to the ExportButton. param - the ActionListener to be added
        
        frame.setVisible(true); //make window visible 

    }

    /**
     * The actionPerformed method is the listener interface for receiving action events.
     * It process an action and does different things depending on when an action occurs 
     * @param e is an action event 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        DataQualityCheck dqc = new DataQualityCheck(); //instanciate a DataQualityCheck class
       
        if(e.getSource().equals(findButton)){
            try {
            //check if findButton event has occurred.  if the user clicks the findButton
            
           table = new JTable(dqc.buildDtm(dqc.findRecord())); //calls the default table model in DataQualityCheck
           
           
           JScrollPane scrollPane = new JScrollPane(table);//create JScrollPane
           scrollPane.setVisible(true);//makes scrollPane visible 
           scrollPane.setPreferredSize(new Dimension(1000,800));//set the dimension of the scrollpane 
           panel = new JPanel();
           panel.add(scrollPane);
           JOptionPane.showMessageDialog(frame, scrollPane);//display the error value for engine capacity
            //JOptionPane.showMessageDialog(frame, "Record has been found");//Brings up an information-message dialog titled "Message" and display massage "Record has been found"
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (UnknownColumnException ex) {
                System.out.println("error" + ex);
            }
        }   else if(e.getSource().equals(exportButton)){//other wise check if the user clicks the export button 
            try {
                dqc.exportRecord(); //call exportRecord method
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (UnknownColumnException uce) {
                System.out.println(uce);
            }
            JOptionPane.showMessageDialog(frame, "Error value for Engine capacity has been exported to noCC.txt file");//make a pop up dialog box and display message 
            
        }
    }
    
    
}
