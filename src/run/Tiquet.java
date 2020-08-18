package run;

import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.ini4j.Ini;

public class Tiquet {

    private String query;
    private int tarifa;
    private String almacen;
    private String mayoreo;

    public Tiquet() {
        query = "SELECT A.CODARTICULO, A.REFPROVEEDOR, AL.CODBARRAS, ISNULL(A.COMPOSICION,'') AS UNIDAD, A.DESCRIPADIC, "
                + "CASE WHEN (LEFT(CONVERT(VARCHAR(8), GETDATE(), 112), 10) BETWEEN PV.DESDE2 AND  PV.HASTA2) "
                + "THEN ISNULL(PV.PNETO2,0) + ISNULL(A.CARGO1,0) "
                + "ELSE ISNULL(PV.PNETO,0) + ISNULL(A.CARGO1,0) "
                + "END AS PRECIO, "
                + "ISNULL(A.TACON,0) AS TACON, ISNULL(S.MAXIMO,0) AS MAXIMO, A.DESCATALOGADO "
                + "FROM ARTICULOS A "
                + "INNER JOIN PRECIOSVENTA PV "
                + "ON (A.CODARTICULO = PV.CODARTICULO) "
                + "INNER JOIN ARTICULOSLIN AL "
                + "ON A.CODARTICULO = AL.CODARTICULO "
                + "LEFT JOIN STOCKS S "
                + "ON A.CODARTICULO = S.CODARTICULO "
                + "WHERE  PV.TALLA = '.' AND PV.COLOR = '.' "
                + "AND S.TALLA = '.' AND S.COLOR = '.' "
                + "AND AL.TALLA = '.' AND AL.COLOR = '.' "
                + "AND S.CODALMACEN = ? AND PV.IDTARIFAV = ? "
                + "AND (A.REFPROVEEDOR = ? OR AL.CODBARRAS = ? OR AL.CODBARRAS2 = ? OR AL.CODBARRAS3 = ? )";
        tarifa = 0;
    }

    public Tiquet(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getMayoreo() {
        return mayoreo;
    }

    public void setMayoreo(String mayoreo) {
        this.mayoreo = mayoreo;
    }

    @Override
    public String toString() {
        return "Tiquet " + query;
    }
    
        public void cargarIni() {
        try {
            final String filename = "Parametros.ini";
            Ini ini = new Ini(new FileReader(filename));
            tarifa = Integer.parseInt(ini.get("TARIFA").get("CODIGO"));
            almacen = ini.get("TARIFA").get("ALMACEN");
            mayoreo = ini.get("TARIFA").get("MAYOREO");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se encuentra o está dañado el archivo de parámetros...");
            ex.printStackTrace();
        }
    }

}
