package com.example.tp2.Controller.Login;


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



            if(member.getUserId()==""){
                bindingResult.addError(new ObjectError("member","아이디를 입력하십시오."));
                return "sign/login";
            }
            if(member.getPassword()==""){
                bindingResult.addError(new ObjectError("member","비밀번호를 입력하십시오."));
            return "sign/login";
            }
            String userId = member.getUserId();
            String password = member.getPassword();
            Optional<Member> byUserId = memberService.findByUserId(userId);
            if(!byUserId.isPresent()){
                bindingResult.addError(new FieldError("member","userId",member.getUserId(),false,null,null,"아이디가 잘못되었습니다."));
            }
            if (!loginRepository.login(userId, password)){
                bindingResult.addError(new FieldError("member","password",member.getPassword(),false,null,null,"비밀번호가 잘못되었습니다."));
            }
            if(bindingResult.hasErrors())
            {
                return "sign/login";
            }

            String id = String.valueOf(byUserId.get().getId());
            if (loginRepository.login(userId, password)) {
                Cookie idCookie = new Cookie("memberId",id);
                response.addCookie(idCookie);
                //쿠키로 로그인 상태 유지
                return "redirect:/main";
            }
            return "sign/login";
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
