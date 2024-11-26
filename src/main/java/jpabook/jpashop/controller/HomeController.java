package jpabook.jpashop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jpabook.jpashop.domain.Admin.Admin;
import jpabook.jpashop.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home";
    }


    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if(session == null) {
            return "home";
        }

       Admin admin = (Admin)session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(admin == null) {
            return "home";
        }


        model.addAttribute("admin", admin);

        return "AdminPage";

    }


}
