package pages;

import generic.*;

public abstract class BasePage {

    public Operation operation;
    public CustomeAssert check;
    public Synchronize synchronize;
    public Scroll scroll;
    public SelectItem selectItem;

    BasePage(){
        operation = new Operation();
        check = new CustomeAssert();
        synchronize = new Synchronize();
        scroll = new Scroll();
        selectItem = new SelectItem();
    }



}
