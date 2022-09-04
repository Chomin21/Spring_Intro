package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트가 끝날때마다 repository가 깔끔하게 지워지는 동작.
    @AfterEach //메서드 하나가 끝날때마다 이 함수를 실행하겠디.(콜백함수)
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){ //저장이 제대로 되는지 확인.
        Member member = new Member();
        member.setName("spring");

        repository.save(member); //내가 저장한 정보.
        Member result = repository.findById(member.getId()).get(); //optional에서는 get으로 바로 꺼낼 수 있음.
//        System.out.println("(result == member) = " + (result == member)); 저장한 값과 꺼낸 값이 같은지 확인.
//        Assertions.assertEquals(member, result); // 두 요소가 같은지 확인 가능. 기대하는 값, 실제 값.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1); //회원 1 저장.

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); //회원 2 저장.

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }
    
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1); //회원 1 저장.

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); //회원 2 저장.

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
