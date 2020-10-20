package com.ws.set;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yunhua
 * @since 2019-08-05
 */
public class SetTest extends TestCase {

    @Test
    public void test(){
        List<Long> itemIds = new ArrayList<Long>() ;
        itemIds.add(1L) ;
        itemIds.add(2L) ;
        itemIds.add(4L) ;
        itemIds.add(4L) ;


        Set<String> itemIdSet = new HashSet<String>();
        itemIds.forEach(t -> itemIdSet.add(String.valueOf(t)));

        for(String a :itemIdSet){
            System.out.printf(a+",");
        }

        List<String> itemIdList = new ArrayList<>(itemIdSet) ;
        for(String a :itemIdList){
            System.out.printf(a+",");
        }
    }

    @Test
    public void testList() {
        List<String> itemIds = new ArrayList();
        itemIds.add("1");
        itemIds.add("2");
        itemIds.add("3");
        itemIds.add("2");
        itemIds.add("6");
        itemIds.add("9");
        itemIds.add("1");
        itemIds.add("8");


        Set<String> itemIdSet = new LinkedHashSet<>(itemIds);

        for (String a : itemIdSet) {
            System.out.printf(a + ",");
        }

    }

}
