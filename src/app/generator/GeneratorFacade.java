package app.generator;

import app.dto.EntityClass;
import app.generator.entity.*;
import app.generator.exception.*;
import app.util.WriteToFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GeneratorFacade
{
    private final ClassGenerator repositoryGenerator,serviceGenerator,controllerGenerator,controllerTestGenerator,serviceTestGenerator,entityGenerator;
    private final ExceptionClassGenerator globalExceptionHandlerGenerator,exceptionResponseGenerator;
    private final WriteToFile writeToFile;
    public GeneratorFacade()
    {
        entityGenerator=new EntityGenerator();
        repositoryGenerator=new RepositoryGenerator();
        serviceGenerator=new ServiceGenerator();
        controllerGenerator=new ControllerGenerator();
        controllerTestGenerator=new ControllerTestGenerator();
        serviceTestGenerator=new ServiceTestGenerator();
        globalExceptionHandlerGenerator=new GlobalExceptionHandlerGenerator();
        exceptionResponseGenerator=new ExceptionResponseGenerator();
        writeToFile=new WriteToFile();
    }
    private void createFolders(String javaPath,String testPath,String entityPath)
    {
        File repositoryFolder=new File(javaPath+"\\repository");
        if(!repositoryFolder.exists())
            repositoryFolder.mkdir();
        File serviceFolder=new File(javaPath+"\\service");
        if(!serviceFolder.exists())
            serviceFolder.mkdir();
        File controllerFolder=new File(javaPath+"\\controller");
        if(!controllerFolder.exists())
            controllerFolder.mkdir();
        File exceptionFolder=new File(javaPath+"\\exception");
        if(!exceptionFolder.exists())
            exceptionFolder.mkdir();
        File entityFolder=new File(javaPath+"\\"+entityPath);
        if(!entityFolder.exists())
        {
            entityFolder.getParentFile().mkdirs();
            entityFolder.mkdir();
        }
        File controllerTestFolder=new File(testPath+"\\controller");
        if(!controllerTestFolder.exists())
        {
            controllerTestFolder.getParentFile().mkdirs();
            controllerTestFolder.mkdir();
        }
        File serviceTestFolder=new File(testPath+"\\service");
        if(!serviceTestFolder.exists())
            serviceTestFolder.mkdir();
    }
    public void generate(String projectPath,String basePackage,String entityPackage,List<String> entityNames) throws IOException
    {
        String entityPath=entityPackage.replace(".","\\");
        String javaPath=projectPath+"\\src\\main\\java\\"+basePackage.replace(".","\\");
        String testPath=projectPath+"\\src\\test\\java\\"+basePackage.replace(".","\\");
        createFolders(javaPath,testPath,entityPath);
        List<EntityClass> entities=new LinkedList<>();
        entityNames.forEach(entityName -> entities.add(new EntityClass(entityName,"Long")));
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
        String globalExceptionHandler=globalExceptionHandlerGenerator.generate(basePackage);
        writeToFile.write(javaPath+"\\exception\\GlobalExceptionHandler.java",globalExceptionHandler);
        String exceptionResponse=exceptionResponseGenerator.generate(basePackage);
        writeToFile.write(javaPath+"\\exception\\ExceptionResponse.java",exceptionResponse);
    }
}