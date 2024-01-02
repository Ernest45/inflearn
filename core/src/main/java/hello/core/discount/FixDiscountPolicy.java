package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 무조건 천원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { // Q)  == 쓰는 게 맞냐 ?           A) enum 타입은 == 쓰는 게 맞다!
            return discountFixAmount; // 할인정책에서 값 빼지 않고, order에서 처리함으로 1000원만 리턴
        } else {
            return 0;
        }
    }
}
