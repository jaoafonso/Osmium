package modelo;

public class Convites {
    private int id_convite;
    private int remetente;
    private int destinatario;
    private int id_jogo;
    private String[] colunas = new String[]{"Convites", "ID"};

    public int getId_convite() {
        return id_convite;
    }

    public void setId_convite(int id_convite) {
        this.id_convite = id_convite;
    }

    public int getRemetente() {
        return remetente;
    }

    public void setRemetente(int remetente) {
        this.remetente = remetente;
    }

    public int getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(int destinatario) {
        this.destinatario = destinatario;
    }

    public int getId_jogo() {
        return id_jogo;
    }

    public void setId_jogo(int id_jogo) {
        this.id_jogo = id_jogo;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    
}
