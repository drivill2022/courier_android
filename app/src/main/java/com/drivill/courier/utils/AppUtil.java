package com.drivill.courier.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.drivill.courier.R;
import com.drivill.courier.interfaces.YesNoInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;

public class AppUtil {

    public static String imgFilePathTemp;

    public static void showAlert(String msg, final Context context) {
        final AlertDialog.Builder builder =
                //new AlertDialog.Builder(context, R.style.MyDialogTheme);
                new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(context.getString(R.string.app_name)).
                setMessage(msg).
                setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    @SuppressLint("SetTextI18n")
    public static void showCustomDialog(String msg, final Context context) {
        Dialog dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.logout_dialog_layout, null);
        TextView heading, acount, okBtn, cancelBtn;
        ImageView logo;
        heading = view.findViewById(R.id.textView9);
        acount = view.findViewById(R.id.textView10);
        okBtn = view.findViewById(R.id.logoutTxt);
        cancelBtn = view.findViewById(R.id.cancelTxt);
        logo = view.findViewById(R.id.imageView4);
        logo.setVisibility(View.GONE);
        cancelBtn.setVisibility(View.GONE);
        acount.setVisibility(View.GONE);

        heading.setText(msg);
        okBtn.setText("OK");
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.show();

    }

    public static List<Address> getAddressList(Context context, String editable) throws IOException {

        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        return geocoder.getFromLocationName(String.valueOf(editable), 5);

    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isValidURL(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

    public static boolean isValidPhone(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() >= 8 && phone.length() <= 12;
        }
        return false;
    }

    public static boolean isValidPassword(final String password) {
        /*  (?=.*[@#$%^&+=])*/ //is the Spacial Char checker
        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public static String getDateFormat(Date date) {
        // SimpleDateFormat localDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault());
        SimpleDateFormat localDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return localDateFormat.format((date.getTime()));
    }

    public static String UppercaseFirstLetters(String str) {
        boolean prevWasWhiteSp = true;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])) {
                if (prevWasWhiteSp) {
                    chars[i] = Character.toUpperCase(chars[i]);
                }
                prevWasWhiteSp = false;
            } else {
                prevWasWhiteSp = Character.isWhitespace(chars[i]);
            }
        }
        return new String(chars);
    }


    public static void hideKeyBoard(Context context) {
        View viewq = ((Activity) context).getCurrentFocus();
        if (viewq != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(viewq.getWindowToken(), 0);
        }
    }

    public static void hideKeyBoardWithAllView(Context context, MotionEvent event) {
        View v = ((Activity) context).getCurrentFocus();

        if (v instanceof EditText) {
            View w = ((Activity) context).getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            //      Log.d("Activity", "Touch event " + event.getRawX() + "," + event.getRawY() + " " + x + "," + y + " rect " + w.getLeft() + "," + w.getTop() + "," + w.getRight() + "," + w.getBottom() + " coords " + scrcoords[0] + "," + scrcoords[1]);
            if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom())) {

                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(((Activity) context).getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public static SpannableString getHalfBoldString(String bold) {
        SpannableString ss1 = new SpannableString(bold);
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
        return ss1;
    }


    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

    public static void showKeyboard(Context context) {
        ((InputMethodManager) (context).getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static SpannableStringBuilder getStarHint(String simple) {
        String colored = " *";
        SpannableStringBuilder builder = new SpannableStringBuilder();

        builder.append(simple);
        int start = builder.length();
        builder.append(colored);
        int end = builder.length();

        builder.setSpan(new ForegroundColorSpan(Color.RED), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    public static void yesNoDialog(final Context context, String reason, String From, final YesNoInterface yesNoInteface) {
        AlertDialog builder = new AlertDialog.Builder(context).create();
        View view = View.inflate(context, R.layout.dialog_cancel_delivery, null);
        builder.setView(view);

        TextView yescancelBtn = view.findViewById(R.id.yescancelBtn);
        TextView noBtn = view.findViewById(R.id.noBtn);
        TextView itemId = view.findViewById(R.id.itemIdTxt);
        TextView title_d = view.findViewById(R.id.title_d);
        TextView desc = view.findViewById(R.id.desc);
        itemId.setText(reason);

        if(From.equalsIgnoreCase("mer")){
            yescancelBtn.setText(context.getString(R.string.yes_delete));
            title_d.setText(context.getString(R.string.delete));
            desc.setText(context.getString(R.string.are_you_realy_want_to_delete_the_shipment));
        }

        builder.setCancelable(false);

        yescancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesNoInteface.isNoYes(true);
                builder.dismiss();

            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesNoInteface.isNoYes(false);
                builder.dismiss();
            }
        });
        // builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.show();
    }

    public static void okayEventDialog(String s, Context context, final YesNoInterface oKayEvent) {

        AlertDialog.Builder builder =
                //  new AlertDialog.Builder(context, R.style.MyDialogTheme);
                new AlertDialog.Builder(context);
        builder.setTitle("ListApp");
        builder.setCancelable(false);
        builder.setMessage(s);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //   oKayEvent.okayEvent(true);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static String fileName;

    @SuppressLint("SimpleDateFormat")
    public static Uri getOutputMediaFile(int type, Context context) {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), context.getString(R.string.app_name));

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File mediaFile;
        if (type == 1) {
            String imageStoragePath = mediaStorageDir + " Images/";

            createDirectory(imageStoragePath);
            mediaFile = new File(imageStoragePath + "IMG" + timeStamp + ".jpg");
        } else {
            return null;
        }
        fileName = mediaFile.getName();
        return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", mediaFile);
    }

    private static File createImageFile(Context context) throws IOException {
        // Create an image file name
        String currentPhotoPath;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        fileName = image.getAbsolutePath();
        // Log.d("path",currentPhotoPath);
        return image;
    }

    public static void TakePictureIntent(Activity activity, Context act, int permission) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(act.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(act);
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.i("ex-", "==>" + ex.toString());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(act, act.getApplicationContext()
                        .getPackageName() + ".provider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                // takePictureIntent.addFlags(FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                activity.startActivityForResult(takePictureIntent, permission);
            }
        }
    }

    public static void createDirectory(String filePath) {
        if (!new File(filePath).exists()) {
            new File(filePath).mkdirs();
        }
    }

    private static String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), (R.string.app_name) + "/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        return (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
    }

    public static String compressImage(File fff) {

        String filePath = fff.getAbsolutePath();
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = (float) actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        assert scaledBitmap != null;
        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - (float) bmp.getWidth() / 2, middleY - (float) bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;

    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    private static void clearNotification(Context context) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancelAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void callCameraPermission(Activity activity, int permission) {
        try {

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri fileUri = AppUtil.getOutputMediaFile(1, activity);
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                intent1.addFlags(FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                activity.startActivityForResult(intent1, permission);
            } else {
               AppUtil.TakePictureIntent(activity, activity, permission);
            }
            //context.grantUriPermission(packageName, uri, FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

        } catch (Exception e) {
            showAlert("Something went wrong", activity);
        }
    }


    public static void callGalleryPermission(Activity activity, int permission) {

        Intent intent;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            // Above 11
            intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }

        try {
            activity.startActivityForResult(intent, permission);
            // act.startActivityForResult(intent,PROFILE_IMG_PERMISSION);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static boolean checkPermissionForStorage(Activity activity) {

        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;


    }

    public static boolean checkPermissionForCall(Context activity) {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkPermissionForCamera(Activity activity) {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkPermissionForLocation(Activity activity) {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED;
    }


    public static void requestPermission(Activity activity, String permissionFor, int permission) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissionFor)) {

                try {
                    ActivityCompat.requestPermissions(activity, new String[]{permissionFor, Manifest.permission.WRITE_EXTERNAL_STORAGE}, permission);
                } catch (Exception e) {
                    //AppUtil.showAlert("Please allow permission for uploading file", activity);
                    e.printStackTrace();
                }
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{permissionFor, Manifest.permission.WRITE_EXTERNAL_STORAGE}, permission);
            }
    }

    public static void setImg(Context context, String img, ImageView toImgView) {
        Glide.with(context)
                .load(img)
                .placeholder(R.drawable.splash_logo)
                .into(toImgView);
    }
    public static void setImgCircle(Context context, String img, ImageView toImgView) {
        Glide.with(context)
                .load(img)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.splash_logo)
                .into(toImgView);
    }

    public static String getDateFormat(String createdAt, String tag) {

        SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        //  String inputPattern = "yyyy-MM-dd'T'HH:mm:ssZZ";
        String outputPattern;
        if (tag.equals("home")) {
            outputPattern = "dd-MMM-yyyy";
        } else if (tag.equals("track")) {
            outputPattern = "dd-MMM h:mm a";
        } else outputPattern = "dd-MMM-yyyy h:mm a";
        // String outputPattern = "dd-MMM-yyyy h:mm a";
        //    SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = localDateFormat.parse(createdAt);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;

    }

}
