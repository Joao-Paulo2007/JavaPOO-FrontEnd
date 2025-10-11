package br.com.pdvfrontend.model;
public class Contato {
    /*atributos */
    private String telefone;
    private String email;
    private String endereco;

    /*constructor*/
    public Contato(String telefone, String email, String endereco){
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }
    /*getters*/
    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }
    public String getEndereco() {
        return endereco;
    }
    /*setters*/
    public void setTelefone (String telefone){
        this.telefone = telefone;
    }
    public void setEmail (String email){
        this.email = email;
    }
    public void setEndereco (String endereco){
        this.endereco = endereco;
    }
}