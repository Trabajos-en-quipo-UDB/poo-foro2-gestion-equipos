package sistemaequipos;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class InterfazUsuario {

    private final List<Equipo> equiposRegistrados = new ArrayList<>();
    private static final Font FUENTE_TEXTO = new Font("Segoe UI", Font.PLAIN, 14);

    public InterfazUsuario() {
        configurarEstilo();
    }

    // ↓↓↓ INTEGRANTE 5: PEGAR TU CODIGO AQUI ↓↓↓
    // Punto de entrada del sistema.
    public static void main(String[] args) {
        InterfazUsuario app = new InterfazUsuario();
        app.ejecutar();
    }

    // ↓↓↓ INTEGRANTE 3: PEGAR TU CODIGO AQUI ↓↓↓
    // Orquesta el flujo general de la aplicacion con un menu principal.
    private void ejecutar() {
        boolean activo = true;

        while (activo) {
            int opcion = mostrarMenuPrincipal();
            switch (opcion) {
                case 0:
                    registrarEquipo();
                    break;
                case 1:
                    mostrarReporte();
                    break;
                case 2:
                default:
                    activo = false;
                    mostrarMensaje("Sesion finalizada.", "Sistema de Equipos");
                    break;
            }
        }
    }

    private void configurarEstilo() {
        UIManager.put("OptionPane.background", new Color(244, 248, 255));
        UIManager.put("Panel.background", new Color(244, 248, 255));
        UIManager.put("OptionPane.messageForeground", new Color(25, 45, 80));
        UIManager.put("Label.foreground", new Color(25, 45, 80));
        UIManager.put("OptionPane.messageFont", FUENTE_TEXTO);
        UIManager.put("OptionPane.buttonFont", FUENTE_TEXTO);
    }

    private int mostrarMenuPrincipal() {
        String[] opciones = {"Registrar equipo", "Ver reporte", "Salir"};
        String mensaje = "<html><div style='font-family:Segoe UI;font-size:14px;'>"
                + "<b>Sistema de Gestion de Equipos</b><br><br>"
                + "Selecciona una opcion para continuar:"
                + "</div></html>";

        int seleccion = JOptionPane.showOptionDialog(
                null,
                mensaje,
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        return seleccion == JOptionPane.CLOSED_OPTION ? 2 : seleccion;
    }

    private void registrarEquipo() {
        String[] tipos = {"Laptop", "Tablet", "Desktop"};
        int tipoSeleccionado = JOptionPane.showOptionDialog(
                null,
                "Selecciona el tipo de equipo a registrar:",
                "Tipo de Equipo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipos,
                tipos[0]
        );

        if (tipoSeleccionado == JOptionPane.CLOSED_OPTION) {
            return;
        }

        String marca = solicitarTextoNoVacio("Marca:", "Datos Comunes");
        String modelo = solicitarTextoNoVacio("Modelo:", "Datos Comunes");
        double precio = solicitarDecimalPositivo("Precio:", "Datos Comunes");
        String sistemaOperativo = solicitarTextoNoVacio("Sistema operativo:", "Datos Comunes");
        int memoriaRam = solicitarEnteroPositivo("Memoria RAM (GB):", "Datos Comunes");
        int almacenamiento = solicitarEnteroPositivo("Almacenamiento (GB):", "Datos Comunes");

        if (precio < 0 || memoriaRam < 0 || almacenamiento < 0) {
            mostrarMensaje("Registro cancelado por datos incompletos.", "Aviso");
            return;
        }

        Equipo nuevoEquipo;
        switch (tipoSeleccionado) {
            case 0:
                int bateria = solicitarEnteroPositivo("Capacidad de bateria (mAh):", "Datos Laptop");
                if (bateria < 0) {
                    mostrarMensaje("Registro cancelado.", "Aviso");
                    return;
                }
                nuevoEquipo = new Laptop(marca, modelo, precio, sistemaOperativo, memoriaRam, almacenamiento, bateria);
                break;
            case 1:
                double pantalla = solicitarDecimalPositivo("Tamano de pantalla (pulgadas):", "Datos Tablet");
                if (pantalla < 0) {
                    mostrarMensaje("Registro cancelado.", "Aviso");
                    return;
                }
                nuevoEquipo = new Tablet(marca, modelo, precio, sistemaOperativo, memoriaRam, almacenamiento, pantalla);
                break;
            case 2:
            default:
                String gabinete = solicitarTextoNoVacio("Tipo de gabinete (Torre, Mini-PC, etc.):", "Datos Desktop");
                nuevoEquipo = new Desktop(marca, modelo, precio, sistemaOperativo, memoriaRam, almacenamiento, gabinete);
                break;
        }

        equiposRegistrados.add(nuevoEquipo);
        mostrarMensaje("Equipo registrado exitosamente.", "Registro completado");
    }

    // ↓↓↓ INTEGRANTE 4: PEGAR TU CODIGO AQUI ↓↓↓
    // Validaciones de entrada para mantener consistencia de datos.
    private String solicitarTextoNoVacio(String mensaje, String titulo) {
        while (true) {
            String valor = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
            if (valor == null) {
                return "";
            }
            valor = valor.trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            mostrarMensaje("El valor no puede estar vacio.", titulo);
        }
    }

    private int solicitarEnteroPositivo(String mensaje, String titulo) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
            if (entrada == null) {
                return -1;
            }
            try {
                int valor = Integer.parseInt(entrada.trim());
                if (valor > 0) {
                    return valor;
                }
                mostrarMensaje("Ingresa un entero mayor que 0.", titulo);
            } catch (NumberFormatException ex) {
                mostrarMensaje("Ingresa un numero entero valido.", titulo);
            }
        }
    }

    private double solicitarDecimalPositivo(String mensaje, String titulo) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
            if (entrada == null) {
                return -1;
            }
            try {
                double valor = Double.parseDouble(entrada.trim());
                if (valor > 0) {
                    return valor;
                }
                mostrarMensaje("Ingresa un decimal mayor que 0.", titulo);
            } catch (NumberFormatException ex) {
                mostrarMensaje("Ingresa un numero decimal valido.", titulo);
            }
        }
    }

    // ↓↓↓ INTEGRANTE 5: PEGAR TU CODIGO AQUI ↓↓↓
    // Consolida los datos usando polimorfismo y muestra el reporte final.
    private void mostrarReporte() {
        if (equiposRegistrados.isEmpty()) {
            mostrarMensaje("No hay equipos registrados.", "Reporte");
            return;
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("REPORTE DE EQUIPOS REGISTRADOS\n");
        reporte.append("========================================\n\n");

        int contador = 1;
        for (Equipo equipo : equiposRegistrados) {
            reporte.append(contador)
                    .append(") ")
                    .append(equipo.aLineaReporte())
                    .append("\n\n");
            contador++;
        }

        JTextArea area = new JTextArea(reporte.toString(), 20, 70);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(FUENTE_TEXTO);

        JScrollPane scrollPane = new JScrollPane(area);
        JOptionPane.showMessageDialog(null, scrollPane, "Reporte", JOptionPane.INFORMATION_MESSAGE);
    }

    // ↓↓↓ INTEGRANTE 3: PEGAR TU CODIGO AQUI ↓↓↓
    // Metodo comun para mensajes informativos de la interfaz.
    private void mostrarMensaje(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    // ↓↓↓ INTEGRANTE 1: PEGAR TU CODIGO AQUI ↓↓↓
    // Resumen POO:
    // - Los atributos comunes viven en la superclase Equipo para evitar duplicacion.
    // - Cada subclase agrega su atributo unico para especializar el comportamiento.
    // - El reporte usa polimorfismo: una lista de Equipo invoca el detalle de cada tipo.
}
