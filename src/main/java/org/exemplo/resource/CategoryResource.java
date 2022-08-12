package org.exemplo.resource;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.exemplo.model.Category;
import org.exemplo.model.Client;
import org.exemplo.repository.CategoryRespository;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {
    @Inject
    CategoryRespository categoryRespository;

    @GET
    @Path("/list")
    public List<Category> listaCategorias() { return categoryRespository.listAll(); }

    @POST
    @Transactional
    public Response criaCategoria(Category category) {
        categoryRespository.persist(category);
        return Response.ok().status(201).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response alteraCategoria(@PathParam("id") Long id, Category category) {
        try {
            Category categoryData = categoryRespository.findById(id);
            categoryData.setNome(category.getNome());

            categoryRespository.persist(categoryData);
            return Response.ok().status(204).build();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }

    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response apagaCategoria(@PathParam("id") Long id) {
        try {
            categoryRespository.deleteById(id);
            return Response.ok().status(204).build();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
