package lalalalz.springdatajpa;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DITest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);

    @Test
    void test1() {
        MemberService memberService = (MemberService) ac.getBean("memberService");

        memberService.callHello();
    }
}
