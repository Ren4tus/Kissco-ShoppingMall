package com.kissco.ex.Controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemForm {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String content;
    private MultipartFile imagePath;
    private String imageName;
    private ItemType itemType;
    private String author;
    private String isbn;
    private String artist;
    private String genre;
    private String director;
    private String distributor;
    public enum ItemType {
        ALBUM, BOOK, MOVIE;
    }

}