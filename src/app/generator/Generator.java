package app.generator;

import app.dto.EntityClass;
import app.util.GetEntityClasses;
import app.util.WriteToFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Generator
{
    private final ClassGenerator repositoryGenerator,serviceGenerator,controllerGenerator,controllerTestGenerator,serviceTestGenerator;
    private final GetEntityClasses getEntityClasses;
    private final WriteToFile writeToFile;
    public Generator()
    {
        repositoryGenerator=new RepositoryGenerator();
        serviceGenerator=new ServiceGenerator();
        controllerGenerator=new ControllerGenerator();
        getEntityClasses=new GetEntityClasses();
        controllerTestGenerator=new ControllerTestGenerator();
        serviceTestGenerator=new ServiceTestGenerator();
        writeToFile=new WriteToFile();
    }
    public void generate(String projectPath,String basePackage,String entityPackage) throws IOException
    {
        String javaPath=projectPath+"\\src\\main\\java\\"+basePackage.replace(".","\\");
        String testPath=projectPath+"\\src\\test\\java\\"+basePackage.replace(".","\\");
        File repositoryFolder=new File(javaPath+"\\repository");
        if(!repositoryFolder.exists())
            repositoryFolder.mkdir();
        File serviceFolder=new File(javaPath+"\\service");
        if(!serviceFolder.exists())
            serviceFolder.mkdir();
        File controllerFolder=new File(javaPath+"\\controller");
        if(!controllerFolder.exists())
            controllerFolder.mkdir();
        File controllerTestFolder=new File(testPath+"\\controller");
        if(!controllerTestFolder.exists())
        {
            controllerTestFolder.getParentFile().mkdirs();
            controllerTestFolder.mkdir();
        }
        File serviceTestFolder=new File(testPath+"\\service");
        if(!serviceTestFolder.exists())
            serviceTestFolder.mkdir();
        List<EntityClass> entities=getEntityClasses.get(javaPath,entityPackage);
        for(EntityClass entity:entities)
        {
            String repository=repositoryGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(javaPath+"\\repository\\"+entity.getClassName()+"Repository.java",repository);
            String service=serviceGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(javaPath+"\\service\\"+entity.getClassName()+"Service.java",service);
            String controller=controllerGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(javaPath+"\\controller\\"+entity.getClassName()+"Controller.java",controller);
            String controllerTest=controllerTestGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(testPath+"\\controller\\"+entity.getClassName()+"ControllerTest.java",controllerTest);
            String serviceTest=serviceTestGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(testPath+"\\service\\"+entity.getClassName()+"ServiceTest.java",serviceTest);
        }
    }
}