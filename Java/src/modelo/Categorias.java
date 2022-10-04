package modelo;

public class Categorias {
    
    private int id_categoria;
    private String nome_categoria;
    private String[] colunas = new String[]{"Categorias de Jogos"};

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }
}