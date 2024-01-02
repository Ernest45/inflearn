package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final을 파라미터로 받는 생성자 만들어줌
public class OrderServiceImpl implements OrderService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 바뀐 정책으로 서비스 영역에서 바꿔야 한다는 문제점이 있다. 1. 그래서 밑에 코드로 변경함!


    private final MemberRepository memberRepository;

    private final DiscountPolicy discountPolicy;



    //  2. 이대로 바꾸면 구현체가 아닌 인터페이스기에 discountPolicy에 구체 값이 없기에 NPE에러가 남.
    //  3. 그래서 따로 생성자 만들어주고, appconfig를 통해 생성자를 가져다 쓰면 DIP 지키게 됨 위에 보면 인터페이스만 의존


    // 세터 주입
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }

    // new orderserivceImp(MemberRepo memberRepo, DiscountPolicy disCountPolicy);

    public OrderServiceImpl(MemberRepository memberRepository,
                             @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
