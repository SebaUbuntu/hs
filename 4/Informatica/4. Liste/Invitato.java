public class Invitato {
    private String nome;
    private char sesso;
    private String telefono;

    public Invitato(String nome, char sesso, String telefono) {
        this.nome = nome;
        this.sesso = sesso;
        this.telefono = telefono;
    }

    public Invitato(Invitato invitato) {
        this.nome = invitato.getNome();
        this.sesso = invitato.getSesso();
        this.telefono = invitato.getTelefono();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public char getSesso() {
        return sesso;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public String toString() {
        return nome + " " + sesso + " " + telefono;
    }
}
