package kft;

public class Boss extends Human {

    private Connection connection;

    public Boss(String name, String date, String sex, int money, Connection connection, int salary) {
        super(name, date, sex, money, salary);
        this.connection = connection;
    }

    public void setConnetcion(Connection connection) {
        this.connection = connection;
    }

    public boolean isImportantPerson() {
        switch (this.connection) {
            case FRIEND:
                return true;
            case SON:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void doWork() {
        System.out.println(this.getName() + " drinks a coffee");
    }
}
