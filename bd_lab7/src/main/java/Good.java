import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "good")
public class Good {
    Good(){}

    Good(String code, String brand, double price, int count) {
        this.code = code;
        this.brand = brand;
        this.price = price;
        this.count = count;
    }

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "image", nullable = true)
    private byte[] image;

    @Column(name = "brand")
    private String brand;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "count")
    private int count;

    @ManyToMany
    @JoinTable(name = "good_provider", joinColumns = @JoinColumn(name = "good_code", referencedColumnName = "code", nullable = false), inverseJoinColumns = @JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false))
    private List<Provider> providers;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Good that = (Good) o;

        if (code != that.code) return false;
        if (price != that.price) return false;
        if (count != that.count) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) price;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
