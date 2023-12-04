import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main {
    public static void main(String[] args) {
        // Desactivamos los logs de MongoDB
        Logger logger = LoggerFactory.getLogger("org.mongodb.driver");

        // String uri = "mongodb://usuario:password@host:puerto";
        String uri = "mongodb://ubuntuAdmin:12345678@52.22.126.59:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            System.out.println("Conexión con MongoClient y CodecRegistry para el trabajo con POJOs");

            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            MongoDatabase database = mongoClient.getDatabase("f1-2006").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Driver> collection = database.getCollection("drivers", Driver.class);
            System.out.println("Conexión con la base de datos 'f1-2006' y la colección 'drivers' realizada con éxito\n");

            System.out.println("***** Operaciones CRUD con MongoDB *****\n");

            // Crear un objeto Piloto con los datos de Fernando Alonso
            Driver sainz = new Driver(44, "SAI", "Carlos", "Sainz", LocalDate.of(1994, 9, 1), "Spanish", "https://en.wikipedia.org/wiki/Carlos_Sainz_Jr.", new Constructors("renault", "Renault", "French", "https://en.wikipedia.org/wiki/Renault_in_Formula_One"));

            // CrearPiloto(), que reciba un objeto Piloto y lo añada a la base de datos.
            System.out.println("Creando a Carlos Sainz en la base de datos...");
            OperacionesCRUDMongoDB.crearPiloto(collection, sainz);
            System.out.println("Piloto creado con éxito.");
            System.out.println("Mostrando todos los pilotos de la base de datos...");
            OperacionesCRUDMongoDB.leerPilotos(collection).forEach(System.out::println);
            System.out.println();

            //LeerPiloto(), que reciba un entero y devuelva un objeto Piloto con la información del piloto
            //con el driverid coincidente.
            System.out.println("Mostrando el piloto con driverid = 44...");
            Driver pilotoALeer = OperacionesCRUDMongoDB.leerPiloto(collection, 44);
            System.out.println(pilotoALeer);
            System.out.println();

            //LeerPilotos(), que devuelva un listado completo de objetos Piloto.
            System.out.println("Mostrando todos los pilotos de la base de datos...");
            ArrayList<Driver> listadoCompleto = OperacionesCRUDMongoDB.leerPilotos(collection);
            for (Driver driver : listadoCompleto) {
                System.out.println(driver);
            }
            System.out.println();

            //ActualizarPiloto(), que reciba un objeto Piloto y actualice los datos del registro coincidente
            //en la base de datos con el mismo driverid.
            Driver sainz2 = new Driver(44, "SAI", "Carlos", "Sainz", LocalDate.of(1994, 9, 1), "Spanish", "https://en.wikipedia.org/wiki/Carlos_Sainz_Jr.", new Constructors("Red Bull", "Red Bull", "Austrian", "https://en.wikipedia.org/wiki/Red_Bull_Racing"));
            System.out.println("Actualizando los datos de Carlos Sainz, cambiando de escudería...");
            OperacionesCRUDMongoDB.actualizarPiloto(collection, sainz2);
            System.out.println("Datos actualizados con éxito.");
            System.out.println("Mostrando todos los pilotos de la base de datos...");
            OperacionesCRUDMongoDB.leerPilotos(collection).forEach(System.out::println);
            System.out.println();


            // BorrarPiloto(), que reciba un objeto Piloto y lo elimine de la base de datos.
            System.out.println("Borrando a Carlos Sainz de la base de datos...");
            OperacionesCRUDMongoDB.borrarPiloto(collection, sainz2);
            System.out.println("Piloto borrado con éxito.");
            System.out.println("Mostrando todos los pilotos de la base de datos...");
            OperacionesCRUDMongoDB.leerPilotos(collection).forEach(System.out::println);
            System.out.println();


            // MostrarPilotosOrdenadosPorEdadDescendente(), que muestre el listado de pilotos
            //ordenado con edad de mayor a menor y su edad en el inicio de la temporada (año 2006).
            System.out.println("Mostrando todos los pilotos de la base de datos ordenados por edad descendente...");
            OperacionesCRUDMongoDB.mostrarPilotosOrdenadosPorEdadDescendente(collection);
            System.out.println();

            // MostrarPilotosConEdadMayorQue(), que reciba como parámetro un entero y muestre el
            //listado de pilotos con edad mayor o igual que ese entero en el inicio de la temporada (año
            //2006) ordenados de mayor a menor edad.
            System.out.println("Mostrando todos los pilotos de la base de datos con edad >= 30"+
                    " ordenados de mayor a menor edad...");
            OperacionesCRUDMongoDB.mostrarPilotosConEdadMayorQue(collection, 30);
            System.out.println();
        }
    }

}

