package app.generator;

import app.util.DefaultIdValue;

public class ControllerTestGenerator extends ClassGenerator
{
    public String generate(String entity,String idType,String basePackage,String entityPackage)
    {
        String entityNameLowerCase=Character.toLowerCase(entity.charAt(0))+entity.substring(1);
        String defaultId=DefaultIdValue.get(idType);
        return "package "+basePackage+".controller;\n"+
                "\n"+
                "import "+basePackage+"."+entityPackage+"."+entity+";\n"+
                "import "+basePackage+".service."+entity+"Service;\n"+
                "import org.junit.jupiter.api.Assertions;\n"+
                "import org.junit.jupiter.api.BeforeEach;\n"+
                "import org.junit.jupiter.api.Test;\n"+
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
                "        Assertions.assertEquals(saved,"+entityNameLowerCase+");\n"+
                "    }\n"+
                "    @Test\n"+
                "    void update()\n"+
                "    {\n"+
                "        "+idType+" id="+defaultId+";\n"+
                "        "+entity+" "+entityNameLowerCase+"=new "+entity+"();\n"+
                "        Mockito.when("+entityNameLowerCase+"Service.update(Mockito.any())).thenAnswer(e->e.getArgument(0,"+entity+".class));\n"+
                "        "+entity+" updated="+entityNameLowerCase+"Controller.update("+entityNameLowerCase+",id);\n"+
                "        Assertions.assertEquals(updated,"+entityNameLowerCase+");\n"+
                "        Assertions.assertEquals(updated.getId(),id);\n"+
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
                "        Assertions.assertEquals(actual,"+entityNameLowerCase+");\n"+
                "    }\n"+
                "    @Test\n"+
                "    void findAll()\n"+
                "    {\n"+
                "        List<"+entity+"> "+entityNameLowerCase+"s=List.of(new "+entity+"());\n"+
                "        Mockito.when("+entityNameLowerCase+"Service.findAll()).thenReturn("+entityNameLowerCase+"s);\n"+
                "        List<"+entity+"> actual="+entityNameLowerCase+"Controller.findAll();\n"+
                "        Assertions.assertEquals(actual,"+entityNameLowerCase+"s);\n"+
                "    }\n"+
                "}";
    }
}
