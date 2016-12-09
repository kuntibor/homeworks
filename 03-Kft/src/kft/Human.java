package kft;

public abstract class Human {

    private String name;
    private String birthDate;
    private String sex;
    private int money;
    private int salary;

    public Human(String name, String birthDate, String sex, int money, int salary) {
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.money = money;
        this.salary = salary;
    }

    public String getName() {
        return this.name;
    }

    public int getMoney() {
        return this.money;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean deposit(int money) {
        if (this.money - money >= 0) {
            this.money = this.money - money;
            return true;
        }
        return false;
    }

    public void income(int salary) {
        this.money = this.money + salary;
        System.out.println(this.name + " has " + this.money + " HUF");
    }

    abstract public void doWork();
}
