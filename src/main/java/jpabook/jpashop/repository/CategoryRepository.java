package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class).getResultList();
    }


    public Category findOne(Long categoryId) {
        return em.createQuery("select c from Category c where c.id =:categoryId", Category.class)
                .setParameter("categoryId", categoryId).getSingleResult();
    }
}
