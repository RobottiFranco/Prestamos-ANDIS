package com.andis.prestamos.services.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.andis.prestamos.models.Prestamo;
import com.andis.prestamos.services.PrestamoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.io.InputStream;

import java.util.stream.Collectors;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private List<Prestamo> prestamos;

    @PostConstruct
    public void init() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("prestamos.json")) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: prestamos.json");
            }
            ObjectMapper mapper = new ObjectMapper();
            prestamos = mapper.readValue(inputStream, new TypeReference<List<Prestamo>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to load prestamos.json", e);
        }
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        return prestamos;
    }

    @Override
    public Prestamo obtenerPorId(Long id) {
        return prestamos.stream()
                .filter(prestamo -> prestamo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Prestamo> obtenerPrestamosPorCliente(Long idCliente) {
        return prestamos.stream().filter(prestamo -> prestamo.getIdCliente().equals(idCliente)).toList();
    }

    @Override
    public List<Prestamo> obtenerPrestamosPagados() {
        return prestamos.stream().filter(prestamo -> prestamo.getPagado() == true).collect(Collectors.toList());
    }

    @Override
    public List<Prestamo> obtenerPrestamosNoPagados() {
        return prestamos.stream().filter(prestamo -> prestamo.getPagado() == false).collect(Collectors.toList());

    }

    @Override
    public boolean guardarPrestamo(Prestamo prestamo) {
        prestamo.setId(prestamos.size() + 1L);
        return prestamos.add(prestamo);
    }

    @Override
    public Prestamo actualizarPrestamo(Long id, Prestamo prestamo) {
        Prestamo prestamoActualizado = prestamos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (prestamoActualizado != null) {
            prestamoActualizado.setmontoPrestamo(prestamo.getmontoPrestamo());
            prestamoActualizado.setInteres(prestamo.getInteres());
            prestamoActualizado.setCuotas(prestamo.getCuotas());
            prestamoActualizado.setFechainicio(prestamo.getFechainicio());
            prestamoActualizado.setBalance(prestamo.getBalance());
            prestamoActualizado.setIdCliente(prestamo.getIdCliente());
            prestamoActualizado.setPagado(prestamo.getPagado());
        }

        return prestamoActualizado;
    }

    @Override
    public boolean eliminarPrestamo(Long id) {
        return prestamos.removeIf(prestamo -> prestamo.getId().equals(id));
    }

}
