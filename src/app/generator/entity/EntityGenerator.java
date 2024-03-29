package app.generator.entity;

import app.dto.EntityClass;

public class EntityGenerator extends ClassGenerator
{
    public String generate(EntityClass entity,String basePackage,String entityPackage)
    {
        String entityNameUpperCase=entity.className().toUpperCase();
        return "package "+basePackage+"."+entityPackage+";\n"+
                "\n"+
                "import jakarta.persistence.*;\n"+
                "\n"+
                "@Entity\n"+
                "public class "+entity.className()+"\n"+
                "{\n"+
                "    @Id\n"+
                "    @SequenceGenerator(name=\""+entityNameUpperCase+"_SEQUENCE_GENERATOR\",sequenceName=\""+entityNameUpperCase+"_SEQUENCE\",allocationSize=1)\n"+
                "    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator=\""+entityNameUpperCase+"_SEQUENCE_GENERATOR\")\n"+
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
