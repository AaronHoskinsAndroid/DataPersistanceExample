package examples.aaronhoskins.com.datapersistance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static examples.aaronhoskins.com.datapersistance.CarDatabaseContract.COL_ENGINE;
import static examples.aaronhoskins.com.datapersistance.CarDatabaseContract.COL_MAKE;
import static examples.aaronhoskins.com.datapersistance.CarDatabaseContract.COL_MODLE;
import static examples.aaronhoskins.com.datapersistance.CarDatabaseContract.COL_TRANSMISSION;
import static examples.aaronhoskins.com.datapersistance.CarDatabaseContract.COL_VIN;
import static examples.aaronhoskins.com.datapersistance.CarDatabaseContract.COL_YEAR;
import static examples.aaronhoskins.com.datapersistance.CarDatabaseContract.DATABASE_NAME;
import static examples.aaronhoskins.com.datapersistance.CarDatabaseContract.DATABASE_VERSION;
import static examples.aaronhoskins.com.datapersistance.CarDatabaseContract.SELECT_ALL_CARS;
import static examples.aaronhoskins.com.datapersistance.CarDatabaseContract.TABLE_NAME;


public class CarDatabaseHelper extends SQLiteOpenHelper {
    public CarDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CarDatabaseContract.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CarDatabaseContract.DROP_TABLE_QUERY);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Cars> getAllCars() {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        ArrayList<Cars> returnCarList = new ArrayList<>();

        Cursor cursor = readableDatabase.rawQuery(SELECT_ALL_CARS, null);

        if (cursor.moveToFirst()) {
            do {
                final String vin = cursor.getString(cursor.getColumnIndex(COL_VIN));
                final String make = cursor.getString(cursor.getColumnIndex(COL_MAKE));
                final String model = cursor.getString(cursor.getColumnIndex(COL_MODLE));
                final String year = cursor.getString(cursor.getColumnIndex(COL_YEAR));
                final String engine = cursor.getString(cursor.getColumnIndex(COL_ENGINE));
                final String transmission = cursor.getString(cursor.getColumnIndex(COL_TRANSMISSION));
                Cars currentCar = new Cars(make, model, year, engine, transmission, vin);
                returnCarList.add(currentCar);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return returnCarList;
    }

    public Cars getCarByVin(String vinToQuery) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cars returnCar = null;

        Cursor cursor = readableDatabase.rawQuery(CarDatabaseContract.getSelectCarByVinQuery(vinToQuery), null);

        if (cursor.moveToFirst()) {
            final String vin = cursor.getString(cursor.getColumnIndex(COL_VIN));
            final String make = cursor.getString(cursor.getColumnIndex(COL_MAKE));
            final String model = cursor.getString(cursor.getColumnIndex(COL_MODLE));
            final String year = cursor.getString(cursor.getColumnIndex(COL_YEAR));
            final String engine = cursor.getString(cursor.getColumnIndex(COL_ENGINE));
            final String transmission = cursor.getString(cursor.getColumnIndex(COL_TRANSMISSION));
            returnCar = new Cars(make, model, year, engine, transmission, vin);
        }
        cursor.close();
        return returnCar;
    }

    public void insertCarIntoDatabase(Cars carToInsert) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_VIN, carToInsert.getVinNumber());
        contentValues.put(COL_MAKE, carToInsert.getMake());
        contentValues.put(COL_MODLE, carToInsert.getModel());
        contentValues.put(COL_YEAR, carToInsert.getYear());
        contentValues.put(COL_ENGINE, carToInsert.getEngineSize());
        contentValues.put(COL_TRANSMISSION, carToInsert.getTransmissionType());

        database.insert(TABLE_NAME, null, contentValues);
    }

    public void deleteCar(String vinToDelete) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, COL_VIN + " = ?", new String[]{vinToDelete});
    }

    public void updateCarIntoDatabase(Cars carToUpdate) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_VIN, carToUpdate.getVinNumber());
        contentValues.put(COL_MAKE, carToUpdate.getMake());
        contentValues.put(COL_MODLE, carToUpdate.getModel());
        contentValues.put(COL_YEAR, carToUpdate.getYear());
        contentValues.put(COL_ENGINE, carToUpdate.getEngineSize());
        contentValues.put(COL_TRANSMISSION, carToUpdate.getTransmissionType());

        database.update(TABLE_NAME, contentValues, COL_VIN + " = ?", new String[]{carToUpdate.getVinNumber()});
    }
}
