package run;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Precio {

    private int codArticulo;
    private String refProveedor;
    private String descripcion;
    private String codBarras;
    private String unidad;
    private String porPeso;
    private float medida;
    private String composicion;
    private float precioVenta;
    private float maximo;
    private String descatalogado;

    public Precio() {
    }

    public int getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRefProveedor() {
        return refProveedor;
    }

    public void setRefProveedor(String refProveedor) {
        this.refProveedor = refProveedor;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getPorPeso() {
        return porPeso;
    }

    public void setPorPeso(String porPeso) {
        this.porPeso = porPeso;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public float getMedida() {
        return medida;
    }

    public void setMedida(float medida) {
        this.medida = medida;
    }

    public float getPrecioUnidad() {
        medida = Float.parseFloat(composicion);
        return (float) precioVenta / medida;
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

    public float getMaximo() {
        return maximo;
    }

    public void setMaximo(float maximo) {
        this.maximo = maximo;
    }

    public String getDescatalogado() {
        if (this.descatalogado.equals("T")) {
            return "(T)";
        }else{
            return "";
        }
    }

    public void setDescatalogado(String descatalogado) {
        this.descatalogado = descatalogado;
    }

    @Override
    public String toString() {
        return "codArticulo: " + codArticulo + ", refProveedor: " + refProveedor + ", "
                + "descripcion: " + descripcion + ", codBarras: " + codBarras
                + ", precio: " + precioVenta + " medida: " + composicion ;
    }

    public Precio consultarPrecio(String codBarra, Tiquet tiquet) {

        Precio precio = new Precio();
        PreparedStatement statement = null;

        try {
            Conexion conexion = new Conexion().conectarICG();

            conexion.conectarSQL();
            statement = conexion.getConn().prepareStatement(tiquet.getQuery());
            statement.setString(1, tiquet.getAlmacen());
            statement.setInt(2, tiquet.getTarifa());
            statement.setString(3, codBarra);
            statement.setString(4, codBarra);
            statement.setString(5, codBarra);
            statement.setString(6, codBarra);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                precio.setCodArticulo(rs.getInt("CODARTICULO"));
                precio.setRefProveedor(rs.getString("REFPROVEEDOR"));
                precio.setCodBarras(rs.getString("CODBARRAS"));
                precio.setUnidad(rs.getString("UNIDAD"));
                precio.setDescripcion(rs.getString("DESCRIPADIC"));
                precio.setPrecioVenta(rs.getFloat("PRECIO"));
                precio.setComposicion(rs.getString("TACON"));
                precio.setMaximo(rs.getFloat("MAXIMO"));
                precio.setDescatalogado(rs.getString("DESCATALOGADO"));
                System.out.println(precio);
            } else {
                System.out.println("No existe el articulo");
            }

            conexion.cerrarConeccion();
            return precio;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Msj: " + e.getMessage(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }

        return precio;
    }
}
