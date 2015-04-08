package br.com.malaDiretaService.service;

import java.io.Serializable;
import java.util.List;

import br.com.malaDiretaService.dao.LojaDAO;
import br.com.malaDiretaService.model.Loja;

/**
 * @author Cristian
 *
 */
public class LojaService implements Serializable {

	private static final long	serialVersionUID	= -476208148426581751L;

	private LojaDAO				lojaDAO;

	public LojaService() {
		lojaDAO = new LojaDAO();
	}

	public List<Loja> listarTodos() {
		return lojaDAO.listar();
	}
	
	public Loja buscarLojaPorId(Integer idLoja){
		return lojaDAO.buscarPorId(idLoja);
	} 
}
