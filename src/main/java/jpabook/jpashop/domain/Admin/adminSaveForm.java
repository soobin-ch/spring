package jpabook.jpashop.domain.Admin;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class adminSaveForm {

    @NotEmpty
    private String name;
    @NotEmpty
    private String adminId;
    @NotEmpty
    private String adminPassword;


    public adminSaveForm(String name, String adminId, String adminPassword) {

        this.name = name;
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }
}
