package com.drivill.courier.merchantModul.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drivill.courier.R;
import com.drivill.courier.databinding.FragmentTrackingBinding;
import com.drivill.courier.merchantModul.adapter.Adapter_TrackingLogs;
import com.drivill.courier.merchantModul.adapter.PayDetails_popupAdapter;
import com.drivill.courier.merchantModul.model.TrackingModel;
import com.drivill.courier.utils.AppUtil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrackingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrackingFragment extends Fragment {
    FragmentTrackingBinding mBinding;

    private static final String ARG_PARAM1 = "data";
    private static final String ARG_PARAM2 = "param2";
    private TrackingModel model;
    private String mParam2;

    void initUI() {
/*        mBinding.pendingRBtn.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
        mBinding.pendingView.setBackgroundColor(getResources().getColor(R.color.theme_color));*/

        if (model != null) {
            mBinding.trackingNoTxt.setText(model.getData().getShipmentNo());
          //  mBinding.orderCreationTxt.setText(AppUtil.getDateFormat(model.getData().getCreatedAt(), "track"));
            mBinding.orderCreationTxt.setText(model.getData().getCreated_date());
            mBinding.estimateDateTxt.setText(model.getData().getPickupDate());
            mBinding.itemNameTxt.setText(model.getData().getMerchant().getName());
            mBinding.shopName.setText(model.getData().getdAddress());
            mBinding.priceItem.setText("Tk. " + model.getData().getCodAmount());

            mBinding.RvTrackinglogs.setLayoutManager(new LinearLayoutManager(requireActivity()));

            if (model.getData().getStatusLogs() != null) {
                /*int isStatus = 0;
                for (TrackingModel.StatusLog status : model.getData().getStatusLogs()) {
                    if (status.getStatus() != 18)  // replace by 8
                       // if (isStatus < status.getStatus()) {
                            isStatus = status.getStatus();
                          //  updateChart(isStatus, AppUtil.getDateFormat(status.getCreatedAt(), "track"), status.getNote());
                            updateChart(isStatus, status.getCreated_date(), status.getNote());
                       // }
                  }*/

                // Set Adapter ---

                Adapter_TrackingLogs adapter = new Adapter_TrackingLogs(requireActivity(),model);
                mBinding.RvTrackinglogs.setAdapter(adapter);

             }
        }

    }

    public TrackingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrackingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrackingFragment newInstance(String param1, String param2) {
        TrackingFragment fragment = new TrackingFragment();
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
            model = (TrackingModel) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tracking, container, false);
        mBinding = FragmentTrackingBinding.bind(view);
        initUI();
        return view;
    }

  /*  void updateChart(int currentStatus, String date, String statusTxt) {
        Log.i("chart","data=> "+ currentStatus+" == "+date + " == "+statusTxt);
        if (currentStatus == 0) {
            mBinding.pendingRBtn.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.pendingView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.pendingDateTxt.setText(date);
            mBinding.pendingStatusTxt.setText(statusTxt);
        } else if (currentStatus == 1) {
            mBinding.pendingRBtn.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.pendingView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.confirmImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
           // mBinding.confirmView.setBackgroundColor(getResources().getColor(R.color.theme_color));

            mBinding.conDate.setText(date);
            mBinding.conStatus.setText(statusTxt);
        } else if (currentStatus == 2||currentStatus == 3) {
            mBinding.pendingRBtn.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.pendingView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.confirmImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.confirmView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.pikedImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
           // mBinding.pikedView.setBackgroundColor(getResources().getColor(R.color.theme_color));

            mBinding.pickupDate.setText(date);
            mBinding.pickupStatus.setText(statusTxt);
        } else if (currentStatus == 4) {
            mBinding.pendingRBtn.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.pendingView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.confirmImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.confirmView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.pikedImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.pikedView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.inTransImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            //mBinding.inTransView.setBackgroundColor(getResources().getColor(R.color.theme_color));

            mBinding.inTranDate.setText(date);
            mBinding.inTranStatus.setText(statusTxt);
        } else if (currentStatus == 5) {
            mBinding.pendingRBtn.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.pendingView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.confirmImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.confirmView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.pikedImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.pikedView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.inTransImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.inTransView.setBackgroundColor(getResources().getColor(R.color.theme_color));

            mBinding.onThewayImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.onTheWayView.setBackgroundColor(getResources().getColor(R.color.theme_color));

            mBinding.onWayDate.setText(date);
            mBinding.inonthewayStatus.setText(statusTxt);

        } else if (currentStatus == 6 || currentStatus == 7) {
            mBinding.pendingRBtn.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.pendingView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.confirmImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.confirmView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.pikedImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.pikedView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.inTransImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.inTransView.setBackgroundColor(getResources().getColor(R.color.theme_color));
            mBinding.onThewayImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.onTheWayView.setBackgroundColor(getResources().getColor(R.color.theme_color));

            mBinding.deliveredImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));

            mBinding.deliverDate.setText(date);
            mBinding.inTranStatus.setText(statusTxt);

        }else if(currentStatus == 10){
            mBinding.cancelView.setVisibility(View.VISIBLE);
          //  mBinding.viewOnWay.setVisibility(View.VISIBLE);

            mBinding.inTransImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.inTransView.setBackgroundColor(getResources().getColor(R.color.theme_color));

            mBinding.onCancelImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.onCancelView.setBackgroundColor(getResources().getColor(R.color.theme_color));

            mBinding.onThewayImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.onTheWayView.setBackgroundColor(getResources().getColor(R.color.theme_color));

            mBinding.onCancelDate.setText(date);
            mBinding.tvOncancelNote.setText(getString(R.string.returntxt)+"\n"+"Delivery attempt failed");
        }

        else if(currentStatus == 11 || currentStatus == 12){
            mBinding.returnView.setVisibility(View.VISIBLE);
           // mBinding.viewOnWay.setVisibility(View.GONE);

            mBinding.inTransImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.inTransView.setBackgroundColor(getResources().getColor(R.color.theme_color));

            mBinding.onReturnImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
             mBinding.onreturnView.setBackgroundColor(getResources().getColor(R.color.theme_color));

            mBinding.onreturnDate.setText(date);
            if(currentStatus == 12){
                mBinding.tvOnreturnNote.setText("Cancel by rider"+"\n"+statusTxt);
            }else {
                mBinding.tvOnreturnNote.setText(getString(R.string.returntxt)+"\n"+statusTxt);
            }
        }

        else if(currentStatus == 8){
            mBinding.cancel8View.setVisibility(View.VISIBLE);
         //   mBinding.viewOnWay.setVisibility(View.GONE);

            mBinding.inTransImg.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
            mBinding.inTransView.setBackgroundColor(getResources().getColor(R.color.theme_color));

            mBinding.onCancel8Img.setBackground(getResources().getDrawable(R.drawable.check_box_checked));
             mBinding.onCancelView.setBackgroundColor(getResources().getColor(R.color.theme_color));

            mBinding.oncancel8Date.setText(date);
            mBinding.tvCancel8Note.setText(getString(R.string.cancelled)+"\n"+statusTxt);
        }

    }*/

}