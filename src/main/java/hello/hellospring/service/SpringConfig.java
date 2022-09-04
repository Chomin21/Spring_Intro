package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

/*    스프링이 뜰 때 memberService, memberRepository를 둘 다 스프링 빈에 등록함.
    스프링 빈에 등록되어있는 memberRepository를 memberService에 넣어줌.*/
    @Bean //스프링 빈에 등록할거야!
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}