package com.team.codealmanac.w2do.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.team.codealmanac.w2do.contract.FontContract;
import com.team.codealmanac.w2do.R;
import com.team.codealmanac.w2do.database.SQLiteManager;
import com.team.codealmanac.w2do.models.Todo;

import java.util.Calendar;

/**
 * Created by sihyeon on 2017-01-15.
 */

public class SimpleInputDialogFragment extends DialogFragment implements View.OnClickListener {
    private final String TAG = "SimpleInputDialog";
    private EditText frag_simpleinput_edittext;
    private InputMethodManager mInputMethodManager;

    public SimpleInputDialogFragment() {}
    public static SimpleInputDialogFragment newInstance() {
        return new SimpleInputDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialogfragment_simpleinput_todo, container);
        FontContract mFontContract = new FontContract(getDialog().getContext().getAssets());

        TextView frag_simpletodo_header_text = (TextView) v.findViewById(R.id.frag_simpletodo_header_text);
        frag_simpleinput_edittext = (EditText) v.findViewById(R.id.frag_simpleinput_edittext);
        Button frag_simpleinput_cancel_btn = (Button) v.findViewById(R.id.frag_simpleinput_cancel_btn);
        Button frag_simpleinput_submit_btn = (Button) v.findViewById(R.id.frag_simpleinput_submit_btn);

        frag_simpleinput_edittext.requestFocus();
        mInputMethodManager = (InputMethodManager) getDialog().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        frag_simpleinput_edittext.setTypeface(mFontContract.NahumSquareR_Regular());
        frag_simpletodo_header_text.setTypeface(mFontContract.FranklinGothic_MediumCond());
        frag_simpleinput_cancel_btn.setTypeface(mFontContract.NahumSquareR_Regular());
        frag_simpleinput_submit_btn.setTypeface(mFontContract.NahumSquareR_Regular());

        frag_simpleinput_cancel_btn.setOnClickListener(this);
        frag_simpleinput_submit_btn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.frag_simpleinput_submit_btn) {
            if (TextUtils.isEmpty(frag_simpleinput_edittext.getText())) {
                frag_simpleinput_edittext.setError("내용을 입력해주세요.");
                return;
            }
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            long todayTimeInMillis = today.getTimeInMillis();
            Todo todo = new Todo(todayTimeInMillis, frag_simpleinput_edittext.getText().toString());
            SQLiteManager sqliteManager = SQLiteManager.getInstance(getDialog().getContext());
            sqliteManager.addTodo(todo);
            dismiss();
        }else {
            dismiss();
        }
    }


    @Override
    public void dismiss() {
        mInputMethodManager.hideSoftInputFromWindow(frag_simpleinput_edittext.getWindowToken(), 0);
        super.dismiss();
    }
}