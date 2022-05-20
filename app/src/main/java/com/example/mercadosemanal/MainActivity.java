package com.example.mercadosemanal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_total;
    private Button btn_pagar;
    private CheckBox ck_arroz;
    private CheckBox ck_carne;
    private CheckBox ck_pao;
    private CheckBox ck_leite;
    private CheckBox ck_ovos;
    private RadioGroup rd_grupo;
    private RadioButton rd_sem;
    private RadioButton rd_5;
    private RadioButton rd_10;
    private RadioButton rd_15;
    private EditText nbr_pag;
    private TextView txt_total;
    double total = 0;
    double recebetotal = 0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_total = findViewById(R.id.btn_total);
        btn_pagar = findViewById(R.id.btn_pagar);
        ck_arroz = findViewById(R.id.ck_arroz);
        ck_carne = findViewById(R.id.ck_carne);
        ck_pao = findViewById(R.id.ck_pao);
        ck_leite = findViewById(R.id.ck_leite);
        ck_ovos = findViewById(R.id.ck_ovos);
        rd_grupo = findViewById(R.id.rd_grupo);
        rd_sem = findViewById(R.id.rd_sem);
        rd_5 = findViewById(R.id.rd_5);
        rd_10 = findViewById(R.id.rd_10);
        rd_15 = findViewById(R.id.rd_15);
        nbr_pag = findViewById(R.id.nbr_pag);
        txt_total = findViewById(R.id.txt_total);

        btn_total.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){

                if (ck_arroz.isChecked()){
                    total += 3.5;
                }

                if (ck_carne.isChecked()){
                    total += 12.3;
                }

                if (ck_pao.isChecked()){
                    total += 2.2;
                }

                if (ck_leite.isChecked()){
                    total += 5.5;
                }

                if (ck_ovos.isChecked()){
                    total += 7.5;
                }

                txt_total.setText("Total: " +total);
                recebetotal = total;
                total = 0;
            }



        });




        btn_pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int op = rd_grupo.getCheckedRadioButtonId();
                double novoValor = 0;
                double valorOriginal = recebetotal;

                if (op == R.id.rd_sem){
                    novoValor = valorOriginal;
                }

                if (op == R.id.rd_5) {
                    novoValor = valorOriginal - (valorOriginal * 0.05);
                }

                if (op == R.id.rd_10){
                    novoValor = valorOriginal - (valorOriginal * 0.1);
                }

                if (op == R.id.rd_15){
                    novoValor = valorOriginal - (valorOriginal * 0.15);
                }

                double valorInserido = Double.parseDouble(nbr_pag.getText().toString());
                double posPagamento = 0;


                AlertDialog.Builder janelapag = new AlertDialog.Builder(MainActivity.this);
                if (valorInserido >= novoValor){
                    posPagamento = valorInserido - novoValor;

                    RadioButton desconto = findViewById(op);
                    StringBuilder desc = new StringBuilder();
                    desc.append("Valor total da compra: ").append(String.format("%.2f", recebetotal)).append("\nDesconto: ").append(desconto.getText().toString());
                    desc.append("\nValor pago: ").append(novoValor).append("\nTroco ").append(String.format("%.2f", posPagamento));
                    janelapag.setTitle("Pagamento Efetuado!");
                    janelapag.setMessage(desc);
                    //janelapag.setMessage("Valor total da compra: " + recebetotal + "\nDesconto: " + op + "\n Valor pago: " + novoValor + "\nTroco: " + posPagamento);
                    //janelapag.setMessage(String.format("Valor total da compra: %.2f\nDesconto: %i\n Valor pago: %.2f\n Troco: %.2f", recebetotal, op, novoValor, posPagamento));
                    janelapag.setNeutralButton("Ok", null);
                    janelapag.show();
                } else {
                    janelapag.setTitle("Falha no pagamento.");
                    janelapag.setMessage("O valor inserido pelo cliente não satisfaz o valor dos produtos.");
                    janelapag.setNeutralButton("Ok", null);
                    janelapag.show();
                }
            }

        });

    }
}