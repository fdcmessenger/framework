package org.sbbs.base.model;

import java.io.Serializable;

/**
 * 实体类基类,所有的实体对象都应该实现如下方法: toString(), equals() and hashCode().
 */
public abstract class BaseObject implements Serializable {

	/**
	 * 返回一个多行的字符串,它以 key=value 的形式呈现.
	 * */
	public abstract String toString();

	/**
	 * 比较对象是否相等.当使用Hibernate时,主键不应该称为比较的一部分.
	 */
	public abstract boolean equals(Object o);

	/**
	 * 当重载equals方法时,你相应的应该重载hashCode方法.至于为什么请看hibernate的文章,其有非常详尽的阐述.
	 * 
	 * <dl>
	 * <dt><b>参见：</b>
	 * <dd><a href="http://www.hibernate.org/109.html">Why are equals() and
	 * hashCode() importation" for more information</a>
	 * </dl>
	 * 
	 */
	public abstract int hashCode();
}
