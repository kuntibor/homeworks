package xyz.codingmentor.beanvalidation05.beans;

import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import xyz.codingmentor.beanvalidation05.constraint.GreaterThanZero;
import xyz.codingmentor.beanvalidation05.constraint.ManufecturerColor;

/**
 *
 * @author teiep
 */
@ManufecturerColor
public class DeviceEntity {

    @NotNull
    @Size(min = 36, max = 36)
    private String id;

    public enum Manufacturer {
        APPLE,
        SAMSUNG,
        HTC,
        ONEPLUS;
    }

    @NotNull
    private Manufacturer manufacturer;

    @NotNull
    @Size(min = 3)
    private String type;

    @NotNull
    @GreaterThanZero
    private double price;

    public enum Color {
        WHITE,
        BLACK,
        GREEN,
        RED,
        PURPLE,
        BLUE;
    }

    @NotNull
    private Color color;

    @NotNull
    @DecimalMin(value = "0")
    private int count;

    public DeviceEntity(Manufacturer manufacturer, String type, double price, Color color, int count) {
        String randomUUID = UUID.randomUUID().toString();
        this.id = randomUUID;
        this.manufacturer = manufacturer;
        this.type = type;
        this.price = price;
        this.color = color;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.manufacturer);
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.color);
        hash = 97 * hash + this.count;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DeviceEntity other = (DeviceEntity) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (this.manufacturer != other.manufacturer) {
            return false;
        }
        if (this.color != other.color) {
            return false;
        }
        return true;
    }

}
