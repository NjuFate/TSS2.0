package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by dyp on 2016/7/13.
 */
public class MydataBaseHelper extends SQLiteOpenHelper {
    public static  final String Create_InformMsg = "create table InformMsg("+"id Text primary key , "
                                                                            +"receiverId Text,"
                                                                            +"senderId Text,"
                                                                            +"title Text,"
                                                                            +"content Text,"
                                                                            +"time Integer,"
                                                                            +"ifread Integer,"
                                                                            +"localiconurl Text,"
                                                                            +"iconurl Text)";

    private Context mContext;
    private SQLiteDatabase mdb;

    public  MydataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_InformMsg);
        Toast.makeText(mContext, "Create suceeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //增
    public void insert(String table,ContentValues values){
        mdb = this.getWritableDatabase();
        mdb.insert(table,null, values);
        mdb.close();
    }

    //改
    public void update( String table, ContentValues values, String wherecause, String[] whereArgs){
        mdb = this.getWritableDatabase();
        mdb.update(table, values, wherecause, whereArgs);
        mdb.close();;
    }
    //查
    public Cursor query( String table, String[] columns, String secletion, String[] args, String groupBy, String having, String orderBy){
        mdb = this.getWritableDatabase();
        Cursor cursor = mdb.query(table, columns, secletion, args, groupBy, having, orderBy);
//        db.close();
        return cursor;
    }
    //删
    public void delete( String table, String whereCause, String[] args){
        mdb = this.getWritableDatabase();
        mdb.delete(table, whereCause, args);
        mdb.close();
    }
}
