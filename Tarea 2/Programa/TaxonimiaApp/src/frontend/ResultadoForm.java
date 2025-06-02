package frontend;

import backend.PreguntaVF;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;


public class ResultadoForm extends javax.swing.JDialog {
    private final List <PreguntaVF> preguntas;
    private final Map<Integer, Boolean> respuestas;
    
   public ResultadoForm(List<PreguntaVF> preguntas, Map<Integer, Boolean> respuestas) {
        this.preguntas  = preguntas;
        this.respuestas = respuestas;
        initComponents();
        setLocationRelativeTo(null);
        cargarResultado();
        cargarPorNivel();
    }


    
    private void cargarResultado(){
        DefaultTableModel model = (DefaultTableModel) tblResultados.getModel();
        model.setRowCount(0);
        
        int correctas = 0;
        for( int i = 0 ; i < preguntas.size() ; i++){
        PreguntaVF p = preguntas.get(i);
        Boolean resp = respuestas.get(i);
        boolean user =  (resp != null && resp);
        boolean ok = (user == p.isCorrecta());
        
        if(ok) correctas++;
        String resultado = ok ? "correctas": "incorrectas";
        model.addRow(new Object[]{
        i+1, resultado
        });
        }
        int total = preguntas.size();
        int incorrectas = total - correctas;
        double pst = 100.0*correctas /total;
        
        lblBuenas.setText("Buenas: " + correctas);
        lblMalas.setText("Malas: " + incorrectas);
        lblPorcentaje.setText(String.format("porcentaje:  %.2f%% ", pst));           
    }
    

        private void cargarPorNivel() {
            // 1) Preparamos el modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) tblPorNivel.getModel();
            model.setRowCount(0);

            // 2) Agrupamos: key = nivel, value = [total, correctas]
            Map<String, int[]> stats = new LinkedHashMap<>();
            for (int i = 0; i < preguntas.size(); i++) {
                PreguntaVF p = preguntas.get(i);
                String nivel = p.getTaxonomia();
                stats.putIfAbsent(nivel, new int[2]);
                stats.get(nivel)[0]++; // total por nivel

                Boolean resp = respuestas.get(i);
                boolean user = (resp != null && resp);
                if (user == p.isCorrecta()) {
                    stats.get(nivel)[1]++; // correctas por nivel
                }
            }

            // 3) Volcamos filas con porcentaje
            for (Map.Entry<String,int[]> e : stats.entrySet()) {
                String nivel = e.getKey();
                int total   = e.getValue()[0];
                int corr    = e.getValue()[1];
                double pct  = 100.0 * corr / total;
                model.addRow(new Object[]{ nivel, String.format("%.2f%%", pct) });
            }
}
    
    
  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblBuenas = new javax.swing.JLabel();
        lblMalas = new javax.swing.JLabel();
        lblPorcentaje = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPorNivel = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        btnVolverInicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Preguntas", "Resultado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblResultados);

        lblBuenas.setText("Buenas");

        lblMalas.setText("Malas");

        lblPorcentaje.setText("%");

        tblPorNivel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "NIVEL", "% Correctas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblPorNivel);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnVolverInicio.setText("Inicio");
        btnVolverInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblBuenas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMalas)
                        .addGap(95, 95, 95)
                        .addComponent(lblPorcentaje)
                        .addGap(46, 46, 46))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(btnVolverInicio)
                .addGap(72, 72, 72)
                .addComponent(btnSalir)
                .addGap(74, 74, 74))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuenas)
                    .addComponent(lblMalas)
                    .addComponent(lblPorcentaje))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnVolverInicio)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        btnSalir.addActionListener(e -> System.exit(0));
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnVolverInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverInicioActionPerformed
        // TODO add your handling code here:
        btnVolverInicio.addActionListener(e -> {
            PrincipalForm inicio = new PrincipalForm();
            inicio.setVisible(true);
            dispose(); // Cierra PantallaResultados
        });

    }//GEN-LAST:event_btnVolverInicioActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVolverInicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBuenas;
    private javax.swing.JLabel lblMalas;
    private javax.swing.JLabel lblPorcentaje;
    private javax.swing.JTable tblPorNivel;
    private javax.swing.JTable tblResultados;
    // End of variables declaration//GEN-END:variables
}
