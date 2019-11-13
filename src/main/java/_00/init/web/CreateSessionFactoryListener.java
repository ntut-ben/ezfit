package _00.init.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import _00.utils.HibernateUtils;

/**
 * Application Lifecycle Listener implementation class
 * CreateSessionFactoryListener
 *
 */
//@WebListener
public class CreateSessionFactoryListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public CreateSessionFactoryListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		HibernateUtils.getSessionFactory().close();
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		HibernateUtils.getSessionFactory();
	}

}
