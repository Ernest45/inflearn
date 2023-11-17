package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy DiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void vip_o() { // vip는 잘 적용되어야 한다고 o 라고 적음 ㅋㅋ

        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = DiscountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000); // static import를 사용한 것 ! (자바 기본 문법이다)
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {

        //given
        Member member = new Member(2L, "memberVIP", Grade.BASIC);

        //when
        int discount = DiscountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(0);

    }
}