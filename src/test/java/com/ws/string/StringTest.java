package com.ws.string;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author yunhua
 * @since 2019-08-20
 */
public class StringTest extends TestCase {


    @Test
    public void test() {
        String word = "贝因美纸尿裤";
        String newWord = word.replaceAll("贝因美", "");
        System.out.printf(newWord);
    }

}
