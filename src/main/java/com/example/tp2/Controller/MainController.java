package com.example.tp2.Controller;

import com.example.tp2.Repository.Member.MemberRepository;
import com.example.tp2.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {


    private final MemberRepository memberRepository;

    @GetMapping("/about")
    public String show_about_page(){
        return "about";
    }

    @GetMapping("/myPage")
    public String show_myPage(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        Cookie c = cookies[0];

        Long id = Long.valueOf(c.getValue());
        Member member = memberRepository.findOne(id);

        model.addAttribute(member);

        return "";
    }
}
