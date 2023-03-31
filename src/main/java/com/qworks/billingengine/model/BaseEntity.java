package com.qworks.billingengine.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -887950424957439557L;
	private String description;
	private boolean active = Boolean.TRUE;
	private String createdby;
	private String lastmodifiedby;
	private Long createddate;
	private Long lastmodifieddate;
}
