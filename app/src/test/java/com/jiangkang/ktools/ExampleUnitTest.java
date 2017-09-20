package com.jiangkang.ktools;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
  @Test public void addition_isCorrect() throws Exception {

    String str = null;
    JSONObject shit = new JSONObject(str);

    assertEquals(4, 2 + 2);
  }
}