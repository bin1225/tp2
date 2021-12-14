package com.example.tp2.Controller.Member;


import com.example.tp2.Service.LoginService;
import com.example.tp2.Service.MemberService;
import com.example.tp2.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

        private final MemberService memberService;
        private final LoginService loginRepository;


        @PostConstruct
        public void createMember(){
                Member member = new Member();
                member.setUserId("123");
                member.setPassword("123");
                member.setName("kimgyubin");
                memberService.join(member);
        }

        @GetMapping("/signin")
        public String signForm(Model model){
                model.addAttribute("member",new Member());
                return "/sign/signin";
        }

        @PostMapping("/members/new")
        public String regist(@ModelAttribute Member member, BindingResult bindingResult) {


                //검증
                log.info(member.getName());
                log.info(member.getUserId());
                log.info(member.getPassword());
                if (member.getName().length() == 0 || member.getUserId().length()==0 || member.getPassword().length() == 0)
                {
                        bindingResult.addError(new ObjectError("member","값을 모두 입력하십시오."));
                }
                if(memberService.findByUserId(member.getUserId()).isPresent()){
                        bindingResult.addError(new FieldError("member","userId",member.getUserId(),false,null,null,"이미 중복된 아이디가 존재합니다."));
                        log.info("userId={}",member.getUserId());
                }
                if(member.getPassword().length()<8){
                        bindingResult.addError(new FieldError("member","password",member.getPassword(),false,null,null,"비밀번호는 8자리 이상이어야 합니다. "));
                }




                if(bindingResult.hasErrors())
                {
                        log.info("errors={}",bindingResult);

                        return "/sign/signin";
                }


                memberService.join(member);
                return "sign/login";
        }


        @GetMapping("/members/list")
        public String memberList(Model model){
                List<Member> members = memberService.getMemberList();
                model.addAttribute("members",members);
                return "members/memberList";
        }
}
