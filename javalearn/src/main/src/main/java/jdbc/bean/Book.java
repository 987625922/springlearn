package jdbc.bean;

import jdbc.annotation.Column;
import jdbc.annotation.Id;
import jdbc.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table("book")
public class Book {

    @Id
    @Column("id")
    private Integer book_id;

    @Column("name")
    private String name;

    @Column("number")
    private String number;


}
