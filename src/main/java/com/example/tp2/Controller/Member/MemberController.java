package com.example.tp2.Controller.Member;


import com.example.tp2.Service.LoginService;
import com.example.tp2.Service.MemberService;
import com.example.tp2.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        public String regist(Member member) {

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
