package com.kissco.ex.domain;

import com.kissco.ex.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class ItemImage {
    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "item_id")
//    private Item item;
}
