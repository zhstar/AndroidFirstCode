package com.codekun.androidfirstcode.ch2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;
import com.codekun.androidfirstcode.utils.ActivityCollector;


public class Ch2MainActivity extends TitleBarActivity implements View.OnClickListener{


    public static void startActivity(Context context, String title){
        Intent i = new Intent(context, Ch2MainActivity.class);
        Bundle b = new Bundle();
        b.putString("title", title);
        i.putExtras(b);
        context.startActivity(i);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch2_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String title = getIntent().getExtras().getString("title");
        setTitle(title);



        Button button2 = (Button)findViewById(R.id.ch2_button2);//��
        Button button3 = (Button)findViewById(R.id.ch2_button3);//��������
        Button button4 = (Button)findViewById(R.id.ch2_button4);//�
        Button button5 = (Button)findViewById(R.id.ch2_button5);//�
        Button button6 = (Button)findViewById(R.id.ch2_button6);//�
        Button button7 = (Button)findViewById(R.id.ch2_button7);//�
        Button button8 = (Button)findViewById(R.id.ch2_button8);//
        Button button9 = (Button)findViewById(R.id.ch2_button9);//
        Button button10 = (Button)findViewById(R.id.ch2_button10);//
        Button button11 = (Button)findViewById(R.id.ch2_button11);//
        Button exit_app_button = (Button)findViewById(R.id.ch2_exit_app_button);//

        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        exit_app_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, Ch2SecondActivity.class);
        Bundle bundle = new Bundle();
        //����һ��String���͵�����
        bundle.putString("title", ((Button) v).getText().toString());
        switch (v.getId()){
            case R.id.ch2_button2:
                //ʲô��û��
                break;
            case R.id.ch2_button3:
                break;
            case R.id.ch2_button4:
                Person p = new Person();
                p.setName("CodeKun3");
                p.setAge(3);
                //ע�⣺���л��Ķ���������޲εĹ��캯��
                bundle.putSerializable("person", p);
                break;
            case R.id.ch2_button5:
                Person pp = new Person();
                pp.setName("Kun");
                pp.setAge(1);
                Book b = new Book();
                b.setName("Android");
                b.setAuthor(pp);
                //ע�⣺���л��Ķ���������޲εĹ��캯��
                bundle.putParcelable("book", b);
                break;
            case R.id.ch2_button6:
                intent = new Intent("com.codekun.androidfirstcode.ACTION_START");
                intent.addCategory("com.codekun.androidfirstcode.CATEGORY_START");
                break;
            case R.id.ch2_button7:
                intent = new Intent("com.codekun.androidfirstcode.ACTION_START");
                intent.addCategory("com.codekun.androidfirstcode.CATEGORY_THIRD_START");

                break;
            case R.id.ch2_button8:
                intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://www.baidu.com");
                intent.setData(uri);
                /*
                    ��ο� ����һ�д��롷2.3.3 ������ʽIntent���÷�
                 */
                break;
            case R.id.ch2_button9:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:10086"));
                break;
            case R.id.ch2_button10:
                intent = new Intent(this, Ch2LoginActivity.class);
                break;
            case R.id.ch2_button11:
                intent = new Intent(this, Ch2LaunchModeActivity.class);
                break;
            case R.id.ch2_exit_app_button:
                //��ʾ�˳�����Activity������������������
                ActivityCollector.finishAll();
                return;//���أ���ִֹ�е����д���
        }
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == 1){
           if (resultCode == RESULT_OK){
               Log.d("AndroidFirstCode", data.getStringExtra("result"));
           }
       }
    }

    @Override
    protected void onClickLeftBtn(View v) {
        //������ǰActivity = ����
        finish();
    }

    //ʹ���Զ����TitleBar�����´����û������
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ch2_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        String msg = item.getTitle().toString();

        //��Ҫ����Ϊʲô����д��ֻ�Ǽ���ʾ�����֧
        switch (id){
            case R.id.ch2_action_settings:
                msg = "Pressed: " + msg + "";
                break;
            case R.id.ch2_action_about:
                msg = "Pressed: " + msg ;
                break;
        }
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

        return true;//super.onOptionsItemSelected(item);
    }
    */
}
