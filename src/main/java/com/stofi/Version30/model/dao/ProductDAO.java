package com.stofi.Version30.model.dao;

import com.stofi.Version30.model.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductDAO extends ListCrudRepository<Product,Long> {
}
