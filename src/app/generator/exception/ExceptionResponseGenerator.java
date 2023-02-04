package app.generator.exception;

public class ExceptionResponseGenerator extends ExceptionClassGenerator
{
    public String generate(String basePackage)
    {
        return "package "+basePackage+".exception;\n"+
                "\n"+
                "public class ExceptionResponse\n"+
                "{\n"+
                "    private String error;\n"+
                "    public ExceptionResponse(String error)\n"+
                "    {\n"+
                "        this.error=error;\n"+
                "    }\n"+
                "    public String getError()\n"+
                "    {\n"+
                "        return error;\n"+
                "    }\n"+
                "}";
    }
}