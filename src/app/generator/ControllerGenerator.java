package app.generator;

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
                +"import org.springframework.web.bind.annotation.*;\n"
                +"import org.springframework.data.domain.Pageable;\n"
                +"\n"+"import org.springframework.data.domain.Page;\n"
                +"\n"+"import org.springframework.http.HttpStatus;\n"
                +"\n"
                +"@RestController\n"
                +"@RequestMapping(\"/"+entityNameLowerCase+"\")\n"
                +"public class "+entity+"Controller\n"
                +"{\n"
                +"\tprivate "+entity+"Service "+entityNameLowerCase+"Service;\n"
                +"\tpublic "+entity+"Controller("+entity+"Service "+entityNameLowerCase+"Service)\n"
                +"\t{\n"
                +"\t\tthis."+entityNameLowerCase+"Service="+entityNameLowerCase+"Service;\n"
                +"\t}\n"
                +"\t@PostMapping\n"
                +"\t@ResponseStatus(code=HttpStatus.CREATED)\n"
                +"\tpublic "+entity+" save(@RequestBody "+entity+" "+entityNameLowerCase+")\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Service.save("+entityNameLowerCase+");\n"
                +"\t}\n"
                +"\t@PutMapping(\"/{id}\")\n"
                +"\tpublic "+entity+" update(@RequestBody "+entity+" "+entityNameLowerCase+",@PathVariable "+entityClass.idType()+" id)\n"
                +"\t{\n"
                +"\t\t"+entityNameLowerCase+".setId(id);\n"
                +"\t\treturn "+entityNameLowerCase+"Service.update("+entityNameLowerCase+");\n"
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