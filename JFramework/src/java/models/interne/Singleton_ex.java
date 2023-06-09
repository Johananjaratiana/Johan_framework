package models.interne;

import annotation_J.Scope;
import annotation_J.Url;

@Scope(SingleTon = true)
public class Singleton_ex
{
    int count = 0;

    @Url(class_method = "Singleton_ex-count")
    public void count()
    {
        this.count += 1;
        System.out.println(this.count + "------------------- Singleton counter -----------------");
    }
}
