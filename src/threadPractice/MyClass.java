package threadPractice;

public class MyClass{

    public static void main(String[] args) {

        for (int i = 0; i < 4; i++){
            Thread t1 = new Thread(new HelloMessage(i + 1));
            t1.start();
        }
    }

}

class testTreading implements Runnable{

    @Override
    public void run() {

        for (int i =0; i <= 5; i++){
            System.out.println(" threading number :" + i );
        }

    }

}

class HelloMessage implements Runnable{

    public int threadNum;

    public HelloMessage(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        System.out.println("Hello from thread " + threadNum);
    }
}