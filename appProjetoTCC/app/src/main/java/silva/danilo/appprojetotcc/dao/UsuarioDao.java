package silva.danilo.appprojetotcc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioDao extends SQLiteOpenHelper {

    public UsuarioDao(Context context)
    {
        super(context, "projetoTCC", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String strSql = "create table usuarios (id integer primary key, caminhoFoto text)";

        db.execSQL(strSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String strSql = "drop table if exists usuarios";
        db.execSQL(strSql);
        onCreate(db);
    }

    public void atribuirCaminhoFoto(String caminhoFoto, Long idUsuario)
    {
        String caminhoAntigo = retornaCaminhoFotoDoUsuario(idUsuario);

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("caminhoFoto", caminhoFoto);

        if(caminhoAntigo == null)
        {
            db.insert("usuarios", null, cv);
        }
        else
        {
            db.update("usuarios", cv, "id = ?", new String[]{ String.valueOf(idUsuario) });
        }
    }

    public String retornaCaminhoFotoDoUsuario(Long idUsuario)
    {
        String retorno = null;

        String strSql = "select id, caminhoFoto from usuarios where id = ?";

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(strSql, new String[]{ String.valueOf(idUsuario) });

        if(c.moveToNext())
        {
            String caminhoFoto = c.getString(c.getColumnIndex("caminhoFoto"));

            c.close();
            return caminhoFoto;
        }

        c.close();

        return null;
    }

}
