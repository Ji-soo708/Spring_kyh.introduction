package hello2.hellospring2.repository;

import hello2.hellospring2.domain.Member;
import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member); // 회원을 저장하면 저장된 회원을 반환
    // Optional: findBy~~ 할 때 없으면 null 그대로 반환하는 것보다 optional? 로 처리해 반환
    Optional<Member> findById(Long id); // 아이디로 회원을 찾음
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 모든 회원 리스트를 다 반환
}
