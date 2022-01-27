package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;
import es.uvigo.mei.accidentes.entidades.AccidenteVehiculo;
import es.uvigo.mei.accidentes.entidades.AccidenteVehiculoId;

public interface AccidenteVehiculoService {
	public AccidenteVehiculo crear(AccidenteVehiculo accidenteVehiculo);
	public AccidenteVehiculo modificar(AccidenteVehiculo accidenteVehiculo);
	public void eliminar(AccidenteVehiculo accidenteVehiculo);
	public Optional<AccidenteVehiculo> buscarPorId(AccidenteVehiculoId accidenteVehiculoId);
	public List<AccidenteVehiculo> buscarTodos();

	public List<AccidenteVehiculo> buscarPorVehiculoEstado(String vehiculoEstado);
	public List<AccidenteVehiculo> buscarPorAccidente(long accidenteId);
	public List<AccidenteVehiculo> buscarPorVehiculo(long vehiculoId);

	/*public List<AccidentePersona> buscarPorProvincia(String provincia);
	public List<AccidentePersona> buscarPorCodigoPostal(String codigoPostal);
	public List<AccidentePersona> buscarPorDescripcion(String descripcion);*/

	/*public ArticuloAlmacen crearArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public ArticuloAlmacen crearArticuloAlmacen(Articulo articulo, Almacen almacen, Integer stock);
	public ArticuloAlmacen modificarArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public void eliminarArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public void eliminarArticuloAlmacen(Long idArticulo, Long idAlmacen);
	public Optional<ArticuloAlmacen> buscarArticuloAlmacenPorArticuloIdAlmacenId(Long articuloId, Long almacenId);
	public List<ArticuloAlmacen> buscarArticulosAlmacenPorAlmacenId(Long almacenId);
	public List<ArticuloAlmacen> buscarArticulosAlmacenPorArticuloId(Long articuloId);*/
}