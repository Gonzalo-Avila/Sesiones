package com.alkemy.app.repositories;

import com.alkemy.app.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientesDAO extends JpaRepository<Paciente,Long> { }
