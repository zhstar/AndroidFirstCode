package com.codekun.androidfirstcode.ch3;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

import org.w3c.dom.Text;

public class Ch3TextViewActivity extends TitleBarActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        当然也可以在xml中设置TextView的相关属性，表4-1列出了几个常用的方法以及对应的xml属性。
TextView对象方法及对应的xml属性
方法
xml属性
setText
android:text
setTextSize
android:textSize
setTextColor
android:textColor
setBackgroundResource
android:background
在上面的代码中用到了Color.Green来设置TextView的颜色，用Color.WHITE来设置TextView的背景色。
Android中提供了如下常见颜色：
Color.BLACK；
Color.BLUE；
Color.CYAN；
Color.DKGRAY；
Color.GRAY；
Color.GREEN；
Color.LTGRAY；
Color.MAGENTA；
Color.RED；
Color.TRANSPARENT。
除此之外，当文字中出现URL、E-mail、电话号码等的时候，还可以为TextView设置链接。总结起来，一共有4种方法来为TextView实现链接。
（1）在xml里添加android:autoLink属性。如果写为android:autoLink=”all”，则为所有种类添加链接。当然，同样的也可以在Java代码中完成，用法为tv.setAutoLinkMask(Linkify.ALL)。
（2）将显示内容写到资源文件，一般为String.xml中，并且用<a>标签来声明链接，但是这还不够，要激活这个链接，需要在Java代码中使用setMovementMethod()方法设置TextView可点击。
（3）用Html类的fromHtml()方法格式化要放到TextView里的文字。
（4）用Spannable或实现它的类，如SpannableString。与其他方法不同的是，Spannable对象可以为个别字符设置链接（当然也可以为个别字符设置颜色、字体等，实现某些字符高亮显示的效果等）。
         */
        //第一种方式见 activity_ch3_text_view.xml 文件

        //第二种方式：
        TextView link2_tv = (TextView)findViewById(R.id.ch3_link2_textView);
        link2_tv.setMovementMethod(LinkMovementMethod.getInstance());
        //link2_tv.setText(getResources().getString(R.string.ch3_link_text));

        //第三种方式：
        TextView link3_tv = (TextView)findViewById(R.id.ch3_link3_textView);
        link3_tv.setText(Html.fromHtml(
                "<b>第二种实现链接的方式：</b>" +
                        "<a href='http://www.baidu.com'>连接到度娘</a>"
        ));
        link3_tv.setMovementMethod(LinkMovementMethod.getInstance());

        //第四种方式：
        TextView link4_tv = (TextView)findViewById(R.id.ch3_link4_textView);
        link4_tv.setTextSize(20);
        //创建一个 SpannableString 对象
        SpannableString ss = new SpannableString("第四种实现超链接的方式：点击这里链接到Baidu, 拔打10086");
        //设置 第四种实现超链接的方式 这个字符加粗
        ss.setSpan(new StyleSpan(Typeface.BOLD), 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置 "这里" 为超链接
        ss.setSpan(new URLSpan("http://www.baidu.com"), 14, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置 Baidu 的背景色
        ss.setSpan(new BackgroundColorSpan(Color.GREEN), 19, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置 10086 可打开拔号界面
        ss.setSpan(new URLSpan("tel:10086"), 28, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        link4_tv.setText(ss);
        link4_tv.setMovementMethod(LinkMovementMethod.getInstance());


    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch3_text_view;
    }

}
