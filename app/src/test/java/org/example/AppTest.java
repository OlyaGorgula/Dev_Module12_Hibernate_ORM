
package org.example;

import com.feature.dbhelper.DatabaseInitService;
import com.feature.dbhelper.crud.ClientCrudService;
import com.feature.dbhelper.crud.PlanetCrudService;
import com.feature.dbhelper.crud.TicketCrudService;
import com.feature.entity.*;
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
    private TicketCrudService crudTicket;

    @BeforeEach
    public void beforeEach() throws SQLException {
        final String connectionUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

        new DatabaseInitService().initDb(connectionUrl);

        connection = DriverManager
                .getConnection(connectionUrl);

        crudClient = new ClientCrudService();
        crudPlanet = new PlanetCrudService();
        crudTicket = new TicketCrudService();
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

    //********************** Planet  **********************************

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

    //******************* Ticket *****************************

    @Test void testWriteTicket() {

        Ticket newTicket = new Ticket();
        newTicket.setCreatedAt(crudTicket.getTime());
        newTicket.setClient(crudClient.getById(1L));
        newTicket.setFromPlanet(crudPlanet.getById("MARS"));
        newTicket.setToPlanet(crudPlanet.getById("VENERA"));

        long id = crudTicket.create(newTicket);

        Ticket saved = crudTicket.getById(id);

        //Assert
        Assertions.assertEquals(newTicket.getId(), saved.getId());
        Assertions.assertEquals(newTicket.getCreatedAt(), saved.getCreatedAt());
        Assertions.assertEquals(newTicket.getFromPlanet().getId(), saved.getFromPlanet().getId());
        Assertions.assertEquals(newTicket.getToPlanet().getId(), saved.getToPlanet().getId());
    }

    @Test public void testUpdateTicket(){
        Ticket newTicket = new Ticket();
        newTicket.setCreatedAt(crudTicket.getTime());
        newTicket.setClient(crudClient.getById(1L));
        newTicket.setFromPlanet(crudPlanet.getById("MARS"));
        newTicket.setToPlanet(crudPlanet.getById("VENERA"));

        long id = crudTicket.create(newTicket);
        newTicket.setId(id);

        crudClient.getById(1L);

        //Update
        newTicket.setClient(crudClient.getById(2L));
        newTicket.setFromPlanet(crudPlanet.getById("MERCURY"));
        newTicket.setToPlanet(crudPlanet.getById("MARS"));
        crudTicket.update(id, newTicket);

        Ticket updateTicket = crudTicket.getById(id);

        Assertions.assertEquals(crudPlanet.getById("MERCURY").getId(), updateTicket.getFromPlanet().getId());
        Assertions.assertEquals(crudPlanet.getById("MARS").getId(), updateTicket.getToPlanet().getId());
        Assertions.assertEquals(crudClient.getById(2L).getId(), updateTicket.getClient().getId());
    }

    @Test void testDeleteTicket() {
        Ticket newTicket = new Ticket();
        newTicket.setCreatedAt(crudTicket.getTime());
        newTicket.setClient(crudClient.getById(1L));
        newTicket.setFromPlanet(crudPlanet.getById("MARS"));
        newTicket.setToPlanet(crudPlanet.getById("VENERA"));

        long id = crudTicket.create(newTicket);
        crudTicket.deleteById(id);

        Assertions.assertNull(crudTicket.getById(id));
    }

    @Test void testWriteTicketNullClient() {

        Ticket newTicket = new Ticket();
        newTicket.setCreatedAt(crudTicket.getTime());
        newTicket.setClient(null);
        newTicket.setFromPlanet(crudPlanet.getById("MARS"));
        newTicket.setToPlanet(crudPlanet.getById("VENERA"));

        long id = crudTicket.create(newTicket);

        //Assert
        Assertions.assertEquals(-1, id);
    }

    @Test void testWriteTicketNullFromPlanet() {

        Ticket newTicket = new Ticket();
        newTicket.setCreatedAt(crudTicket.getTime());
        newTicket.setClient(crudClient.getById(1L));
        newTicket.setFromPlanet(null);
        newTicket.setToPlanet(crudPlanet.getById("VENERA"));

        long id = crudTicket.create(newTicket);

        //Assert
        Assertions.assertEquals(-1, id);
    }

    @Test void testWriteTicketNullToPlanet() {

        Ticket newTicket = new Ticket();
        newTicket.setCreatedAt(crudTicket.getTime());
        newTicket.setClient(crudClient.getById(1L));
        newTicket.setFromPlanet(crudPlanet.getById("MARS"));
        newTicket.setToPlanet(crudPlanet.getById("VENERAAAAA"));

        long id = crudTicket.create(newTicket);

        //Assert
        Assertions.assertEquals(-1, id);
    }
}
