package wjc.niceweather.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import wjc.niceweather.db.NiceWeatherOpenHelper;

/**
 * Created by wjc on 16/5/21.
 */
public class NiceWeatherDB {
    //数据库命
    public static final String DB_NAME = "cool_weather";
    //数据库版本
    public static final int VERSION = 1;
    private static NiceWeatherDB niceWeatherDB;
    private SQLiteDatabase db;

    /**
     * @param context 私有化 NiceWeatherDB 的构造函数，该构造函数使用已经设定好建表语句的SQLiteOpenHelpe来返回一个数据库。
     */
    private NiceWeatherDB(Context context) {
        NiceWeatherOpenHelper dbHelper = new NiceWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * @param context
     * @return 对外提供一个获得NiceWeatherDB实例的静态方法，但是该方法是synchronized修饰的并且在只在当前全局都
     * 没有实例的情况下执行，结合前面构造方法的私有化，保证全局只会有一个NiceWeatherDB的实例。
     */
    public synchronized static NiceWeatherDB getInstance(Context context) {
        if (niceWeatherDB == null) {
            niceWeatherDB = new NiceWeatherDB(context);

        }
        return niceWeatherDB;

    }


    /**
     * 将Province对象的数据信息取出并存入数据库的 province表中
     *
     * @param province
     */
    public void saveProvince(Province province) {

        if (province != null) {
            db.execSQL("INSERT INTO province (province_name,province_code)VALUES (?,?)", new String[]{province.getProvinceName(), province.getProvinceCode()});
        }
    }

    /**
     * 从数据库读取Province的信息
     *
     * @return
     */

    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.rawQuery("SELECT * FROM province", null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);

            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }


        return list;

    }

    /**
     * 将City对象的数据信息取出并存入数据库的 city表中
     *
     * @param city
     */
    public void saveProvince(City city) {

        if (city != null) {
            db.execSQL("INSERT INTO city (city_name,city_code,province_id)VALUES (?,?,?)", new String[]{city.getCityName(), city.getCityCode(), String.valueOf(city.getProvinceId())});
        }
    }

    /**
     * 从数据库读取City的信息
     *
     * @return
     */

    public List<City> loadCities() {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.rawQuery("SELECT * FROM city", null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
                list.add(city);

            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }


        return list;

    }

    /**
     * 将County 对象的数据信息取出并存入数据库的 county表中
     *
     * @param county
     */
    public void saveCounty(County county) {

        if (county != null) {
            db.execSQL("INSERT INTO county(county_name,county_code,city_id)VALUES (?,?,?)", new String[]{county.getCountyName(), county.getCountyCode(), String.valueOf(county.getCityId())});
        }
    }

    /**
     * 从数据库读取County的信息
     *
     * @return
     */

    public List<County> loadCounties() {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.rawQuery("SELECT * FROM county", null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
                list.add(county);

            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }


        return list;

    }
}










