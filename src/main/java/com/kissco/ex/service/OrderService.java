package com.kissco.ex.service;

import com.kissco.ex.domain.*;
import com.kissco.ex.domain.item.Item;
import com.kissco.ex.repository.ItemRepository;
import com.kissco.ex.repository.MemberRepository;
import com.kissco.ex.repository.OrderRepository;
import com.kissco.ex.user.SiteUser;
import com.kissco.ex.user.SiteUserDto;
import com.kissco.ex.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    /** 주문 */
//    @Transactional
//    public Long order(Long memberId, Long itemId, int count) {
//        //엔티티 조회
//        Member member = memberRepository.findOne(memberId);
//        Item item = itemRepository.findOne(itemId);
//        //배송정보 생성
//        Delivery delivery = new Delivery();
//        delivery.setAddress(member.getAddress());
//        delivery.setStatus(DeliveryStatus.READY);
//        //주문상품 생성
//        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),
//                count);
//        //주문 생성
//        Order order = Order.createOrder(member, delivery, orderItem);
//        //주문 저장
//        orderRepository.save(order);
//        return order.getId();
//    }
    @Transactional
    public Long orderItem(SiteUserDto user, Long itemId, int amount) {
        //엔티티 조회
        Optional<SiteUser> userData = userRepository.findByusername(user.getUsername());
        Item item = itemRepository.findOne(itemId);
        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(userData.get().getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), amount);
        //주문 생성
        Order order = Order.createOrder(userData.get(), delivery, orderItem);
        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }
    @Transactional
    public int addItemToCart(SiteUserDto user, Long itemId, int amount) {
        //엔티티 조회
        Optional<SiteUser> userData = userRepository.findByusername(user.getUsername());
        Item item = itemRepository.findOne(itemId);
        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), amount);

        //주문 생성
        userData.get().addCartItem(orderItem);
        userRepository.save(userData.get());

        return userData.get().getCartCount();
    }
    @Transactional
    public void orderItem(SiteUserDto user, List<OrderItem> cart){
        Optional<SiteUser> userData = userRepository.findByusername(user.getUsername());

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(userData.get().getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        //주문 생성
        for(OrderItem orderItem : cart) {
            Order order = Order.createOrder(userData.get(), delivery, orderItem);
            //주문 저장
            orderRepository.save(order);
        }
        userData.get().getCartItems().clear();
        userRepository.save(userData.get());
    }
    /** 주문 취소 */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }
    /** 주문 검색 */

     public List<Order> findOrders(OrderSearch orderSearch) {
         return orderRepository.findAllByString(orderSearch);
     }

}
