package com.codekun.androidfirstcode.ch2;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch2SecondActivity extends TitleBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        //��ʾ��ʾIntent���ݻ�����������
        String title = bundle.getString("title");
        if (title == null){
            title = "Default Title";
        }
        setTitle(title);

        TextView tv = (TextView)findViewById(R.id.ch2_second_textView);

        //��ʾ���� �����л���������
        Person person = (Person)bundle.getSerializable("person");
        if (person != null){
            tv.setText(person.toString());
        }
        //��ʾ���� �����л���������
        Book book = (Book)bundle.getParcelable("book");
        if (book != null){
            tv.setText(book.toString());
        }

    }

    @Override
    protected void onClickLeftBtn(View v) {
        Intent i = new Intent();
        i.putExtra("result", "OK");
        setResult(RESULT_OK, i);
        super.onClickLeftBtn(v);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //����ϵͳ�ط��ؼ�
        if (keyCode == KeyEvent.KEYCODE_BACK){
            onClickLeftBtn(null);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch2_second;
    }

}
