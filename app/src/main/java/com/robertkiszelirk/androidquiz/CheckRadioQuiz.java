package com.robertkiszelirk.androidquiz;

import java.util.ArrayList;

class CheckRadioQuiz{

    private ArrayList<String> list = new ArrayList<>();

    CheckRadioQuiz(String question,
                   String answerOne, String answerOneTF,
                   String answerTwo, String answerTwoTF,
                   String answerThree, String answerThreeTF,
                   String answerFour, String answerFourTF,
                   String answerFive, String answerFiveTF){

        list.add(question);
        list.add(answerOne);
        list.add(answerOneTF);
        list.add(answerTwo);
        list.add(answerTwoTF);
        list.add(answerThree);
        list.add(answerThreeTF);
        list.add(answerFour);
        list.add(answerFourTF);
        list.add(answerFive);
        list.add(answerFiveTF);

    }

    ArrayList<String> getCheckRadioQuizData(){
        return list;
    }

}
