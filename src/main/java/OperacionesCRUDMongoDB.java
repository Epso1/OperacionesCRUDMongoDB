import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class OperacionesCRUDMongoDB {

    // CrearPiloto(), que reciba un objeto Piloto y lo añada a la base de datos.
    public static void crearPiloto(MongoCollection<Driver> collection, Driver driver) {
        collection.insertOne(driver);
    }

    // LeerPiloto(), que reciba un entero y devuelva un objeto Piloto con la información del piloto
    //con el driverid coincidente.
    public static Driver leerPiloto(MongoCollection<Driver> collection, int driverid) {
        return collection.find(eq("driverid", driverid)).first();
    }

    // LeerPilotos(), que devuelva un listado completo de objetos Piloto.
    public static ArrayList<Driver> leerPilotos(MongoCollection<Driver> collection) {
        return collection.find().into(new ArrayList<>());
    }

    // ActualizarPiloto(), que reciba un objeto Piloto y actualice los datos del registro coincidente
    //en la base de datos con el mismo driverid.
    public static void actualizarPiloto(MongoCollection<Driver> collection, Driver driver) {
        collection.replaceOne(eq("driverid", driver.getDriverid()), driver);
    }

    // BorrarPiloto(), que reciba un objeto Piloto y lo elimine de la base de datos.
    public static void borrarPiloto(MongoCollection<Driver> collection, Driver driver) {
        collection.deleteMany(eq("driverid", driver.getDriverid()));
    }

    //MostrarPilotosOrdenadosPorEdadDescendente(), que muestre el listado de pilotos
    //ordenado con edad de mayor a menor y su edad en el inicio de la temporada (año 2006).
    public static void mostrarPilotosOrdenadosPorEdadDescendente(MongoCollection<Driver> collection) {
        ArrayList<Driver> porEdad = collection.find().sort(new Document("dob", 1)).into(new ArrayList<>());

        for (Driver driver : porEdad) {
            System.out.println("Nombre: " + driver.getForename() + " " + driver.getSurname() + " - Edad: " + driver.getAge());
        }
    }

    // MostrarPilotosConEdadMayorQue(), que reciba como parámetro un entero y muestre el
    //listado de pilotos con edad mayor o igual que ese entero en el inicio de la temporada (año
    //2006) ordenados de mayor a menor edad.
    public static void mostrarPilotosConEdadMayorQue(MongoCollection<Driver> collection, int edad) {
        ArrayList<Driver> porEdad = collection.find().sort(new Document("dob", 1)).into(new ArrayList<>());

        for (Driver driver : porEdad) {
            if (driver.getAge() >= edad) {
                System.out.println("Nombre: " + driver.getForename() + " " + driver.getSurname() + " - Edad: " + driver.getAge());
            }
        }
    }

}
