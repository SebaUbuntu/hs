package strumentimusicali;

public class StrumentoAria extends StrumentoMusicale {
	protected boolean isAncia;
	protected boolean isBocchino;
	protected boolean isCanna;

	public StrumentoAria(String nome, int codice, int annoInvenzione, String descrizione,
			boolean isAncia, boolean isBocchino, boolean isCanna) {
		super(nome, codice, annoInvenzione, descrizione);

		setIsAncia(isAncia);
		setIsBocchino(isBocchino);
		setIsCanna(isCanna);
	}

	public boolean getIsAncia() {
		return isAncia;
	}

	public void setIsAncia(boolean isAncia) {
		this.isAncia = isAncia;
	}

	public boolean getIsBocchino() {
		return isBocchino;
	}

	public void setIsBocchino(boolean isBocchino) {
		this.isBocchino = isBocchino;
	}

	public boolean getIsCanna() {
		return isCanna;
	}

	public void setIsCanna(boolean isCanna) {
		this.isCanna = isCanna;
	}

	@Override
	public String toString() {
		return super.toString()
			   + ", ancia: " + isAncia
			   + ", bocchino: " + isBocchino
			   + ", canna: " + isCanna;
	}
}
