public class VideoProject extends Project {
	
	private String videoPath;
	
	public VideoProject(String[] info)
	{
		super(info);
		this.videoPath = info[4];
	}
	
	public String getVideoPath()
	{
		return this.videoPath;
	}
	
	public void setVideoPath(String videoPath)
	{
		this.videoPath = videoPath;
	}
 }
