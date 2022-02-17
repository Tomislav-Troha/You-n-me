package com.example.myapplication.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;
import com.example.myapplication.R;
import com.example.myapplication.settings.EditProfile;
import com.parse.*;

public class EditProfileActivity extends EditProfile {

    EditProfileActivity() {

    }


    public void editAboutPartner(String aboutPartner,  Context mContext) {
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
                                object.put("About_partner", aboutPartner);


                                object.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            Toast.makeText(mContext, mContext.getString(R.string.uspijesnoAzurirano), Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(mContext, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(mContext, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void editAboutYou(String aboutYou,  Context mContext) {
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
                                object.put("About_you", aboutYou);


                                object.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            Toast.makeText(mContext, mContext.getString(R.string.uspijesnoAzurirano), Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(mContext, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(mContext, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void editYearsForMeeting(String godineOd, String godineDo,  Context mContext) {
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
                                object.put("Godine_od", godineOd);
                                object.put("Godine_do", godineDo);

                                object.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            Toast.makeText(mContext, mContext.getString(R.string.uspijesnoAzurirano), Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                           Toast.makeText(mContext, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(mContext, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void editNameOfUser(String ime, Context context) {

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
                                            Toast.makeText(context, context.getString(com.example.myapplication.R.string.uspijesnoAzurirano), Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(context, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(context, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void editPickSexForMeeting(String spol, Context context) {

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
                                object.put("User_traziSpol", spol);

                                object.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            Toast.makeText(context, context.getString(R.string.uspijesnoAzurirano), Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(context, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(context, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
