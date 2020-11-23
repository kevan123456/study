package com.ws.interview;

import org.junit.Test;

/**
 * @author yunhua
 * @since 2020-11-20
 */
public class NumTest {

    /**
     * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int newLength = 0;
        if (nums == null) {
            return newLength;
        }
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[j - 1]) {
                newLength++;
                nums[newLength] = nums[j];
            }
        }
        return newLength + 1;
    }

    @Test
    public void testRemoveDuplicates() {
        int[] nums = {1, 1, 2};
        int newLength = removeDuplicates(nums);

        System.out.println("length:" + (newLength));
    }


    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * <p>
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int newLength = 0;
        if (nums == null) {
            return newLength;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[newLength] = nums[i];
                newLength++;
            }
        }
        return newLength;
    }

    @Test
    public void testRemoveElement() {
        int[] nums = {1, 2, 4, 2};
        int length = removeElement(nums, 2);
        System.out.println(length);
    }

}
