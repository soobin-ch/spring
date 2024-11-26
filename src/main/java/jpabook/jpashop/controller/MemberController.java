package jpabook.jpashop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Admin.adminLoginForm;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.servicce.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createMember(Model model, HttpServletRequest request) {

        //1. admin인지 아닌지 검증
        HttpSession session = request.getSession(false);

        if(session == null) {
            model.addAttribute("adminLoginForm", new adminLoginForm());
            return "login/AdminLoginForm";
        }

        //2. admin 맞으면 아래 진행

        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";

    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {

        if(result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String List(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if(session == null) {
            model.addAttribute("adminLoginForm", new adminLoginForm());
            return  "redirect:/login";
        }else {


            List<Member> members = memberService.findMembers();
            List<MemberForm> memberDtoList = members.stream().map(MemberForm::new).collect(Collectors.toList());

            model.addAttribute("members", memberDtoList);
            return "members/memberList";
        }
   }

}
