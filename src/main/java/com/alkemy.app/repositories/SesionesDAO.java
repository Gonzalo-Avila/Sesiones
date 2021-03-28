package com.alkemy.app.repositories;

import com.alkemy.app.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SesionesDAO extends JpaRepository<Sesion,Long> { }
