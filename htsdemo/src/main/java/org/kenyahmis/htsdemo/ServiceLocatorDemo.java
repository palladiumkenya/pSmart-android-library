package org.kenyahmis.htsdemo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Muhoro on 2/26/2018.
 */

public final class ServiceLocatorDemo {

    private static HashMap<String, Object> _services = new HashMap<>();

    public static void addService(String key, Object service){
        if(key.length() != 0 && service != null)
            _services.put(key, service);
    }

    public static Object getService(String key){
        if(key.length() != 0){
            Object service = null;
            return _services.get(key);
        }
        return null;
    }
}
