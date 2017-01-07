package xyz.codingmentor.tiborkun.carpartfactory.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import xyz.codingmentor.tiborkun.carpartfactory.factory.CarType;

/**
 *
 * @author teiep
 */
public abstract class CarPart implements Serializable {

    private int count;
    private String productionDate;
    private int productID;
    private CarType madeFor;

    public CarPart(CarType madeFor, int count, int productID) {
        this.count = count;
        this.productID = productID;
        this.madeFor = madeFor;
        this.productionDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public CarType getMadeFor() {
        return madeFor;
    }

    public void setMadeFor(CarType madeFor) {
        this.madeFor = madeFor;
    }

    public abstract String getType();

}
