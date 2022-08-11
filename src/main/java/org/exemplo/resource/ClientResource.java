package org.exemplo.resource;

import org.exemplo.model.Client;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.ok;

@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
public class ClientResource {
    @GET
    @Path("/list")
    public List<Client> listaClientes() {
        return Client.listAll();
    }

    @POST
    @Transactional
    public Response criaCliente(Client client) {
        Client.persist(client);
        return ok(client)
                .status(201)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response alteraCliente(@PathParam("id") Long id, Client cliente) {
        try {
            Client clienteData = Client.findById(id);
            clienteData.setAge(cliente.getAge());
            clienteData.setName(cliente.getName());
            clienteData.setVat(cliente.getVat());
            clienteData.setEmail(cliente.getEmail());
            clienteData.persist();
            return Response
                    .ok(clienteData)
                    .status(204)
                    .build();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response apagaCliente(@PathParam("id") Long id) {
        try {
            Client.deleteById(id);
            return Response.noContent().build();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}