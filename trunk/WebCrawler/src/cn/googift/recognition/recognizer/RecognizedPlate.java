package cn.googift.recognition.recognizer;

import cn.googift.recognition.recognizer.RecognizedChar;

import java.util.Vector;

public class RecognizedPlate {
    Vector<RecognizedChar> chars;

    public RecognizedPlate() {
        this.chars = new Vector<RecognizedChar>();
    }

    public void addChar(RecognizedChar chr) {
        this.chars.add(chr);
    }

    public RecognizedChar getChar(int i) {
        return this.chars.elementAt(i);
    }

    public Vector<RecognizedChar> getChars() {
        return chars;
    }

    public String getString() {
        String ret = "";
        for (int i=0; i<chars.size();i++) {
            ret = ret + this.chars.elementAt(i).getPattern(0).getChar();
        }
        return ret;
    }

}