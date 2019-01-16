package pl.lodz.uni.math.kuba.projekt1;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

public class MemoryGameActivity extends AppCompatActivity {
    ImageView fieldA1, fieldA2, fieldA3, fieldA4, fieldA5, fieldB1, fieldB2, fieldB3, fieldB4, fieldB5, fieldC1, fieldC2, fieldC3,
            fieldC4, fieldC5, fieldD1, fieldD2, fieldD3, fieldD4, fieldD5;
    ImageView[] imageViews;

    Integer[] cardsArray = {0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

    int[] images;

    int image1, image2, image3, image4, image5, image6,
            image7, image8, image9, image10, image11, image12,
            image13, image14, image15, image16, image17, image18,
            image19, image20;

    int firstCard, secondCard;
    int clickFirst, clickSecond;
    int cardNumber = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);

        imageViews = new ImageView[]{fieldA1, fieldA2, fieldA3, fieldA4, fieldA5, fieldB1, fieldB2, fieldB3, fieldB4, fieldB5, fieldC1, fieldC2, fieldC3,
                fieldC4, fieldC5, fieldD1, fieldD2, fieldD3, fieldD4, fieldD5};

        images = new int[]{image1, image2, image3, image4, image5, image6,
                image7, image8, image9, image10, image11, image12,
                image13, image14, image15, image16, image17, image18,
                image19, image20};

        init();
        //   setTags();

        showCards();

     //   Collections.shuffle(Arrays.asList(cardsArray));

        setClickListenerOnImageView();

        Collections.shuffle(Arrays.asList(images));

    }


    private void init() {
        imageViews[0] = findViewById(R.id.field_a1_iv);
        imageViews[1] = findViewById(R.id.field_a2_iv);
        imageViews[2] = findViewById(R.id.field_a3_iv);
        imageViews[3] = findViewById(R.id.field_a4_iv);
        imageViews[4] = findViewById(R.id.field_a5_iv);
        imageViews[5] = findViewById(R.id.field_b1_iv);
        imageViews[6] = findViewById(R.id.field_b2_iv);
        imageViews[7] = findViewById(R.id.field_b3_iv);
        imageViews[8] = findViewById(R.id.field_b4_iv);
        imageViews[9] = findViewById(R.id.field_b5_iv);
        imageViews[10] = findViewById(R.id.field_c1_iv);
        imageViews[11] = findViewById(R.id.field_c2_iv);
        imageViews[12] = findViewById(R.id.field_c3_iv);
        imageViews[13] = findViewById(R.id.field_c4_iv);
        imageViews[14] = findViewById(R.id.field_c5_iv);
        imageViews[15] = findViewById(R.id.field_d1_iv);
        imageViews[16] = findViewById(R.id.field_d2_iv);
        imageViews[17] = findViewById(R.id.field_d3_iv);
        imageViews[18] = findViewById(R.id.field_d4_iv);
        imageViews[19] = findViewById(R.id.field_d5_iv);
    }

    private void showCards() {
        images[0] = R.drawable.image1;
        images[1] = R.drawable.image2;
        images[2] = R.drawable.image3;
        images[3] = R.drawable.image4;
        images[4] = R.drawable.image5;
        images[5] = R.drawable.image6;
        images[6] = R.drawable.image7;
        images[7] = R.drawable.image8;
        images[8] = R.drawable.image9;
        images[9] = R.drawable.image10;
        images[10] = R.drawable.image11;
        images[11] = R.drawable.image12;
        images[12] = R.drawable.image13;
        images[13] = R.drawable.image14;
        images[14] = R.drawable.image15;
        images[15] = R.drawable.image16;
        images[16] = R.drawable.image17;
        images[17] = R.drawable.image18;
        images[18] = R.drawable.image19;
        images[19] = R.drawable.image20;

    }

    private void setClickListenerOnImageView() {
        Toast.makeText(MemoryGameActivity.this, String.valueOf(imageViews.length), Toast.LENGTH_SHORT).show();
        for (int i = 0; i < imageViews.length; i++) {
            final int finalI = i;
            imageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageViews[finalI].setImageResource(images[finalI]);
                    doStuff(imageViews[finalI], finalI);
                }
            });
        }
    }

    @TargetApi(Build.VERSION_CODES.P)
    private void doStuff(ImageView iv, int card) {
        if (cardNumber == 1) {
            firstCard = cardsArray[card];
            if (firstCard > 9) {
                firstCard = firstCard - 10;
            }
            cardNumber = 2;
            clickFirst = card;
         //   Toast.makeText(MemoryGameActivity.this, "First catddd: " + String.valueOf(firstCard), Toast.LENGTH_SHORT).show();
            iv.setEnabled(false);
        } else if (cardNumber == 2) {
            secondCard = cardsArray[card];
            if (secondCard > 9) {
                secondCard = secondCard - 10;
            }
            cardNumber = 1;
            clickSecond = card;
          //  Toast.makeText(MemoryGameActivity.this, "Second catddd: " + String.valueOf(secondCard), Toast.LENGTH_SHORT).show();

            for (int i = 0; i < imageViews.length; i++) {
                imageViews[i].setEnabled(false);
            }

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    equaling();
                }
            }, 1000);
        }
    }

    private void equaling() {
        if (firstCard == secondCard) {
            for (int i = 0; i < 20; i++) {
                if (clickFirst == i) {
                   // Toast.makeText(MemoryGameActivity.this, "Click first: " + String.valueOf(firstCard), Toast.LENGTH_SHORT).show();
                    imageViews[i].setVisibility(View.INVISIBLE);
                    break;
                }
            }
            for (int i = 0; i < 20; i++) {
                if (clickSecond == i) {
                  //  Toast.makeText(MemoryGameActivity.this, "Click second: " + String.valueOf(secondCard), Toast.LENGTH_SHORT).show();
                    imageViews[i].setVisibility(View.INVISIBLE);
                    break;
                }
            }


        } else {
          //  Toast.makeText(MemoryGameActivity.this, "Click first tt: " + String.valueOf(firstCard), Toast.LENGTH_SHORT).show();
           // Toast.makeText(MemoryGameActivity.this, "Click second dd: " + String.valueOf(secondCard), Toast.LENGTH_SHORT).show();
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[i].setImageResource(R.drawable.android);
            }
        }
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setEnabled(true);
        }

        checkEnd();

    }

    private void checkEnd() {
        if (checkVisibilityOfImageView()) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MemoryGameActivity.this);
            alertDialogBuilder.setMessage("Koniec gry").setCancelable(false)
                    .setPositiveButton("Nowa gra", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), MemoryGameActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton("Wyjscie", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MemoryGameActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }

    }

    private boolean checkVisibilityOfImageView() {
        boolean result = false;
        for (int i = 0; i < imageViews.length; i++) {
            if (imageViews[i].getVisibility() == View.INVISIBLE) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

}