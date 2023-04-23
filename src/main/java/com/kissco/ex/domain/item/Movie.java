package com.kissco.ex.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@SuperBuilder
public class Movie extends Item
{
    private String director;
    private String distributor;
}
