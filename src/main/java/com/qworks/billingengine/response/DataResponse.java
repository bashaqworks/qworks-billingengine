package com.qworks.billingengine.response;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse implements Serializable {
	private static final long serialVersionUID = -4745089942536263216L;
	private String status = "SUCCESS";
	private Object data;
	private String message;
}
