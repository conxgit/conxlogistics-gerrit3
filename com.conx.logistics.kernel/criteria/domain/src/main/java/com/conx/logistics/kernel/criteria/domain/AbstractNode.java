package com.conx.logistics.kernel.criteria.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class AbstractNode implements Serializable {
	@Transient
	private final CriteriaBuilderImpl criteriaBuilder;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date dateCreated = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date dateLastUpdated = new Date();	

	public AbstractNode(CriteriaBuilderImpl criteriaBuilder) {
		this.criteriaBuilder = criteriaBuilder;
	}

	/**
	 * Provides access to the underlying {@link CriteriaBuilderImpl}.
	 *
	 * @return The underlying {@link CriteriaBuilderImpl} instance.
	 */
	public CriteriaBuilderImpl criteriaBuilder() {
		return criteriaBuilder;
	}
}
