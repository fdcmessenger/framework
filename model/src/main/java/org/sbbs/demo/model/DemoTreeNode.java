package org.sbbs.demo.model;

/* 
 org.sbbs.app.demo.model.DemoTreeNode
 *  */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.sbbs.base.model.BaseTreeNode;

@Entity
@Table(name = "t_demo_tree")
public class DemoTreeNode extends BaseTreeNode<DemoTreeNode, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7954621631250429549L;
	private String nodeName;

	@Override
	public String toString() {
		return "DemoTreeNode [nodeName=" + nodeName + "]";
	}

	@Column(name = "nodeName", length = 50, nullable = false)
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemoTreeNode other = (DemoTreeNode) obj;
		if (nodeName == null) {
			if (other.nodeName != null)
				return false;
		} else if (!nodeName.equals(other.nodeName))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nodeName == null) ? 0 : nodeName.hashCode());
		return result;
	}

}
