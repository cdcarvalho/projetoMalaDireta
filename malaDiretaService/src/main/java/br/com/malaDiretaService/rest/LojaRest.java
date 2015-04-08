package br.com.malaDiretaService.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.malaDiretaService.model.Loja;
import br.com.malaDiretaService.service.LojaService;

/**
 * @author Cristian
 *
 */
@Path("/loja")
public class LojaRest {
	
	LojaService lojaService = new LojaService();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Loja> listarLojas(){
		return lojaService.listarTodos();
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Loja buscarLojaPorId(@PathParam("id")Integer idLoja){
		return lojaService.buscarLojaPorId(idLoja);
		
	}
	


}
