package jpabook.jpashop.servicce;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.ItemSaleStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.CategoryRepository;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ItemRepository itemRepository;


    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void  findAll() {
        Category book = new Category();
        book.setName("책");
        book.setParent(null);
        em.persist(book);


        Category assay = new Category();
        assay.setName("에세이");
        assay.setParent(book);
        assay.setChild(null);
        em.persist(assay);

        Book jpa = new Book();
        jpa.builder().name("jpa").price(10000).stockQuantity(20).itemSaleStatusEnum(ItemSaleStatus.SALE).build();

        em.persist(jpa);

        book.getItems().add(jpa);
        assay.getItems().add(jpa);


        List<Category> categories = categoryService.findAll();

        Assertions.assertEquals(categories.contains(assay), true, "assay 카테고리가 존재");
        Assertions.assertEquals(categories.contains(book), true, "북 카테고리가 존재합니다.");


        List<Item> categoryItem = itemRepository.findByCategoryId(assay.getId());
        Assertions.assertEquals(categoryItem.size(),1);

    }


}