package com.drivill.courier.merchantModul.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.drivill.courier.R;
import com.drivill.courier.databinding.FragmentSignUp2MerchantBinding;
import com.drivill.courier.fragment.SetPasswordFragment;
import com.drivill.courier.interfaces.SignUpListener;

import java.util.ArrayList;

import static com.drivill.courier.merchantModul.activity.SignUpActivityMerchant.product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUp2Merchant#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp2Merchant extends Fragment implements View.OnClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    FragmentSignUp2MerchantBinding mBinding;
    SignUpListener mListener;
    public static ArrayList<String> selectedProduct;
    ArrayList<CheckBox> checkBoxArrayList;

    void initUI() {
        selectedProduct = new ArrayList<>();
        checkBoxArrayList = new ArrayList<>();
        mListener = (SignUpListener) this.getActivity();
        mBinding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedProduct != null && selectedProduct.size() != 0) {
                    mListener.nextScreen(new SetPasswordFragment(), 2);
                } else {
                    Toast.makeText(getContext(), (R.string.select_product_first), Toast.LENGTH_SHORT).show();
                }
            }
        });

        for (int i = 0; i < product.size(); i++) {
            CheckBox cb = new CheckBox(getContext());
            cb.setText(product.get(i).getName());
            cb.setTag(product.get(i).getId());
            cb.setPadding(25, 0, 25, 0);
            cb.setOnClickListener(this);
            cb.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            mBinding.productLL.addView(cb);
            checkBoxArrayList.add(cb);
        }

    }


    public SignUp2Merchant() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUp2Merchant.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUp2Merchant newInstance(String param1, String param2) {
        SignUp2Merchant fragment = new SignUp2Merchant();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up2_merchant, container, false);
        mBinding = DataBindingUtil.bind(view);
        initUI();
        return view;
    }

    @Override
    public void onClick(View view) {

        String name = view.getTag().toString();
        if (selectedProduct.contains(name)) {
            selectedProduct.remove(name);
        } else selectedProduct.add(name);


        Log.d("tag", String.valueOf(selectedProduct));
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.nextScreen(null, 1);
        for (String tag : selectedProduct) {
            for (int i = 0; i < checkBoxArrayList.size(); i++) {
                if (checkBoxArrayList.get(i).getTag().toString().equals(tag)) {
                    checkBoxArrayList.get(i).setChecked(true);
                    break;
                }
            }

        }
    }
}