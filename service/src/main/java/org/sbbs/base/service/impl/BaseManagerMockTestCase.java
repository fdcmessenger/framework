package org.sbbs.base.service.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A mock class for testing using JMock. This test class can be moved to the
 * test tree.
 *
 * @see <a href="http://code.google.com/p/mockito/">mockito</a>
 * @see <a href="http://www.cnblogs.com/TankXiao/archive/2012/03/06/2366073.html">stub和mock的区别</a>
 * @see <a href="http://blog.csdn.net/onlyqi/article/details/6396646">Mockito(一) -- 入门篇</a>
 * @see <a href="http://blog.csdn.net/onlyqi/article/details/6546589">Mockito(二)--实例篇</a>
 * @see <a href="http://blog.csdn.net/onlyqi/article/details/6544989">Mockito(三)--完整功能介绍</a>
 */

public abstract class BaseManagerMockTestCase {
	/**
	 * A logger
	 */
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * The resourceBundle
	 */
	protected ResourceBundle rb;


	/**
	 * Default constructor will set the ResourceBundle if needed.
	 */
	public BaseManagerMockTestCase() {
		// Since a ResourceBundle is not required for each class, just
		// do a simple check to see if one exists
		String className = this.getClass().getName();

		try {
			rb = ResourceBundle.getBundle(className);
		} catch (MissingResourceException mre) {
			// log.debug("No resource bundle found for: " + className);
		}
	}

	/**
	 * Utility method to populate a javabean-style object with values from a
	 * Properties file
	 *
	 * @param obj
	 *            the model object to populate
	 * @return Object populated object
	 * @throws Exception
	 *             if BeanUtils fails to copy properly
	 */
	protected Object populate(Object obj) throws Exception {
		// loop through all the beans methods and set its properties from
		// its .properties file
		Map<String, String> map = new HashMap<String, String>();

		for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements();) {
			String key = keys.nextElement();
			map.put(key, rb.getString(key));
		}

		BeanUtils.copyProperties(obj, map);

		return obj;
	}
}
