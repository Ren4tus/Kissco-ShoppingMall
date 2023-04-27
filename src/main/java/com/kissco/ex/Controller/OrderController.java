package com.kissco.ex.Controller;

import com.kissco.ex.Controller.dto.ItemForm;
import com.kissco.ex.domain.Order;
import com.kissco.ex.domain.OrderItem;
import com.kissco.ex.domain.OrderSearch;
import com.kissco.ex.domain.Member;
import com.kissco.ex.domain.item.Item;
import com.kissco.ex.service.ItemService;
import com.kissco.ex.service.MemberService;
import com.kissco.ex.service.OrderService;
import com.kissco.ex.user.SiteUserDto;
import com.kissco.ex.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;
    private final UserService userService;

    @GetMapping(value = "/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();
        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }

    @GetMapping(value = "/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch
                                    orderSearch, Model model) {
//        List<Order> orders = orderService.findOrders(orderSearch);
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "order/orderPage";
    }
    @GetMapping(value = "/orders/book")
    public String orderBookList(@ModelAttribute("orderSearch") OrderSearch
                                    orderSearch, Model model) {
        List<Item> items = itemService.findBooks();
        model.addAttribute("items", items);
        return "order/orderPage";
    }
    @GetMapping(value = "/orders/movie")
    public String orderMovieList(@ModelAttribute("orderSearch") OrderSearch
                                        orderSearch, Model model) {
        List<Item> items = itemService.findMovies();
        model.addAttribute("items", items);
        return "order/orderPage";
    }
    @GetMapping(value = "/orders/album")
    public String orderAlbumList(@ModelAttribute("orderSearch") OrderSearch
                                        orderSearch, Model model) {
        List<Item> items = itemService.findAlbums();
        model.addAttribute("items", items);
        return "order/orderPage";
    }
    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
    @PostMapping("/orderDetail")
    public String showOrderDetail(Model model, ItemForm item) {
        model.addAttribute("item", item);
        return "order/orderDetail";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/calculateCart")
    public String calculateCart(@RequestParam("quantity") int quantity,
                                       @RequestParam("itemId") Long itemId,
                                       Principal principal) {
        SiteUserDto siteUserDto = this.userService.getUser(principal.getName());
        orderService.orderItem(siteUserDto, itemId, quantity);
        return "redirect:/orders";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addCart")
    public String addCart(@RequestParam("quantity") int quantity,
                          @RequestParam("itemId") Long itemId,
                          Principal principal, HttpServletRequest request) {
        SiteUserDto siteUserDto = this.userService.getUser(principal.getName());
        HttpSession session = request.getSession();
        int cartCount = orderService.addItemToCart(siteUserDto, itemId, quantity);
        session.setAttribute("cartCount", cartCount);
//        model.addAttribute("cart", siteUserDto.getCart());
        return "redirect:/orders";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cartPage")
    public String cartPage(Model model, Principal principal) {

        List<OrderItem> cart = this.userService.getCart(principal.getName());
        int totalPrice = this.userService.getTotalPrice(principal.getName());
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", totalPrice);
        return "order/cartPage";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/buyCart")
    public String buyCart(Principal principal, HttpServletRequest request) {
        SiteUserDto siteUserDto = this.userService.getUser(principal.getName());
        List<OrderItem> cart = this.userService.getCart(principal.getName());
        orderService.orderItem(siteUserDto, cart);
        HttpSession session = request.getSession();
        session.setAttribute("cartCount", 0);
        return "redirect:/orders";
    }
}