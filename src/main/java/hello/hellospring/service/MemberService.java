package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    //MemberService와 test쪽의 memberRepository가 각각 생기는 문제가 있어서 이렇게 해결.
    //DI(의존성 주입)
    //MemberService를 생성할 때, 스프링이 컨테이너에 등록하면서 이 생성자를 호출함.
    //memberRepository가 필요한 것을 보고 스프링 컨테이너에 있는 memberRepository를 넣어줌.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원X.

        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { //회원을 이름으로 찾은 뒤 result로 반환받음. result가 있다면 예외 발생시킴.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        Optional로 감싸면, Optional안에 Member객체가 있는 것.
        Optional을 통해 여러 메서드 사용 가능.
        요즘은 null일 가능성이 있으면 Optional로 감싸서 반환해줌.
        result.orElseGet() //값이 있으면 꺼내고, 값이 없으면 디폴트 값을 넣어서 꺼내 라는 것을 씀. result.get()은 권장X.
*/

        validateDuplicateMember(member); //중복 회원 검증.

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { //길어져서 함수로 extract.
        // Optional사용하는 것이 이쁘지 않고, 바로 반환하는 것이 좋지 않음. 권장방식은 아래.
        memberRepository.findByName(member.getName()) //결과가 Optional member.
                .ifPresent(m -> { //회원을 이름으로 찾은 뒤 result로 반환받음. result가 있다면 예외 발생시킴.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
