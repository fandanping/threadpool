package thread.Semaphore;
import java.util.concurrent.Semaphore;

/**
 * 　Semaphore翻译成字面意思为 信号量，Semaphore可以控同时访问的线程个数，通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
 *　假若一个工厂有5台机器，但是有8个工人，一台机器同时只能被一个工人使用，只有使用完了，其他工人才能继续使用。那么我们就可以通过Semaphore来实现：
 *
 */
class Worker extends  Thread{
    private int num;
    private Semaphore semaphore;
    public Worker(int num,Semaphore semaphore){
        this.num=num;
        this.semaphore=semaphore;
    }
    @Override
    public void run (){
        try {
            semaphore.acquire();
            System.out.println("工人"+this.num+"占用一个机器生产");
            Thread.sleep(2000);
            System.out.println("工人"+this.num+"释放出机器");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class MySemaphore  {
    public static void main(String [] args){
        int N=8; //工人数
        Semaphore semaphore=new Semaphore(5);//机器数
        for(int i=0;i<N;i++){
            new Worker(i,semaphore).start();
        }
    }
}