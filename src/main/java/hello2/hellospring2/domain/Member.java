package hello2.hellospring2.domain;
import javax.persistence.*;

@Entity
public class Member {
    // 요구사항에 따라

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //db에 값을 넣으면 db가 id를 자동으로 생성 -> Identity
    private Long id; // 아이디 식별자
    private String name; // 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}