package org.exemplo.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.exemplo.model.Client;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientRepository implements PanacheRepository<Client> {
}
