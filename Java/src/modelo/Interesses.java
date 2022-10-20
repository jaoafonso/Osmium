package modelo;

public class Interesses {
    private int id_usuario;
    private int id_categoria;
    private String[] colunas = new String[]{"Interesses"};

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_jogo) {
        this.id_categoria = id_jogo;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
}
