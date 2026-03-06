package sistemaequipos;

// ↓↓↓ INTEGRANTE 2: PEGAR TU CODIGO AQUI ↓↓↓
// Hereda de Equipo y agrega el atributo unico de pantalla.
public class Tablet extends Equipo {
    // tamanoPantalla: medida diagonal en pulgadas para usabilidad.
    private final double tamanoPantalla;

    public Tablet(
            String marca,
            String modelo,
            double precio,
            String sistemaOperativo,
            int memoriaRam,
            int almacenamiento,
            double tamanoPantalla
    ) {
        super(marca, modelo, precio, sistemaOperativo, memoriaRam, almacenamiento);
        this.tamanoPantalla = tamanoPantalla;
    }

    public double getTamanoPantalla() {
        return tamanoPantalla;
    }

    @Override
    public String getTipoEquipo() {
        return "Tablet";
    }

    @Override
    public String getDetalleUnico() {
        return "Pantalla: " + String.format("%.1f", tamanoPantalla) + " pulgadas";
    }
}
