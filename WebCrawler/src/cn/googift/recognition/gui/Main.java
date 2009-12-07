package cn.googift.recognition.gui;

import cn.googift.recognition.gui.windows.FrameMain;
import cn.googift.recognition.gui.windows.FrameComponentInit;

import javax.swing.*;

//import javaanpr.analysis.Graph.ProbabilityDistributor;

public class Main {

    public static void main(String[] args) throws Exception {
            // DONE run gui
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            FrameComponentInit frameComponentInit = new FrameComponentInit(); // show wait
            frameComponentInit.dispose(); // hide wait
            new FrameMain();


    }
}
