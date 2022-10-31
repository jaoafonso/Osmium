package modelo;

public class Mensagens {
    private int id_mensagem;
    private int id_remetente;
    private int id_destinatario;
    private String mensagem;
    private boolean lida;
    private String[] colunas = new String[]{"Remetente", "Mensagem"};

    public int getId_mensagem() {
        return id_mensagem;
    }

    public void setId_mensagem(int id_mensagem) {
        this.id_mensagem = id_mensagem;
    }

    public int getId_remetente() {
        return id_remetente;
    }

    public void setId_remetente(int id_remetente) {
        this.id_remetente = id_remetente;
    }

    public int getId_destinatario() {
        return id_destinatario;
    }

    public void setId_destinatario(int id_destinatario) {
        this.id_destinatario = id_destinatario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
}
