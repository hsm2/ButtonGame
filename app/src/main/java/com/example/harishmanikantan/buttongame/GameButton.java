package com.example.harishmanikantan.buttongame;

import android.widget.Button;

/**
 * Created by harishmanikantan on 4/3/17.
 */

public class GameButton {

    private Button button;
    private int status;

    private final int STATUS_ON = 1;
    private final int STATUS_OFF = 0;

    public GameButton(Button button) {
        this.button = button;
        status = STATUS_ON;
    }

    // Flips button text, color and status
    public void flip() {
        if (status == STATUS_ON) {
            button.setText("OFF");
            button.setBackgroundResource(R.color.red);
            status = STATUS_OFF;
        }
        else {
            button.setText("ON");
            button.setBackgroundResource(R.color.green);
            status = STATUS_ON;
        }
    }

    //Sets the button text to on, color to green and status to on
    public void setOn() {
        button.setText("ON");
        button.setBackgroundResource(R.color.green);
        status = STATUS_ON;
    }

    public int getStatus() {
        return status;
    }

    public void setVisibility(int visibility) {
        button.setVisibility(visibility);
    }
}
