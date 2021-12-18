package systemLife;

import javax.swing.*;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        Controller sessionController = new Controller();
        sessionController.runGame();
    }
}
