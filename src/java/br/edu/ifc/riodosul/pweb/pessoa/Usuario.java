
package br.edu.ifc.riodosul.pweb.pessoa;

import java.io.Serializable;

public class Usuario implements Serializable, Comparable<Usuario>{
    
    private int id;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private int admin;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @Override
    public int compareTo(Usuario o) {
        int saida = -2;
        if (this.getId() < o.getId()) {
            saida = -1;
        } else if (this.getId() == o.getId()) {
            saida = 0;
        } else {
            saida = 1;
        }
        return saida;
    }          
}
