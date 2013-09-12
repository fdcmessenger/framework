package org.sbbs.base.dao;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * DAO测试基类
 */
@ContextConfiguration(locations = {
		"classpath:/applicationContext-resources.xml",
		"classpath:/applicationContext-dao.xml" })
public abstract class BaseDaoTestCase extends
		AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * 加载资源文件,路径为: src/test/resources/${package.name}/ClassName.properties
	 * (如果存在的话)
	 * 
	 */
	protected ResourceBundle rb;

	/**
	 * 缺省构造器,包装"rb"变量,如果此类所对应的资源文件在目录(src/test/resources)下存在的话.
	 */
	public BaseDaoTestCase() {
		// 资源文件不是必须的,因此这里只做简单的检查.
		String className = this.getClass().getName();

		try {
			rb = ResourceBundle.getBundle(className);
		} catch (MissingResourceException mre) {
			log.trace("No resource bundle found for: " + className);
		}
	}

	/**
	 * 将属性文件中的值包装到javabean的一个工具方法.
	 */
	protected Object populate(final Object obj) throws Exception {
		// loop through all the beans methods and set its properties
		// from its .properties file
		Map<String, String> map = new HashMap<String, String>();

		for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements();) {
			String key = keys.nextElement();
			map.put(key, rb.getString(key));
		}

		BeanUtils.copyProperties(obj, map);

		return obj;
	}

	/**
	 * 使用SessionFactory对象创建HibernateTemplate助手类对象,并且调用flush() and
	 * clear()方法.
	 * 
	 * 
	 * <dl>
	 * <dt><b>参见：</b>
	 * <dd><a href="http://issues.appfuse.org/browse/APF-178">设计意图为在测试中在调用save(保存)类的方法后,使数据反映的真实数据库中.</a>
	 * </dl>
	 * 
	 */
	protected void flush() throws BeansException {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(
				sessionFactory);
		hibernateTemplate.flush();
		hibernateTemplate.clear();
	}
}
