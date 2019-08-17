package net.ukr.tigor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

    public static Statistics stat;

    @Override
    public void contextInitialized(ServletContextEvent arg0) {

        stat = new Statistics(true);

    }
}
