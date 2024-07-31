package hello.servlet.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Member {

    private Long id;
    private String username;
    private int age;

    public Member(String name, int age) {
        this.username = name;
        this.age = age;
    }
}
