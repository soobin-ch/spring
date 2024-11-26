package jpabook.jpashop.controller;


import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.ItemSaleStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemForm {

    private Long id;
    /*
    상품수정이 있으므로 아이디값이 존재해야함
     */
    private String itemName;
    private int price;
    private int stockQuantity;
    private List<Category> itemCategory;

    private Enum<ItemSaleStatus> saleStatusEnum;



}
