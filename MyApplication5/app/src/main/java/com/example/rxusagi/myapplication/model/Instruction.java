package com.example.rxusagi.myapplication.model;

import android.util.Log;

/**
 * Created by RXUsagi on 15/10/2015.
 */
public class Instruction {
    private int key;
    private String img;
    private String instruction;
    private String instruction2;
    private String instruction_name;
    private String style;
    public  Instruction(int key,String img,String instruction,String instruction2,String instruction_name,String style){
        Log.i("TAG4", "INCre" + instruction_name +" " + style);
        this.key = key;
        this.img = img;
        this.instruction = instruction;
        this.instruction2 = instruction2;
        this.instruction_name = instruction_name;
        this.style = style;
    }
    public int getKey() {
        return key;
    }
    public String getImg() {
        return img;
    }
    public String getInstruction() {
        return instruction;
    }
    public String getInstruction2() {
        return instruction2;
    }
    public String getInstruction_name() {
        return instruction_name;
    }
    public String getStyle() {
        return style;
    }
}
