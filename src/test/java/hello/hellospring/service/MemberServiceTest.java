package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){ //테스트 실행 전에 각각의 인스턴스가 생김.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given : 무언가가 주어졌을 때
        Member member = new Member();
        member.setName("hello");
        //when : 이거를 실행했을 때
        Long saveId = memberService.join(member);

        //then : 결과가 이게 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//오른쪽의 로직이 실행될 때 왼쪽의 예외가 터져야 해
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //assertThrows에서 메시지를 뽑아서 메시지가 같은지 검증.
        /*try{
            memberService.join(member1);
            fail(); //예외가 터지지 않아서 실패.
        }catch(IllegalStateException e){
            //예외가 터져서 정상적으로 실행.
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}