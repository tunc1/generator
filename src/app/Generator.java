package app;

import java.io.File;
import java.io.IOException;

public class Generator
{
    private final ClassGenerator repositoryGenerator;
    private final ClassGenerator serviceGenerator;
    private final ClassGenerator controllerGenerator;
    private final WriteToFile writeToFile;
    public Generator()
    {
        repositoryGenerator=new RepositoryGenerator();
        serviceGenerator=new ServiceGenerator();
        controllerGenerator=new ControllerGenerator();
        writeToFile=new WriteToFile();
    }
    public void generate(String projectPath,String basePackage,String entityPackage,Entity[] entities) throws IOException
    {
        projectPath+="\\src\\main\\java\\"+basePackage.replace(".","\\");
        File repositoryFolder=new File(projectPath+"\\repository");
        if(!repositoryFolder.exists())
            repositoryFolder.mkdir();
        File serviceFolder=new File(projectPath+"\\service");
        if(!serviceFolder.exists())
            serviceFolder.mkdir();
        File controllerFolder=new File(projectPath+"\\controller");
        if(!controllerFolder.exists())
            controllerFolder.mkdir();
        for(Entity entity:entities)
        {
            String repository=repositoryGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(projectPath+"\\repository\\"+entity.getName()+"Repository.java",repository);
            String service=serviceGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(projectPath+"\\service\\"+entity.getName()+"Service.java",service);
            String controller=controllerGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(projectPath+"\\controller\\"+entity.getName()+"Controller.java",controller);
        }
    }
}