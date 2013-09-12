package org.sbbs.demo.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.sbbs.base.model.BaseObject;



@Entity
@Table(name = "GL_accountType")
//@IdClass(OrgCopyPk.class)
public class AccountType extends BaseObject implements Serializable {

	private OrgCopyPk orgCopyPk;
	
/*	private String orgId;
	private String copyCode;
	private String accouttypecode;*/
	private String accounttype;
	

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "orgId",
		column = @Column(name="OrgId")),
		@AttributeOverride(name = "copyCode",
		column = @Column(name="CopyCode")),
		@AttributeOverride(name = "accouttypecode",
		column = @Column(name="Accouttypecode"))
		})
	public OrgCopyPk getOrgCopyPk() {
		return orgCopyPk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accounttype == null) ? 0 : accounttype.hashCode());
		result = prime * result
				+ ((orgCopyPk == null) ? 0 : orgCopyPk.hashCode());
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
		AccountType other = (AccountType) obj;
		if (accounttype == null) {
			if (other.accounttype != null)
				return false;
		} else if (!accounttype.equals(other.accounttype))
			return false;
		if (orgCopyPk == null) {
			if (other.orgCopyPk != null)
				return false;
		} else if (!orgCopyPk.equals(other.orgCopyPk))
			return false;
		return true;
	}

	public void setOrgCopyPk(OrgCopyPk orgCopyPk) {
		this.orgCopyPk = orgCopyPk;
	}
	
	@Transient
	public String getOrgId() {
		return this.orgCopyPk.getOrgId();
	}

	public void setOrgId(String orgId) {
		this.orgCopyPk.setOrgId(orgId);
	}

	@Transient
	public String getCopyCode() {
		return this.orgCopyPk.getCopyCode();
	}

	public void setCopyCode(String copyCode) {
		this.orgCopyPk.setCopyCode(copyCode);
	}

	@Transient
	public String getAccouttypecode() {
		return this.orgCopyPk.getAccouttypecode();
	}

	public void setAccouttypecode(String accouttypecode) {
		this.orgCopyPk.setAccouttypecode(accouttypecode);
	}

	@Column(name = "Accounttype")
	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	@Override
	public String toString() {
		return "AccountType [orgCopyPk=" + orgCopyPk + ", accounttype="
				+ accounttype + "]";
	}

	public AccountType(){}


	
	

}
