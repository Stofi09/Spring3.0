package com.stofi.Version30.model.dao;

import com.stofi.Version30.model.LocalUser;
import com.stofi.Version30.model.VerificationToken;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface VerificationDAO  extends ListCrudRepository<VerificationToken, Long> {

        Optional<VerificationToken> findByToken(String token);

        void deleteByUser(LocalUser user);
}
