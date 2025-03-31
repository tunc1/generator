package app.generator.entity;

import app.dto.EntityClass;

public class ServiceTestGenerator extends ClassGenerator
{
    public String generate(EntityClass entityClass,String basePackage,String entityPackage)
    {
        String entity=entityClass.className();
        String entityNameLowerCase=Character.toLowerCase(entity.charAt(0))+entity.substring(1);
        String defaultId=entityClass.idType()+".valueOf(\"1\")";
        return "package "+basePackage+".service;\n"+
                "\n"+
                "import "+basePackage+"."+entityPackage+"."+entity+";\n"+
                "import "+basePackage+".dto."+entity+"DTO;\n"+
                "import "+basePackage+".repository."+entity+"Repository;\n"+
                "import "+basePackage+".controller.request."+entity+"SaveRequest;\n"+
                "import "+basePackage+".controller.request."+entity+"UpdateRequest;\n"+
                "import org.junit.jupiter.api.Assertions;\n"+
                "import org.junit.jupiter.api.BeforeEach;\n"+
                "import org.junit.jupiter.api.Test;\n"+
                "import org.junit.jupiter.api.extension.ExtendWith;\n"+
                "import com.fasterxml.jackson.databind.ObjectMapper;\n"+
                "import org.mockito.Mock;\n"+
                "import org.mockito.Mockito;\n"+
                "import org.mockito.junit.jupiter.MockitoExtension;\n"+
                "import org.springframework.data.domain.Page;\n"+
                "import org.springframework.data.domain.PageImpl;\n"+
                "import org.springframework.data.domain.PageRequest;\n"+
                "import org.springframework.data.domain.Pageable;\n"+
                "\n"+
                "import jakarta.persistence.EntityNotFoundException;\n"+
                "import java.util.List;\n"+
                "import java.util.Optional;\n"+
                "\n"+
                "@ExtendWith(MockitoExtension.class)\n"+
                "class "+entity+"ServiceTest\n"+
                "{\n"+
                "\t@Mock\n"+
                "\t"+entity+"Repository "+entityNameLowerCase+"Repository;\n"+
                "\t@Mock\n"+
				"\tObjectMapper objectMapper;\n"+
                "\t"+entity+"Service "+entityNameLowerCase+"Service;\n"+
                "\n"+
                "\t@BeforeEach\n"+
                "\tvoid setUp()\n"+
                "\t{\n"+
                "\t\t"+entityNameLowerCase+"Service=new "+entity+"Service("+entityNameLowerCase+"Repository,objectMapper);\n"+
                "\t}\n"+
                "\t@Test\n"+
                "\tvoid save()\n"+
                "\t{\n"+
                "\t\t"+entity+" "+entityNameLowerCase+"=new "+entity+"();\n"+
                "\t\tMockito.when(objectMapper.convertValue(Mockito.any(),Mockito.any(Class.class))).thenReturn("+entityNameLowerCase+");\n"+
                "\t\t"+entityClass.idType()+" id="+defaultId+";\n"+
                "\t\tMockito.when("+entityNameLowerCase+"Repository.save(Mockito.any())).thenAnswer(e->\n" +
                "\t\t{\n" +
                "\t\t\t"+entity+" entity=e.getArgument(0);\n" +
                "\t\t\tentity.setId(id);\n" +
                "\t\t\treturn entity;\n" +
                "\t\t});\n"+
                "\t\t"+entity+"SaveRequest request=new "+entity+"SaveRequest();\n"+
                "\t\t"+entityClass.idType()+" savedId="+entityNameLowerCase+"Service.save(request);\n"+
                "\t\tAssertions.assertEquals(id,savedId);\n"+
                "\t}\n"+
                "\t@Test\n"+
                "\tvoid update()\n"+
                "\t{\n"+
                "\t\t"+entityClass.idType()+" id="+defaultId+";\n"+
                "\t\t"+entity+" "+entityNameLowerCase+"=new "+entity+"();\n"+
                "\t\tMockito.when(objectMapper.convertValue(Mockito.any(),Mockito.any(Class.class))).thenReturn("+entityNameLowerCase+");\n"+
                "\t\t"+entity+"UpdateRequest request=new "+entity+"UpdateRequest();\n"+
                "\t\t"+entityNameLowerCase+"Service.update(request,id);\n"+
                "\t\tMockito.verify("+entityNameLowerCase+"Repository).save("+entityNameLowerCase+");\n"+
                "\t\tAssertions.assertEquals(id,"+entityNameLowerCase+".getId());\n"+
                "\t}\n"+
                "\t@Test\n"+
                "\tvoid deleteById()\n"+
                "\t{\n"+
                "\t\t"+entityNameLowerCase+"Service.deleteById("+defaultId+");\n"+
                "\t\tMockito.verify("+entityNameLowerCase+"Repository).deleteById(Mockito.any());\n"+
                "\t}\n"+
                "\t@Test\n"+
                "\tvoid findById_returns"+entity+"()\n"+
                "\t{\n"+
                "\t\t"+entity+" "+entityNameLowerCase+"=new "+entity+"();\n"+
                "\t\tMockito.when("+entityNameLowerCase+"Repository.findById(Mockito.any())).thenReturn(Optional.of("+entityNameLowerCase+"));\n"+
                "\t\t"+entity+"DTO "+entityNameLowerCase+"DTO=new "+entity+"DTO();\n"+
                "\t\tMockito.when(objectMapper.convertValue(Mockito.any(),Mockito.any(Class.class))).thenReturn("+entityNameLowerCase+"DTO);\n"+
                "\t\t"+entity+"DTO actual="+entityNameLowerCase+"Service.findById("+defaultId+");\n"+
                "\t\tAssertions.assertEquals("+entityNameLowerCase+"DTO,actual);\n"+
                "\t}\n"+
                "\t@Test\n"+
                "\tvoid findById_throwsEntityNotFoundException()\n"+
                "\t{\n"+
                "\t\tMockito.when("+entityNameLowerCase+"Repository.findById(Mockito.any())).thenReturn(Optional.empty());\n"+
                "\t\tAssertions.assertThrows(EntityNotFoundException.class,()->"+entityNameLowerCase+"Service.findById("+defaultId+"));\n"+
                "\t}\n"+
                "\t@Test\n"+
                "\tvoid findAll()\n"+
                "\t{\n"+
                "\t\tPage<"+entity+"> page=new PageImpl<>(List.of(new "+entity+"()));\n"+
                "\t\tMockito.when("+entityNameLowerCase+"Repository.findAll(Mockito.any(Pageable.class))).thenReturn(page);\n"+
                "\t\t"+entity+"DTO "+entityNameLowerCase+"DTO=new "+entity+"DTO();\n"+
                "\t\tMockito.when(objectMapper.convertValue(Mockito.any(),Mockito.any(Class.class))).thenReturn("+entityNameLowerCase+"DTO);\n"+
                "\t\tPage<"+entity+"DTO> actual="+entityNameLowerCase+"Service.findAll(PageRequest.of(0,20));\n"+
                "\t\tAssertions.assertFalse(actual.getContent().isEmpty());\n"+
                "\t\tAssertions.assertEquals(actual.getTotalElements(),page.getTotalElements());\n"+
                "\t\tAssertions.assertEquals(actual.getPageable(),page.getPageable());\n"+
                "\t}\n"+
                "}";
    }
}