/*
 *  Created 12/11/2021
 * 	MainView class is the main class of the application.
 * 	Here the main logic is described.
 * 
 * 	There are 2 views in the application (at least in the beggining):
 * 	the first one displays all projects in the the database,
 * 	the second one displays information of a concrete project 
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;


public class MainView extends JFrame
{
	// GUI fields
	private JPanel panel;
	private final int WINDOW_WIDTH = 710;	//window width
	private final int WINDOW_HEIGHT = 1000;	//window height
	
	private ArrayList<Project> projects = new ArrayList<Project>();
	
	
	public static void main(String args[])
	{
		// inint the main view
	    MainView mainView = new MainView();
	}
	
	
	public MainView() {
		// collect data about projects from the db and display it as a list through GUI
		ArrayList<String[]> dbProjects = Database.getProjects();
		
		for (int i = 0; i < dbProjects.size(); i++)
		{
			String[] projectInfo = dbProjects.get(i);
			projects.add(new Project(projectInfo));
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("CS Project");
		// setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setVisible(true);
		
		buildMainPanel();
	}
	
	// view for the list of all projects
	private void buildMainPanel()
	{
		// remove old pane;		
		if (panel != null)
			remove(panel);
		
		// rows are equal to the number of projects			
		int rows = projects.size();
	    
	    // create panel with margins	    	
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
		
		// create layout for the list of projects		
		GridLayout gridlayout = new GridLayout(rows, 1);
		gridlayout.setHgap(40);
		gridlayout.setVgap(20);

		// add layout to the panel
	    panel.setLayout(gridlayout);

	    // add elements (rows of projects) to the panel
	    for (int i = 0; i < rows; i++)
	    {	
			JLabel nameLabel = new JLabel(projects.get(i).getName());
			 nameLabel.setPreferredSize(new Dimension(50,40));
	  
	  		JLabel descLabel = new JLabel(projects.get(i).getDescription());
	  		descLabel.setPreferredSize(new Dimension(220,40));
	  
	  		JLabel dateLabel = new JLabel("Created: " + projects.get(i).getDateCreated());
	  		// dateLabel.setPreferredSize(new Dimension(80,40));
	  
	  		JButton openButton = new JButton("Open #" + (i + 1));
	  		openButton.putClientProperty("id", i);
	  		// button.setPreferredSize(new Dimension(110,40));
	  		OpenButtonListener listener = new OpenButtonListener();
	  		openButton.addActionListener(listener);	
	  		
	  
			panel.add(nameLabel);
			panel.add(descLabel);
			panel.add(dateLabel);
			panel.add(openButton);
			
	    }
	    
	    add(panel);
	    pack();
	    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	// on btn click open the view of a specific project
	private class OpenButtonListener implements ActionListener			
	{								
		// the actionPerformed method executes				 
		//when the user clicks on the calculate button */			
									
		public void actionPerformed(ActionEvent e)			
		{							
			///////// the code for the event handler goes here	
			JButton clickedButton = (JButton) e.getSource();
			int projectId = (Integer) clickedButton.getClientProperty("id");

			buildProjectPanel(projectId);					
		}							
	}
	
	// view for a specific project	
	private void buildProjectPanel(int id)
	{
		// rempve old panel
		if (panel != null)
			remove(panel);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
		
		// get a specific project
		Project project = projects.get(id);
		
		// create layout for the list of projects		
		
		JLabel nameLabel = new JLabel(project.getName());
		 nameLabel.setPreferredSize(new Dimension(50,40));
 
  		JLabel descLabel = new JLabel(project.getDescription());
  		descLabel.setPreferredSize(new Dimension(220,40));
  
  		JLabel dateLabel = new JLabel("Created: " + project.getDateCreated());
  		// dateLabel.setPreferredSize(new Dimension(80,40));
  
  		JButton backButton = new JButton("Go back");
  		// button.setPreferredSize(new Dimension(110,40));
  		MainViewButtonListener backButtonListener = new MainViewButtonListener();
  		backButton.addActionListener(backButtonListener);	
  		
  
		panel.add(nameLabel);
		panel.add(descLabel);
		panel.add(dateLabel);
		panel.add(backButton);
		
		add(panel);
		pack();
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	// on btn click open main view 
	private class MainViewButtonListener implements ActionListener			
	{								
		// the actionPerformed method executes				 
		//when the user clicks on the calculate button */			
									
		public void actionPerformed(ActionEvent e)			
		{							
			buildMainPanel();					
		}							
	}
	
}
