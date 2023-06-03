package com.example.notepad_dev;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notepad_dev.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
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
            Toast.makeText(getApplicationContext(), "Usuário: " + Objects.requireNonNull(currentUser).getEmail() + " Logado.", Toast.LENGTH_LONG).show();
            abrirHome();
            finish();
        }catch(Exception ignored) {


        }
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //vincula o Back end com Front End
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("61017014612-6e8qboo82kqahj5mkndf3tr6lt92eh5d.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this,gso);

        //muda o nome do botão do Login do google
        TextView text_botao_logar_google = (TextView) binding.botaoGoogle.getChildAt(0);

        text_botao_logar_google.setText("Fazer login com Google Account");


        binding.botaoEntrar.setOnClickListener(view12 -> {
                try {
                    loginUsuarioESenha(
                        binding.editUser.getText().toString(),
                        binding.editPassword.getText().toString());

                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Campos vazios, verifique o Usuário e a Senha!", Toast.LENGTH_LONG).show();
                }

            }
        );

        binding.botaoGoogle.setOnClickListener(view2 -> signIn());

        binding.linkCriarConta.setOnClickListener(view3 ->{
            criarConta();
            finish();
        });
    }

    private void signIn(){
        Intent intent = googleSignInClient.getSignInIntent();
        //startActivityForResult(intent,1);
        abreActivity.launch(intent);
    }

    ActivityResultLauncher<Intent> abreActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK){
                    Intent intent = result.getData();

                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
                    try {
                        GoogleSignInAccount conta = task.getResult(ApiException.class);
                        loginComGoogle(conta.getIdToken());

                    }catch(ApiException exception){
                        Toast.makeText(getApplicationContext(), "Nenhum usuário Google logado no momento.", Toast.LENGTH_LONG).show();
                        Log.d("Erro: ",exception.toString());
                    }
                }
            }
    );
    private void loginComGoogle(String token){
        AuthCredential credencial = GoogleAuthProvider.getCredential(token,null);

        mAuth.signInWithCredential(credencial).addOnCompleteListener(this, task -> {
            if(task.isSuccessful()){
                Toast.makeText(getApplicationContext(), "Login com Google efetuado com Sucesso!", Toast.LENGTH_LONG).show();
                abrirHome();
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "Erro ao efetuar Login com Google.", Toast.LENGTH_LONG).show();


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
            try {
                GoogleSignInAccount conta = task.getResult(ApiException.class);
                loginComGoogle(conta.getIdToken());

            }catch(ApiException exception){
                Toast.makeText(getApplicationContext(), "Nenhum usuário Google logado no momento.", Toast.LENGTH_LONG).show();
                Log.d("Erro: ",exception.toString());
            }
        }
    }

    private void loginUsuarioESenha(String usuario, String senha){
        mAuth.signInWithEmailAndPassword(usuario, senha)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                        abrirHome();
                        finish();
                        //updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Erro para efetuar o Login.", Toast.LENGTH_LONG).show();
                        //updateUI(null);
                    }
                });

    }
    public void criarConta() {
        Intent intent = new Intent(getApplicationContext(), CreateAccount_Activity.class );
        startActivity(intent);
    }

    private void abrirHome(){
        binding.editUser.setText("");
        binding.editPassword.setText("");
        Intent intent = new Intent(getApplicationContext(), Home_Activity.class );
        startActivity(intent);
    }
}