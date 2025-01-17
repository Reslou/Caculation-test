package com.example.calculationtest;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculationtest.databinding.FragmentQuestionBinding;
import com.example.calculationtest.databinding.FragmentTitleBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(String param1, String param2) {
        QuestionFragment fragment = new QuestionFragment();
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
        MyViewModel myViewModel = new ViewModelProvider(requireActivity(),
                new SavedStateViewModelFactory(requireActivity().
                        getApplication(), requireActivity())).get(MyViewModel.class);
        FragmentQuestionBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());
        StringBuilder sb = new StringBuilder();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.button2) {
                    sb.append("1");
                } else if (v.getId() == R.id.button3) {
                    sb.append("2");
                }else if (v.getId() == R.id.button4) {
                    sb.append("3");
                }else if (v.getId() == R.id.button5) {
                    sb.append("4");
                }else if (v.getId() == R.id.button6) {
                    sb.append("5");
                }else if (v.getId() == R.id.button7) {
                    sb.append("6");
                }else if (v.getId() == R.id.button8) {
                    sb.append("7");
                }else if (v.getId() == R.id.button9) {
                    sb.append("8");
                }else if (v.getId() == R.id.button10) {
                    sb.append("9");
                }else if (v.getId() == R.id.button11) {
                    sb.setLength(0);
                }else if (v.getId() == R.id.button12) {
                    sb.append("0");
                }
                if (sb.length() == 0) {
                    binding.textView11.setText(getString(R.string.input_indicator));
                } else {
                    binding.textView11.setText(sb.toString());
                }
            }
        };
        binding.button2.setOnClickListener(listener);
        binding.button3.setOnClickListener(listener);
        binding.button4.setOnClickListener(listener);
        binding.button5.setOnClickListener(listener);
        binding.button6.setOnClickListener(listener);
        binding.button7.setOnClickListener(listener);
        binding.button8.setOnClickListener(listener);
        binding.button9.setOnClickListener(listener);
        binding.button10.setOnClickListener(listener);
        binding.button11.setOnClickListener(listener);
        binding.button12.setOnClickListener(listener);
        binding.button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sb.length() == 0) {
                    sb.append("-1");
                }
                if (Integer.valueOf(sb.toString()).intValue() == myViewModel.getAnswer().getValue()) {
                    myViewModel.answerCorrect();
                    sb.setLength(0);
                    binding.textView11.setText(R.string.answer_correct_message);
                } else {
                    NavController controller = Navigation.findNavController(view);
                    if (myViewModel.win_flag) {
                        controller.navigate(R.id.action_questionFragment_to_winFragment);
                        myViewModel.win_flag = false;
                        myViewModel.save();
                    } else {
                        controller.navigate(R.id.action_questionFragment_to_loseFragment);
                    }
                }
            }
        });
        return binding.getRoot();
    }
}