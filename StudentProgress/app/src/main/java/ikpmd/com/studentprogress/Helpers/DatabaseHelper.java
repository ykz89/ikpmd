package ikpmd.com.studentprogress.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class DatabaseHelper extends SQLiteOpenHelper{
    public static SQLiteDatabase mSQLDB;
    private static DatabaseHelper mInstance;
    public static final String dbName = "courses.db";
    public static final int dbVersion = 1;

    public DatabaseHelper(Context ctx) {
        super(ctx, dbName, null, dbVersion);
    }

    public static synchronized DatabaseHelper getHelper (Context ctx){
        if (mInstance == null){
            mInstance = new DatabaseHelper(ctx);
            mSQLDB = mInstance.getWritableDatabase();
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseInfo.CourseTables.COURSE + " (" +
                DatabaseInfo.CourseColumn.ID + " INTEGER PRIMARY KEY, " +
                DatabaseInfo.CourseColumn.NAME + " TEXT," +
                DatabaseInfo.CourseColumn.ECTS + " TEXT," +
                DatabaseInfo.CourseColumn.TERM + " TEXT," +
                DatabaseInfo.CourseColumn.MANDATORY + " TEXT," +
                DatabaseInfo.CourseColumn.GRADE + " TEXT);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInfo.CourseTables.COURSE);
        onCreate(db);
    }
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ){
        super(context,name,factory, version);
    }

    public void insert(String table, String nullColumnHack, ContentValues values){
        mSQLDB.insertWithOnConflict(table, nullColumnHack, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void update(String table, ContentValues values, String whereClause, String[] whereArgs){
        mSQLDB.update(table, values, whereClause,whereArgs);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectArgs, String groupBy, String having, String orderBy){
        return mSQLDB.query(table, columns, selection, selectArgs, groupBy, having, orderBy);
    }



}
