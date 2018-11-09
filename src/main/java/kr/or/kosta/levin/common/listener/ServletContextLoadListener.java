package kr.or.kosta.levin.common.listener;

import java.net.URL;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import io.github.leeseungeun.webframework.beans.RequestHandler;
import io.github.leeseungeun.webframework.utils.Initiator;

public class ServletContextLoadListener implements ServletContextListener {

	private Logger logger = Logger.getLogger(ServletContextLoadListener.class);

	public void contextInitialized(ServletContextEvent event) {

		ServletContext servletContext = event.getServletContext();
		String configLocation = servletContext.getInitParameter("configLocation");

		URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
		Initiator initiator = new Initiator(url, configLocation);
		Map<String, Object> beans = initiator.getBeans();
		RequestHandler requestHandler = initiator.getRequestHandler();
		
		servletContext.setAttribute("beans", beans);
		servletContext.setAttribute("requestHandler", requestHandler);
	}

	public void contextDestroyed(ServletContextEvent event) {
		logger.debug("ServletContext(서블릿컨테이너) 종료.");
	}

}
