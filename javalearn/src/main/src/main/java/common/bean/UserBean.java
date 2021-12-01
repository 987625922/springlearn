package common.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserBean {
    private Book book;
    private String beanName;

    public void setBook(Book book) {
        this.book = book;
    }

    public UserBean(String beanName){
        this.beanName = beanName;
    }

}
