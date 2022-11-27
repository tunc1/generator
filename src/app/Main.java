package app;

import app.generator.Generator;
import app.ui.GeneratorPresenter;
import app.ui.GeneratorView;

public class Main
{
    public static void main(String[] args)
    {
        new GeneratorPresenter(new GeneratorView(),new Generator()).start();
    }
}