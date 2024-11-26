package jpabook.jpashop.domain.Admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class adminLoginForm {

    private String adminId;
    private String adminPassword;

    public adminLoginForm(String adminId, String adminPassword) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }
}
