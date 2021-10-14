package app.ui.presenter;

import app.generator.Generator;
import app.ui.view.GeneratorView;

import java.io.IOException;

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
        String idType=view.getIdType();
        try
        {
            generator.generate(projectPath,basePackage,entityPackage,idType);
            view.showMessage("Generated!");
        }
        catch(IOException e)
        {
            view.showMessage(e.getMessage());
        }
    }
}