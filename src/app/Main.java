package app;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Project Path:");
        String projectPath=scanner.nextLine();
        System.out.println("Base Package Name:");
        String basePackage=scanner.nextLine();
        System.out.println("Entity Package Name:");
        String entityPackage=scanner.nextLine();
        System.out.println("How many Entity Classes?");
        int entityClassQuantity=Integer.parseInt(scanner.nextLine());
        Entity[] entityClasses=new Entity[entityClassQuantity];
        for(int i=0;i<entityClasses.length;i++)
        {
            System.out.println("Enter Entity Class Name:");
            String name=scanner.nextLine();
            System.out.println("Enter Entity ID Type:");
            String idType=scanner.nextLine();
            entityClasses[i]=new Entity(name,idType);
        }
        Generator generator=new Generator();
        generator.generate(projectPath,basePackage,entityPackage,entityClasses);
        System.out.println("Generated!");
        scanner.close();
    }
}