package jpabook.jpashop.servicce;

import jpabook.jpashop.domain.Admin.Admin;
import jpabook.jpashop.domain.Admin.adminLoginMember;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {


    private final MemberRepository memberRepository;

    public Admin login(String loginId, String password) {

        Optional<Admin> findMember = memberRepository.findByLoginId(loginId);

        return findMember.filter(a -> a.getAdminPassword().equals(password)).orElse(null);



    }


    @Transactional
    public void  save(Admin admin) {
         memberRepository.saveAdmin(admin);
    }

}
