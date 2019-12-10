package com.zhili.analyzer.lucene;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import java.io.IOException;

public class ZWTokenizer extends Tokenizer {
    private int offset = 0; //起始字符元素下标+1
    private int bufferIndex = 0; //最后一个字符的下标+1
    private int dataLen = 0; //文本总长度
    private int start; //截取文本的起始下标
    private int length; //截取文本的长度
    //private int invalidCharCount = 0;
    private static final int MAX_WORD_LEN = 50; //最大截取字符长度
    //private static final int IO_BUFFER_SIZE = 1024;
    private final char[] buffer = new char['ÿ'];
    private final char[] ioBuffer = new char[1024];
    private final CharTermAttribute termAtt = (CharTermAttribute) addAttribute(CharTermAttribute.class);
    private final OffsetAttribute offsetAtt = (OffsetAttribute) addAttribute(OffsetAttribute.class);

    //全角转半角
    private char quanjiaoToBanjiao(char c) {
        if (((65345 <= c) && (c <= 65370)) ||
                ((65313 <= c) && (c <= 65338)) || (
                (65296 <= c) && (c <= 65305))) {
            return (char) (c - 65248);
        }
        return c;
    }

    final void push(char c) {
        if (this.length == 0) {
            this.start = (this.offset - 1);
        }
        this.buffer[(this.length++)] = Character.toLowerCase(c);
    }

    final boolean flush() {
        if (this.length > 0) {
            this.termAtt.copyBuffer(this.buffer, 0, this.length);
            this.offsetAtt.setOffset(correctOffset(this.start), correctOffset(this.start + this.length));
            return true;
        }
        return false;
    }

    public boolean incrementToken() throws IOException {
        //清除所有词的元属性
        clearAttributes();
        //this.length = 0;
        this.start = this.offset;
        do {
            if (this.length==0) {
                this.offset += 1;
            }
            //this.offset += 1;
            if (this.bufferIndex >= this.dataLen) {
                this.dataLen = this.input.read(this.ioBuffer);
                this.bufferIndex = 0;
            }
            if (this.dataLen == -1 || this.start>=this.dataLen) {
                this.offset -= 1;
                this.length = 0;//
                return flush();
            }
            // 当截取字符长度大于文本长则重置
            if (this.length >= this.dataLen - this.offset + 1) {
                this.length = 0;
                this.offset++;
            }
            /*
            限制最大分词长度
             */
            if (this.length >= MAX_WORD_LEN) {
                this.length = 0;
                this.offset++;
                this.bufferIndex = this.offset-1;
            }
            char c = this.ioBuffer[(this.bufferIndex++)];
            // 当最后一个字符下标大于文本长度时则重置
            if (this.bufferIndex >= this.dataLen) {
                this.bufferIndex = this.offset;
            }
            switch (Character.getType(c)) {
                case 1:
                case 2:
                case 5:
                case 9:
//                    if (this.length > 0) {
//                        this.bufferIndex -= 1;
//                        this.offset -= 1;
//                        return flush();
//                    }
                    push(quanjiaoToBanjiao(c));
                    return flush();
                case 3:
                case 4:
                case 6:
                case 7:
                case 8:
            }
        } while (this.length <= 0);
        return flush();
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        this.offset = (this.bufferIndex = this.dataLen = 0);
    }

    @Override
    public final void end() {
        final int finalOffset = correctOffset(this.offset);
        this.offsetAtt.setOffset(finalOffset, finalOffset);
    }
}
