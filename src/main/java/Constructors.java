import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Constructors {
    private String constructorRef;
    private String name;
    private String nationality;
    private String url;

    @Override
    public String toString() {
        return "Constructors{" +
                "constructorRef='" + constructorRef + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
