package app.generator;

import app.dto.EntityClass;

public class RepositoryGenerator extends ClassGenerator
{
    public String generate(EntityClass entityClass,String basePackage,String entityPackage)
    {
        String entity=entityClass.getClassName();
        return "package "+basePackage+".repository;\n"
                +"\n"+"import org.springframework.data.jpa.repository.JpaRepository;\n"
                +"import "+basePackage+"."+entityPackage+"."+entity+";\n"
                +"\n"
                +"public interface "+entity+"Repository extends JpaRepository<"+entity+","+entityClass.getIdType()+">{}";
    }
}