package com.example.tp2.Controller.Login;


import com.example.tp2.Service.LoginService;
import com.example.tp2.Service.MemberService;
import com.example.tp2.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

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
    public String login(Member member, BindingResult bindingResult, HttpServletResponse response) {
        try {
            String userId = member.getUserId();
            String password = member.getPassword();
            Optional<Member> byUserId = memberService.findByUserId(userId);
            String id = String.valueOf(byUserId.get().getId());
            log.info("아아앙");
            log.info(id);
            if (loginRepository.login(userId, password)) {
                log.info("로그인 성공1");
                Cookie idCookie = new Cookie("memberId",id);
                response.addCookie(idCookie);
                //쿠키로 로그인 상태 유지
                log.info("로그인 성공2");
                return "redirect:/main";
            } else {
                log.info("로그인 실패-----------------");
                bindingResult.reject("loginFail", "아이디 비밀번호가 맞지 않습니다.");
                return "sign/login";
            }
        } catch (NullPointerException e) {
            return "sign/login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response,"memberId");
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName){
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        log.info("쿠키 지우기------------------------");
    }

}
