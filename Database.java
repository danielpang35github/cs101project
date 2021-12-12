import java.io.*;
import java.util.*;

public class Database {
	public static ArrayList<String[]> getProjects()
	{
		// read data from txt file and add it to an ArrayList
		ArrayList<String[]> projects = new ArrayList<String[]>();
		File file = new File("src/DB.txt");
		
		try
		{
			Scanner reader = new Scanner(file);
			
			while (reader.hasNextLine())
			{
				// in the database file, fields are divided by //
				String[] line = reader.nextLine().split("//");
//				for (int i = 0; i < line.length; i++)
//				{
//					System.out.println(line[i]);
//				}
				projects.add(line);
			}
			
			reader.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Databe was not found.");
		}
		
		// return ArrayList of projects		
		return projects;
	}
}
