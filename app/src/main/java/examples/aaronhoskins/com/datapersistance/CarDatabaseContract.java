package examples.aaronhoskins.com.datapersistance;

import java.util.Locale;

public class CarDatabaseContract {
    public static final String DATABASE_NAME = "cars_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Cars_Table";
    public static final String COL_MAKE = "make";
    public static final String COL_MODLE = "model";
    public static final String COL_YEAR = "year";
    public static final String COL_ENGINE = "engine";
    public static final String COL_TRANSMISSION = "transmission";
    public static final String COL_VIN = "vin";

    public static final String DROP_TABLE_QUERY = "DROP TABLE " + TABLE_NAME;
    public static final String SELECT_ALL_CARS = "SELECT * FROM " + TABLE_NAME;


    public static String getCreateTableQuery() {
        return String.format(Locale.US, "CREATE TABLE %s( %s TEXT PRIMARY_KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, COL_VIN, COL_MAKE, COL_MODLE, COL_YEAR, COL_ENGINE, COL_TRANSMISSION);
    }

    public static String getSelectCarByVinQuery(String vin) {
        return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \'%s\'", TABLE_NAME, vin);
    }



}
