package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
    TextView txDatum;

    EditText username;

    EditText txIme;

    RelativeLayout openEditIme;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txIme = findViewById(R.id.txIme);
        openPopupSpol =  findViewById(R.id.onClickSpol);
        txSpol =  findViewById(R.id.txSpol);
        openPopupDatum =  findViewById(R.id.editTxDatumRodenja);
        openEditIme = findViewById(R.id.openPopupIme);
        username = findViewById(R.id.editTxNadimak);

        //dohacanje podataka o useru
          getUserData();

        //otvara alert za odabir spola
          openPopupEditSex();

        //otvara prozor za odabir datuma
          openPopupEditBirthdate();

        //otvora alert za edit imena; lambda izraz, ne lose
         txIme.setOnClickListener(view -> openPopupEditName());

         //otvara alert za edit nadimka
         username.setOnClickListener(view -> openPopupEditUsername());

         //dohvaca profilnu sliku
           getProfileImageOfUser();

         //dohavaca nadimak
            username.setText(ParseUser.getCurrentUser().getUsername());



        //otvara library za odabir slike iz galerije ili fotoaparat
        btnOpenDialog = (ImageView) findViewById(R.id.imgBtnOpenDialog);
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
                        Toast.makeText(EditProfile.this, "Slika uploadana", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditProfile.this, "Doslo je do greske" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



//klik na action baru, npr. spremi
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.spremi:
                Toast.makeText(EditProfile.this, "Rabilo je prije", Toast.LENGTH_SHORT).show();
                // User chose the "Settings" item, show the app settings UI...

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

    //action bar spremi itd...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_settings, menu);

        return super.onCreateOptionsMenu(menu);
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
                        Toast.makeText(EditProfile.this, "opet nis od slike", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Log.e("ma nis", "slika nije stavljena");
        }
    }


    String ime;
    public void editNameOfUser(String ime) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("editProfile");
        query.whereEqualTo("User_email", ParseUser.getCurrentUser().getEmail());

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                String objectID = "";
                if(e == null){
                    objectID = object.getObjectId();

                    query.getInBackground(objectID, new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject object, ParseException e) {
                            if(e == null){
                                object.put("Ime", ime);

                                object.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            Toast.makeText(EditProfile.this, "Uspijesno azurirano", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(EditProfile.this, "Doslo je do greske u azuriranju podataka" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(EditProfile.this, "Doslo je do greske u azuriranju coursa" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(EditProfile.this, "Doslo je do greske u objectID-u", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    String datumRodenja;
    public void editBirthDateOfUser(String datumRodenja) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("editProfile");
        query.whereEqualTo("User_email", ParseUser.getCurrentUser().getEmail());

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                String objectID = "";
                if(e == null){
                    objectID = object.getObjectId();

                    query.getInBackground(objectID, new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject object, ParseException e) {
                            if(e == null){
                                object.put("Datum_rodenja", datumRodenja);

                                object.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            Toast.makeText(EditProfile.this, "Uspijesno azurirano", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(EditProfile.this, "Doslo je do greske u azuriranju podataka" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(EditProfile.this, "Doslo je do greske u azuriranju coursa" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(EditProfile.this, "Doslo je do greske u objectID-u", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    String spol;
    public void editSexOfUser(String spol) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("editProfile");
        query.whereEqualTo("User_email", ParseUser.getCurrentUser().getEmail());

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                String objectID = "";
                if(e == null){
                    objectID = object.getObjectId();

                    query.getInBackground(objectID, new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject object, ParseException e) {
                            if(e == null){
                                object.put("User_spol", spol);

                                object.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            Toast.makeText(EditProfile.this, "Uspijesno azurirano", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(EditProfile.this, "Doslo je do greske u azuriranju podataka" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(EditProfile.this, "Doslo je do greske u azuriranju coursa" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(EditProfile.this, "Doslo je do greske u objectID-u", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    String nadimak;
    public void editNameOfUsername(String nadimak) {
        ParseUser user = ParseUser.getCurrentUser();
        user.setUsername(nadimak);
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {
                    Toast.makeText(EditProfile.this, "Uspijesno azurirano", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditProfile.this, "Doslo je do greske " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


   public void getUserData() {

       ParseQuery<ParseObject> query = ParseQuery.getQuery("editProfile");
       query.whereEqualTo("User_email", ParseUser.getCurrentUser().getEmail());

       query.findInBackground(new FindCallback<ParseObject>() {
           @Override
           public void done(List<ParseObject> objects, ParseException e) {
               String objectId = "";
               if(e == null) {
                   for (ParseObject object:objects){
                       objectId = object.getObjectId();
                   }
               }
               else {
                   Log.e("Nesto je krivo", e.getMessage());
               }

               ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("editProfile");
               query.getInBackground(objectId, new GetCallback<ParseObject>() {
                   @Override
                   public void done(ParseObject object, ParseException e) {
                       if(e == null) {
                           txSpol.setText(object.getString("User_spol"));
                           txIme.setText(object.getString("Ime"));
                           openPopupDatum.setText(object.getString("Datum_rodenja"));
                       }
                       else {
                           Log.e("Nista od toga", e.getMessage());
                       }
                   }
               });
           }
       });
    }

    public void openPopupEditName () {
        EditText editTextField  = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Unesi svoje ime")
                .setView(editTextField)
                .setPositiveButton("Spremi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String editTextInput = editTextField.getText().toString();
                        txIme.setText(editTextInput);
                        ime = txIme.getText().toString();
                        editNameOfUser(ime);
                        Log.d("onclick","editext value is: "+ editTextInput);
                    }
                })
                .setNegativeButton("Odustani", null)
                .create();
        dialog.show();
    }

    public void openPopupEditUsername () {
        EditText editTextField  = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Unesi novi nadimak")
                .setView(editTextField)
                .setPositiveButton("Spremi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String editTextInput = editTextField.getText().toString();
                        username.setText(editTextInput);
                        nadimak = username.getText().toString();
                        editNameOfUsername(nadimak);
                        Log.d("onclick","editext value is: "+ editTextInput);
                    }
                })
                .setNegativeButton("Odustani", null)
                .create();
        dialog.show();
    }

   public void openPopupEditSex() {

       openPopupSpol.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditProfile.this);
               alertDialog.setTitle("Odaberi spol");
               String[] items = {"Muškarac","Žena"};
               int checkedItem = 1;
               alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       switch (which) {
                           case 0:
                               Toast.makeText(EditProfile.this, "Vi ste muškarac", Toast.LENGTH_LONG).show();
                               txSpol.setText("Muškarac".toString());
                               break;
                           case 1:
                               Toast.makeText(EditProfile.this, "Vi ste žena", Toast.LENGTH_LONG).show();
                               txSpol.setText("Žena".toString());
                               break;
                       }
                   }
               });

               alertDialog.setPositiveButton("Spremi", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int whichButton) {
                       spol = txSpol.getText().toString();
                       editSexOfUser(spol);
                   }
               });
               alertDialog.setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
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
                            editBirthDateOfUser(datumRodenja);
                        }
                    }, mYear, mMonth, mDay );

                    datePickerDialog.show ();
                }
            }
        });
    }
}


