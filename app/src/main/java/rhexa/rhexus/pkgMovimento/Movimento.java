package rhexa.rhexus.pkgMovimento;

public class Movimento {

    private Long id;
    private String tipo;
    private String dataMovimentacao;
    private Long produtoId;
    private Long pedidoProduto;
    private Float quantidade;
    private Float valor;

    public static final String ID = "id";
    public static final String TIPO = "tipo";
    public static final String DATAMOVIMENTACAO = "dataMovimentacao";
    public static final String PRODUTOID = "produtoId";
    public static final String PEDIDOPRODUTO = "pedidoProduto";
    public static final String QUANTIDADE = "quantidade";
    public static final String VALOR = "valor";
    public static final String TABELA = "movimento";
    public static final String[] COLUNAS = {ID,TIPO,DATAMOVIMENTACAO,PRODUTOID,QUANTIDADE,VALOR};

    public Movimento() {    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(String dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Long getPedidoProduto() {
        return pedidoProduto;
    }

    public void setPedidoProduto(Long pedidoProduto) {
        this.pedidoProduto = pedidoProduto;
    }
}
