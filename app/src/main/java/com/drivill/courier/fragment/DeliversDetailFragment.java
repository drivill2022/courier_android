package com.drivill.courier.fragment;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drivill.courier.R;
import com.drivill.courier.databinding.FragmentDeliveresDetailBinding;
import com.drivill.courier.merchantModul.model.ShipmentModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeliversDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeliversDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "data";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ShipmentModel mData;
    private String mParam2;

    FragmentDeliveresDetailBinding mBinding;

    public DeliversDetailFragment() {
        // Required empty public constructor
    }

    public static DeliversDetailFragment newInstance(boolean param1, String param2) {
        DeliversDetailFragment fragment = new DeliversDetailFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    void initUI() {

        mBinding.buttonChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putSerializable("data", mData);
                Navigation.findNavController(view).navigate(R.id.action_deliversDetailFragment_to_changeStatusFragment, data);

            }
        });

        if (mData != null) {
            if (mData.getShipmentNo() != null)
                mBinding.deliveryIDTxt.setText(mData.getShipmentNo());
            if (mData.getProductWeight() != null)
                mBinding.weightTxt.setText(mData.getProductWeight());
            if (mData.getCodAmount() != null)
                mBinding.totalCodCost.setText(mData.getCodAmount());
            if (mData.getProductDetail() != null)
                mBinding.productDetailTxt.setText(mData.getProductDetail().toString());
            if (mData.getNote() != null)
                mBinding.productNots.setText(mData.getNote().toString());

            if (mData.getStatus() == 2 || mData.getStatus() == 3) {
                mBinding.deliveryTxt.setText(getString(R.string.pickup));
                mBinding.nameDD.setText(mData.getMerchant().getName());
                mBinding.addressDD.setText(mData.getsAddress());
                mBinding.phoneDD.setText(mData.getMerchant().getMobile());
                mBinding.buttonChangeStatus.setBackground(getResources().getDrawable(R.drawable.theme_background_button));

            } else {
                mBinding.buttonChangeStatus.setBackground(getResources().getDrawable(R.drawable.backround_dark_gray));
                mBinding.deliveryTxt.setText(getString(R.string.delivery));
                mBinding.nameDD.setText(mData.getReceiverName());
                mBinding.addressDD.setText(mData.getdAddress());
                mBinding.phoneDD.setText(mData.getContactNo());
            }
           /* if (mData.getProductType() == 1) {
                mBinding.docTypeTxt.setText(getString(R.string.documents));
            } else {
                mBinding.docTypeTxt.setText(getString(R.string.heavy_Weight));
            }*/
            mBinding.docTypeTxt.setText(mData.getProductType());

            if (mData.getShipmentType() != null)
                if (Integer.parseInt(String.valueOf(mData.getShipmentType())) == 1) {
                    mBinding.shippingTyp.setText(getString(R.string.stndr_delivery));
                } else {
                    mBinding.shippingTyp.setText(getString(R.string.express_delivery));
                }

            mBinding.addressDD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OpenOnMap(mData);
                }
            });

        } // model ! = null

    }

    private void OpenOnMap(ShipmentModel detail){
        try {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("geo:" + detail.getdLatitude()
                            + "," + detail.getdLongitude()
                            + "?q=" + detail.getdLatitude()
                            + "," + detail.getdLongitude()
                            + "(" + "Address" + ")"));
            intent.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
            requireActivity().startActivity(intent);
        } catch (ActivityNotFoundException e) {

            try {
                requireActivity().startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=com.google.android.apps.maps")));
            } catch (android.content.ActivityNotFoundException anfe) {
                requireActivity().startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.apps.maps")));
            }

            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mData = (ShipmentModel) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deliveres_detail, container, false);
        mBinding = DataBindingUtil.bind(view);
        initUI();
        return view;
    }

    void hideAndView(boolean is) {
        if (is) {
            mBinding.deliveryTxt.setText(R.string.my_location);
            mBinding.nameDD.setVisibility(View.INVISIBLE);
            mBinding.phoneDD.setVisibility(View.INVISIBLE);
            mBinding.productDetailLL.setVisibility(View.GONE);
            //    mBinding.recyclerView.setVisibility(View.VISIBLE);
        } else {

        }
    }
}