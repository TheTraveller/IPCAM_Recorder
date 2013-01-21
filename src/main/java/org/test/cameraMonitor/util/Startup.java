package org.test.cameraMonitor.util;

import org.hibernate.Session;
import org.test.cameraMonitor.entities.Camera;

/**
 * Created with IntelliJ IDEA.
 * User: dreambrotherirl
 * Date: 11/01/2013
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */
public class Startup {

    public static Camera getCamera(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Camera camera = (Camera) session.get(Camera.class, 0);
        return camera;
    }

}
