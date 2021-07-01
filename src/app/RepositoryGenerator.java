package app;

public class RepositoryGenerator extends ClassGenerator
{
    public String generate(Entity entity,String basePackage,String entityPackage)
    {
        return "package "+basePackage+".repository;\n"
                +"\n"+"import org.springframework.data.jpa.repository.JpaRepository;\n"
                +"import "+basePackage+"."+entityPackage+"."+entity.getName()+";\n"
                +"\n"
                +"public interface "+entity.getName()+"Repository extends JpaRepository<"+entity.getName()+","+entity.getIdType()+">{}";
    }
}