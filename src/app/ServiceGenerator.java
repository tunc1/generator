package app;

public class ServiceGenerator extends ClassGenerator
{
    public String generate(Entity entity,String basePackage,String entityPackage)
    {
        String entityNameLowerCase=Character.toLowerCase(entity.getName().charAt(0))+entity.getName().substring(1);
        return "package "+basePackage+".service;\n"
                +"\n"+"import java.util.List;\n"
                +"import org.springframework.stereotype.Service;\n"
                +"import "+basePackage+"."+entityPackage+"."+entity.getName()+";\n"
                +"import "+basePackage+".repository."+entity.getName()+"Repository;\n"
                +"\n"
                +"@Service\n"
                +"public class "+entity.getName()+"Service\n"
                +"{\n"
                +"\tprivate "+entity.getName()+"Repository "+entityNameLowerCase+"Repository;\n"
                +"\tpublic "+entity.getName()+"Service("+entity.getName()+"Repository "+entityNameLowerCase+"Repository)\n"
                +"\t{\n"
                +"\t\tthis."+entityNameLowerCase+"Repository="+entityNameLowerCase+"Repository;\n"
                +"\t}\n"
                +"\tpublic "+entity.getName()+" save("+entity.getName()+" "+entityNameLowerCase+")\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Repository.save("+entityNameLowerCase+");\n"
                +"\t}\n"
                +"\tpublic "+entity.getName()+" update("+entity.getName()+" "+entityNameLowerCase+")\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Repository.save("+entityNameLowerCase+");\n"
                +"\t}\n"
                +"\tpublic void deleteById("+entity.getIdType()+" id)\n"
                +"\t{\n"
                +"\t\t"+entityNameLowerCase+"Repository.deleteById(id);\n"
                +"\t}\n"
                +"\tpublic "+entity.getName()+" findById("+entity.getIdType()+" id)\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Repository.findById(id).orElse(null);\n"
                +"\t}\n"
                +"\tpublic List<"+entity.getName()+"> findAll()\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Repository.findAll();\n"
                +"\t}\n"
                +"}";
    }
}