package br.com.malaDiretaService.dao.contreller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.malaDiretaService.model.Loja;
import br.com.malaDiretaService.service.LojaService;
import br.com.malaDiretaService.util.Utilitario;

@ManagedBean(name = "lojaController")
@ViewScoped
public class LojaController implements Serializable {

    private static final long serialVersionUID = 8301865434469950945L;

    private LojaService lojaService = new LojaService();

    private Loja loja = new Loja();
    private List<Loja> lojas = new ArrayList<Loja>();

    public void salvar() {
	if (this.loja != null) {
	    lojaService.persistir(loja);
	}
	limparFormulario();
	Utilitario.mensagemOperacaoRealizadaSucesso();
    }
    
    public void excluir(Loja loja){
	lojaService.deletar(loja);
    }

    public List<Loja> listarLojas() {
	return lojaService.listarTodos();
    }

    public void limparFormulario() {
	lojas.clear();
	loja = new Loja();
    }

    public Loja getLoja() {
	return loja;
    }

    public void setLoja(Loja loja) {
	this.loja = loja;
    }

    public List<Loja> getGetLojas() {
	return lojas;
    }

    public void setGetLojas(List<Loja> getLojas) {
	this.lojas = getLojas;
    }
    

}
