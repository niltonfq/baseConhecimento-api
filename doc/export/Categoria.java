import java.String;
import java.Date;
import java.util.Collection;

public class Categoria {

	private long id;

	private String nome;

	private Date dataCricacao;

	private Date dataAtualizacao;

	private Collection<Topico> topicoCategoria;

	private Categoria pai;

	private Collection<Categoria> filhos;

}
