package com.kissco.ex.Controller;

import com.kissco.ex.Controller.dto.ItemForm;
import com.kissco.ex.domain.item.Album;
import com.kissco.ex.domain.item.Book;
import com.kissco.ex.domain.item.Item;
import com.kissco.ex.domain.item.Movie;
import com.kissco.ex.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @GetMapping(value = "/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new ItemForm());
        return "items/createItemForm";
    }
    @PostMapping(value = "/items/new")
    public String create(ItemForm form) {
        Item item = null;
        switch (form.getItemType()) {
            case BOOK:
                item = Book.builder()
                        .name(form.getName())
                        .price(form.getPrice())
                        .stockQuantity(form.getStockQuantity())
                        .author(form.getAuthor())
                        .isbn(form.getIsbn())
                        .build();
                break;
            case ALBUM:
                item = Album.builder()
                        .name(form.getName())
                        .price(form.getPrice())
                        .stockQuantity(form.getStockQuantity())
                        .artist(form.getArtist())
                        .genre(form.getGenre())
                        .build();
                break;
            case MOVIE:
                Movie movie = Movie.builder()
                        .name(form.getName())
                        .price(form.getPrice())
                        .stockQuantity(form.getStockQuantity())
                        .director(form.getDirector())
                        .distributor(form.getDistributor())
                        .build();
                item = movie;
                break;
        }
        try {
            itemService.saveItem(item, form.getImagePath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/items";
    }

    @GetMapping(value = "/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    /**
     * 상품 수정 폼
     */
    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model
            model) {
//        Book item = (Book) itemService.findOne(itemId);
//        BookForm form = new BookForm();
//        form.setId(item.getId());
//        form.setName(item.getName());
//        form.setPrice(item.getPrice());
//        form.setStockQuantity(item.getStockQuantity());
//        form.setAuthor(item.getAuthor());
//        form.setIsbn(item.getIsbn());
//        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    /**
     * 상품 수정
     */
//    @PostMapping(value = "/items/{itemId}/edit")
//    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form")
//    BookForm form) {
//        itemService.updateItem(itemId, form.getName(), form.getPrice(),
//                form.getStockQuantity());
//        return "redirect:/items";
//    }
}