package com.aplicativo.henrique.urbanexplorer;

import android.Manifest;

import android.content.Intent;

import android.content.pm.PackageManager;

import android.location.Location;

import android.support.annotation.NonNull;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import org.json.JSONArray;

import java.math.BigDecimal;




public class MainActivity extends AppCompatActivity {
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseUser user = mAuth.getCurrentUser();
    private CallbackManager mCallbackManager;
    public static final double PROXIMIDADE = 0.20;
    public static double lat;
    public static double longi;
    public  GoogleSignInClient mGoogleSignInClient;
    public static JSONArray arrayOfUsersInFriendList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
        }
        GPSTracker g = new GPSTracker(this);
        lat = g.getLatitude();
        longi = g.getLongitude();
        pegarPonto();
        pegarNavegacao();
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.setPermissions("email", "public_profile");
        try{
            loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    handleFacebookAccessToken(loginResult.getAccessToken());

                }

                @Override
                public void onCancel() {
                    // ...
                    Toast.makeText(MainActivity.this, "Cancelou", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(FacebookException error) {
                    // ...
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }catch (IllegalStateException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn && user!=null){
            Intent intent = new Intent(this, Principal.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }
    @NonNull
    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            user = mAuth.getCurrentUser();
                            Intent intent = new Intent(MainActivity.this, Principal.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else{

                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
    public static double determinarDistancia(double x, double y){
        Location a = new Location("a");
        Location b = new Location("b");
        a.setLatitude(x);
        a.setLongitude(y);
        b.setLatitude(lat);
        b.setLongitude(longi);
        return Double.parseDouble(""+new BigDecimal(a.distanceTo(b)/1000).setScale(2, BigDecimal.ROUND_UP));
    }
    public static void pegarPonto(){
        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setTimestampsInSnapshotsEnabled(true)
                    .build();
            db.setFirestoreSettings(settings);
            DocumentReference doc = db.collection(user.getUid()).document("score");
            doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    try {
                        Object[] os = documentSnapshot.getData().values().toArray();
                        Servico.setPonto((Long) os[0]);
                    } catch (Exception e) {
                        Servico.setPonto((new Long(0)));

                    }

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void pegarNavegacao(){
        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setTimestampsInSnapshotsEnabled(true)
                    .build();
            db.setFirestoreSettings(settings);
            DocumentReference doc = db.collection(user.getUid()).document("nav");
            doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    try {
                        Object[] os = documentSnapshot.getData().values().toArray();
                        Servico.setNavegacao((String) os[0]);
                    } catch (Exception e) {
                        Servico.setNavegacao("d");
                    }

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void iniciarCadastro(View view){
        EditText e = (EditText)findViewById(R.id.email);
        EditText s = (EditText)findViewById(R.id.senha);
        String email = e.getText().toString();
        String password = s.getText().toString();
        if(!(email.equals("")||password.equals(""))){
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = mAuth.getCurrentUser();
                                Intent intent = new Intent(MainActivity.this, Principal.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, getString(R.string.cadastroerrado), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else{
        Toast.makeText(MainActivity.this, getString(R.string.loginerrado), Toast.LENGTH_LONG).show();
    }

    }
    public void iniciarLogin(View view){
        EditText e = (EditText)findViewById(R.id.email);
        EditText s = (EditText)findViewById(R.id.senha);
        String email = e.getText().toString();
        String password = s.getText().toString();
        if(!(email.equals("")||password.equals(""))){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = mAuth.getCurrentUser();
                                Intent intent = new Intent(MainActivity.this, Principal.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, getString(R.string.loginerrado), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(MainActivity.this, getString(R.string.loginerrado), Toast.LENGTH_LONG).show();
        }

    }
    public void  mandarEmailReset(View view){
        EditText e = (EditText)findViewById(R.id.email);
        String email = e.getText().toString();
        if(!email.equals("")){
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, getString(R.string.emailenviado), Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(MainActivity.this, getString(R.string.Emailinvalido), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(MainActivity.this, getString(R.string.Emailinvalido), Toast.LENGTH_LONG).show();
        }

    }


}