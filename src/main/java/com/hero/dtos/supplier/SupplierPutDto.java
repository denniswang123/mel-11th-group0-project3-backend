package com.hero.dtos.supplier;

import com.hero.entities.PurchaseOrder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class SupplierPutDto {
    private Long id;
    private String supplierName;
    private String companyName;
    private String website;
    private Boolean active;
    private String salutation;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date createdTime;
    private Date lastModifiedTime;
    private String comments;
    private Set<PurchaseOrder> purchaseOrders;
}
