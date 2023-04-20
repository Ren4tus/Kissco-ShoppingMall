package com.kissco.ex.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Exchange {
    @Id
    @GeneratedValue
    @Column(name = "exchange_id")
    private Long id;
    @Column(name = "reg_date")
    private LocalDateTime exchangeRequestDate;
    @Column(name = "exchange_date")
    private LocalDateTime exchangeEndDate;

    @OneToOne(mappedBy = "exchange",fetch = LAZY)
    Order order;
}
