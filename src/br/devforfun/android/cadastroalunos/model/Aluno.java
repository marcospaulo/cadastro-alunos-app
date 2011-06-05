package br.devforfun.android.cadastroalunos.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONStringer;

public class Aluno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3420181366713200064L;
	
	private Long id;
	private String nome;
	private String telefone;
	private String endereco;
	private String site;
	private double nota;
	private String foto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return id + " - " + nome;
	}

	public String toJSON() throws JSONException{
		JSONStringer j = new JSONStringer();
		j.object().key("id").value(id).
		key("nome").value(nome).
		key("telefone").value(telefone).
		key("endereco").value(endereco).
		key("foto").value(foto).
		key("nota").value(nota).endObject();
		return j.toString();
	}
}
