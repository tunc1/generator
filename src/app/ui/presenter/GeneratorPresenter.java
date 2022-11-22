package app.ui.presenter;

import app.generator.Generator;
import app.ui.view.GeneratorView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GeneratorPresenter
{
    private GeneratorView view;
    private Generator generator;
    public GeneratorPresenter(GeneratorView view,Generator generator)
    {
        this.view=view;
        this.generator=generator;
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
            generator.generate(projectPath,basePackage,entityPackage,entityNames);
            view.showMessage("Generated!");
        }
        catch(IOException e)
        {
            view.showMessage(e.getMessage());
        }
    }
}