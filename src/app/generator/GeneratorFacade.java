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
    private final ClassGenerator repositoryGenerator,serviceGenerator,controllerGenerator,controllerTestGenerator,serviceTestGenerator,entityGenerator,dtoGenerator;
    private final ClassGenerator saveRequestGenerator,updateRequestGenerator,saveResponseGenerator;
    private final ExceptionClassGenerator globalExceptionHandlerGenerator,exceptionResponseGenerator;
    private final WriteToFile writeToFile;
    public GeneratorFacade()
    {
        entityGenerator=new EntityGenerator();
        dtoGenerator=new DTOGenerator();
        repositoryGenerator=new RepositoryGenerator();
        serviceGenerator=new ServiceGenerator();
        controllerGenerator=new ControllerGenerator();
        controllerTestGenerator=new ControllerTestGenerator();
        serviceTestGenerator=new ServiceTestGenerator();
        globalExceptionHandlerGenerator=new GlobalExceptionHandlerGenerator();
        exceptionResponseGenerator=new ExceptionResponseGenerator();
        saveRequestGenerator=new SaveRequestGenerator();
        updateRequestGenerator=new UpdateRequestGenerator();
        saveResponseGenerator=new SaveResponseGenerator();
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
        File requestFolder=new File(javaPath+"\\controller\\request");
        if(!requestFolder.exists())
            requestFolder.mkdir();
        File responseFolder=new File(javaPath+"\\controller\\response");
        if(!responseFolder.exists())
            responseFolder.mkdir();
        File exceptionFolder=new File(javaPath+"\\exception");
        if(!exceptionFolder.exists())
            exceptionFolder.mkdir();
		File dtoFolder=new File(javaPath+"\\dto");
        if(!dtoFolder.exists())
            dtoFolder.mkdir();
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
			writeToFile(entityGenerator.generate(entity,basePackage,entityPackage),javaPath+"\\"+entityPath+"\\"+entity.className()+".java");
			writeToFile(dtoGenerator.generate(entity,basePackage,entityPackage),javaPath+"\\dto\\"+entity.className()+"DTO.java");
			writeToFile(repositoryGenerator.generate(entity,basePackage,entityPackage),javaPath+"\\repository\\"+entity.className()+"Repository.java");
			writeToFile(serviceGenerator.generate(entity,basePackage,entityPackage),javaPath+"\\service\\"+entity.className()+"Service.java");
			writeToFile(controllerGenerator.generate(entity,basePackage,entityPackage),javaPath+"\\controller\\"+entity.className()+"Controller.java");
			writeToFile(saveRequestGenerator.generate(entity,basePackage,entityPackage),javaPath+"\\controller\\request\\"+entity.className()+"SaveRequest.java");
			writeToFile(updateRequestGenerator.generate(entity,basePackage,entityPackage),javaPath+"\\controller\\request\\"+entity.className()+"UpdateRequest.java");
			writeToFile(saveResponseGenerator.generate(entity,basePackage,entityPackage),javaPath+"\\controller\\response\\"+entity.className()+"SaveResponse.java");
			
			writeToFile(controllerTestGenerator.generate(entity,basePackage,entityPackage),testPath+"\\controller\\"+entity.className()+"ControllerTest.java");
			writeToFile(serviceTestGenerator.generate(entity,basePackage,entityPackage),testPath+"\\service\\"+entity.className()+"ServiceTest.java");
        }
		writeToFile(globalExceptionHandlerGenerator.generate(basePackage),javaPath+"\\exception\\GlobalExceptionHandler.java");
		writeToFile(exceptionResponseGenerator.generate(basePackage),javaPath+"\\exception\\ExceptionResponse.java");
    }
	private void writeToFile(String data,String filePath) throws IOException
	{
		File file=new File(filePath);
		if(!file.exists())
		{
			file.createNewFile();
			writeToFile.write(file,data);
		}
	}
}