import javax.persistence.*;

@Entity
@Table(name = "good_provider")
public class GoodProvider {
    GoodProvider(){}

    GoodProvider(String code, int id) {
        this.goodCode = code;
        this.providerId = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "good_code")
    private String goodCode;

    @Column(name = "provider_id")
    private int providerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }
}
