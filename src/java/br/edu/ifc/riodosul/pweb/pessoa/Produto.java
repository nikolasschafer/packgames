
package br.edu.ifc.riodosul.pweb.pessoa;

import java.io.Serializable;

public class Produto implements Serializable, Comparable<Produto> {
    
    private int id;
    private String nome;
    private double preco;
    private String url;
    private String descricao;
    private int usuario_id;
    private int categoria_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }
    
    @Override
    public int compareTo(Produto o) {
        int saida = -2;
        if (id < o.getId()) {
            saida = -1;
        } else if (id == o.getId()) {
            saida = 0;
        } else {
            saida = 1;
        }
        return saida;
    }
    
    
}
