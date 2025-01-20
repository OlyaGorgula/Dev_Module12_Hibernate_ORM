
package org.example;

import com.feature.dbhelper.DatabaseInitService;
import com.feature.dbhelper.crud.ClientCrudService;
import com.feature.dbhelper.crud.PlanetCrudService;
import com.feature.entity.Client;
import com.feature.entity.Planet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


class AppTest {
    private Connection connection;
    private ClientCrudService crudClient;
    private PlanetCrudService crudPlanet;

    @BeforeEach
    public void beforeEach() throws SQLException {
        final String connectionUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

        new DatabaseInitService().initDb(connectionUrl);

        connection = DriverManager
                .getConnection(connectionUrl);

        crudClient = new ClientCrudService();
        crudPlanet = new PlanetCrudService();
    }
    @AfterEach
    public void afterEach() throws SQLException {
        connection.close();
    }

    @Test void testWriteClient() {

        Client newClient = new Client();
        newClient.setName("Client1");

        long id = crudClient.create(newClient);
        Client saved = crudClient.getById(id);

        //Assert
        Assertions.assertEquals(id, saved.getId());
        Assertions.assertEquals(newClient.getName(), saved.getName());
    }

    @Test public void testUpdateClient(){
        Client client = new Client();
        client.setName("Client add");
        long id = crudClient.create(client);
        client.setId(id);

        //Update
        client.setName("Client Update");
        crudClient.update(id, client);

        Client updateClient = crudClient.getById(id);
        Assertions.assertEquals("Client Update", updateClient.getName());
    }

    @Test void testDeleteClient() {
        Client client = new Client();
        client.setName("TestNameDel");

        long id = crudClient.create(client);
        crudClient.deleteById(id);

        Assertions.assertNull(crudClient.getById(id));
    }

    //********************************************************

    @Test void testWritePlanet() {
        Planet newPlanet = new Planet();
        newPlanet.setId("ven54");
        newPlanet.setName("Venera");

        String id = crudPlanet.create(newPlanet);

        Planet saved = crudPlanet.getById(id);

        //Assert
        Assertions.assertEquals(newPlanet.getName(), saved.getName());
    }

    @Test void testCreatePlanetDouble() {
        Planet newPlanet = new Planet();
        newPlanet.setId("MARS");
        newPlanet.setName("MARS");

        String id = crudPlanet.create(newPlanet);

        //Assert
        Assertions.assertNull(id);
    }

    @Test void testCreatePlanetWithSymbol() {
        String id = "ven54-";

        Planet newPlanet = new Planet();
        newPlanet.setId(id);
        newPlanet.setName("Venera");

        id = crudPlanet.create(newPlanet);

        //Assert
        Assertions.assertNull(id);
    }

    @Test void testUpdatePlanet(){
        Planet newPlanet = new Planet();
        newPlanet.setId("ven");
        newPlanet.setName("ven");

        String id = crudPlanet.create(newPlanet);

        //Update
        newPlanet.setName("uranus");
        crudPlanet.update(id, newPlanet);

        Planet updatePlanet = crudPlanet.getById(id);
        Assertions.assertEquals("uranus", updatePlanet.getName());
    }

    @Test void testDeletePlanet() {
        Planet planet = new Planet();
        planet.setId("uran");
        planet.setName("uranus");
        String id = crudPlanet.create(planet);

        crudPlanet.deleteById(id);

        Assertions.assertNull(crudPlanet.getById(id));
    }
}
