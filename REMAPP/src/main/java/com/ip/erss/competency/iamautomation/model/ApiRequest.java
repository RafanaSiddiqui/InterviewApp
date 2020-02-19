package com.ip.erss.competency.iamautomation.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "api_request")
public class ApiRequest {
	
    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;
    @Column(name="APINAME")
    private String apiName;
    @Column(name="DESC")
    private String desc;
   	@Column(name="ENDPOINTURL")
    private String endPointUrl;
	
	//@OneToMany(cascade=CascadeType.ALL,mappedBy ="apiRequest")
	//@JsonProperty("headerList")
   	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
   	@JoinColumn(name = "ID", referencedColumnName = "ID")
   	@JsonProperty("headerList")
   	private List<Headers> headerList;
	   public ApiRequest() {
    }
	
	public ApiRequest(Long id, String apiName, String desc, String endPointUrl,
			List<Headers> headerList) {
		super();
		this.id = id;
		this.apiName = apiName;
		this.desc = desc;
		this.endPointUrl = endPointUrl;
		this.headerList = headerList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getEndPointUrl() {
		return endPointUrl;
	}
	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}
	
	
	public List<Headers> getHeaderList() {
		return headerList;
	}
	public void setHeaderList(List<Headers> headerList) {
		this.headerList = headerList;
	}
	@Override
	public String toString() {
		return "ApiRequest [id=" + id + ", apiName=" + apiName + ", desc="
				+ desc + ", endPointUrl=" + endPointUrl + "]";
	}

    
  
    
   
}
