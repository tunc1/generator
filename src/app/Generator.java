package app;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Generator
{
    private final ClassGenerator repositoryGenerator,serviceGenerator,controllerGenerator;
    private final GetEntityClasses getEntityClasses;
    private final WriteToFile writeToFile;
    public Generator()
    {
        repositoryGenerator=new RepositoryGenerator();
        serviceGenerator=new ServiceGenerator();
        controllerGenerator=new ControllerGenerator();
        getEntityClasses=new GetEntityClasses();
        writeToFile=new WriteToFile();
    }
    public void generate(String projectPath,String basePackage,String entityPackage,String idType) throws IOException
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
        List<String> entities=getEntityClasses.get(projectPath,entityPackage);
        for(String entity:entities)
        {
            String repository=repositoryGenerator.generate(entity,idType,basePackage,entityPackage);
            writeToFile.write(projectPath+"\\repository\\"+entity+"Repository.java",repository);
            String service=serviceGenerator.generate(entity,idType,basePackage,entityPackage);
            writeToFile.write(projectPath+"\\service\\"+entity+"Service.java",service);
            String controller=controllerGenerator.generate(entity,idType,basePackage,entityPackage);
            writeToFile.write(projectPath+"\\controller\\"+entity+"Controller.java",controller);
        }
    }
}