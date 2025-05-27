package com.springfield.repository;

import com.springfield.model.ParcelaIPTU;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParcelaIPTURepository extends JpaRepository<ParcelaIPTU, Long> {
    List<ParcelaIPTU> findByIdCidadao(int idCidadao);
    long countByIdCidadaoAndPagoTrue(int idCidadao);
    List<ParcelaIPTU> findByIdCidadaoAndPagoFalse(int idCidadao);
}