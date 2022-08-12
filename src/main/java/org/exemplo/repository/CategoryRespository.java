package org.exemplo.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.exemplo.model.Category;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryRespository implements PanacheRepository<Category> {
}
