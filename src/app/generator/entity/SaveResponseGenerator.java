package app.generator.entity;

import app.dto.EntityClass;

public class SaveResponseGenerator extends ClassGenerator
{
    public String generate(EntityClass entity,String basePackage,String entityPackage)
    {
        return "package "+basePackage+".controller.response;\n"+
                "\n"+
                "public class "+entity.className()+"SaveResponse\n"+
                "{\n"+
                "\tprivate "+entity.idType()+" id;\n"+
                "\tpublic "+entity.className()+"SaveResponse("+entity.idType()+" id)\n"+
                "\t{\n"+
                "\t\tthis.id=id;\n"+
                "\t}\n"+
                "\tpublic "+entity.idType()+" getId()\n"+
                "\t{\n"+
                "\t\treturn id;\n"+
                "\t}\n"+
                "}";
    }
}
