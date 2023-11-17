package hello.core.member;

public interface MemberRepository {

    void save(Member member); // 회원을 저장하는 기능 (역할)

    Member findById(Long memberId); // 호원의 아이디를 찾느 기능 (역할)
}
