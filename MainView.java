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
	private JScrollPane scrPane;
	private final int WINDOW_WIDTH = 1200;	//window width
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
			
			if (projectInfo.length < 6)
			{
				projects.add(new Project(projectInfo));
			}
			else if (projectInfo[6].replaceAll("\\s", "").equals("VideoProject"))
			{
				projects.add(new VideoProject(projectInfo));
			}
			else if (projectInfo[6].replaceAll("\\s", "").equals("CodeProject"))
			{
				projects.add(new CodeProject(projectInfo));
			}
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
		// remove old panel and scrollbar	
		if (panel != null)
		{
			remove(panel);
			remove(scrPane);
		}
		
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
	    
	    // add panel
	    add(panel);
	    pack();
	    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    
	    // add scroll bar
 		scrPane = new JScrollPane(panel);
 		scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
 		scrPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
 		getContentPane().add(scrPane);
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
		// rempve old panel and scrollbar
		if (panel != null)
		{
			remove(panel);
			remove(scrPane);
		}
		
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
		
  		
		// if it is a video project, then add video to the view
  		if (project instanceof VideoProject) {
  			// video player is under construction
  			// it is required to install javax.media package
  			
  			// temporaly just show the path in text area
  			JTextArea videoArea = new JTextArea(("* place for videoplayer*\n" + ((VideoProject) project).getVideoPath()));
  			panel.add(videoArea);
  		}
  		
  		// if it is a code project, then add code blocks to the view
  		if (project instanceof CodeProject) {
  			String codesPath = ((CodeProject) project).getCodeFolderPath();
  			
  			File folder = new File(codesPath);
  			File[] listOfFiles = folder.listFiles();

  			for (File file : listOfFiles) {
  			    if (file.isFile()) {
  			    	
					try {
						JLabel fileLabel = new JLabel(file.getName());
						 nameLabel.setPreferredSize(new Dimension(50,40));
						 
						Scanner reader = new Scanner(file);
	  			    	JTextArea codeArea = new JTextArea();
	  			    	codeArea.setEditable(false);
	  			    	
	  			    	while (reader.hasNextLine())
	  			    	{
	  			    		codeArea.append(reader.nextLine() + "\n");
	  			    	}
	  			    	
	  			    	panel.add(fileLabel);
	  		  			panel.add(codeArea);
  		  			
					}
					catch (FileNotFoundException e) {
						System.out.println("File was not found: " + file.getAbsolutePath());
					}
					
  			    }
  			}
  			
  			
  		}
  		
  	
  		// add panel
		add(panel);
		pack();
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		// add scroll bar
		scrPane = new JScrollPane(panel);
		scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scrPane);
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
