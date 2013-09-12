package org.sbbs.demo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class OrgCopyPk implements Serializable {
	
	
	private String orgId;
	private String copyCode;
	private String accouttypecode;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCopyCode() {
		return copyCode;
	}

	public void setCopyCode(String copyCode) {
		this.copyCode = copyCode;
	}
	
	public String getAccouttypecode() {
		return accouttypecode;
	}

	public void setAccouttypecode(String accouttypecode) {
		this.accouttypecode = accouttypecode;
	}

	public OrgCopyPk(){}
	
	public OrgCopyPk(String orgId,String copyCode,String accouttypecode){
		this.orgId = orgId;
		this.copyCode = copyCode;
		this.accouttypecode = accouttypecode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accouttypecode == null) ? 0 : accouttypecode.hashCode());
		result = prime * result
				+ ((copyCode == null) ? 0 : copyCode.hashCode());
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrgCopyPk other = (OrgCopyPk) obj;
		if (accouttypecode == null) {
			if (other.accouttypecode != null)
				return false;
		} else if (!accouttypecode.equals(other.accouttypecode))
			return false;
		if (copyCode == null) {
			if (other.copyCode != null)
				return false;
		} else if (!copyCode.equals(other.copyCode))
			return false;
		if (orgId == null) {
			if (other.orgId != null)
				return false;
		} else if (!orgId.equals(other.orgId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrgCopyPk [orgId=" + orgId + ", copyCode=" + copyCode
				+ ", accouttypecode=" + accouttypecode + "]";
	}

	
	
}
