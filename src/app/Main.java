package app;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        String allIdType=null;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Project Path:");
        String projectPath=scanner.nextLine();
        System.out.println("Base Package Name:");
        String basePackage=scanner.nextLine();
        System.out.println("Entity Package Name:");
        String entityPackage=scanner.nextLine();
        System.out.println("How many Entity Classes?");
        int entityClassQuantity=Integer.parseInt(scanner.nextLine());
        System.out.println("Do all entity classes have same id type? (true/false)");
        boolean sameIdType=Boolean.parseBoolean(scanner.nextLine());
        if(sameIdType)
        {
            System.out.println("Enter Id Type:");
            allIdType=scanner.nextLine();
        }
        Entity[] entityClasses=new Entity[entityClassQuantity];
        for(int i=0;i<entityClasses.length;i++)
        {
            System.out.println("Enter Entity Class Name:");
            String name=scanner.nextLine();
            String idType=allIdType;
            if(!sameIdType)
            {
                System.out.println("Enter Entity ID Type:");
                idType=scanner.nextLine();
            }
            entityClasses[i]=new Entity(name,idType);
        }
        Generator generator=new Generator();
        generator.generate(projectPath,basePackage,entityPackage,entityClasses);
        System.out.println("Generated!");
        scanner.close();
    }
}