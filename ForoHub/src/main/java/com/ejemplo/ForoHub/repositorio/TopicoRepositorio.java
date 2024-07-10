package com.ejemplo.ForoHub.repositorio;

import com.ejemplo.ForoHub.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepositorio extends JpaRepository<Topico, Long> {

}
