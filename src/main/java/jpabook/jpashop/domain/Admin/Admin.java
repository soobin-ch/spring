package jpabook.jpashop.domain.Admin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue
    private Long id;


    private String name;
    private String adminId;
    private String adminPassword;

    public Admin(String name , String adminId, String adminPassword) {

        this.name = name;
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }
}
