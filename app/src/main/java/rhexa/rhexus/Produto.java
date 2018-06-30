package rhexa.rhexus;

import android.app.Activity;
import android.os.Bundle;

import java.sql.Blob;

public class Produto{
    private Long id;
    private String nome;
    private String desc;
    private double custo;
    private double quantidade;
    private String codigo;
    private double preco;
    private double margem;
    private Blob image;

    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String DESC = "desc";
    public static final String CODIGO = "codigo";
    public static final String CUSTO = "custo";
    public static final String QUANTIDADE = "quantidade";
    public static final String PRECO = "preco";
    public static final String MARGEM = "margem";
    public static final String IMAGE = "image";
    public static final String TABELA = "produto";
    public static final String[] COLUNAS = {ID,NOME,DESC,CODIGO,CUSTO,QUANTIDADE,PRECO,MARGEM};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getMargem() {
        return margem;
    }

    public void setMargem(double margem) {
        this.margem = margem;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
