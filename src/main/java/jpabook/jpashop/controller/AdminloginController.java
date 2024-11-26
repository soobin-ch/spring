package jpabook.jpashop.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jpabook.jpashop.domain.Admin.Admin;
import jpabook.jpashop.domain.Admin.adminLoginForm;
import jpabook.jpashop.domain.Admin.adminLoginMember;
import jpabook.jpashop.domain.Admin.adminSaveForm;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.servicce.AdminService;
import jpabook.jpashop.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminloginController {


    private final AdminService adminService;

    @GetMapping("/save")
    public String saveForm(Model model) {

        adminSaveForm form = new adminSaveForm();
        model.addAttribute("adminSaveForm", form);

        return "login/AdminSaveForm";
    }


    @PostMapping("/save")
    public String saveForm(@Valid @ModelAttribute adminSaveForm form, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "login/AdminSaveForm";
        }

        Admin admin = new Admin();
        admin.setName(form.getName());
        admin.setAdminId(form.getAdminId());
        admin.setAdminPassword(form.getAdminPassword());

       adminService.save(admin);


        return "redirect:/";
    }


    @GetMapping("/login")
    public String login(@ModelAttribute("adminLoginForm") adminLoginForm form) {
        return "login/AdminLoginForm";
    }



    @PostMapping("/login")
    public String login(@Valid @ModelAttribute adminLoginForm form, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/AdminLoginForm";
        }

        Admin admin = adminService.login(form.getAdminId(), form.getAdminPassword());

        if (admin == null) {
            bindingResult.reject("AdminLoginfail", "관리자 아이디 및 비밀번호가 맞지 않습니다.");


            return "login/AdminLoginForm";

        }


        HttpSession session = request.getSession();

        session.setAttribute(SessionConst.LOGIN_MEMBER,admin);


        return "redirect:/members";
    }



    @GetMapping("/logout")
    public String logoutV3(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
