package app.generator.entity;

import app.dto.EntityClass;

public class ServiceGenerator extends ClassGenerator
{
    public String generate(EntityClass entityClass,String basePackage,String entityPackage)
    {
        String entity=entityClass.className();
        String entityNameLowerCase=Character.toLowerCase(entity.charAt(0))+entity.substring(1);
        return "package "+basePackage+".service;\n"
                +"\n"+"import org.springframework.data.domain.Pageable;\n"
                +"\n"+"import org.springframework.data.domain.Page;\n"
                +"import org.springframework.stereotype.Service;\n"
                +"import "+basePackage+"."+entityPackage+"."+entity+";\n"
                +"import "+basePackage+".repository."+entity+"Repository;\n"
                +"import javax.persistence.EntityNotFoundException;\n"
                +"\n"
                +"@Service\n"
                +"public class "+entity+"Service\n"
                +"{\n"
                +"\tprivate "+entity+"Repository "+entityNameLowerCase+"Repository;\n"
                +"\tpublic "+entity+"Service("+entity+"Repository "+entityNameLowerCase+"Repository)\n"
                +"\t{\n"
                +"\t\tthis."+entityNameLowerCase+"Repository="+entityNameLowerCase+"Repository;\n"
                +"\t}\n"
                +"\tpublic "+entity+" save("+entity+" "+entityNameLowerCase+")\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Repository.save("+entityNameLowerCase+");\n"
                +"\t}\n"
                +"\tpublic "+entity+" update("+entity+" "+entityNameLowerCase+")\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Repository.save("+entityNameLowerCase+");\n"
                +"\t}\n"
                +"\tpublic void deleteById("+entityClass.idType()+" id)\n"
                +"\t{\n"
                +"\t\t"+entityNameLowerCase+"Repository.deleteById(id);\n"
                +"\t}\n"
                +"\tpublic "+entity+" findById("+entityClass.idType()+" id)\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Repository.findById(id).orElseThrow(EntityNotFoundException::new);\n"
                +"\t}\n"
                +"\tpublic Page<"+entity+"> findAll(Pageable pageable)\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Repository.findAll(pageable);\n"
                +"\t}\n"
                +"}";
    }
}