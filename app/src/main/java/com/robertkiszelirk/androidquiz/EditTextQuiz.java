package com.robertkiszelirk.androidquiz;

import java.util.ArrayList;

class EditTextQuiz {

    private ArrayList<String> list = new ArrayList<>();

    EditTextQuiz(String question, String answer){

        list.add(question);
        list.add(answer);
    }

    ArrayList<String> getEditTextQuizData(){
        return list;
    }

}
