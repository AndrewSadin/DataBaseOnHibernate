import javax.persistence.*;

@Entity
@Table(name="City")
public class City {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    public City(){};

    public City(String name){
        this.name = name;
    }

    public int getCityId(){
        return this.id;
    };
}
