package app;

public class ControllerGenerator extends ClassGenerator
{
    public String generate(String entity,
            String idType,String basePackage,String entityPackage)
    {
        String entityNameLowerCase=Character.toLowerCase(entity.charAt(0))+entity.substring(1);
        return "package "+basePackage+".controller;\n"
                +"\n"
                +"import "+basePackage+"."+entityPackage+"."+entity+";\n"
                +"import "+basePackage+".service."+entity+"Service;\n"
                +"import org.springframework.web.bind.annotation.*;\n"
                +"\n"+"import java.util.List;\n"
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
                +"\tpublic "+entity+" update(@RequestBody "+entity+" "+entityNameLowerCase+",@PathVariable "+idType+" id)\n"
                +"\t{\n"
                +"\t\t"+entityNameLowerCase+".setId(id);\n"
                +"\t\treturn "+entityNameLowerCase+"Service.update("+entityNameLowerCase+");\n"
                +"\t}\n"
                +"\t@GetMapping(\"/{id}\")\n"
                +"\tpublic "+entity+" findById(@PathVariable "+idType+" id)\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Service.findById(id);\n"
                +"\t}\n"
                +"\t@GetMapping\n"
                +"\tpublic List<"+entity+"> findAll()\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Service.findAll();\n"
                +"\t}\n"
                +"\t@DeleteMapping(\"/{id}\")\n"
                +"\t@ResponseStatus(code=HttpStatus.NO_CONTENT)\n"
                +"\tpublic void deleteById(@PathVariable "+idType+" id)\n"
                +"\t{\n"
                +"\t\t"+entityNameLowerCase+"Service.deleteById(id);\n"
                +"\t}\n"
                +"}";
    }
}