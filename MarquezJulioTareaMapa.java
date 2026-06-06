// Julio A. Marquez Torres

import java.util.Map;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;

public class MarquezJulioTareaMapa {

   private static String poblado_actual = "";
   private static String poblado_meta = "";
   private static int movimientos = 0;
   private static Map<String, Map<String, String>> mapa = new HashMap<>();

   private static JFrame ventana;
   private static JLabel mensajeLabel;
   private static JTextField inicioField;
   private static JTextField metaField;
   private static JTextField direccionField;
   private static JButton startButton;
   private static JButton submitButton;

   /*
    * Nombre: main
    * Propósito: Iniciar el programa, crear las conexiones del mapa y mostrar la ventana principal del juego.
    * Parámetros: String args[] - argumentos que pueden recibirse al ejecutar el programa.
    * Retorna: No retorna ningún valor.
    */
   public static void main(String args[]) {
      inicializarMapa();
      crearVentana();
   }

   /*
    * Nombre: crearVentana
    * Propósito: Crear la interfaz gráfica del juego, incluyendo la ventana, el título, la imagen,
    *             los campos de texto, los botones y los paneles donde se organizan los elementos.
    * Parámetros: No recibe parámetros.
    * Retorna: No retorna ningún valor.
    */
   public static void crearVentana() {
      ventana = new JFrame("Map Traveller Game");
      ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ventana.setSize(700, 750);
      ventana.setResizable(false);
      ventana.setLayout(new BorderLayout());

      JLabel tituloLabel = new JLabel("Discrete Mathematics II - Map Traveller Game", JLabel.CENTER);
      tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));

      ImageIcon imagenMapa = new ImageIcon("Mapa.png");
      JLabel imagenLabel = new JLabel(imagenMapa);
      imagenLabel.setHorizontalAlignment(JLabel.CENTER);

      mensajeLabel = new JLabel("Enter the initial town and destination town.", JLabel.CENTER);
      mensajeLabel.setFont(new Font("Arial", Font.BOLD, 16));

      // Panel para Initial town, Destination town y Start Game
      JPanel panelCentro = new JPanel();
      panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));

      JPanel panelPueblos = new JPanel(new FlowLayout());

      panelPueblos.add(new JLabel("Initial town:"));
      inicioField = new JTextField(10);
      panelPueblos.add(inicioField);

      panelPueblos.add(new JLabel("Destination town:"));
      metaField = new JTextField(10);
      panelPueblos.add(metaField);

      JPanel panelStart = new JPanel(new FlowLayout());

      startButton = new JButton("Start Game");
      startButton.setPreferredSize(new Dimension(120, 30));
      panelStart.add(startButton);

      panelCentro.add(panelPueblos);
      panelCentro.add(panelStart);

      // Panel para Move y Submit Move
      JPanel panelMovimiento = new JPanel();
      panelMovimiento.setLayout(new BoxLayout(panelMovimiento, BoxLayout.Y_AXIS));

      JPanel panelInput = new JPanel(new FlowLayout());
      panelInput.add(new JLabel("Move (n/s/e/w):"));

      direccionField = new JTextField(5);
      direccionField.setEnabled(false);
      panelInput.add(direccionField);

      JPanel panelBoton = new JPanel(new FlowLayout());

      submitButton = new JButton("Submit Move");
      submitButton.setPreferredSize(new Dimension(120, 30));
      submitButton.setEnabled(false);
      panelBoton.add(submitButton);

      panelMovimiento.add(panelInput);
      panelMovimiento.add(panelBoton);

      JPanel panelAbajo = new JPanel();
      panelAbajo.setLayout(new BorderLayout());
      panelAbajo.setPreferredSize(new Dimension(700, 200));

      panelAbajo.add(mensajeLabel, BorderLayout.NORTH);
      panelAbajo.add(panelCentro, BorderLayout.CENTER);
      panelAbajo.add(panelMovimiento, BorderLayout.SOUTH);

      ventana.add(tituloLabel, BorderLayout.NORTH);
      ventana.add(imagenLabel, BorderLayout.CENTER);
      ventana.add(panelAbajo, BorderLayout.SOUTH);

      startButton.addActionListener(e -> comenzarJuego());
      submitButton.addActionListener(e -> procesarMovimiento());
      direccionField.addActionListener(e -> procesarMovimiento());

      ventana.setVisible(true);
   }

   /*
    * Nombre: comenzarJuego
    * Propósito: Obtener el pueblo inicial y el pueblo destino escritos por el usuario,
    *             validar que los campos no estén vacíos y activar los controles para comenzar a moverse.
    * Parámetros: No recibe parámetros.
    * Retorna: No retorna ningún valor.
    */
   public static void comenzarJuego() {
      poblado_actual = inicioField.getText();
      poblado_meta = metaField.getText();
      movimientos = 0;

      if (poblado_actual.isEmpty() || poblado_meta.isEmpty()) {
         mensajeLabel.setText("Please enter both towns.");
         return;
      }

      mensajeLabel.setText("Starting at " + poblado_actual + ". Try to reach " + poblado_meta + ".");

      inicioField.setEnabled(false);
      metaField.setEnabled(false);
      startButton.setEnabled(false);

      direccionField.setEnabled(true);
      submitButton.setEnabled(true);
      direccionField.requestFocus();
   }

   /*
    * Nombre: procesarMovimiento
    * Propósito: Leer la dirección escrita por el usuario, verificar si el movimiento es válido,
    *             actualizar el pueblo actual, contar los movimientos y determinar si el jugador llegó al destino.
    * Parámetros: No recibe parámetros.
    * Retorna: No retorna ningún valor.
    */
   public static void procesarMovimiento() {
      String direccion = direccionField.getText().toLowerCase();
      direccionField.setText("");

      String siguiente_poblado = nextLand(poblado_actual, direccion);

      if (!siguiente_poblado.isEmpty()) {
         poblado_actual = siguiente_poblado;
         movimientos++;

         if (poblado_actual.equalsIgnoreCase(poblado_meta)) {
            mensajeLabel.setText(
                  "Congratulations! You arrived at " + poblado_meta
                        + " in " + movimientos + " movements."
            );

            direccionField.setEnabled(false);
            submitButton.setEnabled(false);

            int opcion = JOptionPane.showConfirmDialog(
                  ventana,
                  "Do you want to play again?",
                  "Play Again",
                  JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {
               reiniciarJuego();
            } else {
               System.exit(0);
            }

         } else {
            mensajeLabel.setText(
                  "You arrived at " + poblado_actual
                        + " in " + movimientos + " movements. Move: n / s / e / w"
            );
         }

      } else {
         mensajeLabel.setText("You cannot move in this direction. Try again.");
      }

      direccionField.requestFocus();
   }

   /*
    * Nombre: reiniciarJuego
    * Propósito: Limpiar los campos de texto, reiniciar las variables del juego y permitir que el usuario
    *             comience una nueva partida.
    * Parámetros: No recibe parámetros.
    * Retorna: No retorna ningún valor.
    */
   public static void reiniciarJuego() {
      poblado_actual = "";
      poblado_meta = "";
      movimientos = 0;

      inicioField.setText("");
      metaField.setText("");
      direccionField.setText("");

      inicioField.setEnabled(true);
      metaField.setEnabled(true);
      startButton.setEnabled(true);

      direccionField.setEnabled(false);
      submitButton.setEnabled(false);

      mensajeLabel.setText("Enter the initial town and destination town.");
      inicioField.requestFocus();
   }

   /*
    * Nombre: nextLand
    * Propósito: Buscar el próximo pueblo al que se puede mover el jugador según el pueblo actual
    *             y la dirección seleccionada.
    * Parámetros:
    *    land - pueblo actual donde se encuentra el jugador.
    *    direction - dirección que el usuario desea tomar. Puede ser n, s, e o w.
    * Retorna: Retorna el nombre del próximo pueblo si el movimiento es válido.
    *          Si el pueblo o la dirección no existen, retorna una cadena vacía.
    */
   public static String nextLand(String land, String direction) {
      Map<String, String> direcciones = mapa.get(land);

      if (direcciones == null) {
         return "";
      }

      return direcciones.getOrDefault(direction, "");
   }

   
    //Nombre: inicializarMapa
    //Propósito: Crear las conexiones entre los pueblos del mapa utilizando las direcciones norte, sur, este y oeste.
    //Parámetros: No recibe parámetros.
    //Retorna: No retorna ningún valor.
   private static void inicializarMapa() {
      mapa.put("Lam", Map.of("e", "Mit"));
      mapa.put("Mit", Map.of("e", "Fer", "s", "Mer"));
      mapa.put("Fer", Map.of("w", "Mit"));
      mapa.put("Lex", Map.of("e", "Hon", "s", "Maz", "n", "Lam"));
      mapa.put("Hon", Map.of("w", "Lex", "s", "Toy"));
      mapa.put("Maz", Map.of("e", "Toy", "n", "Lex"));
      mapa.put("Toy", Map.of("n", "Hon", "w", "Maz", "e", "Mer"));
      mapa.put("Mer", Map.of("e", "Che", "n", "Mit"));
      mapa.put("Che", Map.of("w", "Mer"));
   }
}