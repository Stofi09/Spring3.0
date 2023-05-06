package com.stofi.Version30.service;

import com.stofi.Version30.model.Product;
import com.stofi.Version30.model.dao.ProductDAO;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {

    /** The Product DAO. */
    private ProductDAO productDAO;

    /**
     * Constructor for spring injection.
     * @param productDAO
     */
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    /**
     * Gets the all products available.
     * @return The list of products.
     */
    public List<Product> getProducts() {
        return productDAO.findAll();
    }
}
