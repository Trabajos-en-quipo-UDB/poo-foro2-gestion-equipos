package sistemaequipos;

// ↓↓↓ INTEGRANTE 1: PEGAR TU CODIGO AQUI ↓↓↓
// Clase base abstracta para modelar atributos comunes de cualquier equipo.
public abstract class Equipo {
    // Marca: identifica el fabricante del equipo (polimorfismo con datos comunes).
    private final String marca;
    // Modelo: identifica la linea/version comercial del equipo.
    private final String modelo;
    // Precio: valor economico para reportes y comparaciones.
    private final double precio;
    // Sistema operativo: define el entorno de software base del dispositivo.
    private final String sistemaOperativo;
    // Memoria RAM: capacidad para multitarea y rendimiento.
    private final int memoriaRam;
    // Almacenamiento: capacidad para guardar archivos y aplicaciones.
    private final int almacenamiento;

    protected Equipo(
            String marca,
            String modelo,
            double precio,
            String sistemaOperativo,
            int memoriaRam,
            int almacenamiento
    ) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.sistemaOperativo = sistemaOperativo;
        this.memoriaRam = memoriaRam;
        this.almacenamiento = almacenamiento;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public int getMemoriaRam() {
        return memoriaRam;
    }

    public int getAlmacenamiento() {
        return almacenamiento;
    }

    public abstract String getTipoEquipo();

    public abstract String getDetalleUnico();

    public String aLineaReporte() {
        return "Tipo: " + getTipoEquipo()
                + " | Marca: " + marca
                + " | Modelo: " + modelo
                + " | Precio: $" + String.format("%.2f", precio)
                + " | S.O.: " + sistemaOperativo
                + " | RAM: " + memoriaRam + " GB"
                + " | Almacenamiento: " + almacenamiento + " GB"
                + " | " + getDetalleUnico();
    }
}
