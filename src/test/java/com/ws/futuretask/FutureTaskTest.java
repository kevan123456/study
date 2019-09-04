package com.ws.futuretask;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

/**
 * @author yunhua
 * @since 2019-08-16
 */
public class FutureTaskTest {

    CountDownLatch latch = new CountDownLatch(1);

    @Test
    public void test() throws Exception {
        //第一种方式
        ExecutorService executor = Executors.newCachedThreadPool();
        List<UserService> userList = new ArrayList<>();

        for (int i = 0; i < 150; i++) {
            UserService userService = new UserService();
            userService.setId(Long.valueOf(i));
            userService.setName("kevan" + i);
            userService.setSort(new Random().nextInt(10));
            userList.add(userService);
        }

        for (int i = 0; i < 10; i++) {
            MyTask task = new MyTask(userList);
            FutureTask<Integer> futureTask = new FutureTask<>(task);
            executor.submit(futureTask);
        }
        /*for (int i = 0; i < 10; i++) {
            MyTask2 task = new MyTask2(userList);
            FutureTask<Integer> futureTask = new FutureTask<>(task);
            executor.submit(futureTask);
        }*/

        latch.await();
        System.out.println("主线程在执行任务。。。。。。。。");
        userList.sort((UserService u1, UserService u2) -> {
            if (u1.getSort() == null || u2.getSort() == null) {
                return 0;
            }
            if (u1.getSort() > u2.getSort()) {
                return 1;
            }
            if (u1.getSort() < u2.getSort()) {
                return -1;
            }
            return 0;
        });
        executor.shutdown();

        //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        /*Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();*/

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
//        try {
//            System.out.println("task运行结果"+futureTask.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        System.out.println("所有任务执行完毕");
    }

    class MyTask2 implements Callable<Integer> {

        private List<UserService> userList;

        //多线程不能使用spring注入，必须构造方法传
        public MyTask2(List<UserService> userList) {
            this.userList = userList;
        }


        @Override
        public Integer call() throws Exception {
            latch.countDown();
            System.out.println("子线程2在进行排序");
            userList.sort((UserService u1, UserService u2) -> {
                int i = new Random().nextInt(1);
                return i;
            });
            return 0;
        }
    }

    class MyTask implements Callable<Integer> {

        private List<UserService> userList;

        //多线程不能使用spring注入，必须构造方法传
        public MyTask(List<UserService> userList) {
            this.userList = userList;
        }


        @Override
        public Integer call() throws Exception {

            List<Long> idList = userList.stream().map(UserService::getId).collect(Collectors.toList());

            System.out.println(idList);
            System.out.println("子线程2在进行计算");
            /*for (UserService user : userList) {
                System.out.println(user.getId() + ",sort" + user.getSort());
            }*/
            latch.countDown();
            Thread.sleep(3000);

            int sum = 0;
            for (int i = 0; i < 100; i++)
                sum += i;
            return sum;
        }
    }
}
