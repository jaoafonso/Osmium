package modelo;

public class Usuario {
    
    private int id_usuario;
    private String nome_usuario;
    private String email_usuario;
    private String senha_usuario;
    private String desc_usuario;
    private int idade_usuario;
    private int foto_usuario;
    private int banner_usuario;
    private boolean tema_escuro;
    private boolean administrador;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public String getDesc_usuario() {
        return desc_usuario;
    }

    public void setDesc_usuario(String desc_usuario) {
        this.desc_usuario = desc_usuario;
    }

    public int getIdade_usuario() {
        return idade_usuario;
    }

    public void setIdade_usuario(int idade_usuario) {
        this.idade_usuario = idade_usuario;
    }

    public int getFoto_usuario() {
        return foto_usuario;
    }

    public void setFoto_usuario(int foto_usuario) {
        this.foto_usuario = foto_usuario;
    }

    public int getBanner_usuario() {
        return banner_usuario;
    }

    public void setBanner_usuario(int banner_usuario) {
        this.banner_usuario = banner_usuario;
    }

    public boolean isTema_escuro() {
        return tema_escuro;
    }

    public void setTema_escuro(boolean tema_escuro) {
        this.tema_escuro = tema_escuro;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
}