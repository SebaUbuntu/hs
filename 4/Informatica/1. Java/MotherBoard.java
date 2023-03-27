public class MotherBoard {
    private String marca;
    private String modello;
    private String socket;
    private String tipoRam;

    public MotherBoard() {
        this.marca = "Sconosciuto";
        this.modello = "Sconosciuto";
        this.socket = "Sconosciuto";
        this.tipoRam = "ddr3";
    }

    public MotherBoard(String marca, String modello, String socket, String tipoRam) {
        this.marca = marca;
        this.modello = modello;
        this.socket = socket;
        this.tipoRam = tipoRam;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getTipoRam() {
        return tipoRam;
    }

    public void setTipoRam(String tipoRam) {
        if (tipoRam.equals("ddr3") || tipoRam.equals("ddr4") || tipoRam.equals("ddr5"))
            this.tipoRam = tipoRam;
    }

    public boolean isEqual(MotherBoard motherboard) {
        return (marca.equals(motherboard.marca)
                && modello.equals(motherboard.modello)
                && socket.equals(motherboard.socket)
                && tipoRam.equals(motherboard.tipoRam));
    }

    public String toString() {
        return "Marca: " + marca
               + ", modello: " + modello
               + ", socket: " + socket
               + ", tipo RAM: " + tipoRam;
    }
}
