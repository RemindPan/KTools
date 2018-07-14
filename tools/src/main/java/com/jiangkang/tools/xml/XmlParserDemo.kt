package com.jiangkang.tools.xml

import android.content.Context
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStreamReader
import java.io.Reader
import java.nio.charset.Charset


class XmlParserDemo(context: Context) {

    private val mContext = context

    fun createXmlParser(): XmlPullParser {
        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        return factory.newPullParser()
    }

    fun getXmlFromAssets(filename: String): Reader {
        val inputStream = mContext.resources.assets.open(filename)
        return InputStreamReader(inputStream, Charset.defaultCharset())
    }


}




