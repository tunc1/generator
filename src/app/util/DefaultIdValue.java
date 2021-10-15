package app.util;

import app.consts.Consts;

public class DefaultIdValue
{
    private static final String[] values={"1L","1","\"id\"","1"};
    public static String get(String type)
    {
        int index=-1;
        for(int i=0;i<values.length;i++)
        {
            if(type.equals(Consts.idTypes[i]))
                index=i;
        }
        return values[index];
    }
}