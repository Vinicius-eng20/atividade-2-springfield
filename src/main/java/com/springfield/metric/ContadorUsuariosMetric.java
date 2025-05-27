package com.springfield.metric;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class ContadorUsuariosMetric {
    public ContadorUsuariosMetric(MeterRegistry registry) {
        registry.gauge("UsuarioRepository::count", () -> 42); 
    }
}