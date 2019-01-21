package pl.lodz.uni.math.kuba.projekt1;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static pl.lodz.uni.math.kuba.projekt1.PhotoActivity.URI_IMAGE_LIST;

public class MemoryGameActivity extends AppCompatActivity {
    private ImageView imageFieldA1, imageFieldA2, imageFieldA3, imageFieldB1, imageFieldB2, imageFieldB3, imageFieldC1, imageFieldC2,
            imageFieldC3, imageFieldD1, imageFieldD2, imageFieldD3;
    private ImageView[] imageViewsTable;
    private Integer[] cardsArray = {0, 1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15};
    private int firstCard, secondCard, clickFirst, clickSecond, cardNumber = 1;
    private ArrayList<Uri> uriImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);

        uriImageList = new ArrayList<Uri>();
        uriImageList = (ArrayList<Uri>) getIntent().getSerializableExtra(URI_IMAGE_LIST);

        imageViewsTable = new ImageView[]{imageFieldA1, imageFieldA2, imageFieldA3, imageFieldB1, imageFieldB2, imageFieldB3, imageFieldC1,
                imageFieldC2, imageFieldC3, imageFieldD1, imageFieldD2, imageFieldD3};

        initializeVariables();

        for (int i = 0; i < imageViewsTable.length; i++) {
            imageViewsTable[i].setTag(String.valueOf(i));
        }

        Collections.shuffle(Arrays.asList(cardsArray));

        setClickListenerOnImageView();

    }


    private void initializeVariables() {
        imageViewsTable[0] = findViewById(R.id.image1);
        imageViewsTable[1] = findViewById(R.id.image2);
        imageViewsTable[2] = findViewById(R.id.image3);
        imageViewsTable[3] = findViewById(R.id.image4);
        imageViewsTable[4] = findViewById(R.id.image5);
        imageViewsTable[5] = findViewById(R.id.image6);
        imageViewsTable[6] = findViewById(R.id.image7);
        imageViewsTable[7] = findViewById(R.id.image8);
        imageViewsTable[8] = findViewById(R.id.image9);
        imageViewsTable[9] = findViewById(R.id.image10);
        imageViewsTable[10] = findViewById(R.id.image11);
        imageViewsTable[11] = findViewById(R.id.image12);
    }

    private void setClickListenerOnImageView() {
        for (int i = 0; i < imageViewsTable.length; i++) {
            final int finalI = i;
            imageViewsTable[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int card = Integer.parseInt((String) v.getTag());
                    setImageView(imageViewsTable[finalI], card);
                }
            });
        }
    }

    @TargetApi(Build.VERSION_CODES.P)
    private void setImageView(ImageView iv, int card) {
        for (int i = 0; i < cardsArray.length; i++) {
            if (i < 6) {
                if (cardsArray[card] == i) {
                    iv.setImageURI(uriImageList.get(i));
                }
            } else {
                if (cardsArray[card] == (i + 4)) {
                    iv.setImageURI(uriImageList.get(i - 6));
                }
            }
        }

        if (cardNumber == 1) {
            firstCard = cardsArray[card];
            if (firstCard > 9) {
                firstCard = firstCard - 10;
            }
            cardNumber = 2;
            clickFirst = card;
            iv.setEnabled(false);
        } else if (cardNumber == 2) {
            secondCard = cardsArray[card];
            if (secondCard > 9) {
                secondCard = secondCard - 10;
            }
            cardNumber = 1;
            clickSecond = card;

            for (int i = 0; i < imageViewsTable.length; i++) {
                imageViewsTable[i].setEnabled(false);
            }

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    compareSelectedCards();
                }
            }, 1000);
        }
    }

    private void compareSelectedCards() {
        if (firstCard == secondCard) {
            for (int i = 0; i < 12; i++) {
                if (clickFirst == i) {
                    imageViewsTable[i].setVisibility(View.INVISIBLE);
                    break;
                }
            }
            for (int i = 0; i < 12; i++) {
                if (clickSecond == i) {
                    imageViewsTable[i].setVisibility(View.INVISIBLE);
                    break;
                }
            }


        } else {
            for (int i = 0; i < imageViewsTable.length; i++) {
                imageViewsTable[i].setImageResource(R.drawable.add_icon);
            }
        }

        for (int i = 0; i < imageViewsTable.length; i++) {
            imageViewsTable[i].setEnabled(true);
        }

        checkIfTheEnd();

    }

    private void checkIfTheEnd() {
        if (checkVisibilityOfImageView()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MemoryGameActivity.this);
            alertDialogBuilder.setMessage("Koniec gry").setCancelable(false)
                    .setPositiveButton("Nowa gra", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), MemoryGameActivity.class);
                            intent.putExtra(URI_IMAGE_LIST, uriImageList);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton("Wyjscie", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MemoryGameActivity.this, PhotoActivity.class);
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
        for (int i = 0; i < imageViewsTable.length; i++) {
            if (imageViewsTable[i].getVisibility() == View.INVISIBLE) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }
}