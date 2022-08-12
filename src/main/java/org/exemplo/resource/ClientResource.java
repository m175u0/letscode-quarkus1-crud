package org.exemplo.resource;

import org.exemplo.model.Client;
import org.exemplo.repository.ClientRepository;

import javax.inject.Inject;
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
    @Inject
    ClientRepository clientRepository;

    @GET
    @Path("/list")
    public List<Client> listaClientes() {
        return clientRepository.listAll();
    }

    @POST
    @Transactional
    public Response criaCliente(Client client) {
        clientRepository.persist(client);
        return ok(client)
                .status(201)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response alteraCliente(@PathParam("id") Long id, Client cliente) {
        try {
            Client clienteData = clientRepository.findById(id);
            clienteData.setAge(cliente.getAge());
            clienteData.setName(cliente.getName());
            clienteData.setVat(cliente.getVat());
            clienteData.setEmail(cliente.getEmail());
            clientRepository.persist(clienteData);
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
            clientRepository.deleteById(id);
            return Response.noContent().build();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}