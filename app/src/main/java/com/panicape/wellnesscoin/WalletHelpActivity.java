package com.panicape.wellnesscoin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class WalletHelpActivity extends AppCompatActivity {

    private ImageButton nextBtn;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_help);

        nextBtn = findViewById(R.id.WH_nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MarketplaceHelpActivity.class);
                startActivity(intent);
            }
        });
    }
}