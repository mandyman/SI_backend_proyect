package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.accidentes.daos.MultaDAO;
import es.uvigo.mei.accidentes.entidades.Multa;
import es.uvigo.mei.accidentes.servicios.MultaService;

@Service
public class MultaServiceImpl implements MultaService {

	@Autowired
	private MultaDAO multaDAO;

	@Override
	@Transactional
	public Multa crear(Multa multa){
		return multaDAO.save(multa);
	}

	@Override
	@Transactional
	public Multa modificar(Multa multa){
		return multaDAO.save(multa);
	}

	@Override
	@Transactional
	public void eliminar(Multa multa){
		multaDAO.delete(multa);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Multa> buscarPorId(Long id){
		return multaDAO.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Multa> buscarTodos(){
		return multaDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Multa> buscarPorTipo(String tipo){
		return multaDAO.findByTipo(tipo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Multa> buscarPorSancion(String sancion){
		return multaDAO.findBySancion(sancion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Multa> buscarPorAccidenteId(Long accidenteId){
		return multaDAO.findByAccidente(accidenteId);
	}

}