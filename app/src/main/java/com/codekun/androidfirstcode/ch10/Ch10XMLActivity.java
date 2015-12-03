package com.codekun.androidfirstcode.ch10;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.utils.CKXMLReader;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

public class Ch10XMLActivity extends AppCompatActivity {

    private Button pullButton;
    private Button saxButton;
    private TextView textView;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch10_xml);

        mContext = this;

        pullButton = (Button)findViewById(R.id.ch10_xml_pull_button);
        saxButton = (Button)findViewById(R.id.ch10_xml_sax_button);
        textView = (TextView)findViewById(R.id.ch10_xml_textView);

        pullButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        XmlPullParser parser = CKXMLReader.getParser(mContext, "get_data.xml");
                        App app = null;
                        try {
                            int eventType = parser.getEventType();
                            String nodeName = "";
                            while (eventType != XmlPullParser.END_DOCUMENT){
                                switch (eventType){
                                    case XmlPullParser.START_TAG:
                                        nodeName = parser.getName();
                                        if (nodeName.equals("app")){
                                            app = new App();
                                        }else if(nodeName.equals("id")){
                                            app.setId(Integer.parseInt(parser.nextText()));
                                        }else if (nodeName.equals("name")){
                                            app.setName(parser.nextText());
                                        }else if (nodeName.equals("version")){
                                            app.setVersion(parser.nextText());
                                        }
                                        break;
                                    case XmlPullParser.END_TAG:
                                        nodeName = parser.getName();
                                        if (nodeName.equals("app")){
                                            Log.d("AndroidFirstCode", app.toString());
                                        }
                                        textView.append(app.toString());
                                        textView.append("\n");
                                        break;
                                }
                                eventType = parser.next();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("AndroidFirstCode", e.getMessage());
                        }

                    }
                }).start();
            }
        });

        saxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            String data = AssetReader.read(mContext, "get_data.xml").toString();
                            Log.d("AndroidFirstCode", data);

                            SAXParserFactory parser = SAXParserFactory.newInstance();
                            XMLReader reader = parser.newSAXParser().getXMLReader();
                            XmlSaxHandler handler = new XmlSaxHandler();
                            reader.setContentHandler(handler);
                            reader.parse(new InputSource(new StringReader(data)));

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("AndroidFirstCode", e.getMessage());
                        }
                    }
                }).start();

            }
        });

    }



}
