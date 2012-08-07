package com.conx.logistics.app.whse.rcv.rcv.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.conx.logistics.mdm.domain.MultitenantBaseEntity;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="whreceivelinestockitemset")
public class ReceiveLineStockItemSet extends MultitenantBaseEntity {
    private int matchedCount;

    @OneToOne(targetEntity = ReceiveLine.class, fetch = FetchType.EAGER)
    @JoinColumn
    private ReceiveLine receiveLine;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<StockItem> stockItems = new java.util.HashSet<StockItem>();
}