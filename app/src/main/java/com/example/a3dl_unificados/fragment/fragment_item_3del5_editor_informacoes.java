package com.example.a3dl_unificados.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.a3dl_unificados.R;
import com.example.a3dl_unificados.adapters.AdapterListaItens3del5;
import com.example.a3dl_unificados.objetos.objeto_3del5_item;

import java.util.ArrayList;
import java.util.List;

import static com.example.a3dl_unificados.dao.bdInternoDAO.excluirItem3del5;
import static com.example.a3dl_unificados.dao.bdInternoDAO.inserirItem3del5;
import static com.example.a3dl_unificados.ferramentas.ferramentasListaItens.ordenaListaItensNome;
import static com.example.a3dl_unificados.ferramentas.ferramentasListaItens.ordenaListaItensTipo;


public class fragment_item_3del5_editor_informacoes extends DialogFragment {

    private objeto_3del5_item obj;
    private List<objeto_3del5_item> listObj;
    private fragment_item_3del5_editor_informacoes dialog;
    private AdapterListaItens3del5 adapterListaItens3del5;

    public fragment_item_3del5_editor_informacoes() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        this.dialog=this;
        return dialog;
    }

    @SuppressLint("ValidFragment")
    public fragment_item_3del5_editor_informacoes(List<objeto_3del5_item> listObj, objeto_3del5_item item,AdapterListaItens3del5 adapterListaItens3del5 ) {
        this.obj = item;
        this.listObj = listObj;
        this.adapterListaItens3del5=adapterListaItens3del5;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_item_3del5_editor_informacoes, container, false);
        definirListaSpinner(inflate);
        definirFuncaoBtnFechar(inflate);
        definirFuncaoBtnSalvar(inflate);
        preencheDialog(inflate);
        return inflate;
    }

    private void definirListaSpinner(View inflate) {
        ArrayList<String> listaSpinner=criarListaSpinner();
        final ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter(inflate.getContext(), android.R.layout.simple_spinner_item,listaSpinner);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner)(inflate.findViewById(R.id.spinnerTipoItem_item_3del5_editor))).setAdapter(stringArrayAdapter);
    }

    private ArrayList<String> criarListaSpinner() {
        ArrayList<String> listaSpinner=new ArrayList<>();
        listaSpinner.add("Utilizável");
        listaSpinner.add("Equipamento");
        listaSpinner.add("Não Utilizavel");
        listaSpinner.add("Carga");
        listaSpinner.add("Equipamento Carga");
        listaSpinner.add("Personalizado");
        return listaSpinner;
    }

    private void definirFuncaoBtnSalvar(final View inflate) {
        ((Button)(inflate.findViewById(R.id.buttonSalvar_item_3del5_editor))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                obj.setNome(String.valueOf(((EditText)(inflate.findViewById(R.id.editTextNomeItem_item_3del5_editor))).getText()));
                obj.setQuantidade(Integer.parseInt(((EditText)(inflate.findViewById(R.id.editTextQuantidadeItem_item_3del5_editor))).getText().toString()));
                obj.setPeso(Float.parseFloat(((EditText)(inflate.findViewById(R.id.editTextPesoItem_item_3del5_editor))).getText().toString()));
                obj.setTipo(((Spinner)(inflate.findViewById(R.id.spinnerTipoItem_item_3del5_editor))).getSelectedItemPosition());
                obj.setDescricao(String.valueOf(((EditText)(inflate.findViewById(R.id.editTextDescricaoItem_item_3del5_editor))).getText()));
                obj.setIdItem((int) inserirItem3del5(obj));
                ordenaListaItensNome(listObj);
                ordenaListaItensTipo(listObj);
                adapterListaItens3del5.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    private void definirFuncaoBtnFechar(View inflate) {
        ((Button)(inflate.findViewById(R.id.buttonFechar_item_3del5_editor))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListaItens3del5.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }



    private void preencheDialog(View inflate) {
        ((EditText)inflate.findViewById(R.id.editTextNomeItem_item_3del5_editor)).setText(obj.getNome());
        ((EditText)inflate.findViewById(R.id.editTextQuantidadeItem_item_3del5_editor)).setText(String.valueOf(obj.getQuantidade()));
        ((EditText)inflate.findViewById(R.id.editTextPesoItem_item_3del5_editor)).setText(String.valueOf(obj.getPeso()));
        ((Spinner)(inflate.findViewById(R.id.spinnerTipoItem_item_3del5_editor))).setSelection(obj.getTipo());
        ((EditText)inflate.findViewById(R.id.editTextDescricaoItem_item_3del5_editor)).setText(obj.getDescricao());

    }


    public AdapterListaItens3del5 getAdapterListaItens3del5() {
        return adapterListaItens3del5;
    }

    public void setAdapterListaItens3del5(AdapterListaItens3del5 adapterListaItens3del5) {
        this.adapterListaItens3del5 = adapterListaItens3del5;
    }
}
