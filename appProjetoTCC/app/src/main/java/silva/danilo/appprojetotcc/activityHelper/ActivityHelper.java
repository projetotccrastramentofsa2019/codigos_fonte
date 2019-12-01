package silva.danilo.appprojetotcc.activityHelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;
import silva.danilo.appprojetotcc.R;
import silva.danilo.appprojetotcc.configuracoes.ParametrosConfig;
import silva.danilo.appprojetotcc.dao.UsuarioDao;

public class ActivityHelper {

    public static void atualizarFotoUsuario(Context context, ImageView imageView)
    {
        String caminhoFoto = new UsuarioDao(context).retornaCaminhoFotoDoUsuario(ParametrosConfig.usuario.getId());

        if(caminhoFoto != null && !caminhoFoto.isEmpty())
        {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            imageView.setImageBitmap(bitmapReduzido);
            imageView.setTag(caminhoFoto);
        }
    }
}
