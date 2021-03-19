package lab1.task1;

public class Main {
    public static void main(String[] args) {
        Cashier cashier1 = new Cashier("cashier1");
        Cashier cashier2 = new Cashier("cashier2");
        Cashier cashier3 = new Cashier("cashier3");
        cashier1.start();
        cashier2.start();
        cashier3.start();
        try {
            cashier1.join();
            cashier2.join();
           cashier3.join();
        }catch (InterruptedException exception){
            exception.printStackTrace();
        }
        System.out.println("General served customers number: "+
                (cashier1.getServedCustomersNumber()+
                cashier2.getServedCustomersNumber()+
                cashier3.getServedCustomersNumber()));
    }
}
