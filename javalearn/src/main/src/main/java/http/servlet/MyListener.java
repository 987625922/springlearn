package http.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * servlet监听器
 * <p>
 * //监听器,ServletContextListener启动时被调用，
 * //ServletContextAttributeEvent是ServletContext里的Attribute被修改时调用
 * //HttpSessionListener监听session被创建和销毁
 * //HttpSessionAttributeListener监听Session里的Attribute被修改时调用
 * //ServletRequestListener监听request的创建和销毁
 */
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized......");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed.......");
    }
}
