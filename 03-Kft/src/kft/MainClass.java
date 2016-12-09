package kft;

public class MainClass {

    public static void main(String[] args) {
        Kft kft = new Kft("PineApple", "Hűvösvölgy", "Software Development", 10000000);
        Boss b1 = new Boss("István", "1930.01.01", "male", 2000000, Connection.FRIEND, 1000000);
        Boss b2 = new Boss("Pali", "1950.10.15", "male", 500000, Connection.SON, 700000);
        Worker w1 = new Worker("Tibor", "1930.01.01", "male", 200, Degree.DEVELOPER, 500000);
        Worker w2 = new Worker("Feri", "1930.01.01", "male", 10, Degree.HISTORIAN, 100000);
        kft.addBoss(b1);
        kft.addBoss(b2);
        kft.addWorker(w1);
        kft.addWorker(w2);
        System.out.println();

        kft.startWork();
        kft.doPayment();
    }

}
