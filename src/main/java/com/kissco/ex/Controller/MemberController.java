package com.kissco.ex.Controller;

import com.kissco.ex.Controller.dto.UserOrderForm;
import com.kissco.ex.domain.Address;
import com.kissco.ex.domain.Member;
import com.kissco.ex.domain.Order;
import com.kissco.ex.domain.OrderItem;
import com.kissco.ex.domain.item.Item;
import com.kissco.ex.service.MemberService;
import com.kissco.ex.user.SiteUser;
import com.kissco.ex.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final UserService userService;

//    @GetMapping(value = "/members/new")
//    public String createForm(Model model) {
//        model.addAttribute("memberForm", new MemberForm());
//        return "members/createMemberForm";
//    }
//    @PostMapping(value = "/members/new")
//    public String create(@Valid MemberForm form, BindingResult result) {
//        if (result.hasErrors()) {
//            return "members/createMemberForm";
//        }
//        Address address = new Address(form.getCity(), form.getStreet(),
//                form.getZipcode());
//        Member member = new Member();
//        member.setName(form.getName());
//        member.setAddress(address);
//        memberService.join(member);
//        return "redirect:/";
//    }

    @GetMapping(value = "/orderList")
    public String list(Model model) {
        List<UserOrderForm> orderList = new ArrayList<>();
        List<SiteUser> users = userService.findAllUsers();
        for(SiteUser user : users)
        {
        	for(Order userOrder : user.getOrders())
            {
                for(OrderItem item : userOrder.getOrderItems())
                {
                    UserOrderForm order = new UserOrderForm();
                    order.setId(userOrder.getId());
                    order.setUsername(user.getUsername());
                    order.setProductName(item.getItem().getName());
                    order.setOrderCount(item.getCount());
                    order.setTotalPrice(item.getTotalPrice());
                    orderList.add(order);
                }
            }
        }
        model.addAttribute("orders", orderList);
        return "members/memberList";
    }
}