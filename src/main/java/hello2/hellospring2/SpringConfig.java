package hello2.hellospring2;

import hello2.hellospring2.aop.TimeTraceAop;
import hello2.hellospring2.domain.Member;
import hello2.hellospring2.repository.*;
import hello2.hellospring2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// Configuration을 읽고 스프링빈을 등록하라는 뜻이네 라고 스프링이 인식
@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Bean // 스프링 빈을 등록하겠다
    public MemberService memberService(){
        // 멤버서비스를 이 로직을 호출해 빈에 등록해줌
        return new MemberService(memberRepository); // 생성자에 멤버 리포지토리 넣어줘야 함
    }

    // Aop 빈에 등록
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

//    // 멤버 리포지토리 추가
//    @Bean
//    public MemberRepository memberRepository(){
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
