package app.generator;

import app.util.DefaultIdValue;

public class ServiceTestGenerator extends ClassGenerator
{
    public String generate(String entity,String idType,String basePackage,String entityPackage)
    {
        String entityNameLowerCase=Character.toLowerCase(entity.charAt(0))+entity.substring(1);
        String defaultId=DefaultIdValue.get(idType);
        return "package "+basePackage+".service;\n"+
                "\n"+
                "import "+basePackage+"."+entityPackage+"."+entity+";\n"+
                "import "+basePackage+".repository."+entity+"Repository;\n"+
                "import org.junit.jupiter.api.Assertions;\n"+
                "import org.junit.jupiter.api.BeforeEach;\n"+
                "import org.junit.jupiter.api.Test;\n"+
                "import org.junit.jupiter.api.extension.ExtendWith;\n"+
                "import org.mockito.Mock;\n"+
                "import org.mockito.Mockito;\n"+
                "import org.mockito.junit.jupiter.MockitoExtension;\n"+
                "import org.springframework.data.domain.Page;\n"+
                "import org.springframework.data.domain.PageImpl;\n"+
                "import org.springframework.data.domain.PageRequest;\n"+
                "import org.springframework.data.domain.Pageable;\n"+
                "\n"+
                "import javax.persistence.EntityNotFoundException;\n"+
                "import java.util.List;\n"+
                "import java.util.Optional;\n"+
                "\n"+
                "@ExtendWith(MockitoExtension.class)\n"+
                "class "+entity+"ServiceTest\n"+
                "{\n"+
                "    @Mock\n"+
                "    "+entity+"Repository "+entityNameLowerCase+"Repository;\n"+
                "    "+entity+"Service "+entityNameLowerCase+"Service;\n"+
                "\n"+
                "    @BeforeEach\n"+
                "    void setUp()\n"+
                "    {\n"+
                "        "+entityNameLowerCase+"Service=new "+entity+"Service("+entityNameLowerCase+"Repository);\n"+
                "    }\n"+
                "    @Test\n"+
                "    void save()\n"+
                "    {\n"+
                "        "+entity+" "+entityNameLowerCase+"=new "+entity+"();\n"+
                "        Mockito.when("+entityNameLowerCase+"Repository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,"+entity+".class));\n"+
                "        "+entity+" saved="+entityNameLowerCase+"Service.save("+entityNameLowerCase+");\n"+
                "        Assertions.assertEquals(saved,"+entityNameLowerCase+");\n"+
                "    }\n"+
                "    @Test\n"+
                "    void update()\n"+
                "    {\n"+
                "        "+entity+" "+entityNameLowerCase+"=new "+entity+"();\n"+
                "        Mockito.when("+entityNameLowerCase+"Repository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,"+entity+".class));\n"+
                "        "+entity+" updated="+entityNameLowerCase+"Service.update("+entityNameLowerCase+");\n"+
                "        Assertions.assertEquals(updated,"+entityNameLowerCase+");\n"+
                "    }\n"+
                "    @Test\n"+
                "    void deleteById()\n"+
                "    {\n"+
                "        "+entityNameLowerCase+"Service.deleteById("+defaultId+");\n"+
                "        Mockito.verify("+entityNameLowerCase+"Repository).deleteById(Mockito.any());\n"+
                "    }\n"+
                "    @Test\n"+
                "    void findById_returns"+entity+"()\n"+
                "    {\n"+
                "        "+entity+" "+entityNameLowerCase+"=new "+entity+"();\n"+
                "        Mockito.when("+entityNameLowerCase+"Repository.findById(Mockito.any())).thenReturn(Optional.of("+entityNameLowerCase+"));\n"+
                "        "+entity+" actual="+entityNameLowerCase+"Service.findById("+defaultId+");\n"+
                "        Assertions.assertEquals(actual,"+entityNameLowerCase+");\n"+
                "    }\n"+
                "    @Test\n"+
                "    void findById_throwsEntityNotFoundException()\n"+
                "    {\n"+
                "        Mockito.when("+entityNameLowerCase+"Repository.findById(Mockito.any())).thenReturn(Optional.empty());\n"+
                "        Assertions.assertThrows(EntityNotFoundException.class,()->"+entityNameLowerCase+"Service.findById("+defaultId+"));\n"+
                "    }\n"+
                "    @Test\n"+
                "    void findAll()\n"+
                "    {\n"+
                "        Page<"+entity+"> page=new PageImpl<>(List.of(new "+entity+"()));\n"+
                "        Mockito.when("+entityNameLowerCase+"Repository.findAll(Mockito.any(Pageable.class))).thenReturn(page);\n"+
                "        Page<"+entity+"> actual="+entityNameLowerCase+"Service.findAll(PageRequest.of(0,20));\n"+
                "        Assertions.assertEquals(actual,page);\n"+
                "    }\n"+
                "}";
    }
}