package lab1.task1;

import java.util.Scanner;

public class Cashier extends Thread {
    private int servedCustomersNumber=0;
    private int timeCounter=0;
    private int workTime;

    public Cashier(String name) {
        super(name);
        inputWorkTime();
    }

    public int getServedCustomersNumber() {
        return servedCustomersNumber;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" started working");
        while (timeCounter<=6000){
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            servedCustomersNumber++;
            timeCounter+=workTime;
        }
        System.out.println(Thread.currentThread().getName()+"served "+servedCustomersNumber+" customers");
    }

    public void inputWorkTime() {
        System.out.println("Введите время работы: ");
        Scanner in = new Scanner(System.in);
        workTime=in.nextInt();
    }
}
