package app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteToFile
{
    public void write(File file,String data) throws IOException
    {
		FileOutputStream fileOutputStream=new FileOutputStream(file);
		fileOutputStream.write(data.getBytes());
		fileOutputStream.close();
    }
}