package modelo;

public class Seguidores {

    private int id_usuario;
    private int id_seguidor;
    private String[] colunas = new String[]{"Seguidores Mútuos"};

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_seguidor() {
        return id_seguidor;
    }

    public void setId_seguidor(int id_seguidor) {
        this.id_seguidor = id_seguidor;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
}