package app.generator.entity;

import app.dto.EntityClass;

public class UpdateRequestGenerator extends ClassGenerator
{
    public String generate(EntityClass entity,String basePackage,String entityPackage)
    {
        return "package "+basePackage+".controller.request;\n"
                +"\n"
                +"public class "+entity.className()+"UpdateRequest{}";
    }
}
