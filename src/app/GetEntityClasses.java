package app;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GetEntityClasses
{
    public List<String> get(String projectPath,String entityPackage)
    {
        File file=new File(projectPath+"\\"+entityPackage);
        return Arrays.stream(file.listFiles())
                .filter(File::isFile)
                .map(file1 -> file1.getName().substring(0,file1.getName().indexOf(".")))
                .collect(Collectors.toList());
    }
}