package hello.hellospring.service;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired //생성자 하나인 경우에는생략 가능.
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }
/* em사용시에만
    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }
     */

    /* dataSource 사용시에만
        private DataSource dataSource;

        @Autowired
        public SpringConfig(DataSource dataSource) {
            this.dataSource = dataSource;
        }
    *   /

        /*    스프링이 뜰 때 memberService, memberRepository를 둘 다 스프링 빈에 등록함.
            스프링 빈에 등록되어있는 memberRepository를 memberService에 넣어줌.*/
    /*
    @Bean //스프링 빈에 등록할거야!
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    */
    /*
    @Bean
    public MemberRepository memberRepository(){

//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource); //의존성 주입
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
    }
    */

 /*   @Bean //AOP는 사용한다는 것이 명확하게 보이면 좋음.
    public TimeTraceAop TimeTraceAop(){
        return new TimeTraceAop();
    }*/

}
