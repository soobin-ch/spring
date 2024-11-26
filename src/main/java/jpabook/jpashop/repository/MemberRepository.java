package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Admin.Admin;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Admin.adminLoginMember;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);

    }
    public Member findOne(Long id) {
        return em.find(Member.class, id);

    }

    public Optional<Admin> findByLoginId(String loginId) {
        return findAllLoginMember().stream().filter(a-> a.getAdminId().equals(loginId)).findFirst();
    }


    public List<Admin> findAllLoginMember() {
        return em.createQuery("select a from Admin a", Admin.class).getResultList();
    }

    public List<Member> findAll() {
       return  em.createQuery("select m from Member m", Member.class).getResultList();

    }
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
    }

    public void saveAdmin(Admin admin) {
        em.persist(admin);
    }


}
