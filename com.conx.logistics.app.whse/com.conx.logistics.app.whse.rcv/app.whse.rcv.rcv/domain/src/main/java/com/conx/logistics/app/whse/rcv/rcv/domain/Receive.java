package com.conx.logistics.app.whse.rcv.rcv.domain;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.conx.logistics.app.tms.domain.types.DROPMODE;
import com.conx.logistics.app.tms.domain.types.TRANSMODE;
import com.conx.logistics.app.whse.domain.warehouse.Warehouse;
import com.conx.logistics.app.whse.rcv.asn.domain.ASN;
import com.conx.logistics.app.whse.rcv.rcv.domain.types.RECEIVESTATUS;
import com.conx.logistics.app.whse.rcv.rcv.domain.types.RECEIVETYPE;
import com.conx.logistics.mdm.domain.MultitenantBaseEntity;
import com.conx.logistics.mdm.domain.commercialrecord.CommercialRecord;
import com.conx.logistics.mdm.domain.geolocation.Address;
import com.conx.logistics.mdm.domain.organization.Organization;
import com.conx.logistics.mdm.domain.product.DimUnit;
import com.conx.logistics.mdm.domain.product.PackUnit;
import com.conx.logistics.mdm.domain.product.WeightUnit;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="whreceive")
public class Receive extends MultitenantBaseEntity {
    @OneToOne(targetEntity = Pickup.class, fetch = FetchType.EAGER)
    @JoinColumn
    private Pickup pickUp;
    
    @OneToOne(targetEntity = DropOff.class, fetch = FetchType.EAGER)
    @JoinColumn
    private DropOff dropOff;
    
    @OneToOne(targetEntity = PackUnit.class, fetch = FetchType.EAGER)
    @JoinColumn
    private PackUnit outerPackUnit;

    @OneToOne(targetEntity = WeightUnit.class, fetch = FetchType.LAZY)
    @JoinColumn
    private WeightUnit weightUnit;

    @OneToOne(targetEntity = DimUnit.class, fetch = FetchType.LAZY)
    @JoinColumn
    private DimUnit dimUnit;

    @OneToOne(targetEntity = DimUnit.class, fetch = FetchType.LAZY)
    @JoinColumn
    private DimUnit volUnit;

    @OneToOne(targetEntity = Organization.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Organization shippedFrom;

    @ManyToOne(targetEntity = Organization.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Organization billTo;

    @ManyToOne(targetEntity = Organization.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Organization consignee;

    @OneToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Address consigneeDocAddress;

    @OneToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Address consigneeDelAddress;

    @ManyToOne(targetEntity = Organization.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Organization consignor;

    @ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Address shipperAddress;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ReceiveLine> rcvLines = new java.util.HashSet<ReceiveLine>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Arrival> arrivals = new java.util.HashSet<Arrival>();

    @OneToOne(targetEntity = CommercialRecord.class, fetch = FetchType.LAZY)
    @JoinColumn
    private CommercialRecord commercialRecord;

    private Integer expectedTotalOuterPackCount;

    private Double expectedTotalweight;

    private Double expectedTotalLen;

    private Double expectedTotalWidth;

    private Double expectedTotalHeight;

    private Double expectedTotalVolume;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date estimatedFirstArrival;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date actualFirstArrival;

    @Enumerated(EnumType.STRING)
    private TRANSMODE mode;

    @Enumerated(EnumType.STRING)
    private RECEIVETYPE rcvType;

    private String asnId;

    @Enumerated(EnumType.STRING)
    private RECEIVESTATUS status;

    private boolean master;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date dateImported;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date dateLastImportUpdated;


    @OneToOne(targetEntity = ASN.class, fetch = FetchType.LAZY)
    @JoinColumn
    private ASN asn;

    /**
     * Booking Info
     */
    @OneToOne(targetEntity = Organization.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Organization bookingBranch;

    @ManyToOne(targetEntity = Warehouse.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Warehouse warehouse;

    private String bookedByUserCode;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date bookingDate;

    private String lastUpdatedByUserCode;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date lastUpdatedDate;

    @Enumerated(EnumType.STRING)
    private DROPMODE dropMode;

	public PackUnit getOuterPackUnit() {
		return outerPackUnit;
	}

	public void setOuterPackUnit(PackUnit outerPackUnit) {
		this.outerPackUnit = outerPackUnit;
	}

	public WeightUnit getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(WeightUnit weightUnit) {
		this.weightUnit = weightUnit;
	}

	public DimUnit getDimUnit() {
		return dimUnit;
	}

	public void setDimUnit(DimUnit dimUnit) {
		this.dimUnit = dimUnit;
	}

	public DimUnit getVolUnit() {
		return volUnit;
	}

	public void setVolUnit(DimUnit volUnit) {
		this.volUnit = volUnit;
	}

	public Organization getShippedFrom() {
		return shippedFrom;
	}

	public void setShippedFrom(Organization shippedFrom) {
		this.shippedFrom = shippedFrom;
	}

	public Organization getBillTo() {
		return billTo;
	}

	public void setBillTo(Organization billTo) {
		this.billTo = billTo;
	}

	public Organization getConsignee() {
		return consignee;
	}

	public void setConsignee(Organization consignee) {
		this.consignee = consignee;
	}

	public Address getConsigneeDocAddress() {
		return consigneeDocAddress;
	}

	public void setConsigneeDocAddress(Address consigneeDocAddress) {
		this.consigneeDocAddress = consigneeDocAddress;
	}

	public Address getConsigneeDelAddress() {
		return consigneeDelAddress;
	}

	public void setConsigneeDelAddress(Address consigneeDelAddress) {
		this.consigneeDelAddress = consigneeDelAddress;
	}

	public Organization getConsignor() {
		return consignor;
	}

	public void setConsignor(Organization consignor) {
		this.consignor = consignor;
	}

	public Address getShipperAddress() {
		return shipperAddress;
	}

	public void setShipperAddress(Address shipperAddress) {
		this.shipperAddress = shipperAddress;
	}

	public Set<ReceiveLine> getRcvLines() {
		return rcvLines;
	}

	public void setRcvLines(Set<ReceiveLine> rcvLines) {
		this.rcvLines = rcvLines;
	}

	public Set<Arrival> getArrivals() {
		return arrivals;
	}

	public void setArrivals(Set<Arrival> arrivals) {
		this.arrivals = arrivals;
	}

	public CommercialRecord getCommercialRecord() {
		return commercialRecord;
	}

	public void setCommercialRecord(CommercialRecord commercialRecord) {
		this.commercialRecord = commercialRecord;
	}

	public Integer getExpectedTotalOuterPackCount() {
		return expectedTotalOuterPackCount;
	}

	public void setExpectedTotalOuterPackCount(Integer expectedTotalOuterPackCount) {
		this.expectedTotalOuterPackCount = expectedTotalOuterPackCount;
	}

	public Double getExpectedTotalweight() {
		return expectedTotalweight;
	}

	public void setExpectedTotalweight(Double expectedTotalweight) {
		this.expectedTotalweight = expectedTotalweight;
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

	public Date getEstimatedFirstArrival() {
		return estimatedFirstArrival;
	}

	public void setEstimatedFirstArrival(Date estimatedFirstArrival) {
		this.estimatedFirstArrival = estimatedFirstArrival;
	}

	public Date getActualFirstArrival() {
		return actualFirstArrival;
	}

	public void setActualFirstArrival(Date actualFirstArrival) {
		this.actualFirstArrival = actualFirstArrival;
	}

	public TRANSMODE getMode() {
		return mode;
	}

	public void setMode(TRANSMODE mode) {
		this.mode = mode;
	}

	public RECEIVETYPE getRcvType() {
		return rcvType;
	}

	public void setRcvType(RECEIVETYPE rcvType) {
		this.rcvType = rcvType;
	}

	public String getAsnId() {
		return asnId;
	}

	public void setAsnId(String asnId) {
		this.asnId = asnId;
	}

	public RECEIVESTATUS getStatus() {
		return status;
	}

	public void setStatus(RECEIVESTATUS status) {
		this.status = status;
	}

	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}

	public Date getDateImported() {
		return dateImported;
	}

	public void setDateImported(Date dateImported) {
		this.dateImported = dateImported;
	}

	public Date getDateLastImportUpdated() {
		return dateLastImportUpdated;
	}

	public void setDateLastImportUpdated(Date dateLastImportUpdated) {
		this.dateLastImportUpdated = dateLastImportUpdated;
	}

	public ASN getAsn() {
		return asn;
	}

	public void setAsn(ASN asn) {
		this.asn = asn;
	}

	public Organization getBookingBranch() {
		return bookingBranch;
	}

	public void setBookingBranch(Organization bookingBranch) {
		this.bookingBranch = bookingBranch;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public String getBookedByUserCode() {
		return bookedByUserCode;
	}

	public void setBookedByUserCode(String bookedByUserCode) {
		this.bookedByUserCode = bookedByUserCode;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getLastUpdatedByUserCode() {
		return lastUpdatedByUserCode;
	}

	public void setLastUpdatedByUserCode(String lastUpdatedByUserCode) {
		this.lastUpdatedByUserCode = lastUpdatedByUserCode;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public DROPMODE getDropMode() {
		return dropMode;
	}

	public void setDropMode(DROPMODE dropMode) {
		this.dropMode = dropMode;
	}

	public Pickup getPickUp() {
		return pickUp;
	}

	public void setPickUp(Pickup pickUp) {
		this.pickUp = pickUp;
	}

	public DropOff getDropOff() {
		return dropOff;
	}

	public void setDropOff(DropOff dropOff) {
		this.dropOff = dropOff;
	}	
}
