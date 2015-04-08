package br.com.malaDiretaService.dao.contreller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.malaDiretaService.service.LojaService;


@ManagedBean(name="lojaController")
@ViewScoped
public class LojaController implements Serializable {

    private static final long serialVersionUID = 8301865434469950945L;
    
    private LojaService lojaService = new LojaService();
    
    public void teste() {
    	lojaService.listarTodos();
	}
    

}
