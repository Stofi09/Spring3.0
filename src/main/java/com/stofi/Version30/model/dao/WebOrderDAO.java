package com.stofi.Version30.model.dao;

import com.stofi.Version30.model.LocalUser;
import com.stofi.Version30.model.WebOrder;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface WebOrderDAO extends ListCrudRepository<WebOrder,Long> {

    List<WebOrder> findByUser(LocalUser user);
}
