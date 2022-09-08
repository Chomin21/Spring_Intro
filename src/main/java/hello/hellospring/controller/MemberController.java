package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//컨트롤러쪽은 어쩔 수 없음. 애너테이션 사용 필수.
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired //스프링이 스프링 컨테이너에 있는 memberService를 가져다가 연결을 시켜줌. (DI)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
