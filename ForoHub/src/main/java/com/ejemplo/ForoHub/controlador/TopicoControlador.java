package com.ejemplo.ForoHub.controlador;

import com.ejemplo.ForoHub.modelo.Topico;
import com.ejemplo.ForoHub.servicio.TopicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/topicos")
public class TopicoControlador {
    @Autowired
    private TopicoServicio topicoServicio;

    @GetMapping
    public List<Topico> obtenerTodosLosTopicos() {
        return topicoServicio.obtenerTodosLosTopicos();
    }

    @GetMapping("/{id}")
    public Topico obtenerTopicoPorId(@PathVariable Long id) {
        return topicoServicio.obtenerTopicoPorId(id);
    }

    @PostMapping
    public ResponseEntity<Topico> crearTopico(@RequestBody Topico topico) {
        Topico topicoCreado = topicoServicio.crearTopico(topico);
        return ResponseEntity.created(URI.create("/api/topicos/" + topicoCreado.getId())).body(topicoCreado);
    }

    @PutMapping("/{id}")
    public Topico actualizarTopico(@PathVariable Long id, @RequestBody Topico detallesTopico) {
        return topicoServicio.actualizarTopico(id, detallesTopico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoServicio.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
