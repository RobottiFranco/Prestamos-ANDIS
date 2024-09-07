package com.andis.prestamos.controllers;

import org.springframework.web.bind.annotation.*;
import com.andis.prestamos.models.Prestamo;
import com.andis.prestamos.services.PrestamoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping("/obtenerTodos")
    public ResponseEntity<List<Prestamo>> obtenerTodos() {
        List<Prestamo> PrestamoList = prestamoService.obtenerTodos();
        return ResponseEntity.ok(PrestamoList);
    }

    @GetMapping("/obtenerPorId/{id}")
    public ResponseEntity<Prestamo> obtenerPorId(@PathVariable Long id) {
        Prestamo PrestamoList = prestamoService.obtenerPorId(id);
        return ResponseEntity.ok(PrestamoList);
    }

    @GetMapping("/obtenerPorCliente/{idCliente}")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosPorCliente(@PathVariable Long idCliente) {
        List<Prestamo> PrestamoList = prestamoService.obtenerPrestamosPorCliente(idCliente);
        return ResponseEntity.ok(PrestamoList);
    }

    @GetMapping("/ObtenerPagados")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosPagados() {
        List<Prestamo> PrestamoList = prestamoService.obtenerPrestamosPagados();
        return ResponseEntity.ok(PrestamoList);
    }

    @GetMapping("/ObtenerNoPagados")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosNoPagados() {
        List<Prestamo> PrestamoList = prestamoService.obtenerPrestamosNoPagados();
        return ResponseEntity.ok(PrestamoList);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Boolean> guardarPrestamo(@RequestBody Prestamo prestamo) {
        boolean result = prestamoService.guardarPrestamo(prestamo);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        Prestamo result = prestamoService.actualizarPrestamo(id, prestamo);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Boolean> eliminarPrestamo(@PathVariable Long id) {
        boolean result = prestamoService.eliminarPrestamo(id);
        return ResponseEntity.ok(result);
    }

}
