package org.sbbs.base.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * @author Administrator
 *
 * @param <T>
 * @param <PK>
 */
/**
 * @author Administrator
 * 
 * @param <T>
 * @param <PK>
 */
@MappedSuperclass
public abstract class BaseTreeNode<T extends BaseTreeNode, PK extends Serializable>
		extends BaseObject {
	/**
	 *
	 */
	private PK id;
	/**
	 *
	 */
	private T parentNode;
	/**
	 *
	 */
	private int lft;
	/**
	 *
	 */
	private int rgt;
	/**
	 *
	 */
	private int level;

	private Boolean disabled = false;

	/**
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public  PK getId() {
		return id;
	}

	/**
	 * @param nodeId
	 */
	public  void setId(final PK nodeId) {
		this.id = nodeId;
	}

	/**
	 * @return
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id", nullable = true)
	public  T getParentNode() {
		return parentNode;
	}

	/**
	 * @param parentNode
	 */
	public  void setParentNode(final T parentNode) {
		this.parentNode = parentNode;
	}

	/**
	 * @return
	 */
	@Column(name = "lft", columnDefinition = "int default 1")
	public  int getLft() {
		return lft;
	}

	/**
	 * @param lft
	 */
	public  void setLft(final int lft) {
		this.lft = lft;
	}

	/**
	 * @return
	 */
	@Column(name = "rgt", columnDefinition = "int default 1")
	public  int getRgt() {
		return rgt;
	}

	/**
	 * @param rgt
	 */
	public  void setRgt(final int rgt) {
		this.rgt = rgt;
	}

	/**
	 * @return
	 */
	@Column(name = "level", columnDefinition = "int default 0")
	public  int getLevel() {
		return level;
	}

	/**
	 * @param level
	 */
	public  void setLevel(final int level) {
		this.level = level;
	}

	@Column(name = "disabled")
	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return
	 */
	@Transient
	public  String getParent() {
		if (this.getParentNode() != null) {
			return this.getParentNode().getId() + "";
		} else {
			return null;
		}
	}

	/**
	 * @return
	 */
	@Transient
	public  boolean getIsLeaf() {
		return (this.getRgt() == (this.getLft() + 1)) ? true : false;
	}

	/**
	 * @return
	 */
	@Transient
	public  boolean getIsParent() {
		/* return (this.getRgt() == (this.getLft() + 1)) ? false : true; */
		return !this.getIsLeaf();
	}
}
