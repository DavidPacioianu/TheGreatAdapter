package com.pixelcan.thegreatadapter.utils;

import java.lang.reflect.Constructor;

/**
 * Created by David Pacioianu on 12/12/16.
 */

public class JavaUtils {

    public static boolean hasNoArgConstructor(Class<?> clazz)  {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            // In Java 7-, use getParameterTypes and check the length of the array returned
            if (constructor.getParameterTypes().length == 0) {
                return true;
            }
        }

        return false;
    }

}
