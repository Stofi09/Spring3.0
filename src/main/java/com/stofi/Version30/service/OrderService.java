package com.stofi.Version30.service;
import com.stofi.Version30.model.LocalUser;
import com.stofi.Version30.model.WebOrder;
import com.stofi.Version30.model.dao.WebOrderDAO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {

    /** The Web Order DAO. */
    private WebOrderDAO webOrderDAO;

    /**
     * Constructor for spring injection.
     * @param webOrderDAO
     */
    public OrderService(WebOrderDAO webOrderDAO) {
        this.webOrderDAO = webOrderDAO;
    }

    /**
     * Gets the list of orders for a given user.
     * @param user The user to search for.
     * @return The list of orders.
     */
    public List<WebOrder> getOrders(LocalUser user) {
        return webOrderDAO.findByUser(user);
    }
}
