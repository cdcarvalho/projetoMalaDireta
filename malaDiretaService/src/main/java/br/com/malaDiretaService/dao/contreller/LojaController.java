package br.com.malaDiretaService.dao.contreller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.malaDiretaService.model.Loja;
import br.com.malaDiretaService.service.LojaService;


@ManagedBean(name="lojaController")
@ViewScoped
public class LojaController implements Serializable {

    private static final long serialVersionUID = 8301865434469950945L;
    
    private LojaService lojaService = new LojaService();
    
    private Loja loja = new Loja();
    
    public void teste() {
    	loja.setCnpj("58.069.360/0001-20");
    	loja.setNomeLoja("Loja V8");
    	loja.setEmail("v8@lojas.com.br");
    	loja.setTelefone("(32) 3041-0067");
    	lojaService.listarTodos();
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}
    

}
