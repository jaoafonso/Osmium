package modelo;

public class Publicacoes {
    private int id_publicacao;
    private int id_usuario;
    private String assunto;
    private String titulo;
    private String descricao;
    private String[] colunas = new String[]{"Assunto", "Usuário", "Título", "ID"};

    public int getId_publicacao() {
        return id_publicacao;
    }

    public void setId_publicacao(int id_publicacao) {
        this.id_publicacao = id_publicacao;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
}
