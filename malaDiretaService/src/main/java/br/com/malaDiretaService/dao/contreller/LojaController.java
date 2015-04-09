package br.com.malaDiretaService.dao.contreller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private List<Loja> getLojas = new ArrayList<Loja>();
    
    public void teste() {
    	loja.setCnpj("58.069.360/0001-20");
    	loja.setNomeLoja("Loja V8");
    	loja.setEmail("v8@lojas.com.br");
    	loja.setTelefone("(32) 3041-0067");
    	getLojas.add(loja);

    	loja = new Loja();
    	loja.setCnpj("348.022.220/0001-44");
    	loja.setNomeLoja("Loja S.A");
    	loja.setEmail("SA@lojas.com.br");
    	loja.setTelefone("(32) 3041-0062");
    	getLojas.add(loja);
    	
    	loja = new Loja();
    	loja.setCnpj("56.346.112/0001-11");
    	loja.setNomeLoja("Loja 1120");
    	loja.setEmail("loja1120@lojas.com.br");
    	loja.setTelefone("(32) 3041-1120");
    	getLojas.add(loja);
    	
    	loja = new Loja();
    	loja.setCnpj("33.921.367/0001-11");
    	loja.setNomeLoja("Loja NOTA 10");
    	loja.setEmail("nota10@lojas.com.br");
    	loja.setTelefone("(32) 2233-3455");
    	getLojas.add(loja);
    	//lojaService.listarTodos();
	}
    
    public void limparLojas() {
    	getLojas.clear();
    	loja = new Loja();
    }

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public List<Loja> getGetLojas() {
		return getLojas;
	}

	public void setGetLojas(List<Loja> getLojas) {
		this.getLojas = getLojas;
	}
    

}
