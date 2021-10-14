package app;

import app.generator.Generator;
import app.ui.presenter.GeneratorPresenter;
import app.ui.view.GeneratorView;

public class Main
{
    public static void main(String[] args)
    {
        new GeneratorPresenter(new GeneratorView(),new Generator()).start();
    }
}