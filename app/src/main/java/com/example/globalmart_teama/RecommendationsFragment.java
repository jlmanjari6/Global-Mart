package com.example.globalmart_teama;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendationsFragment extends Fragment {

    PopupWindow popupWindow;

    public RecommendationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        final Button feedbackBtn = view.findViewById(R.id.submit_feedback);
        final EditText feedbackTxt = view.findViewById(R.id.feedback_text);
        final TextView t = view.findViewById(R.id.feedback_text_id);
        final FrameLayout linearLayout = (FrameLayout) view.findViewById(R.id.frmLayout);

        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(feedbackTxt.getText().toString().isEmpty())) {
                    feedbackTxt.setVisibility(View.INVISIBLE);
                    feedbackBtn.setVisibility(View.INVISIBLE);
                    t.setVisibility(View.GONE);

                    LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    View popUpView = layoutInflater.from(getActivity()).inflate(R.layout.popup_window_feedback, null);

                    ImageView closePopupBtn = (ImageView) popUpView.findViewById(R.id.closePopupFeedback);
                    popupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);

                    closePopupBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();

                            final FragmentManager fm = getFragmentManager();
                            final FragmentTransaction ft = fm.beginTransaction();
                            HomeFragment h = new HomeFragment();
                            ft.replace(((ViewGroup) (getView().getParent())).getId()
                                    , h, "Home");
                            ft.addToBackStack(null);
                            ft.commit();
                            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");
                        }
                    });
                }
            }
        });

        return view;
    }
}
