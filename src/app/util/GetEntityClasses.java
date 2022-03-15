package app.util;

import app.dto.EntityClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

public class GetEntityClasses
{
    public List<EntityClass> get(String projectPath,String entityPackage) throws IOException
    {
        File file=new File(projectPath+"\\"+entityPackage);
        if(!file.exists())
            throw new FileNotFoundException("Folder does not exist: "+projectPath+"\\"+entityPackage);
        List<EntityClass> entityClasses=new LinkedList<>();
        for(File entityFile:file.listFiles())
        {
            if(entityFile.isFile())
            {
                try
                {
                    String className=entityFile.getName().substring(0,entityFile.getName().indexOf("."));
                    String idType=getIdType(Files.readString(entityFile.toPath()));
                    entityClasses.add(new EntityClass(className,idType));
                }
                catch(StringIndexOutOfBoundsException ignored)
                {

                }
            }
        }
        return entityClasses;
    }

    private String getIdType(String fileContent)
    {
        String tmp=fileContent.substring(fileContent.indexOf("@Id")+3);
        tmp=tmp.substring(0,tmp.indexOf(";"));
        tmp=tmp.substring(0,tmp.lastIndexOf(" "));
        tmp=tmp.substring(tmp.lastIndexOf(" ")+1);
        if(Character.isLowerCase(tmp.charAt(0)))
        {
            if(tmp.equals("int"))
                return "Integer";
            return Character.toUpperCase(tmp.charAt(0))+tmp.substring(1);
        }
        return tmp;
    }
}