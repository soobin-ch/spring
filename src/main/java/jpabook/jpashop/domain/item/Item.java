package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.ItemSaleStatus;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {


    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private Enum<ItemSaleStatus> itemSaleStatusEnum;


    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();




    public void changeItemData(String itemName, int itemPrice , int itemStockQuantity) {
        this.name = itemName;
        this.price = itemPrice;
        this.stockQuantity = itemStockQuantity;

    }
    public void addStock(int quantity) {
        stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;

        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;

    }
}

