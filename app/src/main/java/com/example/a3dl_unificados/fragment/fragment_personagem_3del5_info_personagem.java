package com.example.a3dl_unificados.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.adapters.AdapterPersonagem3del5;
import com.example.a3dl_unificados.dao.bdInternoDAO;
import com.example.a3dl_unificados.objetos.objeto_3del5_personagem;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.regex.Pattern;

import static com.example.a3dl_unificados.MainActivity.getPersonagem;
import static com.example.a3dl_unificados.dao.bdInternoDAO.inserirPersonagem3del5;
import static com.example.a3dl_unificados.ferramentas.ferramentas3del5.alerta;


public class fragment_personagem_3del5_info_personagem extends Fragment {


    private AdapterPersonagem3del5 adapter;
    private Context context;
    private List<objeto_3del5_personagem> personagens;
    private boolean salvavel;
    private View inflate;

    public fragment_personagem_3del5_info_personagem() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_personagem_3del5_info_personagem, container, false);
        salvavel=true;
        this.inflate=inflate;
        definirCampos(inflate);
        definirImagem(inflate);
        definirOnClickImageView(inflate);


        return inflate;
    }

    private void definirImagem(View inflate) {
        String imagem = getPersonagem().getImagem();
        if(imagem!=null){
           loadImageFromStorage(imagem);

        }
    }


    public void definirOnClickImageView(View inflate) {
        ImageView imageView = inflate.findViewById(R.id.personagem_3del5_info_imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 123);
            }
        });
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"/imagemPersonagem" + getPersonagem().getCodPersonagem()+".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        getPersonagem().setImagem(mypath.getAbsolutePath());
        bdInternoDAO.inserirPersonagem3del5(getPersonagem());

        return directory.getAbsolutePath();
    }

    private void loadImageFromStorage(String path)
    {
        String[] t = path.split (Pattern.quote ("/imagemPersonagem"));
        //alerta(t[0]+"/imagemPersonagem"+t[1],context);
        //alerta(t[0],context);
        //alerta(t[1],context);
        try {
            File f=new File(t[0], "/imagemPersonagem"+t[1]);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img= inflate.findViewById(R.id.personagem_3del5_info_imageView);
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 123){
                Uri imagemSelecionada = data.getData();
                ImageView imageView = inflate.findViewById(R.id.personagem_3del5_info_imageView);
                Bitmap bitmap = redimencionarImagem(imagemSelecionada);
                imageView.setImageBitmap(bitmap);
                saveToInternalStorage(bitmap);

            }
        }
    }

    public byte[] converterBitmapToByte(Bitmap bitmap){

        int size = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(byteBuffer);
        return byteBuffer.array();
    }

    public byte[] converterUriToByte(Uri data){

        ByteArrayOutputStream baos = new ByteArrayOutputStream  ();
        FileInputStream fis;
        try {
            fis = new FileInputStream(new File(data.getPath()));
            byte[] buf = new byte[1024];
            int n;
            while (-1 != (n = fis.read(buf)))
                baos.write(buf, 0, n);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bbytes = baos.toByteArray();
        return bbytes;
    }

    public Bitmap converterByteTOBitmap(byte[] byteArray){

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bmp;
    }

    public void salvarImagem(byte[] imagem){

        File file = context.getFilesDir();
        String fileName = file.getAbsolutePath();
        fileName += "/imagemPersonagem" + getPersonagem().getCodPersonagem();
        BufferedOutputStream bos = null;


    }

    public Bitmap redimencionarImagem(Uri imagemSelecionada) {
        try {
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(context.getContentResolver(),imagemSelecionada);
            int x=bitmap.getWidth();
            int y=bitmap.getHeight();
            if(x>y){
                return Bitmap.createScaledBitmap(bitmap,y,y,true);
            } else if(y>x){
                return Bitmap.createScaledBitmap(bitmap,x,x,true);
            }
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    private void definirCampos(View inflate) {
        definirEditFilds(inflate);


    }

    private void definirEditFilds(final View inflate) {
        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if((!hasFocus)&&salvavel){
                    EditText atual=(EditText)v;
                    if(atual.getText().length()==0){
                        if(atual.getInputType()==2){
                            atual.setText("0");
                        }else{
                            atual.setText("");
                        }

                    }
                    atualizarPersonagem(inflate);
                    inserirPersonagem3del5(getPersonagem());

                }
            }
        };

        EditText editNome = inflate.findViewById(R.id.editText_nome_personagem);
        editNome.setText(getPersonagem().getNome());
        editNome.setOnFocusChangeListener(onFocusChangeListener);
        EditText editIdade = inflate.findViewById(R.id.editText_idade_personagem);
        editIdade.setText(String.valueOf(getPersonagem().getIdade()));
        editIdade.setOnFocusChangeListener(onFocusChangeListener);
        EditText editFuncao = inflate.findViewById(R.id.editText_funcao_personagem);
        editFuncao.setText(getPersonagem().getFuncao());
        editFuncao.setOnFocusChangeListener(onFocusChangeListener);
        EditText editRaca = inflate.findViewById(R.id.editText_raca_personagem);
        editRaca.setText(getPersonagem().getRaca());
        editRaca.setOnFocusChangeListener(onFocusChangeListener);
        EditText editTamanho = inflate.findViewById(R.id.editText_classe_tamanho);
        editTamanho.setText(getPersonagem().getClasseDeTamanho());
        editTamanho.setOnFocusChangeListener(onFocusChangeListener);
    }

    private void atualizarPersonagem(View inflate) {
        EditText editNome = inflate.findViewById(R.id.editText_nome_personagem);
        getPersonagem().setNome(String.valueOf(editNome.getText()));
        EditText editIdade = inflate.findViewById(R.id.editText_idade_personagem);
        getPersonagem().setIdade(Integer.parseInt(String.valueOf(editIdade.getText())));
        EditText editRaca = inflate.findViewById(R.id.editText_raca_personagem);
        getPersonagem().setRaca(String.valueOf(editRaca.getText()));
        EditText editFuncao = inflate.findViewById(R.id.editText_funcao_personagem);
        getPersonagem().setFuncao(String.valueOf(editFuncao.getText()));
        EditText editTamanho = inflate.findViewById(R.id.editText_classe_tamanho);
        getPersonagem().setClasseDeTamanho(String.valueOf(editTamanho.getText()));
    }






    public AdapterPersonagem3del5 getAdapter() {
        return adapter;
    }

    public void setAdapter(AdapterPersonagem3del5 adapter) {
        this.adapter = adapter;
    }

    @Nullable
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<objeto_3del5_personagem> getPersonagens() {
        return personagens;
    }




}
