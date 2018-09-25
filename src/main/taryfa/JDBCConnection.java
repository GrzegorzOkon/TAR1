package taryfa;

import java.io.Closeable;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class JDBCConnection implements Closeable {

    private final Connection connection;

    JDBCConnection(String url, Properties properties) {
        try {
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            throw new ApiException(e);
        }
    }

    Map<String, Object[]> response() {
        Map<String, Object[]> response = new TreeMap<String, Object[]>();
        String sql = "(SELECT 'TT_ADDCDTYPE_MEASURETYPE' as tabela, COUNT(*) as ilosc FROM TT_ADDCDTYPE_MEASURETYPE) union all" +
                "(SELECT 'TT_ADDCODE' as tabela, COUNT(*) as ilosc FROM TT_ADDCODE) union all" +
                "(SELECT 'TT_ADDCODE_DESCR' as tabela, COUNT(*) as ilosc FROM TT_ADDCODE_DESCR) union all" +
                "(SELECT 'TT_ADDCODETYPE' as tabela, COUNT(*) as ilosc FROM TT_ADDCODETYPE) union all" +
                "(SELECT 'TT_AGRTABLE' as tabela, COUNT(*) as ilosc FROM TT_AGRTABLE) union all" +
                "(SELECT 'TT_AGRTABLE_COMPONENT' as tabela, COUNT(*) as ilosc FROM TT_AGRTABLE_COMPONENT) union all" +
                "(SELECT 'TT_AGRTABLE_LINE' as tabela, COUNT(*) as ilosc FROM TT_AGRTABLE_LINE) union all" +
                "(SELECT 'TT_BASEREGULATION' as tabela, COUNT(*) as ilosc FROM TT_BASEREGULATION) union all" +
                "(SELECT 'TT_CEILING' as tabela, COUNT(*) as ilosc FROM TT_CEILING) union all" +
                "(SELECT 'TT_CERT' as tabela, COUNT(*) as ilosc FROM TT_CERT) union all" +
                "(SELECT 'TT_CERT_DESCR' as tabela, COUNT(*) as ilosc FROM TT_CERT_DESCR) union all" +
                "(SELECT 'TT_CERT_REPLACE' as tabela, COUNT(*) as ilosc FROM TT_CERT_REPLACE) union all" +
                "(SELECT 'TT_CERTTYPE' as tabela, COUNT(*) as ilosc FROM TT_CERTTYPE) union all" +
                "(SELECT 'TT_CODES_REPLACE' as tabela, COUNT(*) as ilosc FROM TT_CODES_REPLACE) union all" +
                "(SELECT 'TT_COMPLETE_ABROGREG' as tabela, COUNT(*) as ilosc FROM TT_COMPLETE_ABROGREG) union all" +
                "(SELECT 'TT_DICTIONARIES' as tabela, COUNT(*) as ilosc FROM TT_DICTIONARIES) union all" +
                "(SELECT 'TT_DOMAIN' as tabela, COUNT(*) as ilosc FROM TT_DOMAIN) union all" +
                "(SELECT 'TT_DOMAIN_VALUE' as tabela, COUNT(*) as ilosc FROM TT_DOMAIN_VALUE) union all" +
                "(SELECT 'TT_DUTYEXPR' as tabela, COUNT(*) as ilosc FROM TT_DUTYEXPR) union all" +
                "(SELECT 'TT_ERN' as tabela, COUNT(*) as ilosc FROM TT_ERN) union all" +
                "(SELECT 'TT_ERN_DESCR' as tabela, COUNT(*) as ilosc FROM TT_ERN_DESCR) union all" +
                "(SELECT 'TT_ERN_IND' as tabela, COUNT(*) as ilosc FROM TT_ERN_IND) union all" +
                "(SELECT 'TT_EXPLICIT_ABROG' as tabela, COUNT(*) as ilosc FROM TT_EXPLICIT_ABROG) union all" +
                "(SELECT 'TT_FOOTNASSOC_ADDCODE' as tabela, COUNT(*) as ilosc FROM TT_FOOTNASSOC_ADDCODE) union all" +
                "(SELECT 'TT_FOOTNASSOC_ERN' as tabela, COUNT(*) as ilosc FROM TT_FOOTNASSOC_ERN) union all" +
                "(SELECT 'TT_FOOTNASSOC_GOODSNOM' as tabela, COUNT(*) as ilosc FROM TT_FOOTNASSOC_GOODSNOM) union all" +
                "(SELECT 'TT_FOOTNASSOC_MEASURE' as tabela, COUNT(*) as ilosc FROM TT_FOOTNASSOC_MEASURE) union all" +
                "(SELECT 'TT_FOOTNASSOC_MEUR' as tabela, COUNT(*) as ilosc FROM TT_FOOTNASSOC_MEUR) union all" +
                "(SELECT 'TT_FOOTNOTE' as tabela, COUNT(*) as ilosc FROM TT_FOOTNOTE) union all" +
                "(SELECT 'TT_FOOTNOTE_DESCR' as tabela, COUNT(*) as ilosc FROM TT_FOOTNOTE_DESCR) union all" +
                "(SELECT 'TT_FOOTNTYPE' as tabela, COUNT(*) as ilosc FROM TT_FOOTNTYPE) union all" +
                "(SELECT 'TT_FTS_REGACTION' as tabela, COUNT(*) as ilosc FROM TT_FTS_REGACTION) union all" +
                "(SELECT 'TT_FULLTEMPSTOP_REG' as tabela, COUNT(*) as ilosc FROM TT_FULLTEMPSTOP_REG) union all" +
                "(SELECT 'TT_GAREA_COUNTRY' as tabela, COUNT(*) as ilosc FROM TT_GAREA_COUNTRY) union all" +
                "(SELECT 'TT_GAREA_GEONOM' as tabela, COUNT(*) as ilosc FROM TT_GAREA_GEONOM) union all" +
                "(SELECT 'TT_GAREA_GROUP' as tabela, COUNT(*) as ilosc FROM TT_GAREA_GROUP) union all" +
                "(SELECT 'TT_GAREADESCR_COUNTRY' as tabela, COUNT(*) as ilosc FROM TT_GAREADESCR_COUNTRY) union all" +
                "(SELECT 'TT_GAREADESCR_GROUP' as tabela, COUNT(*) as ilosc FROM TT_GAREADESCR_GROUP) union all" +
                "(SELECT 'TT_GAREAMEMB_COUNTRY' as tabela, COUNT(*) as ilosc FROM TT_GAREAMEMB_COUNTRY) union all" +
                "(SELECT 'TT_GEO_AREA_DESCR' as tabela, COUNT(*) as ilosc FROM TT_GEO_AREA_DESCR) union all" +
                "(SELECT 'TT_GEO_AREA_MEMB' as tabela, COUNT(*) as ilosc FROM TT_GEO_AREA_MEMB) union all" +
                "(SELECT 'TT_GEOGRAPHICAL_AREA' as tabela, COUNT(*) as ilosc FROM TT_GEOGRAPHICAL_AREA) union all" +
                "(SELECT 'TT_GOODSNOM' as tabela, COUNT(*) as ilosc FROM TT_GOODSNOM) union all" +
                "(SELECT 'TT_GOODSNOM_DESCR' as tabela, COUNT(*) as ilosc FROM TT_GOODSNOM_DESCR) union all" +
                "(SELECT 'TT_GOODSNOM_GROUP' as tabela, COUNT(*) as ilosc FROM TT_GOODSNOM_GROUP) union all" +
                "(SELECT 'TT_GOODSNOM_IND' as tabela, COUNT(*) as ilosc FROM TT_GOODSNOM_IND) union all" +
                "(SELECT 'TT_GOODSNOM_ORIG' as tabela, COUNT(*) as ilosc FROM TT_GOODSNOM_ORIG) union all" +
                "(SELECT 'TT_GOODSNOM_SENSPERIOD' as tabela, COUNT(*) as ilosc FROM TT_GOODSNOM_SENSPERIOD) union all" +
                "(SELECT 'TT_GOODSNOM_SUCC' as tabela, COUNT(*) as ilosc FROM TT_GOODSNOM_SUCC) union all" +
                "(SELECT 'TT_GOODSNOMGROUP_MEMB' as tabela, COUNT(*) as ilosc FROM TT_GOODSNOMGROUP_MEMB) union all" +
                "(SELECT 'TT_LANG' as tabela, COUNT(*) as ilosc FROM TT_LANG) union all" +
                "(SELECT 'TT_LANG_DESCR' as tabela, COUNT(*) as ilosc FROM TT_LANG_DESCR) union all" +
                "(SELECT 'TT_LOAD_LOG' as tabela, COUNT(*) as ilosc FROM TT_LOAD_LOG) union all" +
                "(SELECT 'TT_MEASURE' as tabela, COUNT(*) as ilosc FROM TT_MEASURE) union all" +
                "(SELECT 'TT_MEASURE_ACTION' as tabela, COUNT(*) as ilosc FROM TT_MEASURE_ACTION) union all" +
                "(SELECT 'TT_MEASURE_COMP' as tabela, COUNT(*) as ilosc FROM TT_MEASURE_COMP) union all" +
                "(SELECT 'TT_MEASURE_COND' as tabela, COUNT(*) as ilosc FROM TT_MEASURE_COND) union all" +
                "(SELECT 'TT_MEASURE_EXCL_GEO_AREA' as tabela, COUNT(*) as ilosc FROM TT_MEASURE_EXCL_GEO_AREA) union all" +
                "(SELECT 'TT_MEASURE_EXCLCR' as tabela, COUNT(*) as ilosc FROM TT_MEASURE_EXCLCR) union all" +
                "(SELECT 'TT_MEASURE_PARTTEMPSTOP' as tabela, COUNT(*) as ilosc FROM TT_MEASURE_PARTTEMPSTOP) union all" +
                "(SELECT 'TT_MEASURECOND_CODE' as tabela, COUNT(*) as ilosc FROM TT_MEASURECOND_CODE) union all" +
                "(SELECT 'TT_MEASURECOND_COMP' as tabela, COUNT(*) as ilosc FROM TT_MEASURECOND_COMP) union all" +
                "(SELECT 'TT_MEASUREMENT' as tabela, COUNT(*) as ilosc FROM TT_MEASUREMENT) union all" +
                "(SELECT 'TT_MEASUREMENT_UNIT' as tabela, COUNT(*) as ilosc FROM TT_MEASUREMENT_UNIT) union all" +
                "(SELECT 'TT_MEASUREMENTUNIT_QUAL' as tabela, COUNT(*) as ilosc FROM TT_MEASUREMENTUNIT_QUAL) union all" +
                "(SELECT 'TT_MEASURETYPE' as tabela, COUNT(*) as ilosc FROM TT_MEASURETYPE) union all" +
                "(SELECT 'TT_MEASURETYPE_SERIES' as tabela, COUNT(*) as ilosc FROM TT_MEASURETYPE_SERIES) union all" +
                "(SELECT 'TT_MEUR_ADDCODE' as tabela, COUNT(*) as ilosc FROM TT_MEUR_ADDCODE) union all" +
                "(SELECT 'TT_MEUR_HEAD' as tabela, COUNT(*) as ilosc FROM TT_MEUR_HEAD) union all" +
                "(SELECT 'TT_MEUR_SUBHEAD' as tabela, COUNT(*) as ilosc FROM TT_MEUR_SUBHEAD) union all" +
                "(SELECT 'TT_MEURTABLE_CELLCOMP' as tabela, COUNT(*) as ilosc FROM TT_MEURTABLE_CELLCOMP) union all" +
                "(SELECT 'TT_MEURTABLEPLAN' as tabela, COUNT(*) as ilosc FROM TT_MEURTABLEPLAN) union all" +
                "(SELECT 'TT_MODIF_REGULATION' as tabela, COUNT(*) as ilosc FROM TT_MODIF_REGULATION) union all" +
                "(SELECT 'TT_MONETARY_EXCHPERIOD' as tabela, COUNT(*) as ilosc FROM TT_MONETARY_EXCHPERIOD) union all" +
                "(SELECT 'TT_MONETARY_EXCHRATE' as tabela, COUNT(*) as ilosc FROM TT_MONETARY_EXCHRATE) union all" +
                "(SELECT 'TT_MONETARY_UNIT' as tabela, COUNT(*) as ilosc FROM TT_MONETARY_UNIT) union all" +
                "(SELECT 'TT_NODEOBJ_MAP' as tabela, COUNT(*) as ilosc FROM TT_NODEOBJ_MAP) union all" +
                "(SELECT 'TT_PREF_COND' as tabela, COUNT(*) as ilosc FROM TT_PREF_COND) union all" +
                "(SELECT 'TT_PREFERENCE' as tabela, COUNT(*) as ilosc FROM TT_PREFERENCE) union all" +
                "(SELECT 'TT_PROROGREG' as tabela, COUNT(*) as ilosc FROM TT_PROROGREG) union all" +
                "(SELECT 'TT_PROROGREG_ACTION' as tabela, COUNT(*) as ilosc FROM TT_PROROGREG_ACTION) union all" +
                "(SELECT 'TT_PUBLSIGLE' as tabela, COUNT(*) as ilosc FROM TT_PUBLSIGLE) union all" +
                "(SELECT 'TT_QUOTA_ASSOCIATION' as tabela, COUNT(*) as ilosc FROM TT_QUOTA_ASSOCIATION) union all" +
                "(SELECT 'TT_QUOTA_BALANCE_EVENT' as tabela, COUNT(*) as ilosc FROM TT_QUOTA_BALANCE_EVENT) union all" +
                "(SELECT 'TT_QUOTA_BLOCKING_PERIOD' as tabela, COUNT(*) as ilosc FROM TT_QUOTA_BLOCKING_PERIOD) union all" +
                "(SELECT 'TT_QUOTA_CLOSED_EVENT' as tabela, COUNT(*) as ilosc FROM TT_QUOTA_CLOSED_EVENT) union all" +
                "(SELECT 'TT_QUOTA_CRITICAL_EVENT' as tabela, COUNT(*) as ilosc FROM TT_QUOTA_CRITICAL_EVENT) union all" +
                "(SELECT 'TT_QUOTA_DEFINITION' as tabela, COUNT(*) as ilosc FROM TT_QUOTA_DEFINITION) union all" +
                "(SELECT 'TT_QUOTA_EXH_EVENT' as tabela, COUNT(*) as ilosc FROM TT_QUOTA_EXH_EVENT) union all" +
                "(SELECT 'TT_QUOTA_ORDER_NO_ORIG' as tabela, COUNT(*) as ilosc FROM TT_QUOTA_ORDER_NO_ORIG) union all" +
                "(SELECT 'TT_QUOTA_ORDER_NUMBER' as tabela, COUNT(*) as ilosc FROM TT_QUOTA_ORDER_NUMBER) union all" +
                "(SELECT 'TT_QUOTA_REOPENING_EVENT' as tabela, COUNT(*) as ilosc FROM TT_QUOTA_REOPENING_EVENT) union all" +
                "(SELECT 'TT_QUOTA_SUSP_PERIOD' as tabela, COUNT(*) as ilosc FROM TT_QUOTA_SUSP_PERIOD) union all" +
                "(SELECT 'TT_QUOTA_UNBLOCK_EVENT' as tabela, COUNT(*) as ilosc FROM TT_QUOTA_UNBLOCK_EVENT) union all" +
                "(SELECT 'TT_QUOTA_UNSUSP_EVENT' as tabela, COUNT(*) as ilosc FROM TT_QUOTA_UNSUSP_EVENT) union all" +
                "(SELECT 'TT_REG_REPLACE' as tabela, COUNT(*) as ilosc FROM TT_REG_REPLACE) union all" +
                "(SELECT 'TT_REGGROUP' as tabela, COUNT(*) as ilosc FROM TT_REGGROUP) union all" +
                "(SELECT 'TT_REGROLE_COMBINATION' as tabela, COUNT(*) as ilosc FROM TT_REGROLE_COMBINATION) union all" +
                "(SELECT 'TT_REGROLE_TYPE' as tabela, COUNT(*) as ilosc FROM TT_REGROLE_TYPE) union all" +
                "(SELECT 'TT_REPL_CENTR' as tabela, COUNT(*) as ilosc FROM TT_REPL_CENTR) union all" +
                "(SELECT 'TT_REPL_UNIT' as tabela, COUNT(*) as ilosc FROM TT_REPL_UNIT) union all" +
                "(SELECT 'TT_TRANSMISSION_COMMENT' as tabela, COUNT(*) as ilosc FROM TT_TRANSMISSION_COMMENT) union all" +
                "(SELECT 'TT_WORKSPACE' as tabela, COUNT(*) as ilosc FROM TT_WORKSPACE)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = pstmt.executeQuery()){
            String tabelaColumn = null;
            Integer iloscColumn = null;

            while (rs.next()) {
                tabelaColumn = rs.getString("tabela");
                iloscColumn = rs.getInt("ilosc");

                response.put(tabelaColumn.replace("_", " "), new Object[]{tabelaColumn, iloscColumn});
            }
        } catch (SQLException e) {
            throw new ApiException(e);
        }

        return response;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ApiException(e);
        }
    }
}