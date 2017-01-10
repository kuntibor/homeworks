package xyz.codingmentor.beanvalidation05.beans;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import xyz.codingmentor.beanvalidation05.annotation.Validate;
import xyz.codingmentor.beanvalidation05.constraint.ManufecturerColor;
/**
 *
 * @author teiep
 */
@Validate
@ManufecturerColor
public class DeviceEntity implements Serializable {

    @NotNull
    @Size(message = "Wrong ID size",min = 36, max = 36)
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
    @DecimalMin(value = "1")
    private Integer price;

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

    public DeviceEntity() {
        //to DeviceEntity.class
    }

    private DeviceEntity(Builder builder) {
        this.id = builder.id;
        this.manufacturer = builder.manufacturer;
        this.type = builder.type;
        this.price = builder.price;
        this.color = builder.color;
        this.count = builder.count;
    }

    public DeviceEntity(Manufacturer manufacturer, String type, int price, Color color, int count) {
        this.id = UUID.randomUUID().toString();
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

    public void setPrice(int price) {
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
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.manufacturer);
        hash = 71 * hash + Objects.hashCode(this.type);
        hash = 71 * hash + this.price;
        hash = 71 * hash + Objects.hashCode(this.color);
        hash = 71 * hash + this.count;
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
        if (this.price != other.price) {
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

    public static class Builder {

        private String id;
        private Manufacturer manufacturer;
        private String type;
        private Integer price;
        private Color color;
        private int count;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder manufacturer(Manufacturer manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder price(Integer price) {
            this.price = price;
            return this;
        }

        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public DeviceEntity build() {
            return new DeviceEntity(this);
        }
    }

}
