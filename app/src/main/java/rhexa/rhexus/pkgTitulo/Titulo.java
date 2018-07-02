package rhexa.rhexus.pkgTitulo;

public class Titulo {
    public long id;
    public String tipo;
    public String emissao;
    public String vencimento;
    public long pessoaID;
    public float valor;
    public float valorBaixa;

    public static final String ID = "id";
    public static final String TIPO = "tipo";
    public static final String EMISSAO = "emissao";
    public static final String VENCIMENTO = "vencimento";
    public static final String PESSOAID = "pessoaId";
    public static final String VALOR = "valor";
    public static final String VALORBAIXA = "valorBaixa";
    public static final String TABELA = "titulo";
    public static final String[] COLUNAS = {ID,TIPO,EMISSAO,VENCIMENTO,PESSOAID,VALOR,VALORBAIXA};

    public Titulo() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmissao() {
        return emissao;
    }

    public void setEmissao(String emissao) {
        this.emissao = emissao;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public long getPessoaID() {
        return pessoaID;
    }

    public void setPessoaID(long pessoaID) {
        this.pessoaID = pessoaID;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getValorBaixa() {
        return valorBaixa;
    }

    public void setValorBaixa(float valorBaixa) {
        this.valorBaixa = valorBaixa;
    }
}
