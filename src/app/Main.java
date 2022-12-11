package app;

import app.generator.GeneratorFacade;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
		GeneratorFacade generatorFacade=new GeneratorFacade();
        String projectPath=args[0];
        String basePackage=args[1];
        String entityPackage=args[2];
        List<String> entityNames=new LinkedList<>();
        for(int i=3;i<args.length;i++)
            entityNames.add(args[i]);
        try
        {
            generatorFacade.generate(projectPath,basePackage,entityPackage,entityNames);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}