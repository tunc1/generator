package app.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GetEntityClasses
{
    public List<String> get(String projectPath,String entityPackage) throws IOException
    {
        File file=new File(projectPath+"\\"+entityPackage);
        if(!file.exists())
            throw new FileNotFoundException("Folder does not exist: "+projectPath+"\\"+entityPackage);
        return Arrays.stream(file.listFiles())
                .filter(File::isFile)
                .map(file1 -> file1.getName().substring(0,file1.getName().indexOf(".")))
                .collect(Collectors.toList());
    }
}