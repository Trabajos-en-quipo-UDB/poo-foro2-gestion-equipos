package sistemaequipos;

// ↓↓↓ INTEGRANTE 2: PEGAR TU CODIGO AQUI ↓↓↓
// Hereda de Equipo y agrega el atributo unico del gabinete.
public class Desktop extends Equipo {
    // tipoGabinete: define el formato fisico (Torre, Mini-PC, etc.).
    private final String tipoGabinete;

    public Desktop(
            String marca,
            String modelo,
            double precio,
            String sistemaOperativo,
            int memoriaRam,
            int almacenamiento,
            String tipoGabinete
    ) {
        super(marca, modelo, precio, sistemaOperativo, memoriaRam, almacenamiento);
        this.tipoGabinete = tipoGabinete;
    }

    public String getTipoGabinete() {
        return tipoGabinete;
    }

    @Override
    public String getTipoEquipo() {
        return "Desktop";
    }

    @Override
    public String getDetalleUnico() {
        return "Gabinete: " + tipoGabinete;
    }
}
