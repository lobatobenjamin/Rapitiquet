package run;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Mayoreo {

    private int numeroArticulos;
    private float descuento;
    private float precio;
    private String sqlPromocionMayoreo = "SELECT DISTINCT P.DESCRIPCION, P.NUMEROARTICULOS, "
            + "P.IDGRUPO, CONVERT(FLOAT,REPLACE(CONVERT(varchar(5), A.VALOR),',','.')) AS VALOR "
            + "FROM PROMOCIONES P "
            + "INNER JOIN ACCIONESPROMOCION A "
            + "ON P.IDPROMOCION = A.IDPROMOCION "
            + "WHERE P.CONDICIONAPLICACION = 2 "
            + "AND CONVERT(DATE, GETDATE()) BETWEEN P.FECHAINICIAL AND P.FECHAFINAL "
            + "AND A.TIPOACCION = 4 "
            + "ORDER BY P.DESCRIPCION, P.NUMEROARTICULOS";

    public Mayoreo() {
    }

    public Mayoreo(int numeroArticulos, float descuento) {
        this.numeroArticulos = numeroArticulos;
        this.descuento = descuento;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public int getNumeroArticulos() {
        return numeroArticulos;
    }

    public void setNumeroArticulos(int numeroArticulos) {
        this.numeroArticulos = numeroArticulos;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        DecimalFormat formatPrecios = new DecimalFormat("'$'###,###");
        return numeroArticulos + " und: " +  formatPrecios.format(precio);
    }

    public List<Mayoreo> validarArticulo(Precio precio) {

        List<Mayoreo> mayoreos = new ArrayList<Mayoreo>();

        Mayoreo mayoreo;
        Conexion conexion = new Conexion().conectarICG();
        PreparedStatement ps = null;
        
        try {
            conexion.conectarSQL();
            ps = conexion.getConn().prepareStatement(sqlPromocionMayoreo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String vista = "VIEWGRUPO_" + rs.getString(3);
                String sqlGrupo = "SELECT CODARTICULO FROM " + vista + " WHERE CODARTICULO = ?";
                PreparedStatement psGrupo = conexion.getConn().prepareStatement(sqlGrupo);
                psGrupo.setInt(1, precio.getCodArticulo());
                ResultSet rsGrupo = psGrupo.executeQuery();
                
                if (rsGrupo.next()) {
                    mayoreo = new Mayoreo();
                    mayoreo.setNumeroArticulos(rs.getInt(2));
                    mayoreo.setDescuento(rs.getFloat(4));
                    mayoreo.setPrecio(precio.getPrecioVenta() * ((100 - mayoreo.getDescuento()) / 100));
                    mayoreos.add(mayoreo);
                    System.out.println(mayoreo);
                }
            }
            return mayoreos;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Registro", JOptionPane.INFORMATION_MESSAGE);
            return mayoreos;
        } finally {
            if (conexion.getConn() != null) {
                try {
                    conexion.getConn().close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }
}
