package app.generator;

public class RepositoryGenerator extends ClassGenerator
{
    public String generate(String entity,String idType,String basePackage,String entityPackage)
    {
        return "package "+basePackage+".repository;\n"
                +"\n"+"import org.springframework.data.jpa.repository.JpaRepository;\n"
                +"import "+basePackage+"."+entityPackage+"."+entity+";\n"
                +"\n"
                +"public interface "+entity+"Repository extends JpaRepository<"+entity+","+idType+">{}";
    }
}