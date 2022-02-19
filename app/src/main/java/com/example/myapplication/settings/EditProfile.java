package com.example.myapplication.settings;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.home.FirstFragment;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.parse.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class EditProfile extends AppCompatActivity {



    ImageView btnOpenDialog;

    RelativeLayout openPopupSpol;

    EditText openPopupDatum;
    private int mYear, mMonth, mDay;

    TextView txSpol;

    EditText username;

    EditText txIme;

    RelativeLayout openEditIme;

    RelativeLayout onClickTrazimSpol;
    TextView txTraziSpol;
    EditText onClickIzmeduGodina;

    EditText godinaOd;
    EditText godinaDo;

    ImageView saveYears;

    EditText editTxAboutPartner;
    ImageView saveAboutPartner;

    EditText editTxAboutYou;
    ImageView saveAboutYou;

    private Menu action;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.myapplication.R.layout.edit_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txIme = findViewById(com.example.myapplication.R.id.txIme);
        openPopupSpol =  findViewById(com.example.myapplication.R.id.onClickSpol);
        txSpol =  findViewById(com.example.myapplication.R.id.txSpol);
        openPopupDatum =  findViewById(com.example.myapplication.R.id.editTxDatumRodenja);
        openEditIme = findViewById(com.example.myapplication.R.id.openPopupIme);
        username = findViewById(com.example.myapplication.R.id.editTxNadimak);
        godinaOd = findViewById(com.example.myapplication.R.id.txGodinaOd);
        godinaDo = findViewById(com.example.myapplication.R.id.txGodinaDo);
        onClickTrazimSpol = findViewById(com.example.myapplication.R.id.onClickTrazimSpol);
        saveYears = findViewById(com.example.myapplication.R.id.savePickYears);

        txTraziSpol = findViewById(R.id.txtraziSpol);

        editTxAboutPartner = findViewById(R.id.editTxAboutPartner);
        saveAboutPartner = findViewById(R.id.saveAboutPartner);

        editTxAboutYou = findViewById(R.id.editTxAboutYou);
        saveAboutYou = findViewById(R.id.saveAboutYou);


        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        //dohacanje podataka o useru
          getUserData();

        //otvara alert za odabir spola
          openPopupEditSex();

        //otvara prozor za odabir datuma
          openPopupEditBirthdate();

        //otvora alert za edit imena; lambda izraz, ne lose
         //txIme.setOnClickListener(view -> openPopupEditName());

         //otvara alert za edit nadimka
         //username.setOnClickListener(view -> openPopupEditUsername());

         //dohvaca profilnu sliku
           getProfileImageOfUser();

         //dohavaca nadimak
            username.setText(ParseUser.getCurrentUser().getUsername());

         //trazi spol popup
            openPopupPickSexForMeeting();

         //sprema godine od i do
            saveYearsForMeeting();

            //sprema aboutPartner
            saveAboutPartner();

            //sprema aboutYou
            saveAboutYou();


        openPopupDatum.setEnabled(false);
        openPopupDatum.setFocusableInTouchMode(false);
        openPopupDatum.setFocusable(false);

        openPopupSpol.setEnabled(false);
        openPopupSpol.setFocusableInTouchMode(false);
        openPopupSpol.setFocusable(false);

        onClickTrazimSpol.setEnabled(false);
        onClickTrazimSpol.setFocusableInTouchMode(false);
        onClickTrazimSpol.setFocusable(false);


        //otvara library za odabir slike iz galerije ili fotoaparat
        btnOpenDialog = findViewById(com.example.myapplication.R.id.imgBtnOpenDialog);

       // Toast.makeText(EditProfile.this, btnOpenDialog.getDrawable().toString(), Toast.LENGTH_LONG).show();

       // Bitmap bm = ((BitmapDrawable) btnOpenDialog.getDrawable()).getBitmap();

        //Bitmap icon = BitmapFactory.decodeResource(EditProfile.this.getResources(),
               // R.drawable.avatar);


       // Log.e(btnOpenDialog.getDrawable().toString(), "bruh");

        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseFile imageFile = (ParseFile) currentUser.get("Profil_image");

        if(imageFile == null) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(EditProfile.this);
            builder1.setMessage(getString(R.string.prikazNaProfiluAlert));
            builder1.setCancelable(true);

            builder1.setPositiveButton("Ok", (dialog, id) -> dialog.cancel());
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }


            btnOpenDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImagePicker.Companion.with(EditProfile.this)
                            .cropSquare()
                            .compress(1024)
                            .maxResultSize(350, 200)
                            .start();
                }
            });
    }


    //odabranu sliku stavlja u imageview i uploada na db
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        btnOpenDialog.setImageURI(uri);




        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);

            byte[] bitmapData = bos.toByteArray();

            ParseFile newImage = new ParseFile(bitmapData);

            ParseUser user = ParseUser.getCurrentUser();
            user.put("Profil_image", newImage);
            user.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(EditProfile.this, getString(com.example.myapplication.R.string.slikaUploadana), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditProfile.this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //action bar spremi itd...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_settings, menu);

        action = menu;
        action.findItem(R.id.save).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

//klik na action baru, npr. spremi
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:

                txIme.setFocusableInTouchMode(true);
                username.setFocusableInTouchMode(true);

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(EditProfile.this.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(txIme, inputMethodManager.SHOW_IMPLICIT);

                openPopupDatum.setEnabled(true);
                openPopupDatum.setFocusableInTouchMode(true);


                openPopupSpol.setEnabled(true);
                openPopupSpol.setFocusableInTouchMode(true);

                onClickTrazimSpol.setEnabled(true);
                onClickTrazimSpol.setFocusableInTouchMode(true);


                action.findItem(R.id.edit).setVisible(false);
                action.findItem(R.id.save).setVisible(true);

                return true;

            case R.id.save:

                action.findItem(R.id.edit).setVisible(true);
                action.findItem(R.id.save).setVisible(false);



                username.setFocusableInTouchMode(false);
                username.setFocusable(false);
                nadimak = username.getText().toString();

                txIme.setFocusableInTouchMode(false);
                txIme.setFocusable(false);
                ime = txIme.getText().toString();

                openPopupDatum.setEnabled(false);
                openPopupDatum.setFocusableInTouchMode(false);
                openPopupDatum.setFocusable(false);

                openPopupSpol.setEnabled(false);
                openPopupSpol.setFocusableInTouchMode(false);
                openPopupSpol.setFocusable(false);

                onClickTrazimSpol.setEnabled(false);
                onClickTrazimSpol.setFocusableInTouchMode(false);
                onClickTrazimSpol.setFocusable(false);


                saveUserData(ime, datumRodenja, spol, nadimak, trazimSpol);

                return true;

            case android.R.id.home:
                this.finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }




    public void saveUserData(String ime, String datumRodenja, String spol, String nadimak, String zelimUpoznati) {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("email", ParseUser.getCurrentUser().getEmail());


        query.findInBackground((objects, e) -> {
            if(e == null){
                for (ParseObject object:objects){
                    query.getInBackground(object.getObjectId(), (object1, e1) -> {
                        if(e1 == null) {
                            if(ime != null){
                                object1.put("Ime", ime);
                            }
                            if(datumRodenja != null) {
                                object1.put("Datum_rodenja", datumRodenja);
                            }
                            if(spol != null) {
                                object1.put("User_spol", spol);
                            }
                            if(nadimak != null)  {
                                object1.put("username", nadimak);
                            }
                            if(zelimUpoznati != null) {
                                object1.put("User_traziSpol", zelimUpoznati);
                            }

                            object1.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if(e == null) {
                                        Toast.makeText(EditProfile.this, getString(R.string.uspijesnoAzurirano), Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(EditProfile.this, getString(R.string.dosloJeDoGreske), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(EditProfile.this, getString(R.string.dosloJeDoGreske), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            else {
                Toast.makeText(EditProfile.this, getString(R.string.dosloJeDoGreske), Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void getUserData() {

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("email", ParseUser.getCurrentUser().getEmail());

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                for(ParseObject object:objects){
                    query.getInBackground(object.getObjectId(), new GetCallback<ParseUser>() {
                        @Override
                        public void done(ParseUser object, ParseException e) {
                            txSpol.setText(object.getString("User_spol"));
                            txIme.setText(object.getString("Ime"));
                            openPopupDatum.setText(object.getString("Datum_rodenja"));
                            txTraziSpol.setText(object.getString("User_traziSpol"));
                           // godinaOd.setText(object.getString("Godine_od"));
                           // godinaDo.setText(object.getString("Godine_do"));
                           // editTxAboutPartner.setText(object.getString("About_partner"));
                           // editTxAboutYou.setText(object.getString("About_you"));
                        }
                    });
                }
            }
        });
    }


    void getProfileImageOfUser() {

        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseFile imageFile = (ParseFile) currentUser.get("Profil_image");

        if (imageFile != null) {
            imageFile.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    if(e == null) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);


                        btnOpenDialog.setImageBitmap(bitmap);
                    }
                    else {
                        Toast.makeText(EditProfile.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Log.e("ma nis", "slika nije stavljena");
        }
    }


    String god_od;
    String god_do;
    public void saveYearsForMeeting() {
        EditProfileActivity editProfileActivity = new EditProfileActivity();

        saveYears.setOnClickListener(view -> {
            god_od = godinaOd.getText().toString();
            god_do = godinaDo.getText().toString();
            editProfileActivity.editYearsForMeeting(god_od, god_do, EditProfile.this);
        });
    }

    String aboutParnter;
    public void  saveAboutPartner() {
        EditProfileActivity editProfileActivity = new EditProfileActivity();

        saveAboutPartner.setOnClickListener(view -> {
            aboutParnter = editTxAboutPartner.getText().toString();
            editProfileActivity.editAboutPartner(aboutParnter, EditProfile.this);
        });
    }

    String aboutYou;
    public void  saveAboutYou() {
        EditProfileActivity editProfileActivity = new EditProfileActivity();

        saveAboutYou.setOnClickListener(view -> {
            aboutYou = editTxAboutYou.getText().toString();
            editProfileActivity.editAboutYou(aboutYou, EditProfile.this);
        });
    }


    String datumRodenja;
    String trazimSpol;
    String ime;
    String nadimak;
    String spol;
   public void openPopupEditSex() {

       openPopupSpol.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditProfile.this);
               alertDialog.setTitle(getString(com.example.myapplication.R.string.odaberiSpol));
               String[] items = {getString(com.example.myapplication.R.string.tiSiMuskarac),getString(com.example.myapplication.R.string.tiSiZena)};
               int checkedItem = 1;
               alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       switch (which) {
                           case 0:
                              // Toast.makeText(EditProfile.this, "Vi ste muškarac", Toast.LENGTH_LONG).show();
                               txSpol.setText(getString(com.example.myapplication.R.string.tiSiMuskarac));
                               break;
                           case 1:
                             //  Toast.makeText(EditProfile.this, "Vi ste žena", Toast.LENGTH_LONG).show();
                               txSpol.setText(getString(com.example.myapplication.R.string.tiSiZena));
                               break;
                       }
                   }
               });

               alertDialog.setPositiveButton(getString(com.example.myapplication.R.string.spremi), new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int whichButton) {
                       spol = txSpol.getText().toString();
                      // editSexOfUser(spol);
                   }
               });
               alertDialog.setNegativeButton(getString(com.example.myapplication.R.string.odustani), new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int whichButton) {
                       // Canceled.
                   }
               });
               AlertDialog alert = alertDialog.create();

               alert.show();
           }
       });
    }



    public void openPopupPickSexForMeeting() {
        onClickTrazimSpol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditProfile.this);
                alertDialog.setTitle(getString(com.example.myapplication.R.string.trazim));
                String[] items = {getString(R.string.trazimZenu),getString(R.string.trazimMuskarca)};
                int checkedItem = 1;
                alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                txTraziSpol.setText(getString(R.string.trazimZenu));
                                break;
                            case 1:
                                txTraziSpol.setText(getString(R.string.trazimMuskarca));
                                break;
                        }
                    }
                });

                alertDialog.setPositiveButton(getString(com.example.myapplication.R.string.spremi), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        EditProfileActivity editProfileActivity = new EditProfileActivity();
                        trazimSpol = txTraziSpol.getText().toString();
                    }
                });
                alertDialog.setNegativeButton(getString(R.string.odustani), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });
                AlertDialog alert = alertDialog.create();

                alert.show();
            }
        });
    }



  public void  openPopupEditBirthdate() {
      openPopupDatum.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (v == openPopupDatum) {
                  final Calendar calendar = Calendar.getInstance ();
                  mYear = calendar.get ( Calendar.YEAR );
                  mMonth = calendar.get ( Calendar.MONTH );
                  mDay = calendar.get ( Calendar.DAY_OF_MONTH );

                  //show dialog
                  DatePickerDialog datePickerDialog = new DatePickerDialog ( EditProfile.this, new DatePickerDialog.OnDateSetListener () {
                      @Override
                      public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                          openPopupDatum.setText( dayOfMonth + "/" + (month + 1) + "/" + year);
                          datumRodenja = openPopupDatum.getText().toString();
                          //  editBirthDateOfUser(datumRodenja);
                      }
                  }, mYear, mMonth, mDay );

                  datePickerDialog.show ();
              }
          }
      });

    }
}


