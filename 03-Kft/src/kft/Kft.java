package kft;

import java.util.ArrayList;
import java.util.List;

public class Kft {

    private String name;
    private String address;
    private String activity;
    private final List<Boss> bosses;
    private final List<Worker> workers;
    private int budget;

    public Kft(String name, String address, String activity, int budget) {
        this.name = name;
        this.address = address;
        this.activity = activity;
        this.budget = budget;
        this.bosses = new ArrayList<>();
        this.workers = new ArrayList<>();
    }

    public void addBoss(Boss boss) {
        if (boss.isImportantPerson()) {
            bosses.add(boss);
            System.out.println("New boss: " + boss.getName());
        } else {
            System.out.println(boss.getName() + " not important");
        }
    }

    public void addWorker(Worker worker) {
        if (worker.hasDegree()) {
            workers.add(worker);
            System.out.println(worker.getName() + " got the job");
        } else {
            System.out.println(worker.getName() + " denied: not proper degree");
        }
    }

    public void startWork() {
        for (Boss b : bosses) {
            b.doWork();
        }
        for (Worker w : workers) {
            w.doWork();
        }
    }

    public void doPayment() {
        for (Boss b : bosses) {
            b.income(b.getSalary());
            this.budget -= b.getSalary();
        }
        for (Worker w : workers) {
            w.income(w.getSalary());
            this.budget -= w.getSalary();
        }
        System.out.println(this.name + " budget:" + this.budget);
    }
}
