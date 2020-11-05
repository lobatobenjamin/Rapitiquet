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
        } else {
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
                + ", precio: " + precioVenta + " medida: " + composicion;
    }

    public Precio consultarPrecio(String codBarra, Tiquet tiquet) {

        Precio precio = new Precio();
        Conexion conexion = new Conexion().conectarICG();

        try {

            conexion.conectarSQL();
            String sqlValidarUsaStock = "SELECT A.CODARTICULO, A.USASTOCKS "
                    + "FROM ARTICULOS A "
                    + "INNER JOIN ARTICULOSLIN AL "
                    + "ON A.CODARTICULO = AL.CODARTICULO "
                    + "WHERE AL.TALLA = '.' AND AL.COLOR = '.' "
                    + "AND (A.REFPROVEEDOR = ? OR AL.CODBARRAS = ? OR AL.CODBARRAS2 = ? OR AL.CODBARRAS3 = ? )";
            PreparedStatement psValidarUsaStock = conexion.getConn().prepareStatement(sqlValidarUsaStock);
            psValidarUsaStock.setString(1, codBarra);
            psValidarUsaStock.setString(2, codBarra);
            psValidarUsaStock.setString(3, codBarra);
            psValidarUsaStock.setString(4, codBarra);

            ResultSet rsUsaStock = psValidarUsaStock.executeQuery();
            if (rsUsaStock.next()) {
                    PreparedStatement psPrecio = null;
                if (rsUsaStock.getString("USASTOCKS").equals("T")) {
                    psPrecio = conexion.getConn().prepareStatement(tiquet.getQueryConStock());
                    psPrecio.setString(1, tiquet.getAlmacen());
                    psPrecio.setInt(2, tiquet.getTarifa());
                    psPrecio.setString(3, codBarra);
                    psPrecio.setString(4, codBarra);
                    psPrecio.setString(5, codBarra);
                    psPrecio.setString(6, codBarra);

                    ResultSet rsPrecio = psPrecio.executeQuery();
                    if (rsPrecio.next()) {
                        precio.setCodArticulo(rsPrecio.getInt("CODARTICULO"));
                        precio.setRefProveedor(rsPrecio.getString("REFPROVEEDOR"));
                        precio.setCodBarras(rsPrecio.getString("CODBARRAS"));
                        precio.setUnidad(rsPrecio.getString("UNIDAD"));
                        precio.setDescripcion(rsPrecio.getString("DESCRIPADIC"));
                        precio.setPrecioVenta(rsPrecio.getFloat("PRECIO"));
                        precio.setComposicion(rsPrecio.getString("TACON"));
                        precio.setMaximo(rsPrecio.getFloat("MAXIMO"));
                        precio.setDescatalogado(rsPrecio.getString("DESCATALOGADO"));
                    } else {
                        System.out.println("No existe el articulo");
                    }
                } else {
                    psPrecio = conexion.getConn().prepareStatement(tiquet.getQuerySinStock());
                    psPrecio.setInt(1, tiquet.getTarifa());
                    psPrecio.setString(2, codBarra);
                    psPrecio.setString(3, codBarra);
                    psPrecio.setString(4, codBarra);
                    psPrecio.setString(5, codBarra);

                    ResultSet rsPrecio = psPrecio.executeQuery();
                    if (rsPrecio.next()) {
                        precio.setCodArticulo(rsPrecio.getInt("CODARTICULO"));
                        precio.setRefProveedor(rsPrecio.getString("REFPROVEEDOR"));
                        precio.setCodBarras(rsPrecio.getString("CODBARRAS"));
                        precio.setUnidad(rsPrecio.getString("UNIDAD"));
                        precio.setDescripcion(rsPrecio.getString("DESCRIPADIC"));
                        precio.setPrecioVenta(rsPrecio.getFloat("PRECIO"));
                        precio.setComposicion(rsPrecio.getString("TACON"));
                        precio.setMaximo(rsPrecio.getFloat("MAXIMO"));
                        precio.setDescatalogado(rsPrecio.getString("DESCATALOGADO"));
                    } else {
                        System.out.println("No existe el articulo");
                    }
                }
            }
            System.out.println(precio);
            return precio;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Msj: " + e.getMessage(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            return new Precio();
        }finally{
            if (conexion.getConn() != null) {
                try {
                    conexion.getConn().close();
                } catch (Exception e) {
                    System.out.println("Error al cerrar la conexión. Causa:\n" + e.getMessage());
                }
            }
        }
    }
}
