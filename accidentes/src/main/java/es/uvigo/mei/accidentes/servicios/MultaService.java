package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;
import es.uvigo.mei.accidentes.entidades.Multa;

public interface MultaService {
	public Multa crear(Multa multa);
	public Multa modificar(Multa multa);
	public void eliminar(Multa multa);
	public Optional<Multa> buscarPorId(Long id);
	public List<Multa> buscarTodos();
	public List<Multa> buscarPorTipo(String tipo);
	public List<Multa> buscarPorSancion(String sancion);
	public List<Multa> buscarPorAccidenteId(Long accidenteId);

	/*public ArticuloAlmacen crearArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public ArticuloAlmacen crearArticuloAlmacen(Articulo articulo, Almacen almacen, Integer stock);
	public ArticuloAlmacen modificarArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public void eliminarArticuloAlmacen(ArticuloAlmacen articuloAlmacen);
	public void eliminarArticuloAlmacen(Long idArticulo, Long idAlmacen);
	public Optional<ArticuloAlmacen> buscarArticuloAlmacenPorArticuloIdAlmacenId(Long articuloId, Long almacenId);
	public List<ArticuloAlmacen> buscarArticulosAlmacenPorAlmacenId(Long almacenId);
	public List<ArticuloAlmacen> buscarArticulosAlmacenPorArticuloId(Long articuloId);*/
}