package hello2.hellospring2.repository;

import hello2.hellospring2.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

//@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); // 저장할 거
    private static long sequence = 0L; // sequence: 0, 1, 2 이런 키 값 생성해줌
    @Override
    public Member save(Member member) {
        // 멤버를 저장할 때 먼저 시퀀스 값 하나 올려주고(아이디 세팅)
        member.setId(++sequence);
        // 스토어에 저장
        store.put(member.getId(), member);
        return member; // 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 스토어에서 꺼내는데
        //return store.get(id);
        // 널이 반환될 가능성이 있으면 Oprional로 감쌈
        return Optional.ofNullable(store.get(id)); // 널이어도 반환 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                // 돌리면서 파라미터에 들어온 네임값하고 같은지 비교
                .filter(member -> member.getName().equals(name))
                // 같으면 필터링 되고 찾아서 반환
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 리스트로~
    }

    public void clearStore() {
        store.clear();
    }


}
