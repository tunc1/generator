package app.generator;

import app.dto.EntityClass;
import app.util.WriteToFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Generator
{
    private final ClassGenerator repositoryGenerator,serviceGenerator,controllerGenerator,controllerTestGenerator,serviceTestGenerator,entityGenerator;
    private final WriteToFile writeToFile;
    public Generator()
    {
        entityGenerator=new EntityGenerator();
        repositoryGenerator=new RepositoryGenerator();
        serviceGenerator=new ServiceGenerator();
        controllerGenerator=new ControllerGenerator();
        controllerTestGenerator=new ControllerTestGenerator();
        serviceTestGenerator=new ServiceTestGenerator();
        writeToFile=new WriteToFile();
    }
    public void generate(String projectPath,String basePackage,String entityPackage,List<String> entityNames) throws IOException
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
        String entityPath=entityPackage.replace(".","\\");
        File entityFolder=new File(javaPath+"\\"+entityPath);
        if(!entityFolder.exists())
            entityFolder.mkdir();
        List<EntityClass> entities=new LinkedList<>();
        entityNames.stream().forEach(entityName -> entities.add(new EntityClass(entityName,"Long")));
        for(EntityClass entity:entities)
        {
            String entityData=entityGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(javaPath+"\\"+entityPath+"\\"+entity.className()+".java",entityData);
            String repository=repositoryGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(javaPath+"\\repository\\"+entity.className()+"Repository.java",repository);
            String service=serviceGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(javaPath+"\\service\\"+entity.className()+"Service.java",service);
            String controller=controllerGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(javaPath+"\\controller\\"+entity.className()+"Controller.java",controller);
            String controllerTest=controllerTestGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(testPath+"\\controller\\"+entity.className()+"ControllerTest.java",controllerTest);
            String serviceTest=serviceTestGenerator.generate(entity,basePackage,entityPackage);
            writeToFile.write(testPath+"\\service\\"+entity.className()+"ServiceTest.java",serviceTest);
        }
    }
}