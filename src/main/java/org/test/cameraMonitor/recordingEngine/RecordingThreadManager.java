package org.test.cameraMonitor.recordingEngine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created with IntelliJ IDEA.
 * User: dreambrotherirl
 * Date: 10/01/2013
 * Time: 21:40
 * To change this template use File | Settings | File Templates.
 */
public class RecordingThreadManager implements ServletContextListener {

    final ExecutorService executor = Executors.newFixedThreadPool(3);
    Logger logger = LogManager.getLogger(RecordingThreadManager.class.getName());


    public void contextInitialized(ServletContextEvent sce) {
        final List<Callable<Object>> tasks = new ArrayList<Callable<Object>>();

        tasks.add(Executors.callable(new RecordingEngine()));
        //tasks.add(Executors.callable(new AWS_S3StorageManager()));
        //tasks.add(Executors.callable(new EmailManager()));
        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Killing threads");
        executor.shutdown();
    }


}
