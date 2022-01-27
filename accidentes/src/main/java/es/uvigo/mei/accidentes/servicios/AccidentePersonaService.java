package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;
import es.uvigo.mei.accidentes.entidades.AccidentePersona;
import es.uvigo.mei.accidentes.entidades.AccidentePersonaId;

public interface AccidentePersonaService {
	public AccidentePersona crear(AccidentePersona accidentePersona);
	public AccidentePersona modificar(AccidentePersona accidentePersona);
	public void eliminar(AccidentePersona accidentePersona);
	public Optional<AccidentePersona> buscarPorId(AccidentePersonaId accidentePersonaId);
	public List<AccidentePersona> buscarTodos();

	public List<AccidentePersona> buscarPorTipo(String tipo);
	public List<AccidentePersona> buscarPorGravedad(String tipo);
	public List<AccidentePersona> buscarPorAccidente(long accidenteId);
	public List<AccidentePersona> buscarPorPersona(long personaId);

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