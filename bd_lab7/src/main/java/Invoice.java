import javax.persistence.*;
import java.sql.Date;
import java.util.Random;

@Entity
@Table(name = "invoice")
public class Invoice {
    Invoice(){}

    Invoice(String number, Date date, Provider provider) {
        this.number = number;
        this.date = date;
        this.providerByProvider = provider;
    }

    @Id
    @Column(name = "number")
    private String number;

    @Column(name = "data")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false)
    private Provider providerByProvider;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Provider getProviderByProvider() {
        return providerByProvider;
    }

    public void setProviderByProvider(Provider providerByProvider) {
        this.providerByProvider = providerByProvider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice that = (Invoice) o;

        if (date != that.date) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 123321;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
