package modelo;

public class Usuario {
    
    private int id_usuario;
    private String nome_usuario;
    private String email_usuario;
    private String senha_usuario;
    private String desc_usuario;
    private String dataNasc_usuario;
    private int foto_usuario;
    private int banner_usuario;
    private boolean administrador;
    private boolean perfil_concluido;

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

    public String getDataNasc_usuario() {
        return dataNasc_usuario;
    }

    public void setDataNasc_usuario(String dataNasc_usuario) {
        this.dataNasc_usuario = dataNasc_usuario;
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

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isPerfil_concluido() {
        return perfil_concluido;
    }

    public void setPerfil_concluido(boolean perfil_concluido) {
        this.perfil_concluido = perfil_concluido;
    }
}