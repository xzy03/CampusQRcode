package listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import system.LogCleanupTask;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 应用程序启动时启动日志清理任务
        LogCleanupTask.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 应用程序关闭时执行必要的清理
        // 通常情况下无需额外操作，ScheduledExecutorService会自动终止
    }
}
