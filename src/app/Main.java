package app;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter Project Path: ");
        String projectPath=scanner.nextLine();
        System.out.print("Base Package Name: ");
        String basePackage=scanner.nextLine();
        System.out.print("Entity Package Name: ");
        String entityPackage=scanner.nextLine();
        System.out.print("Enter Id Type for All Entity Classes: ");
        String idType=scanner.nextLine();
        Generator generator=new Generator();
        generator.generate(projectPath,basePackage,entityPackage,idType);
        System.out.print("Generated!");
        scanner.close();
    }
}