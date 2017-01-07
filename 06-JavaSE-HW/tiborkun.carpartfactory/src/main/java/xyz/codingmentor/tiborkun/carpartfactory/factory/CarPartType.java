package xyz.codingmentor.tiborkun.carpartfactory.factory;

/**
 *
 * @author teiep
 */
public enum CarPartType {
    POWERWWINDOW(1, "power window"),
    TURNSIGNAL(2, "turn signal"),
    REARVIEWMIRROR(3, "rear-view mirror"),
    GEARSHIFT(4, "gear shift");

    private final int productID;
    private final String name;

    CarPartType(int productID, String name) {
        this.productID = productID;
        this.name = name;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

}
