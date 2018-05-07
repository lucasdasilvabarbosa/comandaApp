package ucdb.br.appcomanda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.api.ApiRetrofit;
import ucdb.br.appcomanda.api.Rotas;
import ucdb.br.appcomanda.modelDTO.ComandaDTO;
import ucdb.br.appcomanda.modelDTO.UsuarioDTO;

/**
 * Created by lucas on 26/04/2018.
 */

public class LoginActivity extends AppCompatActivity{

    @BindView(R.id.edtLogin)
    EditText edtLogin;

    @BindView(R.id.edtSenha)
    EditText edtSenha;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void login(){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        if(edtLogin.getText() != null && !edtLogin.getText().toString().equals("")){
            usuarioDTO.setLogin(edtLogin.getText().toString());
            if(edtSenha.getText() != null && !edtSenha.getText().toString().equals("")){
                usuarioDTO.setSenha(edtSenha.getText().toString());
                fazerlogin(usuarioDTO);
            }
        }

    }

    private void fazerlogin(UsuarioDTO usuarioDTO) {
        Rotas api = ApiRetrofit.buildRetrofit();

        Call<UsuarioDTO> call = api.login(usuarioDTO);

        call.enqueue(new Callback<UsuarioDTO>() {
            @Override
            public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                if(response.code() == 200){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else if(response.code() == 400){
                    Toast.makeText(LoginActivity.this, "Login ou Senha inválidos", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Houve um erro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Houve um erro", Toast.LENGTH_SHORT).show();
            }

        });

    }

}
