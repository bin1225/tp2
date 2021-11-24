package com.example.tp2.Controller.Login;


import com.example.tp2.Service.LoginService;
import com.example.tp2.Service.MemberService;
import com.example.tp2.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HelloController {

    private final MemberService memberService;
    private final LoginService loginRepository;


    @GetMapping("/")
    public String login_main(Model model){
        model.addAttribute("member",new Member());
        return "sign/login";
    }

    @GetMapping("/login")
    public String login_main_2(Model model){
        model.addAttribute("member",new Member());
        return "sign/login";
    }

    @PostMapping("/main")
    public String login(Member member, HttpServletResponse response) {
        try {
            String userId = member.getUserId();
            String password = member.getPassword();

            String id = String.valueOf(member.getId());
            log.info("3");
            if (loginRepository.login(userId, password)) {
                log.info("로그인 성공1");
                Cookie idCookie = new Cookie("id",id);
                response.addCookie(idCookie);
                //쿠키로 로그인 상태 유지
                log.info("로그인 성공2");
                return "main";
            } else {
                log.info("로그인 실패-----------------");
                return "sign/login";
            }
        } catch (NullPointerException e) {
            log.info("널포");
            return "sign/login";
        }



    }
}
