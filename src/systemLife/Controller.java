package systemLife;

import javax.swing.*;

public class Controller extends Thread{

    private View viewObj;
    private Grid gridObj;
    private Iterator iteratorObj;
    private int iterations;


    public Controller(){
        viewObj = new View(this);
        gridObj = new Grid();
        iteratorObj = new Iterator();

        Icon icon = new ImageIcon();
    }

    public void runGame(){

        viewObj.mainMenu_Comps();
    }
    public void startButton(){

        System.out.println("In Controller startButton()");
        //System.out.println("Iterations: " + iterations);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < iterations; i++) {
                    nextButton();
                }
            }
        });
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.start();
    }

    public void nextButton(){
        System.out.println("In Controller nextButton()");
        gridObj.setRunningColony(iteratorObj.next(gridObj.getRunningColony()));
        //System.out.println("After iterator");
        //viewObj.setDelay(5000);         // Range 1 - 5000, 1 = slowest 5000 = fastest
        viewObj.setGrid(gridObj.getRunningColony());

    }

    public void stopButton(){
        System.out.println("In Controller stopButton()");
        iterations = 0;
        viewObj.setIterations(0);
    }

    public void resetButton(){
        System.out.println("In Controller resetButton()");
        viewObj.resetGrid();
        gridObj = new Grid(gridObj.getSize());
    }

    public void setCell(String cell){
        System.out.println("In Controller setCell()");
        // Will call gridObj to update grid as needed
        gridObj.setCell(Integer.parseInt(cell));
        System.out.println("in Controller setCell " + cell);
    }

    public void setMode(String mode){
        System.out.println("In Controller setMode()");
        System.out.println(mode);

    }

    public void setIterations(JSpinner iteration){
        System.out.println("In Controller setIterations()");
        iterations = (Integer) iteration.getValue();
        System.out.println(iterations);
    }

    public void setSpeed(JSlider speed){
        System.out.println("In Controller setSpeed()");
        //Speed range currently 5 - 50
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                viewObj.setDelay(speed.getValue());
            }
        });
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.start();
        System.out.println(speed.getValue());
    }

    public void setSize(JSlider size){
        System.out.println("In Controller setSize()");
        viewObj.setSize(size.getValue());
        gridObj.setSize(size.getValue());
        //System.out.println(size.getValue());
    }

    public void setPreset(String preset){
        System.out.println("In Controller setPreset()");

        switch (preset){
            case "none"         :   resetButton();
                                    break;
            case "John Conway"  :   //viewObj.setButtonHeight(28);
                                    viewObj.setSizeSliderValue(gridObj.getPreset1().length);
                                    viewObj.setGrid(gridObj.getPreset1());
                                    break;
            case "Glider"       :   //viewObj.setButtonHeight(28);
                                    viewObj.setSizeSliderValue(gridObj.getPreset2().length);
                                    viewObj.setGrid(gridObj.getPreset2());
                                    break;
            case "Exploder"     :   //viewObj.setButtonHeight(28);
                                    viewObj.setSizeSliderValue(gridObj.getPreset3().length);
                                    viewObj.setGrid(gridObj.getPreset3());
                                    break;
            case "Tumbler"      :   //viewObj.setButtonHeight(51);
                                    viewObj.setSizeSliderValue(gridObj.getPreset4().length);
                                    viewObj.setGrid(gridObj.getPreset4());
                                    break;
            default             :   System.out.println("Default");
                                    System.out.println(preset);
                                    break;
        }
    }

    public void setSprite(String value){
        System.out.println("In Controller setSprite()");
        viewObj.setIcon();
    }

}
