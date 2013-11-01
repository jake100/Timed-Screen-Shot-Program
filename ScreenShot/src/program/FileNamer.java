package program;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class FileNamer
{
	public static enum Extension{jpg, gif, png}
	public static Extension ext = Extension.png;
	private int num = 0;
	FileSystemView filesys = FileSystemView.getFileSystemView();
	public String newIntName()
	{
		String path = filesys.getHomeDirectory().toPath().toString()+"/"+num+"."+ext.toString();
		
		File f = new File(path);
		if(f.exists()) 
		{
			num++;
			path = newIntName();
		}
		return path;
	}
}
