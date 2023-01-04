package hello2.hellospring2.service;

import hello2.hellospring2.domain.Member;
import hello2.hellospring2.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 동작하기 전에 넣어줌
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // 멤버 리포지토리를 만들고 이걸 멤버서비스에 넣어줌
        // 그리고 사용
        // 멤버 서비스 입장에서는 직접 리포지토리 만드는 게 아니라 외부에서 넣어줌 -> DI
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();  // 돌때마다 메모리 클리어
    }

    @Test
    public void 회원가입() throws Exception {
        //Given 이게 주어지면
        Member member = new Member();
        member.setName("hello");

        //When 이거 할 때
        Long saveId = memberService.join(member);

        //Then 결과가 이게 나와야 한다
        Member findMember = memberRepository.findById(saveId).get(); // 아이디 넘김
        assertThat(member.getName()).isEqualTo(findMember.getName()); // 멤버 이름과 찾은 게 같냐
        //assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);
        //memberService.join(member2);

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 메시지 만듦
//        }

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 메시지 만듦듦
    }
}