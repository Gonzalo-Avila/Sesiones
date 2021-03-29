package com.alkemy.app.repositories;

import com.alkemy.app.model.Paciente;
import com.alkemy.app.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosDAO extends JpaRepository<Usuario,Long> {
    Usuario findByUsername(String username);

}
