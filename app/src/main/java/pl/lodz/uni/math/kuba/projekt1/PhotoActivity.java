package pl.lodz.uni.math.kuba.projekt1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_1 = 1, PICK_IMAGE_2 = 2, PICK_IMAGE_3 = 3, PICK_IMAGE_4 = 4, PICK_IMAGE_5 = 5, PICK_IMAGE_6 = 6;
    private static final String IMAGE_LIST = "IMAGE_LIST";
    static final String URI_IMAGE_LIST = "URI_IMAGE_LIST";
    private ImageView selectedPhoto1, selectedPhoto2, selectedPhoto3, selectedPhoto4, selectedPhoto5, selectedPhoto6;
    private Button startGameBtn;
    private ArrayList<Uri> uriImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        initializeVariables();

        selectPhotoFromGallery(selectedPhoto1, PICK_IMAGE_1);
        selectPhotoFromGallery(selectedPhoto2, PICK_IMAGE_2);
        selectPhotoFromGallery(selectedPhoto3, PICK_IMAGE_3);
        selectPhotoFromGallery(selectedPhoto4, PICK_IMAGE_4);
        selectPhotoFromGallery(selectedPhoto5, PICK_IMAGE_5);
        selectPhotoFromGallery(selectedPhoto6, PICK_IMAGE_6);

        startGameOnClick();
    }

    private void selectPhotoFromGallery(ImageView selectedPhoto, final int PICK_IMAGE) {
        selectedPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE);
            }
        });
    }

    private void startGameOnClick() {
        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uriImageList.size() > 5) {
                    Intent intent = new Intent(PhotoActivity.this, MemoryGameActivity.class);
                    intent.putExtra(URI_IMAGE_LIST, uriImageList);
                    startActivity(intent);
                } else {
                    Toast.makeText(PhotoActivity.this, "Nie wybrano wszsytkich zdjec", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_1) {
            Uri uriImage1 = data.getData();
            selectedPhoto1.setImageURI(uriImage1);
            uriImageList.add(uriImage1);
        }
        if (requestCode == PICK_IMAGE_2) {
            Uri uriImage2 = data.getData();
            selectedPhoto2.setImageURI(uriImage2);
            uriImageList.add(uriImage2);
        }
        if (requestCode == PICK_IMAGE_3) {
            Uri uriImage3 = data.getData();
            selectedPhoto3.setImageURI(uriImage3);
            uriImageList.add(uriImage3);
        }
        if (requestCode == PICK_IMAGE_4) {
            Uri uriImage4 = data.getData();
            selectedPhoto4.setImageURI(uriImage4);
            uriImageList.add(uriImage4);
        }
        if (requestCode == PICK_IMAGE_5) {
            Uri uriImage5 = data.getData();
            selectedPhoto5.setImageURI(uriImage5);
            uriImageList.add(uriImage5);
        }
        if (requestCode == PICK_IMAGE_6) {
            Uri uriImage6 = data.getData();
            selectedPhoto6.setImageURI(uriImage6);
            uriImageList.add(uriImage6);
        }
    }

    private void initializeVariables() {
        selectedPhoto1 = findViewById(R.id.selected_image1);
        selectedPhoto2 = findViewById(R.id.selected_image2);
        selectedPhoto3 = findViewById(R.id.selected_image3);
        selectedPhoto4 = findViewById(R.id.selected_image4);
        selectedPhoto5 = findViewById(R.id.selected_image5);
        selectedPhoto6 = findViewById(R.id.selected_image6);
        startGameBtn = (Button) findViewById(R.id.start_game_button);
        uriImageList = new ArrayList<>();

    }
}