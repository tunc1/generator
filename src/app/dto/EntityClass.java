package app.dto;

public class EntityClass
{
    private String className;
    private String idType;
    public EntityClass(String className,String idType)
    {
        this.className=className;
        this.idType=idType;
    }
    public String className()
    {
        return className;
    }
    public String idType()
    {
        return idType;
    }
}