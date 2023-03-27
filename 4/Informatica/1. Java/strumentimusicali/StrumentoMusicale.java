package strumentimusicali;

/**
 * StrumentoMusicale
 */
abstract public class StrumentoMusicale {
	protected String nome;
	protected int codice;
	protected int annoInvenzione;
	protected String descrizione;

	public StrumentoMusicale(String nome, int codice, int annoInvenzione, String descrizione) {
		setNome(nome);
		setCodice(codice);
		setAnnoInvenzione(annoInvenzione);
		setDescrizione(descrizione);
	}

	public StrumentoMusicale() {
		this("", 0, 0, "");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		if (codice < 1 || codice > 1000)
			throw new IllegalArgumentException("Codice non valido");

		this.codice = codice;
	}

	public int getAnnoInvenzione() {
		return annoInvenzione;
	}

	public void setAnnoInvenzione(int annoInvenzione) {
		if (annoInvenzione < -2000 || annoInvenzione > 2000)
			throw new IllegalArgumentException("Anno non valido");

		this.annoInvenzione = annoInvenzione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Nome: " + nome
		       + ", codice: " + codice
			   + ", anno invenzione: " + annoInvenzione
			   + ", descrizione: " + descrizione;
	}
}
