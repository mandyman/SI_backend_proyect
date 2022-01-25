package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;
import es.uvigo.mei.accidentes.entidades.Vehiculo;

public interface VehiculoService {
	public Vehiculo crear(Vehiculo vehiculo);
	public Vehiculo modificar(Vehiculo vehiculo);
	public void eliminar(Vehiculo vehiculo);
	public Optional<Vehiculo> buscarPorId(Long id);
	public List<Vehiculo> buscarTodos();
	public List<Vehiculo> buscarPorModelo(String modelo);
	public List<Vehiculo> buscarPorMatricula(String matricula);
	public List<Vehiculo> buscarPorConductorId(Long conductorId);

	/*public ArticuloAlmacen crearArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public ArticuloAlmacen crearArticuloAlmacen(Articulo articulo, Almacen almacen, Integer stock);
	public ArticuloAlmacen modificarArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public void eliminarArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public void eliminarArticuloAlmacen(Long idArticulo, Long idAlmacen);
	public Optional<ArticuloAlmacen> buscarArticuloAlmacenPorArticuloIdAlmacenId(Long articuloId, Long almacenId);
	public List<ArticuloAlmacen> buscarArticulosAlmacenPorAlmacenId(Long almacenId);
	public List<ArticuloAlmacen> buscarArticulosAlmacenPorArticuloId(Long articuloId);*/
}