package com.ws.interview;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yunhua
 * @since 2020-11-20
 */
public class TreeTest extends TestCase {

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    class Solution {

        /**
         * 打印二叉树
         *
         * @param root
         * @return
         */
        public Integer[] printNode(TreeNode root) {
            if (root == null) {
                return new Integer[0];
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            List<Integer> list = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                list.add(node.getVal());
                TreeNode left = node.getLeft();
                if (left != null) {
                    queue.add(left);
                }
                TreeNode right = node.getRight();
                if (right != null) {
                    queue.add(right);
                }
            }
            Integer[] arrar = new Integer[list.size()];
            list.toArray(arrar);

            return arrar;
        }

        /**
         * 从上到下打印
         *
         * @param root
         * @return
         */
        public List<List<Integer>> printNode2(TreeNode root) {
            if (root == null) {
                return new ArrayList();
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            List<List<Integer>> result = new ArrayList<>();
            while (!queue.isEmpty()) {
                List<Integer> list = new ArrayList<>();
                for (int i = queue.size(); i > 0; i--) {
                    TreeNode node = queue.poll();
                    list.add(node.getVal());
                    if (node.getLeft() != null) {
                        queue.add(node.getLeft());
                    }
                    if (node.getRight() != null) {
                        queue.add(node.getRight());
                    }
                }
                result.add(list);

            }

            return result;
        }

        /**
         * 求书的深度
         *
         * @param root
         * @return
         */
        public int getDeep(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = getDeep(root.getLeft()) + 1;

            int right = getDeep(root.getRight()) + 1;

            return left > right ? left : right;

        }

        public int getDeep2(TreeNode root) {
            int deep = 0;
            if (root == null) {
                return deep;
            }
            Queue<TreeNode> stack = new LinkedList<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                deep++;
                for (int i = stack.size(); i > 0; i--) {
                    TreeNode node = stack.poll();
                    if (node.getLeft() != null) {
                        stack.add(node.getLeft());
                    }
                    if (node.getRight() != null) {
                        stack.add(node.getRight());
                    }
                }

            }
            return deep;

        }

        /**
         * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
         * <p>
         * 假设一个二叉搜索树具有如下特征：
         * <p>
         * 节点的左子树只包含小于当前节点的数。
         * 节点的右子树只包含大于当前节点的数。
         * 所有左子树和右子树自身必须也是二叉搜索树。
         * <p>
         *
         * @param root
         * @return
         */
        public boolean isValidBST(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            this.ldr(root, list);
            return this.isSort(list);
        }

        /**
         * 中序遍历
         *
         * @param node
         * @param list
         */
        private void ldr(TreeNode node, List<Integer> list) {
            if (node == null) {
                return;
            }
            if (list == null) {
                list = new ArrayList<>();
            }
            ldr(node.getLeft(), list);
            list.add(node.getVal());
            ldr(node.getRight(), list);
        }

        /**
         * 是否升序
         * @param list
         * @return
         */
        private boolean isSort(List<Integer> list) {
            if (CollectionUtils.isEmpty(list)) {
                return true;
            }
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) <= list.get(i - 1)) {
                    return false;
                }
            }
            return true;
        }

    }


    @Test
    public void testPrint() {
        TreeNode root = new TreeNode();
        root.setVal(3);

        TreeNode left1 = new TreeNode();
        left1.setVal(9);
        root.setLeft(left1);

        TreeNode right2 = new TreeNode();
        right2.setVal(8);
        left1.setRight(right2);

        TreeNode right1 = new TreeNode();
        right1.setVal(7);
        root.setRight(right1);


        TreeNode left2 = new TreeNode();
        left2.setVal(4);
        left1.setLeft(left2);


        TreeNode left3 = new TreeNode();
        left3.setVal(5);
        left2.setLeft(left3);

        /**
         * 目前数据
         *              3
         *          9       7
         *      4       8
         *  5
         *
         */
        Solution solution = new Solution();

        //打印深度
        System.out.println("deep:" + solution.getDeep(root));
        System.out.println("deep2:" + solution.getDeep2(root));


        //打印节点
        Integer[] array = solution.printNode(root);
        System.out.println(JSON.toJSON(array));

        //打印节点二位数组
        List<List<Integer>> list = solution.printNode2(root);
        System.out.println(JSON.toJSON(list));

        //判断是否排序二叉树
        boolean isSort = solution.isValidBST(root);
        System.out.println(isSort);
    }
}
