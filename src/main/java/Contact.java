import javax.persistence.*;

@Entity
public class Contact {
    @Id
    @GeneratedValue
    public int id;

    @Column
    public String name;

    @Column
    public String phone;

    @OneToOne
    @JoinColumn
    public int cityId;

    public Contact() {}

    public Contact(int id, String name, String phone, int cityId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.cityId = cityId;
    }
}
