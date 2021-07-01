package app;

public class ControllerGenerator extends ClassGenerator
{
    public String generate(Entity entity,String basePackage,String entityPackage)
    {
        String entityNameLowerCase=Character.toLowerCase(entity.getName().charAt(0))+entity.getName().substring(1);
        return "package "+basePackage+".controller;\n"
                +"\n"
                +"import "+basePackage+"."+entityPackage+"."+entity.getName()+";\n"
                +"import "+basePackage+".service."+entity.getName()+"Service;\n"
                +"import org.springframework.web.bind.annotation.*;\n"
                +"\n"+"import java.util.List;\n"
                +"\n"
                +"@RestController\n"
                +"@RequestMapping(\"/"+entityNameLowerCase+"\")\n"
                +"public class "+entity.getName()+"Controller\n"
                +"{\n"
                +"\tprivate "+entity.getName()+"Service "+entityNameLowerCase+"Service;\n"
                +"\tpublic "+entity.getName()+"Controller("+entity.getName()+"Service "+entityNameLowerCase+"Service)\n"
                +"\t{\n"
                +"\t\tthis."+entityNameLowerCase+"Service="+entityNameLowerCase+"Service;\n"
                +"\t}\n"
                +"\t@PostMapping\n"
                +"\tpublic "+entity.getName()+" save(@RequestBody "+entity.getName()+" "+entityNameLowerCase+")\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Service.save("+entityNameLowerCase+");\n"
                +"\t}\n"
                +"\t@PutMapping(\"/{id}\")\n"
                +"\tpublic "+entity.getName()+" update(@RequestBody "+entity.getName()+" "+entityNameLowerCase+",@PathVariable "+entity.getIdType()+" id)\n"
                +"\t{\n"
                +"\t\t"+entityNameLowerCase+".setId(id);\n"
                +"\t\treturn "+entityNameLowerCase+"Service.update("+entityNameLowerCase+");\n"
                +"\t}\n"
                +"\t@GetMapping(\"/{id}\")\n"
                +"\tpublic "+entity.getName()+" findById(@PathVariable "+entity.getIdType()+" id)\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Service.findById(id);\n"
                +"\t}\n"
                +"\t@GetMapping\n"
                +"\tpublic List<"+entity.getName()+"> findAll()\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Service.findAll();\n"
                +"\t}\n"
                +"\t@DeleteMapping(\"/{id}\")\n"
                +"\tpublic void deleteById(@PathVariable "+entity.getIdType()+" id)\n"
                +"\t{\n"
                +"\t\t"+entityNameLowerCase+"Service.deleteById(id);\n"
                +"\t}\n"
                +"}";
    }
}