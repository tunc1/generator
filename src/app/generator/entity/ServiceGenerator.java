package app.generator.entity;

import app.dto.EntityClass;

public class ServiceGenerator extends ClassGenerator
{
    public String generate(EntityClass entityClass,String basePackage,String entityPackage)
    {
        String entity=entityClass.className();
        String entityNameLowerCase=Character.toLowerCase(entity.charAt(0))+entity.substring(1);
        return "package "+basePackage+".service;\n"
                +"\n"
				+"import java.util.List;\n"
                +"import org.springframework.data.domain.Pageable;\n"
                +"import org.springframework.data.domain.Page;\n"
                +"import org.springframework.data.domain.PageImpl;\n"
                +"import org.springframework.stereotype.Service;\n"
                +"import com.fasterxml.jackson.databind.ObjectMapper;\n"
                +"import "+basePackage+"."+entityPackage+"."+entity+";\n"
                +"import "+basePackage+".dto."+entity+"DTO;\n"
                +"import "+basePackage+".repository."+entity+"Repository;\n"
                +"import "+basePackage+".controller.request."+entity+"SaveRequest;\n"
                +"import "+basePackage+".controller.request."+entity+"UpdateRequest;\n"
                +"import jakarta.persistence.EntityNotFoundException;\n"
                +"\n"
                +"@Service\n"
                +"public class "+entity+"Service\n"
                +"{\n"
                +"\tprivate "+entity+"Repository "+entityNameLowerCase+"Repository;\n"
                +"\tprivate ObjectMapper objectMapper;\n"
                +"\tpublic "+entity+"Service("+entity+"Repository "+entityNameLowerCase+"Repository,ObjectMapper objectMapper)\n"
                +"\t{\n"
                +"\t\tthis."+entityNameLowerCase+"Repository="+entityNameLowerCase+"Repository;\n"
                +"\t\tthis.objectMapper=objectMapper;\n"
                +"\t}\n"
                +"\tpublic "+entityClass.idType()+" save("+entity+"SaveRequest request)\n"
                +"\t{\n"
                +"\t\t"+entity+" "+entityNameLowerCase+"=objectMapper.convertValue(request,"+entity+".class);\n"
                +"\t\t"+entityNameLowerCase+"Repository.save("+entityNameLowerCase+");\n"
                +"\t\treturn "+entityNameLowerCase+".getId();\n"
                +"\t}\n"
                +"\tpublic void update("+entity+"UpdateRequest request,"+entityClass.idType()+" id)\n"
                +"\t{\n"
                +"\t\t"+entity+" "+entityNameLowerCase+"=objectMapper.convertValue(request,"+entity+".class);\n"
                +"\t\t"+entityNameLowerCase+".setId(id);\n"
                +"\t\t"+entityNameLowerCase+"Repository.save("+entityNameLowerCase+");\n"
                +"\t}\n"
                +"\tpublic void deleteById("+entityClass.idType()+" id)\n"
                +"\t{\n"
                +"\t\t"+entityNameLowerCase+"Repository.deleteById(id);\n"
                +"\t}\n"
                +"\tpublic "+entity+"DTO findById("+entityClass.idType()+" id)\n"
                +"\t{\n"
                +"\t\t"+entity+" "+entityNameLowerCase+"="+entityNameLowerCase+"Repository.findById(id).orElseThrow(EntityNotFoundException::new);\n"
                +"\t\treturn objectMapper.convertValue("+entityNameLowerCase+","+entity+"DTO.class);\n"
                +"\t}\n"
                +"\tpublic Page<"+entity+"DTO> findAll(Pageable pageable)\n"
                +"\t{\n"
                +"\t\tPage<"+entity+"> page="+entityNameLowerCase+"Repository.findAll(pageable);\n"
                +"\t\tList<"+entity+"DTO> list=page.getContent().stream().map(entity->objectMapper.convertValue(entity,"+entity+"DTO.class)).toList();\n"
                +"\t\treturn new PageImpl<>(list,page.getPageable(),page.getTotalElements());\n"
                +"\t}\n"
                +"}";
    }
}