package anotacoes.com.anotaes;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    private AutoCompleteTextView cxtexto;
    private Button botaoSalvar;

    private static final String ARQUIVO = "arquivo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cxtexto = findViewById(R.id.txtAnotacoes);
        botaoSalvar = findViewById(R.id.btnSalvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String  texto = cxtexto.getText().toString();

                gravarArquivo(texto);
                Toast.makeText(MainActivity.this, "Anotação salva com sucesso!", Toast.LENGTH_SHORT).show();

            }
        });

        if (lerArquivo() != null){
            cxtexto.setText(lerArquivo());
        }
    }

    private void gravarArquivo(String texto){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(ARQUIVO, Context.MODE_PRIVATE));
            outputStreamWriter.write(texto);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("MainActivity",e.toString());
        }
    }

    private String lerArquivo(){
        String resultado = "";
        try {
            InputStream arquivo = openFileInput(ARQUIVO);
            if (arquivo != null){
                InputStreamReader inputStreamReader = new InputStreamReader(arquivo);
                BufferedReader bufferreader = new BufferedReader(inputStreamReader);

                String linhasArquivo = "";

                while ( (linhasArquivo = bufferreader.readLine()) != null ){
                    resultado += linhasArquivo + "\n";
                }
                arquivo.close();
            }

        }catch (IOException e){
            Log.v("MainActivity", e.toString());
        }
        return resultado;
    }

}
