package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;
import es.uvigo.mei.accidentes.entidades.Persona;

public interface PersonaService {
	public Persona crear(Persona persona);
	public Persona modificar(Persona persona);
	public void eliminar(Persona persona);
	public Optional<Persona> buscarPorId(Long id);
	public List<Persona> buscarTodos();
	public List<Persona> buscarPorNombre(String nombre);
	public List<Persona> buscarPorApellidos(String apellidos);
	public List<Persona> buscarPorDNI(String dni);
	public List<Persona> buscarPorProvincia(String provincia);
	public List<Persona> buscarPorCodigoPostal(String codigoPostal);

	/*public ArticuloAlmacen crearArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public ArticuloAlmacen crearArticuloAlmacen(Articulo articulo, Almacen almacen, Integer stock);
	public ArticuloAlmacen modificarArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public void eliminarArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public void eliminarArticuloAlmacen(Long idArticulo, Long idAlmacen);
	public Optional<ArticuloAlmacen> buscarArticuloAlmacenPorArticuloIdAlmacenId(Long articuloId, Long almacenId);
	public List<ArticuloAlmacen> buscarArticulosAlmacenPorAlmacenId(Long almacenId);
	public List<ArticuloAlmacen> buscarArticulosAlmacenPorArticuloId(Long articuloId);*/
}