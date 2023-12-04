
import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Driver {
    private Integer driverid;
    private String code;
    private String forename;
    private String surname;
    private LocalDate dob;
    private String nationality;
    private String url;
    private Constructors constructors;


    @Override
    public String toString() {
        return "Piloto{" +
                "driverid='" + driverid + '\'' +
                ", code='" + code + '\'' +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", dob='" + dob + '\'' +
                ", nationality='" + nationality + '\'' +
                ", url='" + url + '\'' +
                ", constructors=" + constructors +
                '}';
    }

    public int getAge() {
        LocalDate inicioTemporada = LocalDate.of(2006, 1, 1);

        int age = inicioTemporada.getYear() - dob.getYear();
        if (inicioTemporada.getMonthValue() < dob.getMonthValue()) {
            age--;
        } else if (inicioTemporada.getMonthValue() == dob.getMonthValue()
                && inicioTemporada.getDayOfMonth() < dob.getDayOfMonth()) {
            age--;
        }
        return age;
    }
}
