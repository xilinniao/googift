package cn.googift.recognition.imageanalysis;

import java.util.Comparator;
import java.util.Vector;
import java.util.Collections;

public class PlateGraph extends Graph {

        Plate handle;

        public PlateGraph(Plate handle) {
            this.handle = handle; // nesie odkaz na obrazok (Plate), ku ktoremu sa graf vztahuje
        }

        public class SpaceComparer implements Comparator {
            Vector<Float> yValues = null;

            public SpaceComparer(Vector<Float> yValues) {
                this.yValues = yValues;
            }

            private float getPeakValue(Object peak) {
                return ((Peak)peak).getCenter(); // left > right
                //return this.yValues.elementAt( ((Peak)peak).center()  );
            }

            public int compare(Object peak1, Object peak2) {
                double comparison = this.getPeakValue(peak2) - this.getPeakValue(peak1);
                if (comparison < 0) return 1;
                if (comparison > 0) return -1;
                return 0;
            }
        }

        public Vector<Peak> findPeaks(int count) {
            Vector<Peak> spacesTemp = new Vector<Peak>();

            // uprava grafu pred segmentaciou :
            // 1. zistime average value a maxval
            // 2. upravime minval ako
            //  diffVal = average - (maxval - average) = 2average - maxval
            //  val -= diffVal

            float diffGVal = 2 * this.getAverageValue() - this.getMaxValue();

            Vector<Float> yValuesNew = new Vector<Float>();
            for (Float f : this.yValues) {
                yValuesNew.add(f.floatValue()-diffGVal);
            }
            this.yValues = yValuesNew;

            this.deActualizeFlags();
            // end

            for (int c=0; c<count; c++) { // for count
                float maxValue = 0.0f;
                int maxIndex = 0;
                for (int i=0; i<this.yValues.size(); i++) { // zlava doprava
                    if (allowedInterval(spacesTemp, i)) { // ak potencialny vrchol sa nachadza vo "volnom" intervale, ktory nespada pod ine vrcholy
                        if (this.yValues.elementAt(i) >= maxValue) {
                            maxValue = this.yValues.elementAt(i);
                            maxIndex = i;
                        }
                    }
                } // end for int 0->max
                // nasli sme najvacsi peak
                                                  // 0.75 mensie cislo znamena tendenciu znaky sekat, vacsie cislo zase tendenciu nespravne zdruzovat

                int leftIndex = indexOfLeftPeakRel(maxIndex, 0.55); //urci sirku detekovanej medzery
                int rightIndex = indexOfRightPeakRel(maxIndex, 0.55);

                spacesTemp.add(new Peak(
                        Math.max(0,leftIndex),
                        maxIndex,
                        Math.min(this.yValues.size()-1,rightIndex)
                        ));
            } // end for count

            // treba filtrovat kandidatov, ktory nezodpovedaju proporciam MEDZERY
            Vector<Peak> spaces = new Vector<Peak>();
            for (Peak p : spacesTemp) {
                if (p.getDiff() < 1 * this.handle.getHeight() // medzera nesmie byt siroka
                    ) spaces.add(p);// znacka ok, bereme ju
                    //else outPeaksFiltered.add(p);// znacka ok, bereme ju
            }

            // Vector<Peak> space OBSAHUJE MEDZERY, zoradime LEFT -> RIGHT
            Collections.sort(spaces, (Comparator<? super Graph.Peak>)
                                     new SpaceComparer(this.yValues));



// outPeaksFiltered teraz obsahuje MEDZERY ... v nasledujucom kode
            // ich transformujeme na pismena
            Vector<Peak> chars = new Vector<Peak>();

/*
 *      + +   +++           +++             +
 *       + +++   +         +   +           +
 *                +       +     +         +
 *                 +     +       +      ++
 *                  +   +         +   ++
 *                   +++           +++
 *                    |      |      1        |     2 ....
 *                    |
 *                    +--> 1. local minimum
 *
 */


            // zapocitame aj znak od medzery na lavo :
            if (spaces.size()!=0) {
                // detekujeme 1. lokalne minimum na grafe
                // 3 = leftmargin
                int minIndex = this.getMinValueIndex(0,spaces.elementAt(0).getCenter());
                //System.out.println("minindex found at " + minIndex + " in interval 0 - " + outPeaksFiltered.elementAt(0).getCenter());
                // hladame index do lava od minindex
                int leftIndex = 0;
//                for (int i=minIndex; i>=0; i--) {
//                    leftIndex = i;
//                    if (this.yValues.elementAt(i) >
//                        0.9 * this.yValues.elementAt(
//                                outPeaksFiltered.elementAt(0).getCenter()
//                                                    )
//                       ) break;
//                }

                Peak first = new Peak(leftIndex/*0*/,spaces.elementAt(0).getCenter());
                if (first.getDiff()>0) chars.add(first);
            }

            for (int i=0; i<spaces.size() - 1; i++) {
                int left = spaces.elementAt(i).getCenter();
                int right = spaces.elementAt(i+1).getCenter();
                chars.add(new Peak(left,right));
            }

            // znak ktory je napravo od poslednej medzery :
            if (spaces.size()!=0) {
                Peak last = new Peak(
                    spaces.elementAt(spaces.size()-1).getCenter(),
                    this.yValues.size()-1
                        );
                if (last.getDiff()>0) chars.add(last);
            }

            super.peaks = chars;
            return chars;

        }
//        public int indexOfLeftPeak(int peak) {
//            int index=peak;
//            int counter = 0;
//            for (int i=peak; i>=0; i--) {
//                index = i;
//                if (yValues.elementAt(index) < 0.7 * yValues.elementAt(peak) ) break;
//            }
//            return Math.max(0,index);
//        }
//        public int indexOfRightPeak(int peak) {
//            int index=peak;
//            int counter = 0;
//            for (int i=peak; i<yValues.size(); i++) {
//                index = i;
//                if (yValues.elementAt(index) < 0.7 * yValues.elementAt(peak) ) break;
//            }
//            return Math.min(yValues.size(), index);
//        }

//        public float minValInInterval(float a, float b) {
//            int ia = (int)(a*yValues.size());
//            int ib = (int)(b*yValues.size());
//            float min = Float.POSITIVE_INFINITY;
//            for (int i=ia; i<ib;i++) {
//                min = Math.min(min, yValues.elementAt(i));
//            }
//            return min;
//        }
//        public float maxValInInterval(float a, float b) {
//            int ia = (int)(a*yValues.size());
//            int ib = (int)(b*yValues.size());
//            float max = 0;
//            for (int i=ia; i<ib;i++) {
//                max = Math.max(max, yValues.elementAt(i));
//            }
//            return max;
//        }

}

