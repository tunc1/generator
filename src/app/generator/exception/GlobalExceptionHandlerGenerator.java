package app.generator.exception;

public class GlobalExceptionHandlerGenerator extends ExceptionClassGenerator
{
    public String generate(String basePackage)
    {
        return "package "+basePackage+".exception;\n"+
                "\n"+
                "import jakarta.persistence.EntityNotFoundException;\n"+
                "import org.springframework.http.HttpStatus;\n"+
                "import org.springframework.web.bind.annotation.ExceptionHandler;\n"+
                "import org.springframework.web.bind.annotation.ResponseStatus;\n"+
                "import org.springframework.web.bind.annotation.RestControllerAdvice;\n"+
                "\n"+
                "@RestControllerAdvice\n"+
                "public class GlobalExceptionHandler\n"+
                "{\n"+
                "    @ExceptionHandler(value={EntityNotFoundException.class})\n"+
                "    @ResponseStatus(value=HttpStatus.NOT_FOUND)\n"+
                "    public ExceptionResponse entityNotFoundExceptionHandler()\n"+
                "    {\n"+
                "        return new ExceptionResponse(\"No Record Found by this id\");\n"+
                "    }\n"+
                "}";
    }
}