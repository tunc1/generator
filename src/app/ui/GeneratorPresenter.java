package app.ui;

import app.generator.GeneratorFacade;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GeneratorPresenter
{
    private GeneratorView view;
    private GeneratorFacade generatorFacade;
    public GeneratorPresenter(GeneratorView view,GeneratorFacade generatorFacade)
    {
        this.view=view;
        this.generatorFacade=generatorFacade;
        view.setOnClick(e->generate());
    }
    public void start()
    {
        view.show();
    }
    public void generate()
    {
        String projectPath=view.getProjectPath();
        String basePackage=view.getBasePackage();
        String entityPackage=view.getEntityPackage();
        List<String> entityNames=Arrays.stream(view.getEntityNames().split(",")).toList();
        try
        {
            generatorFacade.generate(projectPath,basePackage,entityPackage,entityNames);
            view.showMessage("Generated!");
        }
        catch(IOException e)
        {
            view.showMessage(e.getMessage());
        }
    }
}