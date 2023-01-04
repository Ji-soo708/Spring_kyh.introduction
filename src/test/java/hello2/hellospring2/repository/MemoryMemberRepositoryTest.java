package hello2.hellospring2.repository;

import hello2.hellospring2.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
//given
        // 저장이 되는지 테스트
        Member member = new Member();
        member.setName("spring");
//when
        repository.save(member);
//then
        Member result = repository.findById(member.getId()).get(); // 꺼내고
        // System.out.println("result = "+ (result == member));
        assertThat(result).isEqualTo(member); // result랑 member랑 같은지
        // 같으면 녹색불, 다르면 빨간불 들어옴
    }

    @Test
    public void findByName() {
//given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1); // 멤버1 저장

        Member member2 = new Member(); //shift+F6 누르면 한번에 멤버2로 바꾸기 가능
        member2.setName("spring2");
        repository.save(member2);
//when
        Member result = repository.findByName("spring1").get();

//then
        assertThat(result).isEqualTo(member1); // result랑 멤버 1이랑 같은지
    }
    @Test
    public void findAll() {
//given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
//when
        List<Member> result = repository.findAll();
//then
        assertThat(result.size()).isEqualTo(2); // size는 2개
    }


}