package app.generator.entity;

import app.dto.EntityClass;

public class ControllerTestGenerator extends ClassGenerator
{
    public String generate(EntityClass entityClass,String basePackage,String entityPackage)
    {
        String entity=entityClass.className();
        String entityNameLowerCase=Character.toLowerCase(entity.charAt(0))+entity.substring(1);
        String defaultId=entityClass.idType()+".valueOf(\"1\")";
        return "package "+basePackage+".controller;\n"+
                "\n"+
                "import "+basePackage+"."+entityPackage+"."+entity+";\n"+
                "import "+basePackage+".service."+entity+"Service;\n"+
                "import "+basePackage+".controller.request."+entity+"SaveRequest;\n"+
                "import "+basePackage+".controller.response."+entity+"SaveResponse;\n"+
                "import org.junit.jupiter.api.Assertions;\n"+
                "import org.junit.jupiter.api.BeforeEach;\n"+
                "import org.junit.jupiter.api.Test;\n"+
                "import org.springframework.data.domain.Page;\n"+
                "import org.springframework.data.domain.PageImpl;\n"+
                "import org.springframework.data.domain.PageRequest;\n"+
                "import org.junit.jupiter.api.extension.ExtendWith;\n"+
                "import com.fasterxml.jackson.databind.ObjectMapper;\n"+
                "import org.mockito.Mock;\n"+
                "import org.mockito.Mockito;\n"+
                "import org.mockito.junit.jupiter.MockitoExtension;\n"+
                "\n"+
                "import java.util.List;\n"+
                "\n"+
                "@ExtendWith(MockitoExtension.class)\n"+
                "class "+entity+"ControllerTest\n"+
                "{\n"+
                "\t@Mock\n"+
                "\t"+entity+"Service "+entityNameLowerCase+"Service;\n"+
                "\t@Mock\n"+
                "\tObjectMapper objectMapper;\n"+
                "\t"+entity+"Controller "+entityNameLowerCase+"Controller;\n"+
                "\n"+
                "\t@BeforeEach\n"+
                "\tvoid setUp()\n"+
                "\t{\n"+
                "\t\t"+entityNameLowerCase+"Controller=new "+entity+"Controller("+entityNameLowerCase+"Service,objectMapper);\n"+
                "\t}\n"+
                "\t@Test\n"+
                "\tvoid save()\n"+
                "\t{\n"+
                "\t\t"+entity+" "+entityNameLowerCase+"=new "+entity+"();\n"+
                "\t\t"+entityClass.idType()+" id="+defaultId+";\n"+
                "\t\t"+entityNameLowerCase+".setId(id);\n"+
                "\t\tMockito.when("+entityNameLowerCase+"Service.save(Mockito.any())).thenReturn(id);\n"+
                "\t\tMockito.when(objectMapper.convertValue(Mockito.any(),Mockito.eq("+entity+".class))).thenReturn("+entityNameLowerCase+");\n"+
                "\t\t"+entity+"SaveRequest request=new "+entity+"SaveRequest();\n"+
                "\t\t"+entity+"SaveResponse response="+entityNameLowerCase+"Controller.save(request);\n"+
                "\t\tAssertions.assertEquals("+entityNameLowerCase+".getId(),response.getId());\n"+
                "\t}\n"+
                "\t@Test\n"+
                "\tvoid update()\n"+
                "\t{\n"+
                "\t\t"+entityClass.idType()+" id="+defaultId+";\n"+
                "\t\t"+entity+" "+entityNameLowerCase+"=new "+entity+"();\n"+
                "\t\t"+entity+"SaveRequest request=new "+entity+"SaveRequest();\n"+
                "\t\tMockito.when(objectMapper.convertValue(Mockito.any(),Mockito.eq("+entity+".class))).thenReturn("+entityNameLowerCase+");\n"+
                "\t\t"+entityNameLowerCase+"Controller.update(request,id);\n"+
                "\t\tAssertions.assertEquals(id,"+entityNameLowerCase+".getId());\n"+
                "\t\tMockito.verify("+entityNameLowerCase+"Service).update(Mockito.any());\n"+
                "\t}\n"+
                "\t@Test\n"+
                "\tvoid deleteById()\n"+
                "\t{\n"+
                "\t\t"+entityNameLowerCase+"Controller.deleteById("+defaultId+");\n"+
                "\t\tMockito.verify("+entityNameLowerCase+"Service).deleteById(Mockito.any());\n"+
                "\t}\n"+
                "\t@Test\n"+
                "\tvoid findById()\n"+
                "\t{\n"+
                "\t\t"+entity+" "+entityNameLowerCase+"=new "+entity+"();\n"+
                "\t\tMockito.when("+entityNameLowerCase+"Service.findById(Mockito.any())).thenReturn("+entityNameLowerCase+");\n"+
                "\t\t"+entity+" actual="+entityNameLowerCase+"Controller.findById("+defaultId+");\n"+
                "\t\tAssertions.assertEquals("+entityNameLowerCase+",actual);\n"+
                "\t}\n"+
                "\t@Test\n"+
                "\tvoid findAll()\n"+
                "\t{\n"+
                "\t\tPage<"+entity+"> page=new PageImpl<>(List.of(new "+entity+"()));\n"+
                "\t\tMockito.when("+entityNameLowerCase+"Service.findAll(Mockito.any())).thenReturn(page);\n"+
                "\t\tPage<"+entity+"> actual="+entityNameLowerCase+"Controller.findAll(PageRequest.of(0,20));\n"+
                "\t\tAssertions.assertEquals(page,actual);\n"+
                "\t}\n"+
                "}";
    }
}
