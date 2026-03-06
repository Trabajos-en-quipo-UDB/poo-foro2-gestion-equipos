package sistemaequipos;

// ↓↓↓ INTEGRANTE 2: PEGAR TU CODIGO AQUI ↓↓↓
// Hereda de Equipo y agrega el atributo unico de bateria.
public class Laptop extends Equipo {
    // capacidadBateria: autonomia del equipo en mAh.
    private final int capacidadBateria;

    public Laptop(
            String marca,
            String modelo,
            double precio,
            String sistemaOperativo,
            int memoriaRam,
            int almacenamiento,
            int capacidadBateria
    ) {
        super(marca, modelo, precio, sistemaOperativo, memoriaRam, almacenamiento);
        this.capacidadBateria = capacidadBateria;
    }

    public int getCapacidadBateria() {
        return capacidadBateria;
    }

    @Override
    public String getTipoEquipo() {
        return "Laptop";
    }

    @Override
    public String getDetalleUnico() {
        return "Bateria: " + capacidadBateria + " mAh";
    }
}
