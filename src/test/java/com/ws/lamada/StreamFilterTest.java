package com.ws.lamada;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yunhua
 * @since 2019-08-26
 */
public class StreamFilterTest extends TestCase {

    class ItemModuleListVO {
        private Long id;
        private Integer isCoupon;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getCoupon() {
            return isCoupon;
        }

        public void setCoupon(Integer coupon) {
            isCoupon = coupon;
        }
    }


    @Test
    public void test() {
        List<ItemModuleListVO> list = new ArrayList<>();
        ItemModuleListVO itemModuleListVO1 = new ItemModuleListVO();
        itemModuleListVO1.setId(1L);
        itemModuleListVO1.setCoupon(1);
        list.add(itemModuleListVO1);
        ItemModuleListVO itemModuleListVO2 = new ItemModuleListVO();
        itemModuleListVO2.setId(2L);
        itemModuleListVO2.setCoupon(0);
        list.add(itemModuleListVO2);
        ItemModuleListVO itemModuleListVO3 = new ItemModuleListVO();
        itemModuleListVO3.setId(3L);
        itemModuleListVO3.setCoupon(0);
        list.add(itemModuleListVO3);


        List<Long> filterItemIdList = list.stream().filter(t -> t.getCoupon() == 0)
                .map(ItemModuleListVO::getId).collect(Collectors.toList());
        System.out.println(filterItemIdList);
    }
}
