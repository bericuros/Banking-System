/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author pc
 */
public class Paket implements Serializable {
    
    private int valueInt;
    private float valueFloat;
    private char valueChar;
    private Boolean valueBoolean;
    private String valueString;
    private List valueList;

    public int getValueInt() {
        return valueInt;
    }

    public void setValueInt(int valueInt) {
        this.valueInt = valueInt;
    }

    public float getValueFloat() {
        return valueFloat;
    }

    public void setValueFloat(float valueFloat) {
        this.valueFloat = valueFloat;
    }

    public char getValueChar() {
        return valueChar;
    }

    public void setValueChar(char valueChar) {
        this.valueChar = valueChar;
    }

    public Boolean getValueBoolean() {
        return valueBoolean;
    }

    public void setValueBoolean(Boolean valueBoolean) {
        this.valueBoolean = valueBoolean;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public List getValueList() {
        return valueList;
    }

    public void setValueList(List valueList) {
        this.valueList = valueList;
    }
    
    
}
