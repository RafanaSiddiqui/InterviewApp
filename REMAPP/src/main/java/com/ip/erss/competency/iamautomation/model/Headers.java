package com.ip.erss.competency.iamautomation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HEADERS")
public class Headers {
	
	@Id
	 @GeneratedValue
	 @Column(name="HEADER_ID")
	private Long headerId;
	 @Column(name="KEY")
	private String key;
	 @Column(name="VALUE")
 	private String value;
 	//@ManyToOne(cascade=CascadeType.ALL)
   //@JoinColumn(name ="API_REQUEST_ID")
 	
 	//private ApiRequest apiRequest;
	
	public Headers() {
		
	}
	public Headers(Long headerId, String key, String value,
			ApiRequest apiRequest) {
		super();
		this.headerId = headerId;
		this.key = key;
		this.value = value;
		
	}
	
	public Long getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	@Override
		public String toString() {
			return "Headers [headerId=" + headerId + ", key=" + key + ", value="
					+ value + "]";
		}

	

}
