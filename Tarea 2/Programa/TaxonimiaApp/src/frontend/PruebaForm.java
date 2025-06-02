package frontend;

// imports de utilidades
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// imports de tu backend
import backend.LectorPreguntas;
import backend.PreguntaVF;
import frontend.ResultadoForm;
// imports Swing
import javax.swing.JOptionPane;



public class PruebaForm extends javax.swing.JFrame {

    // —–––––––––– Estado de la prueba ––––––––––—
    private List<PreguntaVF> preguntasFiltradas;
    private List<PreguntaVF> preguntasSeleccionadas;
    private int currentIndex = 0;
    private Map<Integer, Boolean> respuestasUsuario = new HashMap<>();
    // —––––––––––––––––––––––––––––––––––––––––—
    
    
    public PruebaForm() {
    // 1) Primero crea todos los componentes del GUI
    initComponents();
    setLocationRelativeTo(null);

    // 2) Ahora que btnPregunta1..btnPregunta10 ya existen, inicializa el array
    btnPreguntas = new javax.swing.JButton[] {
        btnPregunta1, btnPregunta2, btnPregunta3, btnPregunta4, btnPregunta5,
        btnPregunta6, btnPregunta7, btnPregunta8, btnPregunta9, btnPregunta10
    };
    
     // Capturamos el color original del primer botón
    defaultBtnColor = btnPregunta1.getBackground();
    
  
    // 3) Asigna el listener a cada botón para saltar a su índice
    for (int i = 0; i < btnPreguntas.length; i++) {
        final int idx = i;
        btnPreguntas[i].addActionListener(evt -> {
            // Guarda la respuesta actual solo si hay selección
            if (rbVerdadero.isSelected() || rbFalso.isSelected()) {
                respuestasUsuario.put(currentIndex, rbVerdadero.isSelected());
            }
            updateButtonColors();
            // Cambia el índice y muestra esa pregunta
            currentIndex = idx;
            mostrarPregunta(currentIndex);
        });
    }
}

        // Color por defecto de los botones (se capturará al arrancar)
    private java.awt.Color defaultBtnColor;
    // Color para marcar pregunta respondida
    private final java.awt.Color answeredBtnColor = java.awt.Color.GREEN;
        // Array para navegación rápida
    private javax.swing.JButton[] btnPreguntas;

    private void mostrarPregunta(int idx) {
        // 1) Obtenemos la pregunta
        PreguntaVF p = preguntasSeleccionadas.get(idx);

        // 1.1) Actualiza el label con número de pregunta
        jLabelNumero.setText("Pregunta " + (idx + 1)
            + " de " + preguntasSeleccionadas.size());

        // 2) Mostramos el enunciado (html permite wrap automático)
        jLabelEnunciado.setText("<html><body style='width:400px'>"
            + p.getEnunciado() + "</body></html>");

        // 3) Restauramos la selección previa, si existe
        buttonGroup1.clearSelection();
        Boolean resp = respuestasUsuario.get(idx);
        if (resp != null) {
            if (resp) rbVerdadero.setSelected(true);
            else      rbFalso.setSelected(true);
        }

        // 4) Enable/disable de navegación secuencial
        btnAnterior.setEnabled(idx > 0);
        btnSiguiente.setEnabled(idx < preguntasSeleccionadas.size() - 1);

        // 5) Refresca colores de los botones numerados
        updateButtonColors();
        }
    
        private void updateButtonColors() {
        for (int i = 0; i < btnPreguntas.length; i++) {
            boolean answered = respuestasUsuario.containsKey(i);
            btnPreguntas[i].setBackground(answered ? answeredBtnColor : defaultBtnColor);
        }
    }

    /**
     * Constructor que recibe la asignatura seleccionada.
     * @param asignatura la asignatura elegida en PrincipalForm
     */
    public PruebaForm(String asignatura) {
        this();
        lblAsignatura.setText("Asignatura: " + asignatura);

        // 1) Leemos todas las preguntas y filtramos por asignatura
        try {
            preguntasFiltradas = LectorPreguntas.leerPreguntasCSV("data/preguntas.csv");
            System.out.println("Testeo" + preguntasFiltradas);
            preguntasFiltradas.removeIf(p -> 
                !p.getAsignatura().equalsIgnoreCase(asignatura)
            );
            
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error al cargar preguntas:\n" + ex.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // 2) Barajamos y tomamos hasta 10
        Collections.shuffle(preguntasFiltradas);
        int total = Math.min(10, preguntasFiltradas.size());
        preguntasSeleccionadas = preguntasFiltradas.subList(0, total);

        // 3) Inicializamos índice y mostramos la primera
        currentIndex = 0;
        mostrarPregunta(currentIndex);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblAsignatura = new javax.swing.JLabel();
        jLabelEnunciado = new javax.swing.JLabel();
        rbVerdadero = new javax.swing.JRadioButton();
        rbFalso = new javax.swing.JRadioButton();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnTerminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnPregunta1 = new javax.swing.JButton();
        btnPregunta2 = new javax.swing.JButton();
        btnPregunta3 = new javax.swing.JButton();
        btnPregunta4 = new javax.swing.JButton();
        btnPregunta5 = new javax.swing.JButton();
        btnPregunta6 = new javax.swing.JButton();
        btnPregunta7 = new javax.swing.JButton();
        btnPregunta8 = new javax.swing.JButton();
        btnPregunta9 = new javax.swing.JButton();
        btnPregunta10 = new javax.swing.JButton();
        jLabelNumero = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAsignatura.setText("asignatura");

        jLabelEnunciado.setText("pregunta");

        buttonGroup1.add(rbVerdadero);
        rbVerdadero.setText("Verdadero");
        rbVerdadero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbVerdaderoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbFalso);
        rbFalso.setText("Falso");

        btnAnterior.setText("Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnTerminar.setText("Terminar Prueba");
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTerminar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblAsignatura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(rbVerdadero, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(102, 102, 102)
                                                .addComponent(rbFalso, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(1, 1, 1))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(btnAnterior)
                                .addGap(18, 18, 18)
                                .addComponent(btnSiguiente)))
                        .addGap(0, 79, Short.MAX_VALUE))
                    .addComponent(jLabelEnunciado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbVerdadero)
                    .addComponent(rbFalso))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior)
                    .addComponent(btnSiguiente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTerminar)
                .addGap(16, 16, 16))
        );

        btnPregunta1.setText("01");

        btnPregunta2.setText("02");

        btnPregunta3.setText("03");

        btnPregunta4.setText("04");

        btnPregunta5.setText("05");

        btnPregunta6.setText("06");

        btnPregunta7.setText("07");

        btnPregunta8.setText("08");

        btnPregunta9.setText("09");

        btnPregunta10.setText("10");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnPregunta9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPregunta10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnPregunta7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPregunta8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnPregunta5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPregunta6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnPregunta3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPregunta4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnPregunta1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPregunta2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPregunta1)
                    .addComponent(btnPregunta2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPregunta3)
                    .addComponent(btnPregunta4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPregunta5)
                    .addComponent(btnPregunta6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPregunta7)
                    .addComponent(btnPregunta8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPregunta9)
                    .addComponent(btnPregunta10))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jLabelNumero.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelNumero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNumero.setText("numero pregunta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbVerdaderoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbVerdaderoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbVerdaderoActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
                    // 1) Solo guardamos si hay selección
           if (rbVerdadero.isSelected() || rbFalso.isSelected()) {
               respuestasUsuario.put(currentIndex, rbVerdadero.isSelected());
               updateButtonColors();
           }
           // 2) Retrocedemos
           currentIndex--;
           mostrarPregunta(currentIndex);
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
            // 1) Solo guardamos si hay selección
         if (rbVerdadero.isSelected() || rbFalso.isSelected()) {
             respuestasUsuario.put(currentIndex, rbVerdadero.isSelected());
             updateButtonColors();
         }
         // 2) Avanzamos
         currentIndex++;
         mostrarPregunta(currentIndex);
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed
        
        if (rbVerdadero.isSelected() || rbFalso.isSelected()) {
             respuestasUsuario.put(currentIndex, rbVerdadero.isSelected());
         }
  
        ResultadoForm resForm = new ResultadoForm(
                preguntasSeleccionadas,
                respuestasUsuario
        );
        
        resForm.setVisible(true);
        this.dispose();
        
        
    
    }//GEN-LAST:event_btnTerminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnPregunta1;
    private javax.swing.JButton btnPregunta10;
    private javax.swing.JButton btnPregunta2;
    private javax.swing.JButton btnPregunta3;
    private javax.swing.JButton btnPregunta4;
    private javax.swing.JButton btnPregunta5;
    private javax.swing.JButton btnPregunta6;
    private javax.swing.JButton btnPregunta7;
    private javax.swing.JButton btnPregunta8;
    private javax.swing.JButton btnPregunta9;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnTerminar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabelEnunciado;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAsignatura;
    private javax.swing.JRadioButton rbFalso;
    private javax.swing.JRadioButton rbVerdadero;
    // End of variables declaration//GEN-END:variables
}
