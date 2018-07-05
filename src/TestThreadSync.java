/**
 * t1你是第1个使用timer的线程
 * t2你是第2个使用timer的线程
 */
public class TestThreadSync implements  Runnable{ //自己就是一个线程类
    Timer timer = new Timer();//Timer 是另一个内部类
    public void run(){
        timer.add(Thread.currentThread().getName());  //线程启动后，调用另一个内部类的add方法
    }

    public static void main(String[] args){
        TestThreadSync  test = new TestThreadSync(); //本类的对象 test
        Thread t1 = new Thread(test);// 线程1 加入test对象
        Thread t2 = new Thread(test);// 线程2 加入test对象
        t1.setName("t1");
        t2.setName("t2");
        t1.start(); //开始
        t2.start();
    }

}

class Timer{
    private static int num = 0;
    public void add(String name) {
        synchronized (this) {//如果没有这个代码行，输出值都是:第2个使用timer的线程
            // t1你是第1个使用timer的线程
            // t2你是第2个使用timer的线程
            num++;
            try {
                Thread.sleep(1); //睡一秒 ，让线程2进入
            } catch (InterruptedException e) {
                ;
            }
            System.out.println(name + "你是第" + num + "个使用timer的线程");
        }
    }
}
