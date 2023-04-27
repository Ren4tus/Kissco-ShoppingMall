package com.kissco.ex.service;

import com.kissco.ex.domain.item.Item;
import com.kissco.ex.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item, MultipartFile file) throws Exception{
        itemRepository.save(item);
//        MultipartFile file =
        if(!file.isEmpty()) {
//            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
//            String projectPath = System.getProperty("user.dir") + "C:/Users/lys/Documents/WEB/files";
            String projectPath = System.getProperty("user.home") + "/Documents/WEB/files";
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);
            item.setImagePath(fileName);
        }
    }
    @Transactional
    public void updateItem(Long id, String name, int price, int stockQuantity)
    {
        Item item = itemRepository.findOne(id);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
    @Transactional
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    public List<Item> findBooks() {
        return itemRepository.findBooks();
    }
    public List<Item> findAlbums() {
        return itemRepository.findAlbums();
    }
    public List<Item> findMovies() {
        return itemRepository.findMovies();
    }
}
