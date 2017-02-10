package com.robertkiszelirk.androidquiz;

import java.util.ArrayList;

public class EditTextQuiz {

    ArrayList<String> list = new ArrayList();

    public EditTextQuiz(String question, String answer){

        list.add(question);
        list.add(answer);
    }

    public ArrayList<String> getEditTextQuizData(){
        return list;
    }

}
