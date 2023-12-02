import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Driver {
    private Integer driverid;
    private String code;
    private String forename;
    private String surname;
    private LocalDate dob;
    private String nationality;
    private String url;

    private Constructors constructors;

    public Driver(Integer driverId, String code, String forename, String surname, LocalDate dob, String nationality, String url, Constructors constructors) {

        this.driverid = driverId;
        this.code = code;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.nationality = nationality;
        this.url = url;
        this.constructors = constructors;
    }

    public Driver() {
    }

    public Integer getDriverid() {
        return driverid;
    }

    public String getCode() {
        return code;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getNationality() {
        return nationality;
    }

    public String getUrl() {
        return url;
    }

    public Constructors getConstructors() {
        return constructors;
    }

    public void setDriverid(Integer driverid) {
        this.driverid = driverid;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setConstructors(Constructors constructors) {
        this.constructors = constructors;
    }

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
