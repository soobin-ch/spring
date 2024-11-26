package jpabook.jpashop.servicce;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;


    @Test
    public void 상품주문() {

        //given

        Member member = createMember();

       Item book = createBook("시골 Jpa", 10000, 10);


        //when
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품주문시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(),"주문한 상품 종류 수가 정확해야 한다");
        assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문 가격은 * 수량이다");
        assertEquals(8, book.getStockQuantity(),"주문 수량만큼 재고가 줄어야 한다");


    }

    private Item createBook(String name, int price, int stockQuantity) {
        Item book =  Book.builder().name(name).price(price).stockQuantity(stockQuantity).build();
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member= new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강가","123-123"));

        em.persist(member);
        return member;
    }

    @Test
    public void 상품주문_재고수량초과() {
        //given
        Member member = createMember();
        Item item = createBook("시골 Jpa", 10000, 10);
        int orderCount = 11;

        //when

        //then

        Assertions.assertThatThrownBy(()-> orderService.order(member.getId(), item.getId(), orderCount))
                .isInstanceOf(NotEnoughStockException.class);



    }


    @Test
    public void 주문취소() throws Exception {
        //given
      Member member = createMember();
        Item itembook = createBook("시골 JPA", 10000, 10);

        int orderCount=2;
        Long orderId = orderService.order(member.getId(), itembook.getId(), orderCount);
        //when

        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
      assertEquals(OrderStatus.CANCEL, getOrder.getStatus(),"주문 취소시 상태는 CANCEL 입니다.");
      assertEquals(10, itembook.getStockQuantity(),"주문 취소시 주문수만큼 재고 증가해야함");
    }
}