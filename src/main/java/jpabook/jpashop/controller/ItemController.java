package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.servicce.ItemService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new ItemForm());
        return "items/createItemform";

    }
    @PostMapping("/items/new")
    public String create(ItemForm form) {
        Book book = createItem(form);

        itemService.saveItem(book);
        return "redirect:/";
    }

    private Book createItem(ItemForm form) {

      return Book.builder()
                .id(form.getId())
                .name(form.getItemName())
                .price(form.getPrice())
                .stockQuantity(form.getStockQuantity())
                .categories(form.getItemCategory())
                .build();
    }

    @GetMapping("/items")
    public String List(Model model) {
        List<Item> items = itemService.findItems();

        model.addAttribute("items",items);
        return "items/itemList";


    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
       Book findBook = (Book)itemService.findOne(itemId);

        ItemForm form = new ItemForm();


        findBook.builder()
                .id(form.getId())
                        .name(form.getItemName())
                        .price(form.getPrice())
                        .categories(form.getItemCategory())
                        .itemSaleStatusEnum(form.getSaleStatusEnum()).build();

        model.addAttribute("form", findBook);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId ,@ModelAttribute("form") ItemForm form) {

        itemService.updateItem(itemId, form.getItemName(), form.getPrice(), form.getStockQuantity() );

        return "redirect:/items";
    }


}
