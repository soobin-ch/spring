package jpabook.jpashop.servicce;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }



    public Category findOne(Long categoryId) {
        return categoryRepository.findOne(categoryId);
    }





}
