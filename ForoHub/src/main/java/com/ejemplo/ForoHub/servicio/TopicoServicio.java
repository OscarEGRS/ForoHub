package com.ejemplo.ForoHub.servicio;

import com.ejemplo.ForoHub.modelo.Topico;
import com.ejemplo.ForoHub.repositorio.TopicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoServicio {
    @Autowired
    private TopicoRepositorio topicoRepositorio;

    public List<Topico> obtenerTodosLosTopicos() {
        return topicoRepositorio.findAll();
    }

    public Topico obtenerTopicoPorId(Long id) {
        return topicoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado con ID " + id));
    }

    public Topico crearTopico(Topico topico) {
        topico.setFechaCreacion(LocalDateTime.now());
        return topicoRepositorio.save(topico);
    }

    public Topico actualizarTopico(Long id, Topico detallesTopico) {
        Topico topico = topicoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado con ID " + id));

        topico.setTitulo(detallesTopico.getTitulo());
        topico.setMensaje(detallesTopico.getMensaje());

        return topicoRepositorio.save(topico);
    }

    public void eliminarTopico(Long id) {
        Topico topico = topicoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado con ID " + id));

        topicoRepositorio.delete(topico);
    }
}
