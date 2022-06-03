package com.drivill.courier.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drivill.courier.R;
import com.drivill.courier.activity.StatementActivity;
import com.drivill.courier.adapter.CodeStatementAdapter;
import com.drivill.courier.databinding.FragmentCodeStatementBinding;
import com.drivill.courier.model.model.CODStatementModel;
import com.drivill.courier.utils.DataManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CodeStatementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CodeStatementFragment extends Fragment {

    // RiderDashboardViewModel mViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentCodeStatementBinding mBinding;
    LinearLayoutManager mLayoutManager;
    CodeStatementAdapter mStatementAdapter;

    void initUI() {
       /* mViewModel = new ViewModelProvider(requireActivity()).get(RiderDashboardViewModel.class);
        mViewModel.modelMutableLiveData.observe(getViewLifecycleOwner(), success_observer);
       */
        DataManager.getINSTANCE().getDeliveredData().observe(requireActivity(), success_observer);

        mStatementAdapter = new CodeStatementAdapter(getContext());
        mLayoutManager = new LinearLayoutManager(getContext());
        mBinding.codeStateRY.setLayoutManager(mLayoutManager);
        mBinding.codeStateRY.setNestedScrollingEnabled(false);
        mBinding.codeStateRY.setAdapter(mStatementAdapter);
        // mStatementAdapter.setData(DataManager.getINSTANCE().getDeliverCompleteResponse().getValue());
        mStatementAdapter.setData(DataManager.getINSTANCE().getDeliveredData().getValue());

        mBinding.codeStateRY.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);

                if(mLayoutManager.findLastCompletelyVisibleItemPosition() == mStatementAdapter.getItemCount()-1){
                   // Log.i("mydebug-","onScrolled if");
                    //bottom of list!
                    ((StatementActivity) requireActivity()).loadMoreData(mStatementAdapter);
                }
            }
        });
    }

    public CodeStatementFragment() {
        // Required empty public constructor
    }

    public static CodeStatementFragment newInstance(String param1, String param2) {
        CodeStatementFragment fragment = new CodeStatementFragment();
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
        View view = inflater.inflate(R.layout.fragment_code_statement, container, false);
        mBinding = DataBindingUtil.bind(view);
        initUI();
        initUI();
        return view;
    }


    private Observer<CODStatementModel> success_observer = new Observer<CODStatementModel>() {
        @Override
        public void onChanged(CODStatementModel statementModel) {
          //  Log.d("res", String.valueOf(statementModel));

            //StatementActivity.setDepositCOD(String.valueOf(statementModel.getTotalCodEarned()));

           /* if (mStatementAdapter != null) {
                mStatementAdapter.setData(DataManager.getINSTANCE().getDeliveredData().getValue());
                isLoading = false;
            }*/

        }
    };
}