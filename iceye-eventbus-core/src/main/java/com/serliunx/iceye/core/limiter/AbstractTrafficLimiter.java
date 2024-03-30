package com.serliunx.iceye.core.limiter;

import com.serliunx.iceye.core.pool.ExecutorTools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 限流器的抽象实现
 * @author SerLiunx
 * @since 1.0
 */
public abstract class AbstractTrafficLimiter implements TrafficLimiter{

    private final ArrayBlockingQueue<Worker> workerQueue;
    private final ThreadPoolExecutor pool;

    /**
     * 任务最短执行间隔时间
     * <li> 以毫秒为单位
     */
    private final long interval;

    /**
     * 并发任务数量
     * <li> 设定该限流器同一时刻(概念较为模糊, 并发其实并不是同一个时刻，也是时间段，只不过时间很短)
     * <li> 最大同时执行的任务数量
     */
    private final int concurrentTaskCount;

    public AbstractTrafficLimiter(int capacity, long interval) {
        this.pool = ExecutorTools.newCpuIntensiveThreadPool();
        this.workerQueue = new ArrayBlockingQueue<>(capacity * 4);
        this.concurrentTaskCount = capacity;
        this.interval = interval;
        //线程池初始化, 主要逻辑
        start();
    }

    public AbstractTrafficLimiter(long interval) {
        this(Runtime.getRuntime().availableProcessors(), interval);
    }

    @Override
    public void submit(Runnable runnable){
        submit(runnable, null);
    }

    @Override
    public <T extends Throwable> void submit(Runnable runnable, Consumer<T> afterThrows) {
        try {
            workerQueue.put(new Worker(runnable, afterThrows));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public long getInterval() {
        return interval;
    }

    @Override
    public int getConcurrentTaskCount() {
        return concurrentTaskCount;
    }

    @Override
    public void shutdown() {
        this.pool.shutdown();
    }

    protected void beforeRun(Worker currentWorker){}

    protected void afterRun(Worker currentWorker){}

    protected void beforeSleep(){}

    protected void afterSleep(){}

    @SuppressWarnings("all")
    private void start(){
        final int taskCount = this.concurrentTaskCount;
        for (int i = 0; i < taskCount; i++) {
            pool.execute(() -> {
                try{
                    //死循环拿取任务执行
                    for(;;){
                        /*
                         * 计算任务最短执行间隔, 如果不满足则会强行等待回去新的任务
                         * 例如: 一个任务执行耗时30ms, 但如果最短执行间隔为200ms, 则该执行线程会等待170ms才会
                         * 去主动从队列中取下一个任务.
                         */
                        long timeAtWaitingTask = System.currentTimeMillis();

                        Worker worker = workerQueue.take();
                        //执行运行前钩子函数
                        beforeRun(worker);
                        //执行任务
                        boolean state = false;
                        try {
                            worker.run();
                        }catch (Throwable e){
                            worker.afterThrows(e);
                        }finally {

                        }
                        //执行运行后的钩子函数
                        afterRun(worker);

                        long timeAfterRun = System.currentTimeMillis();
                        if(getInterval() < 0)
                            continue;
                        long timeCosted = timeAfterRun - timeAtWaitingTask;
                        long timeDifference = 0;
                        //线程休眠
                        try {
                            if((timeDifference = (timeCosted - getInterval())) < 0){
                                //进入睡眠钩子函数
                                beforeSleep();
                                TimeUnit.MILLISECONDS.sleep(-timeDifference);
                                //结束睡眠钩子函数
                                afterSleep();
                            }
                        }catch (InterruptedException e){
                            continue;
                        }
                    }
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    protected static class Worker{

        private final Runnable runnable;
        private final Consumer<Throwable> afterThrows;

        @SuppressWarnings("unchecked")
        public <T extends Throwable> Worker(Runnable runnable, Consumer<T> afterThrows) {
            this.runnable = runnable;
            this.afterThrows = (Consumer<Throwable>) afterThrows;
        }

        public Worker(Runnable runnable) {
            this(runnable, null);
        }

        public void run(){
            this.runnable.run();
        }

        public void afterThrows(Throwable throwable){
            if(afterThrows != null){
                afterThrows.accept(throwable);
            }
        }
    }
}
