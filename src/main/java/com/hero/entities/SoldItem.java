package com.hero.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "sold_items")
@JsonIgnoreProperties("salesOrder")
public class SoldItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sold_item_id")
    private Long soldItemId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "salesorder_id")
    private SalesOrder salesOrder;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "rate")
    private BigDecimal rate;


//    @Override
//    public String toString() {
//        return "SoldItem{" +
//                "soldItemId=" + soldItemId +
//                ", itemId=" + itemId +
//                ", quality=" + quality +
//                ", rate=" + rate +
//                '}';
//    }
}
