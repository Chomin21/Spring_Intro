package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//컨트롤러쪽은 어쩔 수 없음. 애너테이션 사용 필수.
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired //스프링이 스프링 컨테이너에 있는 memberService를 가져다가 연결을 시켜줌. (DI)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){ //얘는 화면을 띄워주는 역할만 함.
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //정보를 등록하는 것은 PostMapping
    public String create (MemberForm form){
        Member member= new Member();
        member.setName(form.getName()); //form.getName()하면 form에서 입력한 정보 받아옴.

        System.out.println("member.getName() = " + member.getName());
        
        memberService.join(member); //join을 하면 member를 넘겨줌.

        return "redirect:/"; //회원가입 끝나면 홈으로 리다이렉트.
    }

    @GetMapping("/members")
    public String list (Model model){
        List<Member> members = memberService.findMembers(); //저장된 멤버들을 모두 불러옴.
        model.addAttribute("members",members); //멤버 전체를 모델에 담아서 화면에 넘김.
        return "members/memberList";
    }
}
