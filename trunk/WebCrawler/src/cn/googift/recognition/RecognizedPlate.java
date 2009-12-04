package cn.googift.recognition;

import java.util.Vector;

public class RecognizedPlate {
    Vector<CharacterRecognizer.RecognizedChar> chars;

    public RecognizedPlate() {
        this.chars = new Vector<CharacterRecognizer.RecognizedChar>();
    }

    public void addChar(CharacterRecognizer.RecognizedChar chr) {
        this.chars.add(chr);
    }

    public CharacterRecognizer.RecognizedChar getChar(int i) {
        return this.chars.elementAt(i);
    }

    public Vector<CharacterRecognizer.RecognizedChar> getChars() {
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