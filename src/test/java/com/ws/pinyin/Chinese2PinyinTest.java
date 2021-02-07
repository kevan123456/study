package com.ws.pinyin;

import com.ws.util.PinyinUtil;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author wangshun
 * @date 2021-02-07
 * @see
 * @since 1.0.0
 */
public class Chinese2PinyinTest extends TestCase {

    @Test
    public void test() {
        System.out.println(PinyinUtil.cn2Spell("王顺"));
        System.out.println(PinyinUtil.cn2Spell("测试"));
        System.out.println(PinyinUtil.cn2Spell("还是兑换啥都好说的"));
    }
}
