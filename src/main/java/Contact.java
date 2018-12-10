import javax.persistence.*;

@Entity
public class Contact {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    @Column
    private String phone;

    @OneToOne
    @JoinColumn(name = "fk_ad_id", referencedColumnName = "id")
    private City city;

    public Contact() {}

    public Contact(String name, String phone, City city) {
        this.name = name;
        this.phone = phone;
        this.city = city;
    }

    public void setCity(City city){
        this.city = city;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", city=" + city +
                '}';
    }
}
