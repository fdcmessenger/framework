package org.sbbs.demo.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.sbbs.base.model.BaseObject;


@Entity
@Table(name = "t_demo_entity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DemoEntity extends BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 5295152954297131636L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long demoId;

	/**
	 */
	@Column(name = "intField", nullable = false)
	private int intField;

	@Column(name = "longField", nullable = false)
	private long longField;

	private short shortField;

	private byte byteField;

	private boolean booleanField;

	private char charField;

	private float floatField;

	private double doubleField;

	/**
	 */
	private Integer intObjField;

	private Long longObjField;

	private Short shortObjField;

	private Byte byteObjField;

	private Boolean booleanObjField;

	private Character charObjField;

	private Float floatObjField;

	private Double doubleObjField;

	/**
	 */
	private Date dateField;

	private String stringField;

	private BigDecimal bigDecimalField;

	private Timestamp timestampField = new Timestamp(System.currentTimeMillis());

	/*
	 * private Calendar calendarField; private Locale localeField; private
	 * TimeZone timeZoneField; private Currency currencyField;
	 */

	/**
	 */

	public Long getDemoId() {
		return demoId;
	}

	/**
	 */
	public void setDemoId(Long demoId) {
		this.demoId = demoId;
	}

	@Column(name = "bigDecimalField")
	public BigDecimal getBigDecimalField() {
		return this.bigDecimalField;
	}

	public void setBigDecimalField(BigDecimal bigDecimalField) {
		this.bigDecimalField = bigDecimalField;
	}

	@Column(name = "booleanField", nullable = false)
	public boolean isBooleanField() {
		return this.booleanField;
	}

	public void setBooleanField(boolean booleanField) {
		this.booleanField = booleanField;
	}

	@Column(name = "booleanObjField", nullable = false)
	public Boolean getBooleanObjField() {
		return this.booleanObjField;
	}

	public void setBooleanObjField(Boolean booleanObjField) {
		this.booleanObjField = booleanObjField;
	}

	@Column(name = "byteField", nullable = false)
	public byte getByteField() {
		return this.byteField;
	}

	public void setByteField(byte byteField) {
		this.byteField = byteField;
	}

	@Column(name = "byteObjField", nullable = false)
	public Byte getByteObjField() {
		return this.byteObjField;
	}

	public void setByteObjField(Byte byteObjField) {
		this.byteObjField = byteObjField;
	}

	@Column(name = "charField", nullable = false, length = 1)
	public char getCharField() {
		return this.charField;
	}

	public void setCharField(char charField) {
		this.charField = charField;
	}

	@Column(name = "charObjField", length = 1, nullable = false)
	public Character getCharObjField() {
		return this.charObjField;
	}

	public void setCharObjField(Character charObjField) {
		this.charObjField = charObjField;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateField", length = 19, nullable = false)
	public Date getDateField() {
		if (dateField != null) {

			Date a = (Date) dateField.clone();

			return a;
		}
		return null;

	}

	public void setDateField(final Date dateField) {
		this.dateField = (Date) dateField.clone();
	}

	@Column(name = "doubleField", nullable = false, precision = 22, scale = 0)
	public double getDoubleField() {
		return this.doubleField;
	}

	public void setDoubleField(double doubleField) {
		this.doubleField = doubleField;
	}

	@Column(name = "doubleObjField", precision = 22, scale = 0, nullable = false)
	public Double getDoubleObjField() {
		return this.doubleObjField;
	}

	public void setDoubleObjField(Double doubleObjField) {
		this.doubleObjField = doubleObjField;
	}

	@Column(name = "floatField", nullable = false, precision = 12, scale = 0)
	public float getFloatField() {
		return this.floatField;
	}

	public void setFloatField(float floatField) {
		this.floatField = floatField;
	}

	@Column(name = "floatObjField", precision = 12, scale = 0, nullable = false)
	public Float getFloatObjField() {
		return this.floatObjField;
	}

	public void setFloatObjField(Float floatObjField) {
		this.floatObjField = floatObjField;
	}

	// @Max( 100 )
	// @Min( 0 )
	public int getIntField() {
		return this.intField;
	}

	public void setIntField(int intField) {
		this.intField = intField;
	}

	@Column(name = "intObjField")
	public Integer getIntObjField() {
		return this.intObjField;
	}

	public void setIntObjField(Integer intObjField) {
		this.intObjField = intObjField;
	}

	public Long getLongField() {
		return this.longField;
	}

	public void setLongField(Long longField) {
		this.longField = longField;
	}

	@Column(name = "longObjField")
	public Long getLongObjField() {
		return this.longObjField;
	}

	public void setLongObjField(Long longObjField) {
		this.longObjField = longObjField;
	}

	@Column(name = "shortField", nullable = false)
	public short getShortField() {
		return this.shortField;
	}

	public void setShortField(short shortField) {
		this.shortField = shortField;
	}

	@Column(name = "shortObjField")
	public Short getShortObjField() {
		return this.shortObjField;
	}

	public void setShortObjField(Short shortObjField) {
		this.shortObjField = shortObjField;
	}

	// @NotNull
	// @Size( min = 2, max = 500, message =
	// "size must be between {min} and {max}" )
	@Column(name = "stringField", length = 50, nullable = false)
	public String getStringField() {
		return this.stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestampField", length = 19, nullable = false)
	public Timestamp getTimestampField() {
		if (timestampField != null) {
			Timestamp a = (Timestamp) timestampField.clone();
			return a;
		} else
			return null;
	}

	public void setTimestampField(final Timestamp timestampField) {
		this.timestampField = (Timestamp) timestampField.clone();
	}

	@Override
	public String toString() {
		return "DemoEntity [demoId=" + demoId + ", intField=" + intField
				+ ", longField=" + longField + ", shortField=" + shortField
				+ ", byteField=" + byteField + ", booleanField=" + booleanField
				+ ", charField=" + charField + ", floatField=" + floatField
				+ ", doubleField=" + doubleField + ", intObjField="
				+ intObjField + ", longObjField=" + longObjField
				+ ", shortObjField=" + shortObjField + ", byteObjField="
				+ byteObjField + ", booleanObjField=" + booleanObjField
				+ ", charObjField=" + charObjField + ", floatObjField="
				+ floatObjField + ", doubleObjField=" + doubleObjField
				+ ", dateField=" + dateField + ", stringField=" + stringField
				+ ", bigDecimalField=" + bigDecimalField + ", timestampField="
				+ timestampField + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemoEntity other = (DemoEntity) obj;
		if (bigDecimalField == null) {
			if (other.bigDecimalField != null)
				return false;
		} else if (!bigDecimalField.equals(other.bigDecimalField))
			return false;
		if (booleanField != other.booleanField)
			return false;
		if (booleanObjField == null) {
			if (other.booleanObjField != null)
				return false;
		} else if (!booleanObjField.equals(other.booleanObjField))
			return false;
		if (byteField != other.byteField)
			return false;
		if (byteObjField == null) {
			if (other.byteObjField != null)
				return false;
		} else if (!byteObjField.equals(other.byteObjField))
			return false;
		if (charField != other.charField)
			return false;
		if (charObjField == null) {
			if (other.charObjField != null)
				return false;
		} else if (!charObjField.equals(other.charObjField))
			return false;
		if (dateField == null) {
			if (other.dateField != null)
				return false;
		} else if (!dateField.equals(other.dateField))
			return false;
		if (demoId == null) {
			if (other.demoId != null)
				return false;
		} else if (!demoId.equals(other.demoId))
			return false;
		if (Double.doubleToLongBits(doubleField) != Double
				.doubleToLongBits(other.doubleField))
			return false;
		if (doubleObjField == null) {
			if (other.doubleObjField != null)
				return false;
		} else if (!doubleObjField.equals(other.doubleObjField))
			return false;
		if (Float.floatToIntBits(floatField) != Float
				.floatToIntBits(other.floatField))
			return false;
		if (floatObjField == null) {
			if (other.floatObjField != null)
				return false;
		} else if (!floatObjField.equals(other.floatObjField))
			return false;
		if (intField != other.intField)
			return false;
		if (intObjField == null) {
			if (other.intObjField != null)
				return false;
		} else if (!intObjField.equals(other.intObjField))
			return false;
		if (longField != other.longField)
			return false;
		if (longObjField == null) {
			if (other.longObjField != null)
				return false;
		} else if (!longObjField.equals(other.longObjField))
			return false;
		if (shortField != other.shortField)
			return false;
		if (shortObjField == null) {
			if (other.shortObjField != null)
				return false;
		} else if (!shortObjField.equals(other.shortObjField))
			return false;
		if (stringField == null) {
			if (other.stringField != null)
				return false;
		} else if (!stringField.equals(other.stringField))
			return false;
		if (timestampField == null) {
			if (other.timestampField != null)
				return false;
		} else if (!timestampField.equals(other.timestampField))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bigDecimalField == null) ? 0 : bigDecimalField.hashCode());
		result = prime * result + (booleanField ? 1231 : 1237);
		result = prime * result
				+ ((booleanObjField == null) ? 0 : booleanObjField.hashCode());
		result = prime * result + byteField;
		result = prime * result
				+ ((byteObjField == null) ? 0 : byteObjField.hashCode());
		result = prime * result + charField;
		result = prime * result
				+ ((charObjField == null) ? 0 : charObjField.hashCode());
		result = prime * result
				+ ((dateField == null) ? 0 : dateField.hashCode());
		result = prime * result + ((demoId == null) ? 0 : demoId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(doubleField);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((doubleObjField == null) ? 0 : doubleObjField.hashCode());
		result = prime * result + Float.floatToIntBits(floatField);
		result = prime * result
				+ ((floatObjField == null) ? 0 : floatObjField.hashCode());
		result = prime * result + intField;
		result = prime * result
				+ ((intObjField == null) ? 0 : intObjField.hashCode());
		result = prime * result + (int) (longField ^ (longField >>> 32));
		result = prime * result
				+ ((longObjField == null) ? 0 : longObjField.hashCode());
		result = prime * result + shortField;
		result = prime * result
				+ ((shortObjField == null) ? 0 : shortObjField.hashCode());
		result = prime * result
				+ ((stringField == null) ? 0 : stringField.hashCode());
		result = prime * result
				+ ((timestampField == null) ? 0 : timestampField.hashCode());
		return result;
	}

}
