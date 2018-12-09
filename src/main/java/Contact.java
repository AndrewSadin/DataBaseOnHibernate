import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;

@Entity
@Table(name="Contact")
public class Contact {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private String phone;

    @OneToOne
    @JoinColumn
    private int cityId;

    public Contact() {}

    public Contact(String name, String phone, int cityId) {
        this.name = name;
        this.phone = phone;
        this.cityId = cityId;
    }
}
