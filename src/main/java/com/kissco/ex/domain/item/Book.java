package com.kissco.ex.domain.item;

import com.kissco.ex.Controller.dto.ItemForm;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@SuperBuilder
public class Book extends Item
{
    private String author;
    private String isbn;


}
