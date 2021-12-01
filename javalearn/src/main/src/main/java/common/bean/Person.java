package common.bean;

import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class Person {
    String name;
    String number;

    public void init() {
        System.out.println("bean的初始化");
    }

    public void destroy() {
        System.out.println("bean的销毁");
    }
}
