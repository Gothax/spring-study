package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NoteEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;


    @DisplayName("상품주문")
    @Test
    public void 상품주문() {
        // given
        Member member = createMember();
        Item book = createBook("시골 jpa", 10000, 10);
        // when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        // then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals( OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다");
        assertEquals(getOrder.getTotalPrice(), orderCount*book.getPrice());
        assertEquals(8, book.getStockQuantity(), "주문하면 재고가 빠져야한다");

    }


    @DisplayName("주문취소")
    @Test
    public void 주문취소() {
        // given
        Member member = createMember();
        Item book = createBook("시골 jpa", 10000, 10);
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        // when
        orderService.cancelOrder(orderId);
        // then
        Order order = orderRepository.findOne(orderId);
        assertEquals(order.getStatus(), OrderStatus.CANCEL, "주문을 취소하면 status가 CANCEL이 되어야 한다");
        assertEquals(10, book.getStockQuantity(), "주문을 취소하면 다시 원래 개수로 돌아와야 한다");
    }

    @DisplayName("상품주문 재고수량 초과")
    @Test(expected = NoteEnoughStockException.class)
    public void moreThanStock() throws Exception{
        // given
        Member member = createMember();
        Item book = createBook("시골 jpa", 10000, 10);
        int orderCount = 11;
        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        // then
        fail("재고 수량 부족 예외가 발생해야 한다");
    }


    private Item createBook(String name, int price, int quantity) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "테헤란로", "124-142"));
        em.persist(member);
        return member;
    }

}