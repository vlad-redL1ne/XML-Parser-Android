package westele.com.parser;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLHelper extends DefaultHandler {

    private String URL_MAIN = "http://pcsalt.com/postservice/?format=xml";
    String TAG = "XMLHelper";
    Boolean currTag = false;
    String currTagVal = "";
    private PostValue post = null;
    private ArrayList<PostValue> posts = new ArrayList<PostValue>();

    public ArrayList<PostValue> getPostsList() {
        return this.posts;
    }

    public void get() {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser mSaxParser = null;

        try {
            mSaxParser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        XMLReader mXmlReader = null;
        try {
            mXmlReader = mSaxParser.getXMLReader();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        mXmlReader.setContentHandler(this);
        InputStream mInputStream = null;
        try {
            mInputStream = new URL(URL_MAIN).openStream();
            Log.d(TAG, "" + mInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader bf;
        try {
            bf = new BufferedReader(new InputStreamReader(mInputStream, "UTF-8"));

            while((line = bf.readLine()) != null) {
                stringBuilder.append(line);
                Log.d(TAG, "line = " + line);
            }
            InputSource source = new InputSource(new StringReader(stringBuilder.toString()));
            mSaxParser.parse(source, this);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (currTag) {
            currTagVal = currTagVal + new String(ch, start, length);
            currTag = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        currTag = false;

        if (localName.equalsIgnoreCase("id")) //title
            post.setId(currTagVal);

        else if (localName.equalsIgnoreCase("slug")) //description
            post.setSlug(currTagVal);

        else if (localName.equalsIgnoreCase("img"))  //link
            post.setImg(currTagVal);

        else if (localName.equalsIgnoreCase("item"))
            posts.add(post);
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        currTag = true;
        currTagVal = "";
        if (localName.equals("item"))
            post = new PostValue();
    }
}