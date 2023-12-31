package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component // 기본이 첫 글자 소문자 memoryMemberRepository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // 전에 말했듯이 concurrent 해쉬맵을 써야 한다 ! 동시성 문제
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

}
