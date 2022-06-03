package com.drivill.courier.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.drivill.courier.R;
import com.drivill.courier.activity.OTPVerificationActivity;
import com.drivill.courier.interfaces.OTPVerification;

public class GenericTextWatcher implements TextWatcher {
    private View view, nextView;
    OTPVerification mListener;

    public GenericTextWatcher(View view, View view2) {
        this.view = view;
        this.nextView = view2;
        mListener = (OTPVerification) OTPVerificationActivity.otpContext;
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // TODO Auto-generated method stub
        String text = editable.toString();
      /*  switch (view.getId()) {

            case R.id.editText1:
                if (text.length() == 1)
                    mBinding.editText2.requestFocus();
                break;
            case R.id.editText2:
                if (text.length() == 1)
                    mBinding.editText3.requestFocus();
                else if (text.length() == 0)
                    mBinding.editText1.requestFocus();
                break;
            case R.id.editText3:
                if (text.length() == 1)
                    mBinding.editText4.requestFocus();
                else if (text.length() == 0)
                    mBinding.editText2.requestFocus();
                break;
            case R.id.editText4:
                if (text.length() == 0)
                    mBinding.editText3.requestFocus();
                break;
        }*/

        switch (view.getId()) {
            case R.id.editText1:
            case R.id.editText2:
            case R.id.editText4:
                //case R.id.editText5:
            case R.id.editText3:
                //case R.id.editText6:
                if (text.length() == 1)
                    if (nextView != null) {
                        nextView.requestFocus();
                    } else {
                        mListener.otpVerify(true);
                    }

                break;

            //You can use EditText4 same as above to hide the keyboard
        }
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }


}
