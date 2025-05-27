package com.springfield.controller;

import com.springfield.model.ParcelaIPTU;
import com.springfield.repository.ParcelaIPTURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/iptu")
public class IPTUController {

    @Autowired
    private ParcelaIPTURepository repo;

    @PostMapping("/gerar")
    public void gerarParcelas(@RequestParam int idCidadao, @RequestParam boolean pagamentoUnico) {
        List<ParcelaIPTU> parcelas = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            ParcelaIPTU p = new ParcelaIPTU();
            p.setIdCidadao(idCidadao);
            p.setMes(i);
            p.setValor(pagamentoUnico ? (i == 1 ? 1000.00 : 0.0) : 1000.00);
            p.setPago(false);
            parcelas.add(p);
        }
        repo.saveAll(parcelas);
    }

    @PutMapping("/pagar/{id}")
    public void pagarParcela(@PathVariable Long id) {
        var parcela = repo.findById(id).orElseThrow();
        parcela.setPago(true);
        repo.save(parcela);
    }

    @GetMapping("/status/{idCidadao}")
    public Map<String, Object> status(@PathVariable int idCidadao) {
        long pagas = repo.countByIdCidadaoAndPagoTrue(idCidadao);
        List<ParcelaIPTU> pendentes = repo.findByIdCidadaoAndPagoFalse(idCidadao);
        double totalDevido = pendentes.stream().mapToDouble(ParcelaIPTU::getValor).sum();
        return Map.of("pagas", pagas, "totalDevido", totalDevido);
    }
}