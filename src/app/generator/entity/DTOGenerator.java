package app.generator.entity;

import app.dto.EntityClass;

public class DTOGenerator extends ClassGenerator
{
    public String generate(EntityClass entity,String basePackage,String entityPackage)
    {
        String entityNameUpperCase=entity.className().toUpperCase();
        return "package "+basePackage+".dto;\n"+
                "\n"+
                "public class "+entity.className()+"DTO\n"+
                "{\n"+
                "    private Long id;\n"+
                "    public Long getId()\n"+
                "    {\n"+
                "        return id;\n"+
                "    }\n"+
                "    public void setId(Long id)\n"+
                "    {\n"+
                "        this.id=id;\n"+
                "    }\n"+
                "}";
    }
}
