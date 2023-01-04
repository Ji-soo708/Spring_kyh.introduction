package hello2.hellospring2.controller;

import hello2.hellospring2.domain.Member;
import hello2.hellospring2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
// 스프링 컨테이너란 통이 생기는데 @Controller가 있으면 MemberController 객체를 생성해 스프링에 넣어두고 스프링이 관리
// -> 이걸 보고 스프링 컨테이너에서 스프링 빈이 관리된다~~
public class MemberController {
    private final MemberService memberService;

    @Autowired // 스프링 컨테이너에서 멤버 서비스 가져옴
    // 생성자 호출
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /// 등록

    // home.html에서 회원가입 누르면 /members/new 로 이동
    @GetMapping(value = "/members/new") // @GetMapping: 조회할 때 사용
    public String createForm() {
        return "members/createMemberForm"; // 여기로 이동하자~
    }

    @PostMapping(value = "/members/new") // @PostMapping: 데이터를 폼 같은데에서 넣어서 전달할 때 사용(등록)
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName()); // form에서 getName

        //System.out.println("member = "+ member.getName());

        memberService.join(member); // .join 해서 멤버를 넘김
        return "redirect:/"; // 회원가입 끝내면 홈화면으로 보냄
    }

    /// 조회

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); // 멤버를 다 끄집어냄
        model.addAttribute("members", members); // 멤버리스트를 모델에다 다 받아서 넘길거임
        return "members/memberList"; // 멤버리스트.html 만들자~
    }
}
