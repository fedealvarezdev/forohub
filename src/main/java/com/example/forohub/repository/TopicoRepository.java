package com.example.forohub.repository;

import com.example.forohub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByPublicadoTrue(Pageable pageable);
    Topico findByPublicadoTrueAndId(Long id);
}
