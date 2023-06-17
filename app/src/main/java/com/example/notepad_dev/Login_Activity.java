package com.example.notepad_dev;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.notepad_dev.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class Login_Activity extends AppCompatActivity {
    ActivityLoginBinding binding;
    GoogleSignInClient googleSignInClient;
    private FirebaseAuth mAuth;


    @SuppressLint("SetTextI18n")
    @Override

    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        try {
            // Verifica se já há um usuário logado e o redireciona para a tela inicial
            Utilidades.showToast(Login_Activity.this, "Usuário: " + Objects.requireNonNull(currentUser).getEmail() + " Logado.");
            abrirHome();
            finish();
        }catch(Exception ignored) {


        }
    }

    private void updateUI(FirebaseUser currentUser) {
        // Atualiza a interface do usuário com base no usuário atualmente logado (opcional)
    }

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Vincula o arquivo XML de layout à classe de atividade usando o ViewBinding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        // Configura as opções de login com o Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("61017014612-6e8qboo82kqahj5mkndf3tr6lt92eh5d.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Cria um cliente de login com o Google
        googleSignInClient = GoogleSignIn.getClient(this,gso);

        // Altera o texto do botão de login do Google
        TextView text_botao_logar_google = (TextView) binding.botaoGoogle.getChildAt(0);

        text_botao_logar_google.setText("Fazer login com Google Account");


        binding.botaoEntrar.setOnClickListener(view12 -> {
                try {

                    // Efetua login com usuário e senha
                    loginUsuarioESenha(
                        binding.editUser.getText().toString(),
                        binding.editPassword.getText().toString());

                }catch(Exception e){
                    Utilidades.showToast(Login_Activity.this, "Campos vazios, verifique o Usuário e a Senha!");
                }

            }
        );

        binding.botaoGoogle.setOnClickListener(view2 -> signIn());

        binding.linkCriarConta.setOnClickListener(view3 ->{
            criarConta();
            finish();
        });
    }

    // Método chamado quando o botão do Google é clicado
    private void signIn(){

        // Obtém a intenção de login com o Google do cliente de login com o Google
        Intent intent = googleSignInClient.getSignInIntent();

        // Inicia a atividade com o resultado esperado
        abreActivity.launch(intent);
    }

    // Cria um lançador de atividades para obter o resultado da atividade iniciada
    ActivityResultLauncher<Intent> abreActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK){

                    // Obtém a intenção retornada pela atividade
                    Intent intent = result.getData();

                    // Obtém a conta de login com o Google do resultado da intenção
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
                    try {

                        // Obtém a conta de login com o Google
                        GoogleSignInAccount conta = task.getResult(ApiException.class);

                        // Faz o login com a conta de login com o Google
                        loginComGoogle(conta.getIdToken());

                    }catch(ApiException exception){
                        Utilidades.showToast(Login_Activity.this, "Nenhum usuário Google logado no momento.");
                        Log.d("Erro: ",exception.toString());
                    }
                }
            }
    );

    // Método para fazer o login com a conta de login com o Google
    private void loginComGoogle(String token){

        // Cria uma credencial de autenticação com o token de ID do Google
        AuthCredential credencial = GoogleAuthProvider.getCredential(token,null);

        // Faz o login com a credencial
        mAuth.signInWithCredential(credencial).addOnCompleteListener(this, task -> {
            if(task.isSuccessful()){
                Utilidades.showToast(Login_Activity.this, "Login com Google efetuado com Sucesso!");
                abrirHome();
                finish();
            }else {
                Utilidades.showToast(Login_Activity.this, "Erro ao efetuar Login com Google.");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1) {

            // Obtém a conta de login com o Google do resultado da intenção
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
            try {

                // Obtém a conta de login com o Google
                GoogleSignInAccount conta = task.getResult(ApiException.class);

                // Faz o login com a conta de login com o Google
                loginComGoogle(conta.getIdToken());

            }catch(ApiException exception){
                Utilidades.showToast(Login_Activity.this, "Nenhum usuário Google logado no momento");
                Log.d("Erro: ",exception.toString());
            }
        }
    }

    // Método para fazer o login com o usuário e senha
    private void loginUsuarioESenha(String usuario, String senha){
        mAuth.signInWithEmailAndPassword(usuario, senha)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Utilidades.showToast(Login_Activity.this, "Login efetuado com sucesso!");
                        abrirHome();
                        finish();
                    } else {

                        Utilidades.showToast(Login_Activity.this, "Erro para efetuar o Login.");
                    }
                });

    }

    // Método para criar uma nova conta
    public void criarConta() {
        Intent intent = new Intent(getApplicationContext(), CreateAccount_Activity.class );
        startActivity(intent);
    }

    // Método para abrir a atividade Home e limpar os campos de usuário e senha
    private void abrirHome(){
        binding.editUser.setText("");
        binding.editPassword.setText("");
        Intent intent = new Intent(getApplicationContext(), Home_Activity.class );
        startActivity(intent);
    }
}