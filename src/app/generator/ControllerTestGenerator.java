package app.generator;

import app.dto.EntityClass;

public class ControllerTestGenerator extends ClassGenerator
{
    public String generate(EntityClass entityClass,String basePackage,String entityPackage)
    {
        String entity=entityClass.getClassName();
        String entityNameLowerCase=Character.toLowerCase(entity.charAt(0))+entity.substring(1);
        String defaultId=entityClass.getIdType()+".valueOf(\"1\")";
        return "package "+basePackage+".controller;\n"+
                "\n"+
                "import "+basePackage+"."+entityPackage+"."+entity+";\n"+
                "import "+basePackage+".service."+entity+"Service;\n"+
                "import org.junit.jupiter.api.Assertions;\n"+
                "import org.junit.jupiter.api.BeforeEach;\n"+
                "import org.junit.jupiter.api.Test;\n"+
                "import org.springframework.data.domain.Page;\n"+
                "import org.springframework.data.domain.PageImpl;\n"+
                "import org.springframework.data.domain.PageRequest;\n"+
                "import org.junit.jupiter.api.extension.ExtendWith;\n"+
                "import org.mockito.Mock;\n"+
                "import org.mockito.Mockito;\n"+
                "import org.mockito.junit.jupiter.MockitoExtension;\n"+
                "\n"+
                "import java.util.List;\n"+
                "\n"+
                "@ExtendWith(MockitoExtension.class)\n"+
                "class "+entity+"ControllerTest\n"+
                "{\n"+
                "    @Mock\n"+
                "    "+entity+"Service "+entityNameLowerCase+"Service;\n"+
                "    "+entity+"Controller "+entityNameLowerCase+"Controller;\n"+
                "\n"+
                "    @BeforeEach\n"+
                "    void setUp()\n"+
                "    {\n"+
                "        "+entityNameLowerCase+"Controller=new "+entity+"Controller("+entityNameLowerCase+"Service);\n"+
                "    }\n"+
                "    @Test\n"+
                "    void save()\n"+
                "    {\n"+
                "        "+entity+" "+entityNameLowerCase+"=new "+entity+"();\n"+
                "        Mockito.when("+entityNameLowerCase+"Service.save(Mockito.any())).thenAnswer(e->e.getArgument(0,"+entity+".class));\n"+
                "        "+entity+" saved="+entityNameLowerCase+"Controller.save("+entityNameLowerCase+");\n"+
                "        Assertions.assertEquals("+entityNameLowerCase+",saved);\n"+
                "    }\n"+
                "    @Test\n"+
                "    void update()\n"+
                "    {\n"+
                "        "+entityClass.getIdType()+" id="+defaultId+";\n"+
                "        "+entity+" "+entityNameLowerCase+"=new "+entity+"();\n"+
                "        Mockito.when("+entityNameLowerCase+"Service.update(Mockito.any())).thenAnswer(e->e.getArgument(0,"+entity+".class));\n"+
                "        "+entity+" updated="+entityNameLowerCase+"Controller.update("+entityNameLowerCase+",id);\n"+
                "        Assertions.assertEquals("+entityNameLowerCase+",updated);\n"+
                "        Assertions.assertEquals(id,updated.getId());\n"+
                "    }\n"+
                "    @Test\n"+
                "    void deleteById()\n"+
                "    {\n"+
                "        "+entityNameLowerCase+"Controller.deleteById("+defaultId+");\n"+
                "        Mockito.verify("+entityNameLowerCase+"Service).deleteById(Mockito.any());\n"+
                "    }\n"+
                "    @Test\n"+
                "    void findById_returns"+entity+"()\n"+
                "    {\n"+
                "        "+entity+" "+entityNameLowerCase+"=new "+entity+"();\n"+
                "        Mockito.when("+entityNameLowerCase+"Service.findById(Mockito.any())).thenReturn("+entityNameLowerCase+");\n"+
                "        "+entity+" actual="+entityNameLowerCase+"Controller.findById("+defaultId+");\n"+
                "        Assertions.assertEquals("+entityNameLowerCase+",actual);\n"+
                "    }\n"+
                "    @Test\n"+
                "    void findAll()\n"+
                "    {\n"+
                "        Page<"+entity+"> page=new PageImpl<>(List.of(new "+entity+"()));\n"+
                "        Mockito.when("+entityNameLowerCase+"Service.findAll(Mockito.any())).thenReturn(page);\n"+
                "        Page<"+entity+"> actual="+entityNameLowerCase+"Controller.findAll(PageRequest.of(0,20));\n"+
                "        Assertions.assertEquals(page,actual);\n"+
                "    }\n"+
                "}";
    }
}
