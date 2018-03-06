package cn.javava.shelf.util;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * Created by wlrllr on 2018/3/6.
 */
public class ShelfUtils {

    private static final TimeBasedGenerator generator =Generators.timeBasedGenerator(EthernetAddress.fromInterface());

    public static long generateId(){
        return generator.generate().timestamp();
    }
}
