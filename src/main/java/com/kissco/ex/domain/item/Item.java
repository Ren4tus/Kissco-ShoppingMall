package com.kissco.ex.domain.item;

import com.kissco.ex.Controller.dto.ItemForm;
import com.kissco.ex.domain.Category;
import com.kissco.ex.domain.ItemImage;
import com.kissco.ex.execption.NotEnoughStockException;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    private String content;

    @Column(name = "reg_date")
    private LocalDateTime regDate;
    private String imagePath;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();
    //==비즈니스 로직==//

    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0)
        {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
