package com.conx.logistics.app.whse.rcv.rcv.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.conx.logistics.app.whse.rcv.rcv.domain.types.RECEIVELINESTATUS;
import com.conx.logistics.mdm.domain.MultitenantBaseEntity;
import com.conx.logistics.mdm.domain.product.Product;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="whreceiveline")
public class ReceiveLine extends MultitenantBaseEntity {

    @OneToOne(targetEntity = Receive.class, fetch = FetchType.EAGER)
    @JoinColumn
    private Receive parentReceive;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn
    private Product product;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ReceiveLineStockItemSet> rlStockItems = new java.util.HashSet<ReceiveLineStockItemSet>();

    private Integer lineNumber;

    private Integer arrivedInnerPackCount;

    private Integer arrivedOuterPackCount;

    private Double arrivedWeight;

    private Double arrivedTotalLen;

    private Double arrivedTotalWidth;

    private Double arrivedTotalHeight;

    private Double arrivedTotalVolume;

    private Integer expectedInnerPackCount;

    private Integer expectedOuterPackCount;

    private Double expectedTotalWeight;

    private Double expectedTotalLen;

    private Double expectedTotalWidth;

    private Double expectedTotalHeight;

    private Double expectedTotalVolume;

    @Enumerated(EnumType.STRING)
    private RECEIVELINESTATUS status;

	public Receive getParentReceive() {
		return parentReceive;
	}

	public void setParentReceive(Receive parentReceive) {
		this.parentReceive = parentReceive;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Set<ReceiveLineStockItemSet> getRlStockItems() {
		return rlStockItems;
	}

	public void setRlStockItems(Set<ReceiveLineStockItemSet> rlStockItems) {
		this.rlStockItems = rlStockItems;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Integer getArrivedInnerPackCount() {
		return arrivedInnerPackCount;
	}

	public void setArrivedInnerPackCount(Integer arrivedInnerPackCount) {
		this.arrivedInnerPackCount = arrivedInnerPackCount;
	}

	public Integer getArrivedOuterPackCount() {
		return arrivedOuterPackCount;
	}

	public void setArrivedOuterPackCount(Integer arrivedOuterPackCount) {
		this.arrivedOuterPackCount = arrivedOuterPackCount;
	}

	public Double getArrivedWeight() {
		return arrivedWeight;
	}

	public void setArrivedWeight(Double arrivedWeight) {
		this.arrivedWeight = arrivedWeight;
	}

	public Double getArrivedTotalLen() {
		return arrivedTotalLen;
	}

	public void setArrivedTotalLen(Double arrivedTotalLen) {
		this.arrivedTotalLen = arrivedTotalLen;
	}

	public Double getArrivedTotalWidth() {
		return arrivedTotalWidth;
	}

	public void setArrivedTotalWidth(Double arrivedTotalWidth) {
		this.arrivedTotalWidth = arrivedTotalWidth;
	}

	public Double getArrivedTotalHeight() {
		return arrivedTotalHeight;
	}

	public void setArrivedTotalHeight(Double arrivedTotalHeight) {
		this.arrivedTotalHeight = arrivedTotalHeight;
	}

	public Double getArrivedTotalVolume() {
		return arrivedTotalVolume;
	}

	public void setArrivedTotalVolume(Double arrivedTotalVolume) {
		this.arrivedTotalVolume = arrivedTotalVolume;
	}

	public Integer getExpectedInnerPackCount() {
		return expectedInnerPackCount;
	}

	public void setExpectedInnerPackCount(Integer expectedInnerPackCount) {
		this.expectedInnerPackCount = expectedInnerPackCount;
	}

	public Integer getExpectedOuterPackCount() {
		return expectedOuterPackCount;
	}

	public void setExpectedOuterPackCount(Integer expectedOuterPackCount) {
		this.expectedOuterPackCount = expectedOuterPackCount;
	}

	public Double getExpectedTotalWeight() {
		return expectedTotalWeight;
	}

	public void setExpectedTotalWeight(Double expectedTotalWeight) {
		this.expectedTotalWeight = expectedTotalWeight;
	}

	public Double getExpectedTotalLen() {
		return expectedTotalLen;
	}

	public void setExpectedTotalLen(Double expectedTotalLen) {
		this.expectedTotalLen = expectedTotalLen;
	}

	public Double getExpectedTotalWidth() {
		return expectedTotalWidth;
	}

	public void setExpectedTotalWidth(Double expectedTotalWidth) {
		this.expectedTotalWidth = expectedTotalWidth;
	}

	public Double getExpectedTotalHeight() {
		return expectedTotalHeight;
	}

	public void setExpectedTotalHeight(Double expectedTotalHeight) {
		this.expectedTotalHeight = expectedTotalHeight;
	}

	public Double getExpectedTotalVolume() {
		return expectedTotalVolume;
	}

	public void setExpectedTotalVolume(Double expectedTotalVolume) {
		this.expectedTotalVolume = expectedTotalVolume;
	}

	public RECEIVELINESTATUS getStatus() {
		return status;
	}

	public void setStatus(RECEIVELINESTATUS status) {
		this.status = status;
	}
    
}