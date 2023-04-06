package com.kissco.ex.service;

import com.kissco.ex.domain.Address;
import com.kissco.ex.domain.Member;
import com.kissco.ex.domain.Order;
import com.kissco.ex.domain.OrderStatus;
import com.kissco.ex.domain.item.Book;
import com.kissco.ex.domain.item.Item;
import com.kissco.ex.execption.NotEnoughStockException;
import com.kissco.ex.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @PersistenceContext
    EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Test
    public void itemOrder() throws Exception {
        //Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
        int orderCount = 2;
        //When
        Long orderId = orderService.order(member.getId(), item.getId(),
                orderCount);
        //Then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("상품 주문시 상태는 ORDER",OrderStatus.ORDER,
                getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.",1,
                getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * 2,
                getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.",8, item.getStockQuantity());
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }
}