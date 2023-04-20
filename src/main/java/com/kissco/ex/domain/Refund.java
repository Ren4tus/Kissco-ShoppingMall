package com.kissco.ex.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Refund {
    @Id
    @GeneratedValue
    @Column(name = "refund_id")
    private Long id;
    @Column(name = "reg_date")
    private LocalDateTime refundRequestDate;
    @Column(name = "refund_date")
    private LocalDateTime refundEndDate;

    @OneToOne(mappedBy = "refund",fetch = LAZY)
    Order order;
}
