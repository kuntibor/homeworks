package kft;

public class Worker extends Human {

    private Degree degree;

    public Worker(String name, String date, String sex, int money, Degree degree, int salary) {
        super(name, date, sex, money, salary);
        this.degree = degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public boolean hasDegree() {
        switch (degree) {
            case ENGINEER:
                return true;
            case DEVELOPER:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void doWork() {
        System.out.println(this.getName() + " works hard");
    }
}
