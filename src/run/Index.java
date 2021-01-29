package run;

import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.util.JRLoader;

public class Index extends javax.swing.JFrame {

    private Tiquet tiquet;
    private Precio precio;
    List<Mayoreo> mayoreos;
    private static JasperReport ReportEAN13 = null, ReportEAN13Black = null, ReportEAN13BlackWhite = null;
    private static JasperReport ReportEAN8 = null, ReportEAN8Black = null, ReportEAN8BlackWhite = null;
    private static JasperReport ReportUPCA = null, ReportUPCABlack = null, ReportUPCABlackWhite = null;
    private static JasperReport ReportUPCE = null, ReportUPCEBlack = null, ReportUPCEBlackWhite = null;
    private static JasperReport ReportEstandar = null, ReportEstandarBlack = null, ReportEstandarBlackWhite = null;
    private static JasperReport ReportSinCodeBar = null, ReportSinCodeBarBlack = null, ReportSinCodeBarBlackWhite = null;
    private static JasperReport ReportEAN13Mayoreo = null;
    private static JasperReport ReportEAN8Mayoreo = null;
    private static JasperReport ReportUPCAMayoreo = null;
    private static JasperReport ReportUPCEMayoreo = null;
    private static JasperReport ReportEstandarMayoreo = null;
    private static JasperReport ReportSinCodeBarMayoreo = null;
    private static JasperPrint jasperPrint = null;
    private String UrlReportEAN13 = null, UrlReportEAN13Black = null, UrlReportEAN13BlackWhite = null;
    private String UrlReportEAN8 = null, UrlReportEAN8Black = null, UrlReportEAN8BlackWhite = null;
    private String UrlReportUPCA = null, UrlReportUPCABlack = null, UrlReportUPCABlackWhite = null;
    private String UrlReportUPCE = null, UrlReportUPCEBlack = null, UrlReportUPCEBlackWhite = null;
    private String UrlReportEstandar = null, UrlReportEstandarBlack = null, UrlReportEstandarBlackWhite = null;
    private String UrlReportSinCodeBar = null, UrlReportSinCodeBarBlack = null, UrlReportSinCodeBarBlackWhite = null;
    private String UrlReportEAN13Mayoreo = null;
    private String UrlReportEAN8Mayoreo = null;
    private String UrlReportUPCAMayoreo = null;
    private String UrlReportUPCEMayoreo = null;
    private String UrlReportEstandarMayoreo = null;
    private String UrlReportSinCodeBarMayoreo = null;
    public DecimalFormat formatPrecios = new DecimalFormat("'$'###,###");
    public DecimalFormat formatPreciosUM = new DecimalFormat("'$'###,###.#");
    public DecimalFormat formatNumeros = new DecimalFormat("###,###.##");

    public Index() {
        initComponents();
        this.setLocationRelativeTo(null);
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBarra = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RapiTiquet v1.8.1");

        txtBarra.setBackground(new java.awt.Color(180, 180, 233));
        txtBarra.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txtBarra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBarra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBarraKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Pase el articulo por el lector de códigos de");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText(" barras o digite el PLU del producto para");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText(" imprimir el tiquet");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(119, 119, 119))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2))
                            .addComponent(jLabel1))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3))
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/run/LogoRapi02.png"))); // NOI18N
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBarra)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBarraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarraKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtBarra.getText().trim().length() > 14) {
                JOptionPane.showMessageDialog(null, "El código de barras o PLU digitado sobrepasa la longitud permitida.");
            } else {
                if (txtBarra.getText().length() != 0) {
                    String codBarra = this.txtBarra.getText().trim();
                    precio = new Precio();
                    precio = precio.consultarPrecio(codBarra, tiquet);

                    if (precio.getCodArticulo() != 0) {
                        if (validarMayoreo() && tiquet.getMayoreo().equals("T")) {
                            System.out.println("Aplica mayoreo.");
                            ImprimirTiquetMayoreo();
                        } else {
                            System.out.println("No aplica mayoreo.");
                            ImprimirTiquet();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El articulo no existe");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No ha ingresado datos");
                }
            }
            txtBarra.setText("");
        }
    }//GEN-LAST:event_txtBarraKeyPressed

    public void init() {
        tiquet = new Tiquet();
        tiquet.cargarIni();
        if (tiquet.getAlmacen().isEmpty() || tiquet.getTarifa() == 0) {
            this.txtBarra.setEditable(false);
            JOptionPane.showMessageDialog(null, "Faltan parámetros por definir.");
        } else {
            UrlReportEAN13 = "src/reportes/Tiquet_EAN13.jasper";
            UrlReportEAN13Black = "src/reportes/Tiquet_EAN13_black.jasper";
            UrlReportEAN13BlackWhite = "src/reportes/Tiquet_EAN13_black_white.jasper";

            UrlReportEAN8 = "src/reportes/Tiquet_EAN8.jasper";
            UrlReportEAN8Black = "src/reportes/Tiquet_EAN8_black.jasper";
            UrlReportEAN8BlackWhite = "src/reportes/Tiquet_EAN8_black_white.jasper";

            UrlReportUPCA = "src/reportes/Tiquet_UPC-A.jasper";
            UrlReportUPCABlack = "src/reportes/Tiquet_UPC-A_black.jasper";
            UrlReportUPCABlackWhite = "src/reportes/Tiquet_UPC-A_black_white.jasper";

            UrlReportUPCE = "src/reportes/Tiquet_UPC-E.jasper";
            UrlReportUPCEBlack = "src/reportes/Tiquet_UPC-E_black.jasper";
            UrlReportUPCEBlackWhite = "src/reportes/Tiquet_UPC-E_black_white.jasper";

            UrlReportEstandar = "src/reportes/Tiquet_Estandar.jasper";
            UrlReportEstandarBlack = "src/reportes/Tiquet_Estandar_black.jasper";
            UrlReportEstandarBlackWhite = "src/reportes/Tiquet_Estandar_black_white.jasper";

            UrlReportSinCodeBar = "src/reportes/Tiquet_Sin_CodeBar.jasper";
            UrlReportSinCodeBarBlack = "src/reportes/Tiquet_Sin_CodeBar_black.jasper";
            UrlReportSinCodeBarBlackWhite = "src/reportes/Tiquet_Sin_CodeBar_black_white.jasper";

            UrlReportEAN13Mayoreo = "src/reportes/Tiquet_EAN13Mayoreo.jasper";
            UrlReportEAN8Mayoreo = "src/reportes/Tiquet_EAN8Mayoreo.jasper";
            UrlReportUPCAMayoreo = "src/reportes/Tiquet_UPC-AMayoreo.jasper";
            UrlReportUPCEMayoreo = "src/reportes/Tiquet_UPC-EMayoreo.jasper";
            UrlReportEstandarMayoreo = "src/reportes/Tiquet_EstandarMayoreo.jasper";
            UrlReportSinCodeBarMayoreo = "src/reportes/Tiquet_Sin_CodeBarMayoreo.jasper";

            try {
                ReportEAN13 = (JasperReport) JRLoader.loadObject(UrlReportEAN13);
                ReportEAN13Black = (JasperReport) JRLoader.loadObject(UrlReportEAN13Black);
                ReportEAN13BlackWhite = (JasperReport) JRLoader.loadObject(UrlReportEAN13BlackWhite);
                ReportEAN13Mayoreo = (JasperReport) JRLoader.loadObject(UrlReportEAN13Mayoreo);

                ReportEAN8 = (JasperReport) JRLoader.loadObject(UrlReportEAN8);
                ReportEAN8Black = (JasperReport) JRLoader.loadObject(UrlReportEAN8Black);
                ReportEAN8BlackWhite = (JasperReport) JRLoader.loadObject(UrlReportEAN8BlackWhite);
                ReportEAN8Mayoreo = (JasperReport) JRLoader.loadObject(UrlReportEAN8Mayoreo);

                ReportUPCA = (JasperReport) JRLoader.loadObject(UrlReportUPCA);
                ReportUPCABlack = (JasperReport) JRLoader.loadObject(UrlReportUPCABlack);
                ReportUPCABlackWhite = (JasperReport) JRLoader.loadObject(UrlReportUPCABlackWhite);
                ReportUPCAMayoreo = (JasperReport) JRLoader.loadObject(UrlReportUPCAMayoreo);

                ReportUPCE = (JasperReport) JRLoader.loadObject(UrlReportUPCE);
                ReportUPCEBlack = (JasperReport) JRLoader.loadObject(UrlReportUPCEBlack);
                ReportUPCEBlackWhite = (JasperReport) JRLoader.loadObject(UrlReportUPCEBlackWhite);
                ReportUPCEMayoreo = (JasperReport) JRLoader.loadObject(UrlReportUPCEMayoreo);

                ReportEstandar = (JasperReport) JRLoader.loadObject(UrlReportEstandar);
                ReportEstandarBlack = (JasperReport) JRLoader.loadObject(UrlReportEstandarBlack);
                ReportEstandarBlackWhite = (JasperReport) JRLoader.loadObject(UrlReportEstandarBlackWhite);
                ReportEstandarMayoreo = (JasperReport) JRLoader.loadObject(UrlReportEstandarMayoreo);

                ReportSinCodeBar = (JasperReport) JRLoader.loadObject(UrlReportSinCodeBar);
                ReportSinCodeBarBlack = (JasperReport) JRLoader.loadObject(UrlReportSinCodeBarBlack);
                ReportSinCodeBarBlackWhite = (JasperReport) JRLoader.loadObject(UrlReportSinCodeBarBlackWhite);
                ReportSinCodeBarMayoreo = (JasperReport) JRLoader.loadObject(UrlReportSinCodeBarMayoreo);
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "Existen problemas al cargar el informe. " + e.getMessage());
            }
        }
    }

    public synchronized void ImprimirTiquet() {
        String pUnidad = "";
        String pum = "";
        if (!precio.getComposicion().trim().isEmpty()) {
            if (Float.parseFloat(precio.getComposicion()) > 0) {
                pUnidad = String.valueOf(formatPreciosUM.format(precio.getPrecioUnidad()));
                pum = pUnidad + "/" + precio.getUnidad();
            }
        }
        System.out.println(pUnidad);
        jasperPrint = null;

        Map parametros = new HashMap();
        parametros.put("codBarra", precio.getCodBarras());
        parametros.put("descripcion", precio.getDescripcion());
        parametros.put("precio", formatPrecios.format(precio.getPrecioVenta()));
        parametros.put("unidad", precio.getUnidad().toLowerCase());
        parametros.put("pUnidad", pUnidad);
        parametros.put("referencia", precio.getRefProveedor());
        parametros.put("pum", pum);
        parametros.put("max", formatNumeros.format(precio.getMaximo()));
        parametros.put("descatalogado", precio.getDescatalogado());
        try {
            if (precio.getCodBarras().isEmpty()) {
                System.out.println("Sin barra");
                switch (precio.getCodAduana()) {
                    case 1:
                        jasperPrint = JasperFillManager.fillReport(ReportSinCodeBarBlack, parametros, new JREmptyDataSource());
                        break;
                    case 2:
                        jasperPrint = JasperFillManager.fillReport(ReportSinCodeBarBlackWhite, parametros, new JREmptyDataSource());
                        break;
                    default:
                        jasperPrint = JasperFillManager.fillReport(ReportSinCodeBar, parametros, new JREmptyDataSource());
                        break;

                }
            } else {
                System.out.println(precio.getCodBarras().length());
                switch (precio.getCodBarras().length()) {
                    case 8:
                        if (precio.getCodBarras().substring(0, 1).equals("0")) {
                            switch (precio.getCodAduana()) {
                                case 1:
                                    jasperPrint = JasperFillManager.fillReport(ReportUPCEBlack, parametros, new JREmptyDataSource());
                                    break;
                                case 2:
                                    jasperPrint = JasperFillManager.fillReport(ReportUPCEBlackWhite, parametros, new JREmptyDataSource());
                                    break;
                                default:
                                    jasperPrint = JasperFillManager.fillReport(ReportUPCE, parametros, new JREmptyDataSource());
                                    break;
                            }
                        } else {
                            switch (precio.getCodAduana()) {
                                case 1:
                                    jasperPrint = JasperFillManager.fillReport(ReportEAN8Black, parametros, new JREmptyDataSource());
                                    break;
                                case 2:
                                    jasperPrint = JasperFillManager.fillReport(ReportEAN8BlackWhite, parametros, new JREmptyDataSource());
                                    break;
                                default:
                                    jasperPrint = JasperFillManager.fillReport(ReportEAN8, parametros, new JREmptyDataSource());
                                    break;
                            }
                        }
                        break;

                    case 12:
                        System.out.println("UPC-A");
                        switch (precio.getCodAduana()) {
                            case 1:
                                jasperPrint = JasperFillManager.fillReport(ReportUPCABlack, parametros, new JREmptyDataSource());
                                break;
                            case 2:
                                jasperPrint = JasperFillManager.fillReport(ReportUPCABlackWhite, parametros, new JREmptyDataSource());
                                break;
                            default:
                                jasperPrint = JasperFillManager.fillReport(ReportUPCA, parametros, new JREmptyDataSource());
                                break;
                        }
                        break;

                    case 13:
                        System.out.println("EAN13");
                        switch (precio.getCodAduana()) {
                            case 1:
                                jasperPrint = JasperFillManager.fillReport(ReportEAN13Black, parametros, new JREmptyDataSource());
                                break;
                            case 2:
                                jasperPrint = JasperFillManager.fillReport(ReportEAN13BlackWhite, parametros, new JREmptyDataSource());
                                break;
                            default:
                                jasperPrint = JasperFillManager.fillReport(ReportEAN13, parametros, new JREmptyDataSource());
                                break;
                        }
                        break;

                    default:
                        System.out.println("Estandar");
                        switch (precio.getCodAduana()) {
                            case 1:
                                jasperPrint = JasperFillManager.fillReport(ReportEstandarBlack, parametros, new JREmptyDataSource());
                                break;
                            case 2:
                                jasperPrint = JasperFillManager.fillReport(ReportEstandarBlackWhite, parametros, new JREmptyDataSource());
                                break;
                            default:
                                jasperPrint = JasperFillManager.fillReport(ReportEstandar, parametros, new JREmptyDataSource());
                                break;
                        }
                        break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al procesar el formato.\nCausa: " + e.getMessage());
        }
        try {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "Msj: " + ex.getCause().getMessage());
            }
            jasperPrint.setOrientation(OrientationEnum.PORTRAIT);
            JasperPrintManager.printReport(jasperPrint, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Msj: " + ex.getCause().getMessage());
        }
    }

    public synchronized void ImprimirTiquetMayoreo() {
        String pUnidad = "";
        String pum = "";
        if (!precio.getComposicion().trim().isEmpty()) {
            if (Float.parseFloat(precio.getComposicion()) > 0) {
                pUnidad = String.valueOf(formatPreciosUM.format(precio.getPrecioUnidad()));
                pum = pUnidad + "/" + precio.getUnidad();
            }
        }
        System.out.println(pUnidad);
        jasperPrint = null;

        String preciosMayoreo[] = new String[2];
        for (int i = 0; i < 2; i++) {
            preciosMayoreo[i] = mayoreos.get(i).toString();
        }

        Map parametros = new HashMap();
        parametros.put("codBarra", precio.getCodBarras());
        parametros.put("descripcion", precio.getDescripcion());
        parametros.put("precio", formatPrecios.format(precio.getPrecioVenta()));
        parametros.put("unidad", precio.getUnidad().toLowerCase());
        parametros.put("pUnidad", pUnidad);
        parametros.put("referencia", precio.getRefProveedor());
        parametros.put("pum", pum);
        parametros.put("max", formatNumeros.format(precio.getMaximo()));
        parametros.put("precio_1", preciosMayoreo[0]);
        parametros.put("precio_2", preciosMayoreo[1]);

        try {
            if (precio.getCodBarras().isEmpty()) {
                System.out.println("Sin barra");
                jasperPrint = JasperFillManager.fillReport(ReportSinCodeBarMayoreo, parametros, new JREmptyDataSource());
            } else {
                System.out.println(precio.getCodBarras().length());
                switch (precio.getCodBarras().length()) {

                    case 8:
                        if (precio.getCodBarras().substring(0, 1).equals("0")) {
                            System.out.println("UPC-E");
                            jasperPrint = JasperFillManager.fillReport(ReportUPCEMayoreo, parametros, new JREmptyDataSource());
                        } else {
                            System.out.println("EAN8");
                            jasperPrint = JasperFillManager.fillReport(ReportEAN8Mayoreo, parametros, new JREmptyDataSource());
                        }
                        break;

                    case 12:
                        System.out.println("UPC-A");
                        jasperPrint = JasperFillManager.fillReport(ReportUPCAMayoreo, parametros, new JREmptyDataSource());
                        break;

                    case 13:
                        System.out.println("EAN13");
                        jasperPrint = JasperFillManager.fillReport(ReportEAN13Mayoreo, parametros, new JREmptyDataSource());
                        break;

                    default:
                        System.out.println("Estandar");
                        jasperPrint = JasperFillManager.fillReport(ReportEstandarMayoreo, parametros, new JREmptyDataSource());
                        break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al procesar el tiquet de precio.\nCausa:" + e.getMessage());
        }
        try {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "Msj: " + ex.getCause().getMessage());
            }
            jasperPrint.setOrientation(OrientationEnum.PORTRAIT);
            JasperPrintManager.printReport(jasperPrint, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Msj: " + ex.getCause().getMessage());
        }
    }

    public boolean validarMayoreo() {
        Mayoreo mayoreo = new Mayoreo();
        mayoreos = new ArrayList<Mayoreo>();
        mayoreos = mayoreo.validarArticulo(precio);
        if (!mayoreos.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Index().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtBarra;
    // End of variables declaration//GEN-END:variables
}
