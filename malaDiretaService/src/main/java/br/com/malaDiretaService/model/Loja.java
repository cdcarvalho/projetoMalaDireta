
package br.com.malaDiretaService.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Cristian
 *
 */

@XmlRootElement
@Entity
@Table(name="tb_loja")
public class Loja implements Serializable {

	private static final long	serialVersionUID	= 1502655847284496204L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_loja")
    private Integer idLoja;
    
    @Column(name = "nome_loja")
    private String nomeLoja;
    
    @Column(name = "cnpj")
    private String cnpj;
    
    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;
    
    public Loja(){
    }

	public Integer getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(Integer idLoja) {
		this.idLoja = idLoja;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}