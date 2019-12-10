package com.zhili;

import com.zhili.analyzer.lucene.ZWAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        TokenStream ts = null;
        try {
            ZWAnalyzer analyzer = new ZWAnalyzer();
            ts = analyzer.tokenStream("text", "宁波市酒店管理有限公司");
            CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
            ts.reset();
            while (ts.incrementToken()) {
//                System.out.println(term.toString());
                System.out.println("打印="+term.toString());
//                System.out.println();
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ts) {
                    ts.end();
                    ts.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
