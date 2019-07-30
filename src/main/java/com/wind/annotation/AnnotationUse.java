package com.wind.annotation;

@Annotation(desc = "is class",author = "is author",age = 10)
public class AnnotationUse {

    @Annotation(desc = "is method",author = "is author",age = 10)
    public String color(){
        return "red";
    }
}
