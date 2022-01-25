package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;
import es.uvigo.mei.accidentes.entidades.Accidente;

public interface AccidenteService {
	public Accidente crear(Accidente accidente);
	public Accidente modificar(Accidente accidente);
	public void eliminar(Accidente accidente);
	public Optional<Accidente> buscarPorId(Long id);
	public List<Accidente> buscarTodos();
	public List<Accidente> buscarPorProvincia(String provincia);
	public List<Accidente> buscarPorCodigoPostal(String codigoPostal);
	public List<Accidente> buscarPorDescripcion(String descripcion);

	/*public ArticuloAlmacen crearArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public ArticuloAlmacen crearArticuloAlmacen(Articulo articulo, Almacen almacen, Integer stock);
	public ArticuloAlmacen modificarArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public void eliminarArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public void eliminarArticuloAlmacen(Long idArticulo, Long idAlmacen);
	public Optional<ArticuloAlmacen> buscarArticuloAlmacenPorArticuloIdAlmacenId(Long articuloId, Long almacenId);
	public List<ArticuloAlmacen> buscarArticulosAlmacenPorAlmacenId(Long almacenId);
	public List<ArticuloAlmacen> buscarArticulosAlmacenPorArticuloId(Long articuloId);*/
}