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
                +"import "+basePackage+".dto."+entity+"DTO;\n"
                +"import "+basePackage+".service."+entity+"Service;\n"
                +"import "+basePackage+".controller.request."+entity+"SaveRequest;\n"
                +"import "+basePackage+".controller.request."+entity+"UpdateRequest;\n"
                +"import "+basePackage+".controller.response."+entity+"SaveResponse;\n"
                +"import org.springframework.web.bind.annotation.*;\n"
                +"import org.springframework.data.domain.Pageable;\n"
                +"import org.springframework.data.web.PagedModel;\n"
                +"\n"+"import org.springframework.http.HttpStatus;\n"
                +"\n"
                +"@RestController\n"
                +"@RequestMapping(\"/api/"+entityNameLowerCase+"\")\n"
                +"public class "+entity+"Controller\n"
                +"{\n"
                +"\tprivate "+entity+"Service "+entityNameLowerCase+"Service;\n"
                +"\tpublic "+entity+"Controller("+entity+"Service "+entityNameLowerCase+"Service)\n"
                +"\t{\n"
                +"\t\tthis."+entityNameLowerCase+"Service="+entityNameLowerCase+"Service;\n"
                +"\t}\n"
                +"\t@PostMapping\n"
                +"\t@ResponseStatus(code=HttpStatus.CREATED)\n"
                +"\tpublic "+entity+"SaveResponse save(@RequestBody "+entity+"SaveRequest request)\n"
                +"\t{\n"
                +"\t\t"+entityClass.idType()+" savedId="+entityNameLowerCase+"Service.save(request);\n"
                +"\t\treturn new "+entity+"SaveResponse(savedId);\n"
                +"\t}\n"
                +"\t@PutMapping(\"/{id}\")\n"
                +"\t@ResponseStatus(code=HttpStatus.NO_CONTENT)\n"
                +"\tpublic void update(@RequestBody "+entity+"UpdateRequest request,@PathVariable "+entityClass.idType()+" id)\n"
                +"\t{\n"
                +"\t\t"+entityNameLowerCase+"Service.update(request,id);\n"
                +"\t}\n"
                +"\t@GetMapping(\"/{id}\")\n"
                +"\tpublic "+entity+"DTO findById(@PathVariable "+entityClass.idType()+" id)\n"
                +"\t{\n"
                +"\t\treturn "+entityNameLowerCase+"Service.findById(id);\n"
                +"\t}\n"
                +"\t@GetMapping\n"
                +"\tpublic PagedModel<"+entity+"DTO> findAll(Pageable pageable)\n"
                +"\t{\n"
                +"\t\treturn new PagedModel("+entityNameLowerCase+"Service.findAll(pageable));\n"
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