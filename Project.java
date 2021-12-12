import java.text.*;
import java.util.*;

public class Project {
	
	private String name;
	private String description;
	// private int[] developers;
	private String developers;
	private Date dateCreated;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	
	
	public Project()
	{
		this.dateCreated = new Date();
	}
	
	public Project(String[] info)
	{
		this.name = info[0];
		this.description = info[1];
		this.developers = info[2];
		
		try
		{
			this.dateCreated = formatter.parse(info[3]);
		}
		catch (ParseException e)
		{
			this.dateCreated = new Date();
			System.out.println(e);
		}
	}
	
	public Project(String name, String description, String developers, String date)
	{	
		this.name = name;
		this.description = description;
		this.developers = developers;
		
		try
		{
			this.dateCreated = formatter.parse(date);
		}
		catch (ParseException e)
		{
			this.dateCreated = new Date();
			System.out.println(e);
		}
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getDateCreated()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		return dateFormat.format(this.dateCreated);
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public String getDevelopers()
	{
		return this.developers;
	}
}
