package com.codekun.androidfirstcode.ch10;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by kun on 2015/12/2.
 */
public class XmlSaxHandler extends DefaultHandler {
    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;

    private App app = null;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        nodeName = localName;
        if (localName.equals("app")){
            app = new App();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (localName.equals("app")){
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
            Log.d("AndroidFirstCode", "SAX:" + app.toString());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (nodeName.equals("id")){
            id.append(ch, start, length);
            if (app != null){
                app.setId(Integer.parseInt(id.toString().trim()));
            }
        }else if(nodeName.equals("name")){
            name.append(ch, start, length);
            if (app != null){
                app.setName(name.toString().trim());
            }
        }else if (nodeName.equals("version")){
            version.append(ch, start, length);
            if (app != null){
                app.setVersion(version.toString().trim());
            }
        }
    }

}
