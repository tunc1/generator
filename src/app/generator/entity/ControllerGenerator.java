package app.generator.entity;

import app.dto.EntityClass;

public class ControllerGenerator extends ClassGenerator
{
    public String generate(EntityClass entityClass,String basePackage,String entityPackage)
    {
        String entity=entityClass.className();
        String entityNameLowerCase=Character.toLowerCase(entity.charAt(0))+entity.substring(1);
        return "package "+basePackage+".controller;\n"
                +"\n"
                +"import "+basePackage+"."+entityPackage+"."+entity+";\n"
                +"import "+basePackage+".service."+entity+"Service;\n"
                +"import "+basePackage+".controller.request."+entity+"SaveRequest;\n"
                +"import "+basePackage+".controller.response."+entity+"SaveResponse;\n"
                +"import org.springframework.web.bind.annotation.*;\n"
                +"import org.springframework.data.domain.Pageable;\n"
                +"\n"+"import org.springframework.data.domain.Page;\n"
                +"\n"+"import org.springframework.http.HttpStatus;\n"
                +"import com.fasterxml.jackson.databind.ObjectMapper;\n"
                +"\n"
                +"@RestController\n"
                +"@RequestMapping(\"/api/"+entityNameLowerCase+"\")\n"
                +"public class "+entity+"Controller\n"
                +"{\n"
                +"\tprivate "+entity+"Service "+entityNameLowerCase+"Service;\n"
                +"\tprivate ObjectMapper objectMapper;\n"
                +"\tpublic "+entity+"Controller("+entity+"Service "+entityNameLowerCase+"Service,ObjectMapper objectMapper)\n"
                +"\t{\n"
                +"\t\tthis."+entityNameLowerCase+"Service="+entityNameLowerCase+"Service;\n"
                +"\t\tthis.objectMapper=objectMapper;\n"
                +"\t}\n"
                +"\t@PostMapping\n"
                +"\t@ResponseStatus(code=HttpStatus.CREATED)\n"
                +"\tpublic "+entity+"SaveResponse save(@RequestBody "+entity+"SaveRequest request)\n"
                +"\t{\n"
                +"\t\t"+entity+" "+entityNameLowerCase+"=objectMapper.convertValue(request,"+entity+".class);\n"
                +"\t\t"+entityClass.idType()+" savedId="+entityNameLowerCase+"Service.save("+entityNameLowerCase+");\n"
                +"\t\treturn new "+entity+"SaveResponse(savedId);\n"
                +"\t}\n"
                +"\t@PutMapping(\"/{id}\")\n"
                +"\t@ResponseStatus(code=HttpStatus.NO_CONTENT)\n"
                +"\tpublic void update(@RequestBody "+entity+"SaveRequest request,@PathVariable "+entityClass.idType()+" id)\n"
                +"\t{\n"
                +"\t\t"+entity+" "+entityNameLowerCase+"=objectMapper.convertValue(request,"+entity+".class);\n"
                +"\t\t"+entityNameLowerCase+".setId(id);\n"
                +"\t\t"+entityNameLowerCase+"Service.update("+entityNameLowerCase+");\n"
                +"\t}\n"
                +"\t@GetMapping(\"/{id}\")\n"
                +"\tpublic "+entity+" findById(@PathVariable "+entityClass.idType()+" id)\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Service.findById(id);\n"
                +"\t}\n"
                +"\t@GetMapping\n"
                +"\tpublic Page<"+entity+"> findAll(Pageable pageable)\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Service.findAll(pageable);\n"
                +"\t}\n"
                +"\t@DeleteMapping(\"/{id}\")\n"
                +"\t@ResponseStatus(code=HttpStatus.NO_CONTENT)\n"
                +"\tpublic void deleteById(@PathVariable "+entityClass.idType()+" id)\n"
                +"\t{\n"
                +"\t\t"+entityNameLowerCase+"Service.deleteById(id);\n"
                +"\t}\n"
                +"}";
    }
}