package sistemaequipos;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class InterfazUsuario {

    private static final Font FUENTE_MENU = new Font("Arial", Font.BOLD, 18);

    public InterfazUsuario() {
        configurarEstiloDialogos();
    }

    public static void main(String[] args) {
        InterfazUsuario ui = new InterfazUsuario();
        ui.mostrarMenu();
    }

    private void configurarEstiloDialogos() {
        // AQUÍ SE CAMBIA EL COLOR
        UIManager.put("OptionPane.background", new Color(235, 245, 255));
        UIManager.put("Panel.background", new Color(235, 245, 255));
        UIManager.put("OptionPane.messageForeground", new Color(20, 40, 80));
        UIManager.put("Label.foreground", new Color(20, 40, 80));

        // Fuente más grande para texto y botones de los JOptionPane.
        UIManager.put("OptionPane.messageFont", FUENTE_MENU);
        UIManager.put("OptionPane.buttonFont", FUENTE_MENU);
    }

    /**
     * Muestra el menú principal del sistema y devuelve la opción seleccionada.
     *
     * Integrante 5 (Integrador):
     * - Desde la clase principal, llamar este método dentro del ciclo principal del programa.
     * - Usar el valor retornado para enrutar a: registrar, ver reporte o salir.
     *
     * Integrante 3 (Gestor de Datos):
     * - Preparar métodos para guardar/listar equipos, que serán invocados según la opción elegida.
     *
     * @return índice de opción (0: Registrar, 1: Ver Reporte, 2: Salir)
     */
    public int mostrarMenu() {
        String[] opciones = {"Registrar", "Ver Reporte", "Salir"};
        String mensajeMenu = "<html><span style='font-family:Arial;font-size:16px;font-weight:bold;'>"
                + "Seleccione una opción del sistema de registro de equipos:"
                + "</span></html>";

        int seleccion = JOptionPane.showOptionDialog(
                null,
                mensajeMenu,
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        return seleccion == JOptionPane.CLOSED_OPTION ? 2 : seleccion;
    }

    /**
     * Solicita los datos comunes de cualquier equipo (marca, modelo, precio).
     *
     * Integrante 1 (Arquitecto POO):
     * - Definir en la jerarquía de clases dónde se almacenan estos datos comunes
     *   (por ejemplo, clase base Equipo).
     *
     * Integrante 5 (Integrador):
     * - Tomar el arreglo retornado y construir/instanciar el objeto del tipo de equipo correspondiente.
     *
     * Integrante 4 (Validaciones y Errores):
     * - Si desean reglas más estrictas (rangos, formatos), conectar validaciones adicionales
     *   en los métodos auxiliares solicitarTexto/solicitarEntero/solicitarDecimal.
     *
     * @return arreglo con [0]=marca, [1]=modelo, [2]=precio (texto)
     */
    public String[] solicitarDatosComunes() {
        // PUNTO DE CONEXIÓN (Integrante 4): reforzar validaciones de texto/precio si el equipo lo requiere.
        String marca = solicitarTexto("Ingrese la marca del equipo:", "Registro de Equipo - Marca");
        String modelo = solicitarTexto("Ingrese el modelo del equipo:", "Registro de Equipo - Modelo");
        String precio = solicitarTexto("Ingrese el precio del equipo:", "Registro de Equipo - Precio");

        return new String[]{marca, modelo, precio};
    }

    /**
     * Solicita el atributo específico de Desktop: tipo de fuente de poder.
     *
     * Integrante 1 (Arquitecto POO):
     * - Asegurar que la clase Desktop tenga este atributo en su modelo.
     *
     * Integrante 5 (Integrador):
     * - Conectar el valor retornado al constructor/setter de Desktop.
     *
     * @return fuente de poder ingresada por el usuario
     */
    public String solicitarDatosDesktop() {
        return solicitarTexto("Ingrese el tipo de fuente de poder:", "Registro de Desktop");
    }

    /**
     * Solicita el atributo específico de Laptop: capacidad de batería (mAh).
     *
     * Integrante 1 (Arquitecto POO):
     * - Asegurar que la clase Laptop contemple este atributo numérico.
     *
     * Integrante 5 (Integrador):
     * - Conectar el entero retornado al constructor/setter de Laptop.
     *
     * Integrante 4 (Validaciones y Errores):
     * - Definir si se aceptan solo valores positivos y ajustar la validación en solicitarEntero.
     *
     * @return capacidad de batería en mAh (si cancela, retorna -1)
     */
    public int solicitarDatosLaptop() {
        return solicitarEntero("Ingrese la capacidad de batería (mAh):", "Registro de Laptop");
    }

    /**
     * Solicita el atributo específico de Tablet: tamaño de pantalla en pulgadas.
     *
     * Integrante 1 (Arquitecto POO):
     * - Asegurar que la clase Tablet incluya este atributo decimal.
     *
     * Integrante 5 (Integrador):
     * - Conectar el valor retornado al constructor/setter de Tablet.
     *
     * Integrante 4 (Validaciones y Errores):
     * - Definir mínimos/máximos válidos y ajustar la validación en solicitarDecimal.
     *
     * @return tamaño de pantalla en pulgadas (si cancela, retorna -1)
     */
    public double solicitarDatosTablet() {
        return solicitarDecimal("Ingrese el tamaño de pantalla (pulgadas):", "Registro de Tablet");
    }

    /**
     * Muestra el reporte de equipos en un JTextArea dentro de JScrollPane.
     *
     * Integrante 3 (Gestor de Datos):
     * - Debe construir el texto consolidado del reporte con los registros almacenados.
     *
     * Integrante 5 (Integrador):
     * - Llamar este método cuando el usuario elija "Ver Reporte", enviando el texto generado
     *   por el gestor de datos.
     *
     * @param textoReporte contenido completo del reporte a mostrar
     */
    public void mostrarReporte(String textoReporte) {
        JTextArea areaReporte = new JTextArea(textoReporte, 20, 50);
        areaReporte.setEditable(false);
        areaReporte.setLineWrap(true);
        areaReporte.setWrapStyleWord(true);

        JScrollPane panelConScroll = new JScrollPane(areaReporte);

        JOptionPane.showMessageDialog(
                null,
                panelConScroll,
                "Reporte de Equipos Registrados",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Solicita un texto no vacío al usuario.
     *
     * Integrante 4 (Validaciones y Errores):
     * - PUNTO DE VALIDACIÓN: aquí pueden agregar reglas como longitud mínima/máxima,
     *   caracteres permitidos o normalización de texto.
     *
     * Integrante 2 (GUI):
     * - Mantener consistencia visual de mensajes y títulos en la UI.
     *
     * @param mensaje texto del diálogo
     * @param titulo título del diálogo
     * @return texto válido; si cancela, retorna cadena vacía
     */
    private String solicitarTexto(String mensaje, String titulo) {
        String valor;

        while (true) {
            valor = JOptionPane.showInputDialog(
                    null,
                    mensaje,
                    titulo,
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (valor == null) {
                return "";
            }

            valor = valor.trim();
            if (!valor.isEmpty()) {
                return valor;
            }

            JOptionPane.showMessageDialog(
                    null,
                    "El valor no puede estar vacío.",
                    titulo,
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    /**
     * Solicita un número entero y lo valida contra formato numérico.
     *
     * Integrante 4 (Validaciones y Errores):
     * - PUNTO DE VALIDACIÓN: agregar reglas de negocio, por ejemplo,
     *   "debe ser mayor que 0".
     *
     * Integrante 5 (Integrador):
     * - Si el método retorna -1 (cancelación), decidir si se cancela todo el registro.
     *
     * @param mensaje texto del diálogo
     * @param titulo título del diálogo
     * @return entero válido; si cancela, retorna -1
     */
    private int solicitarEntero(String mensaje, String titulo) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(
                    null,
                    mensaje,
                    titulo,
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (entrada == null) {
                return -1;
            }

            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Ingrese un número entero válido.",
                        titulo,
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    /**
     * Solicita un número decimal y lo valida contra formato numérico.
     *
     * Integrante 4 (Validaciones y Errores):
     * - PUNTO DE VALIDACIÓN: agregar reglas de rango (ej. pantalla > 0).
     *
     * Integrante 5 (Integrador):
     * - Si retorna -1 (cancelación), definir política de cancelación del flujo.
     *
     * @param mensaje texto del diálogo
     * @param titulo título del diálogo
     * @return decimal válido; si cancela, retorna -1
     */
    private double solicitarDecimal(String mensaje, String titulo) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(
                    null,
                    mensaje,
                    titulo,
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (entrada == null) {
                return -1;
            }

            try {
                return Double.parseDouble(entrada.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Ingrese un número decimal válido.",
                        titulo,
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }
}
