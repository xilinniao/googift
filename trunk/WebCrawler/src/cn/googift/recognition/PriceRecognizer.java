package cn.googift.recognition;

import java.awt.image.BufferedImage;
import java.util.Vector;
import java.util.List;

import cn.googift.recognition.imageanalysis.Plate;
import cn.googift.recognition.imageanalysis.Char;

public class PriceRecognizer {
    public CharacterRecognizer chrRecog;

    public PriceRecognizer(List<BufferedImage> images, String alphString) {
        this.chrRecog = new KnnPatternClassificator(images, alphString);
    }



    public String recognize(BufferedImage image) throws Exception {
        Plate plate = new Plate(image);
        plate.normalize();
        Vector<Char> chars = plate.getChars();
        RecognizedPlate recognizedPlate = new RecognizedPlate();

        for (Char chr : chars) chr.normalize();

        for (Char chr : chars) {
            CharacterRecognizer.RecognizedChar rc;
            rc = chrRecog.recognize(chr);
            recognizedPlate.addChar(rc);
        } // end for each char
        String parsedOutput = recognizedPlate.getString();
        return parsedOutput;
    }


}
