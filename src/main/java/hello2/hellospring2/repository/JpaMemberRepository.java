package hello2.hellospring2.repository;

import hello2.hellospring2.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // Jpa는 EntityManager로 돌아감

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist: 영구 저장
        return member;
    } // 이렇게 하면 jpa가 insert query 다 만들어서 db에 집어넣고 member에 setid까지 해줌

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // id 조회, 식별자 pk값 넣어줌
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // 객체(entity)를 대상으로 쿼리를 날림
        // Member.class로 조회할거임
        // sql은 m.id 하고 막 그랬었음
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery(
                "select m from Member m where m.name = :name", Member.class)
            .setParameter("name", name)
            .getResultList();
        return result.stream().findAny();
    }
}
