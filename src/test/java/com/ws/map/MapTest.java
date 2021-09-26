package com.ws.map;

import com.ws.bean.UserBean;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest extends TestCase {

    @Test
    public void test() {
        Map<Integer, HashMap<String, UserBean>> typeItemsMap = new HashMap<>();
        Map<Integer, HashMap<String, UserBean>> map = new HashMap<>() ;
        HashMap<String, UserBean> temp1 = new HashMap<>() ;
        UserBean u1 = new UserBean() ;
        u1.setName("A1");
        temp1.put("A",u1) ;
        map.put(1,temp1) ;

        HashMap<String, UserBean> temp2 = new HashMap<>() ;
        UserBean u2 = new UserBean() ;
        u1.setName("A2");
        temp2.put("B",u2) ;
        map.put(2,temp2) ;
        typeItemsMap.putAll(map);
        System.out.println(map);
    }
}
