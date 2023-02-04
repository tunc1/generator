package app.generator.entity;

import app.dto.EntityClass;

public abstract class ClassGenerator
{
    public abstract String generate(EntityClass entity,String basePackage,String entityPackage);
}