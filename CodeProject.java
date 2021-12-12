public class CodeProject extends VideoProject {
	
	private String codeFolderPath;
	
	public CodeProject(String[] info)
	{
		super(info);
		this.codeFolderPath = info[5];
	}
	
	public String getCodeFolderPath()
	{
		return this.codeFolderPath;
	}
	
	public void setCodeFolderPath(String codeFolderPath)
	{
		this.codeFolderPath = codeFolderPath;
	}
 }
