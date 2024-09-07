package com.andis.prestamos.services;

import java.util.List;
import com.andis.prestamos.models.Prestamo;

public interface PrestamoService {

    public List<Prestamo> obtenerTodos();

    public Prestamo obtenerPorId(Long id);

    public List<Prestamo> obtenerPrestamosPorCliente(Long idCliente);

    public List<Prestamo> obtenerPrestamosPagados();

    public List<Prestamo> obtenerPrestamosNoPagados();

    public boolean guardarPrestamo(Prestamo prestamo);

    public Prestamo actualizarPrestamo(Long id, Prestamo prestamo);

    public boolean eliminarPrestamo(Long id);

}
