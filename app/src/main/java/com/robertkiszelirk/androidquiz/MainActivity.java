package com.robertkiszelirk.androidquiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CardView appTheme;
    CardView playerScore;
    CardView quizEnd;

    LinearLayout appThemeLL;
    LinearLayout playerScoreLL;
    LinearLayout quizEndLL;

    ArrayList<LinearLayout> quizLayouts;

    Button submitNextButton;

    int actualView;

    Animation animOut;
    Animation animIn;

    int score = 0;
    int addScore = 5;
    String playerName;
    EditText playerNameEditText;
    TextView playerNameText;
    TextView scoreText;
    TextView quizLevel;
    TextView quizRule;

    Random randomNumber;

    ArrayList<String> editNumberQuizData;
    ArrayList<EditTextQuiz> editTextNumberQuiz;
    EditText editTextNumberAnswer;
    int editTextNumberRandom = 0;

    TextView editTextNumberQuestion;

    ArrayList<String> editTextQuizData;
    ArrayList<EditTextQuiz> editTextTextQuiz;
    EditText editTextTextAnswer;
    int editTextTextRandom = 0;

    ArrayList<String> checkBoxQuizData;
    ArrayList<CheckRadioQuiz> checkQuiz;
    ArrayList<CheckBox> checkBoxes;
    int checkBoxRandom = 0 ;

    ArrayList<String> radioQuizData;
    ArrayList<CheckRadioQuiz> radioQuiz;
    ArrayList<RadioButton> radioButtons;
    int radioButtonRandom = 0;

    boolean orientationChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //CHECK ANDROID VERSION
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            appTheme = (CardView) findViewById(R.id.appTheme);
            playerScore = (CardView) findViewById(R.id.playerScore);
            quizEnd = (CardView) findViewById(R.id.quiz_end);
        } else{
            appThemeLL = (LinearLayout) findViewById(R.id.appTheme);
            playerScoreLL = (LinearLayout) findViewById(R.id.playerScore);
            quizEndLL = (LinearLayout) findViewById(R.id.quiz_end);
        }

        //SET CARD TEXTVIEWS
        playerNameText = (TextView) findViewById(R.id.player_name_card_view);
        scoreText = (TextView) findViewById(R.id.score);
        quizLevel = (TextView) findViewById(R.id.quiz_level);
        quizRule = (TextView) findViewById(R.id.quiz_rule);

        //CREATE LAYOUTS
        quizLayouts = new ArrayList<>();
        quizLayouts.add((LinearLayout) findViewById(R.id.start_layout));
        quizLayouts.add((LinearLayout) findViewById(R.id.quiz_text_number));
        quizLayouts.add((LinearLayout) findViewById(R.id.quiz_text_text));
        quizLayouts.add((LinearLayout) findViewById(R.id.quiz_checkbox));
        quizLayouts.add((LinearLayout) findViewById(R.id.quiz_radio_button));
        quizLayouts.add((LinearLayout) findViewById(R.id.quiz_end_layout));

        //SET EDIT TEXTS
        editTextNumberQuestion = (TextView) findViewById(R.id.edit_text_number_question);
        editTextNumberAnswer = (EditText) findViewById(R.id.edit_text_number_answer);

        //SET BUTTON
        submitNextButton = (Button) findViewById(R.id.submit_next_button);

        //SET BASE DATA
        actualView = 0;
        randomNumber = new Random();

        //SET ANIMATION
        animOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        animIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        //SET EDIT TEXT NUMBER QUIZ QUESTIONS
        editTextNumberQuiz = new ArrayList();
        editTextNumberQuiz.add(new EditTextQuiz(getString(R.string.edit_text_number_question_one),getString(R.string.edit_text_number_answer_one)));
        editTextNumberQuiz.add(new EditTextQuiz(getString(R.string.edit_text_number_question_two),getString(R.string.edit_text_number_answer_two)));
        editTextNumberQuiz.add(new EditTextQuiz(getString(R.string.edit_text_number_question_three),getString(R.string.edit_text_number_answer_three)));
        editTextNumberQuiz.add(new EditTextQuiz(getString(R.string.edit_text_number_question_four),getString(R.string.edit_text_number_answer_four)));
        editTextNumberQuiz.add(new EditTextQuiz(getString(R.string.edit_text_number_question_five),getString(R.string.edit_text_number_answer_five)));
        editTextNumberQuiz.add(new EditTextQuiz(getString(R.string.edit_text_number_question_six),getString(R.string.edit_text_number_answer_six)));

        //SET EDIT TEXT TEXT QUIZ QUESTIONS
        editTextTextQuiz = new ArrayList();
        editTextTextQuiz.add(new EditTextQuiz(getString(R.string.edit_text_text_question),getString(R.string.edit_text_text_answer_one)));
        editTextTextQuiz.add(new EditTextQuiz(getString(R.string.edit_text_text_question),getString(R.string.edit_text_text_answer_two)));
        editTextTextQuiz.add(new EditTextQuiz(getString(R.string.edit_text_text_question),getString(R.string.edit_text_text_answer_three)));
        editTextTextQuiz.add(new EditTextQuiz(getString(R.string.edit_text_text_question),getString(R.string.edit_text_text_answer_four)));
        editTextTextQuiz.add(new EditTextQuiz(getString(R.string.edit_text_text_question),getString(R.string.edit_text_text_answer_five)));
        editTextTextQuiz.add(new EditTextQuiz(getString(R.string.edit_text_text_question),getString(R.string.edit_text_text_answer_six)));
        editTextTextQuiz.add(new EditTextQuiz(getString(R.string.edit_text_text_question),getString(R.string.edit_text_text_answer_seven)));
        editTextTextQuiz.add(new EditTextQuiz(getString(R.string.edit_text_text_question),getString(R.string.edit_text_text_answer_eight)));
        editTextTextQuiz.add(new EditTextQuiz(getString(R.string.edit_text_text_question),getString(R.string.edit_text_text_answer_nine)));
        editTextTextQuiz.add(new EditTextQuiz(getString(R.string.edit_text_text_question),getString(R.string.edit_text_text_answer_ten)));
        editTextTextQuiz.add(new EditTextQuiz(getString(R.string.edit_text_text_question),getString(R.string.edit_text_text_answer_eleven)));
        editTextTextQuiz.add(new EditTextQuiz(getString(R.string.edit_text_text_question),getString(R.string.edit_text_text_answer_twelve)));

        //SET CHECK BOX QUIZ QUESTIONS
        checkQuiz = new ArrayList();
        checkQuiz.add(new CheckRadioQuiz(getString(R.string.check_box_question_one),
                getString(R.string.check_box_answer_three_one),getString(R.string.check_box_false),
                getString(R.string.check_box_answer_one_two),getString(R.string.check_box_false),
                getString(R.string.check_box_answer_one_four),getString(R.string.check_box_false),
                getString(R.string.check_box_answer_one_five),getString(R.string.check_box_true),
                getString(R.string.check_box_answer_two_one),getString(R.string.check_box_false)));
        checkQuiz.add(new CheckRadioQuiz(getString(R.string.check_box_question_two),
                getString(R.string.check_box_answer_three_zero),getString(R.string.check_box_true),
                getString(R.string.check_box_answer_two_six),getString(R.string.check_box_false),
                getString(R.string.check_box_answer_three_one),getString(R.string.check_box_true),
                getString(R.string.check_box_answer_three_two),getString(R.string.check_box_true),
                getString(R.string.check_box_answer_two_one),getString(R.string.check_box_false)));
        checkQuiz.add(new CheckRadioQuiz("Which one(s) are real version of KITKAT ?",
                getString(R.string.check_box_answer_four_four),getString(R.string.check_box_true),
                getString(R.string.check_box_answer_four_five),getString(R.string.check_box_false),
                getString(R.string.check_box_answer_four_zero),getString(R.string.check_box_false),
                getString(R.string.check_box_answer_three_two),getString(R.string.check_box_false),
                getString(R.string.check_box_answer_four_three),getString(R.string.check_box_false)));
        checkQuiz.add(new CheckRadioQuiz("Which one(s) are real version of JELLY BEAN ?",
                getString(R.string.check_box_answer_four_three),getString(R.string.check_box_true),
                getString(R.string.check_box_answer_five_zero),getString(R.string.check_box_false),
                getString(R.string.check_box_answer_four_two),getString(R.string.check_box_true),
                getString(R.string.check_box_answer_four_zero),getString(R.string.check_box_false),
                getString(R.string.check_box_answer_four_one),getString(R.string.check_box_true)));
        checkQuiz.add(new CheckRadioQuiz("Which one(s) are real version of GINGERBREAD ?",
                getString(R.string.check_box_answer_two_two),getString(R.string.check_box_false),
                getString(R.string.check_box_answer_one_eight),getString(R.string.check_box_false),
                getString(R.string.check_box_answer_three_one),getString(R.string.check_box_false),
                getString(R.string.check_box_answer_two_zero),getString(R.string.check_box_false),
                getString(R.string.check_box_answer_two_three),getString(R.string.check_box_true)));

        //Set Radio Button Quiz Questions
        radioQuiz = new ArrayList();
        radioQuiz.add(new CheckRadioQuiz(getString(R.string.radio_button_question_one),
                getString(R.string.radio_button_answer_one_one),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_one_two),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_one_three),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_one_four),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_one_five),getString(R.string.radio_button_true)));
        radioQuiz.add(new CheckRadioQuiz(getString(R.string.radio_button_question_two),
                getString(R.string.radio_button_answer_two_one),getString(R.string.radio_button_true),
                getString(R.string.radio_button_answer_two_two),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_two_three),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_two_four),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_two_five),getString(R.string.radio_button_false)));
        radioQuiz.add(new CheckRadioQuiz(getString(R.string.radio_button_question_three),
                getString(R.string.radio_button_answer_three_one),getString(R.string.radio_button_true),
                getString(R.string.radio_button_answer_three_two),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_three_three),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_three_four),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_three_five),getString(R.string.radio_button_false)));
        radioQuiz.add(new CheckRadioQuiz(getString(R.string.radio_button_question_four),
                getString(R.string.radio_button_answer_four_one),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_four_two),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_four_three),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_four_four),getString(R.string.radio_button_true),
                getString(R.string.radio_button_answer_four_five),getString(R.string.radio_button_false)));
        radioQuiz.add(new CheckRadioQuiz(getString(R.string.radio_button_question_five),
                getString(R.string.radio_button_answer_five_one),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_five_two),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_five_three),getString(R.string.radio_button_true),
                getString(R.string.radio_button_answer_five_four),getString(R.string.radio_button_false),
                getString(R.string.radio_button_answer_five_five),getString(R.string.radio_button_false)));



        //HANDLE ORIENTATION CHANGE
        if(savedInstanceState != null){

            orientationChanged = true;

            playerName = savedInstanceState.getString("playerName");

            playerNameText.setText(savedInstanceState.getString("playerName"));
            scoreText.setText(savedInstanceState.getString("score"));
            quizLevel.setText(savedInstanceState.getString("quizLevel"));
            quizRule.setText(savedInstanceState.getString("quizRule"));
            submitNextButton.setText(savedInstanceState.getString("submitNextButton"));

            editTextNumberQuestion.setText(savedInstanceState.getString("quizOneQuestion"));
            editTextNumberAnswer.setText(savedInstanceState.getString("quizOneAnswer"));

            setRotateView(savedInstanceState.getInt("actualView"));

            actualView = savedInstanceState.getInt("actualView");
            editTextNumberRandom = savedInstanceState.getInt("editTextNumberRandom");
            editTextTextRandom = savedInstanceState.getInt("editTextTextRandom");
            checkBoxRandom = savedInstanceState.getInt("checkBoxRandom");
            radioButtonRandom = savedInstanceState.getInt("radioButtonRandom");

            for (int i = 1; i < 6; i++){
                setQuiz(i);
            }

            orientationChanged = false;
        }
    }

    public void changeView(View view) {

        boolean wasIn = false;

        //VIEW CHANGE AT START,NEXT QUIZ AND SUMMARY
        if ((submitNextButton.getText().equals(getString(R.string.next_quiz))) ||
                (submitNextButton.getText().equals(getString(R.string.start_quiz))) ||
                (submitNextButton.getText().equals(getString(R.string.summary)))) {

            if(submitNextButton.getText().equals(getString(R.string.start_quiz))){

                playerNameEditText = (EditText) findViewById(R.id.edit_player_name_text);

                playerName = playerNameEditText.getText().toString();

                playerNameEditText.setText("");

                if (playerName.equals("")){
                    playerNameText.setText(R.string.player_name);
                }else {
                    playerNameText.setText(playerName);
                }

            }

            if (actualView < (quizLayouts.size() - 1)) {
                if (actualView == 0) {

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                        playerScore.setVisibility(View.VISIBLE);
                        playerScore.startAnimation(animIn);
                        appTheme.setVisibility(View.GONE);
                        appTheme.startAnimation(animOut);
                    } else{
                        playerScoreLL.setVisibility(View.VISIBLE);
                        playerScoreLL.startAnimation(animIn);
                        appThemeLL.setVisibility(View.GONE);
                        appThemeLL.startAnimation(animOut);
                    }

                }

                if(actualView == 1){editTextNumberAnswer.setText("");}
                if(actualView == 2){editTextTextAnswer.setText("");}
                if(actualView == 3){
                    for(CheckBox checkBox : checkBoxes){
                        if(checkBox.isChecked()){checkBox.toggle();}
                    }
                }
                if(actualView == 4){
                    RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radio_group);
                    radioGroup.clearCheck();
                }

                quizLayouts.get(actualView).setVisibility(View.GONE);
                quizLayouts.get(actualView).startAnimation(animOut);
                actualView++;
                quizLayouts.get(actualView).setVisibility(View.VISIBLE);
                quizLayouts.get(actualView).startAnimation(animIn);

                if(actualView != 5) {
                    submitNextButton.setText(R.string.submit_answer);
                    wasIn = true;

                    quizLevel.setText(getString(R.string.quiz_level) + actualView);
                    setQuiz(actualView);
                }else{


                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                        playerScore.setVisibility(View.GONE);
                        playerScore.startAnimation(animOut);
                        quizEnd.setVisibility(View.VISIBLE);
                        quizEnd.startAnimation(animIn);
                    } else{
                        playerScoreLL.setVisibility(View.GONE);
                        playerScoreLL.startAnimation(animOut);
                        quizEndLL.setVisibility(View.VISIBLE);
                        quizEndLL.startAnimation(animIn);
                    }

                    setQuiz(actualView);

                    submitNextButton.setText(R.string.restart);
                    wasIn = true;
                }
            }
        }

        //HANDLE SUBMIT ANSWER BEFORE LAST VIEW
        if((submitNextButton.getText().equals(getString(R.string.submit_answer)))&&(actualView != 4)&&(!wasIn)){
            checkQuiz(actualView);
            submitNextButton.setText(R.string.next_quiz);
        }

        //HANDLE SUBMIT ANSWER ON LAST VIEW
        if((submitNextButton.getText().equals(getString(R.string.submit_answer))) && (actualView == 4)&&(!wasIn)) {
            checkQuiz(actualView);
            submitNextButton.setText(R.string.summary);
            wasIn = true;
        }

        //RESTART QUIZ
        if((submitNextButton.getText().equals(getString(R.string.restart))) && (actualView == 5)&&(!wasIn)) {
            quizLayouts.get(actualView).setVisibility(View.GONE);
            quizLayouts.get(actualView).startAnimation(animOut);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                quizEnd.setVisibility(View.GONE);
                quizEnd.startAnimation(animOut);
            } else{
                quizEndLL.setVisibility(View.GONE);
                quizEndLL.startAnimation(animOut);
            }

            actualView = 0;
            quizLayouts.get(actualView).setVisibility(View.VISIBLE);
            quizLayouts.get(actualView).startAnimation(animIn);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                appTheme.setVisibility(View.VISIBLE);
                appTheme.startAnimation(animIn);
            } else{
                appThemeLL.setVisibility(View.VISIBLE);
                appThemeLL.startAnimation(animIn);
            }


            submitNextButton.setText(getString(R.string.start_quiz));

            score = 0;
            scoreText.setText(getString(R.string.score_text) + score);

            for(CheckBox checkBox : checkBoxes){
                checkBox.setEnabled(true);
                checkBox.setChecked(false);
            }

            for(RadioButton radioButton : radioButtons){
                radioButton.setEnabled(true);
                radioButton.setChecked(false);
            }

            editTextNumberAnswer.setFocusable(true);
            editTextNumberAnswer.setFocusableInTouchMode(true);

            editTextTextAnswer.setFocusable(true);
            editTextTextAnswer.setFocusableInTouchMode(true);

        }

    }

    //HANDLE THE CARD VIEW HELP BUTTON
    public void shoeHelp(View view) {

        if(actualView != 2) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Android_(operating_system)"));
            startActivity(intent);
        }else{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.couponraja.in/theroyale/wp-content/uploads/2016/07/android_nougat_new_android_addition.jpg"));
            startActivity(intent);
        }

        addScore = 3;
    }

    //SET QUIZ QUESTIONS ON ACTUAL VIEW
    public void setQuiz(int actualQuiz){

        switch (actualQuiz){
            case 1:
                addScore = 5;
                quizRule.setText(R.string.quiz_one_hint);
                if(!orientationChanged) {
                    editTextNumberRandom = randomNumber.nextInt(editTextNumberQuiz.size());
                }
                editNumberQuizData = new ArrayList(editTextNumberQuiz.get(editTextNumberRandom).getEditTextQuizData());
                editTextNumberQuestion.setText(editNumberQuizData.get(0));

                break;
            case 2:
                addScore = 5;
                quizRule.setText(R.string.quiz_two_hint);
                if(!orientationChanged) {
                    editTextTextRandom = randomNumber.nextInt(editTextTextQuiz.size()) ;
                }
                editTextQuizData = new ArrayList(editTextTextQuiz.get(editTextTextRandom).getEditTextQuizData());

                TextView editTextTextQuestion = (TextView) findViewById(R.id.edit_text_text_question);
                editTextTextQuestion.setText(editTextQuizData.get(0));

                ImageView imageView = (ImageView) findViewById(R.id.edit_text_text_image);

                switch(editTextTextRandom){
                    case 0:imageView.setBackgroundResource(R.drawable.cupcake);
                        break;
                    case 1:imageView.setBackgroundResource(R.drawable.donut);
                        break;
                    case 2:imageView.setBackgroundResource(R.drawable.eclair);
                        break;
                    case 3:imageView.setBackgroundResource(R.drawable.froyo);
                        break;
                    case 4:imageView.setBackgroundResource(R.drawable.gingerbread);
                        break;
                    case 5:imageView.setBackgroundResource(R.drawable.honeycomb);
                        break;
                    case 6:imageView.setBackgroundResource(R.drawable.icecreamsandwich);
                        break;
                    case 7:imageView.setBackgroundResource(R.drawable.jellybean);
                        break;
                    case 8:imageView.setBackgroundResource(R.drawable.kitkat);
                        break;
                    case 9:imageView.setBackgroundResource(R.drawable.lollipop);
                        break;
                    case 10:imageView.setBackgroundResource(R.drawable.marshmallow);
                        break;
                    case 11:imageView.setBackgroundResource(R.drawable.nougat);
                        break;
                }
                editTextTextAnswer = (EditText) findViewById(R.id.edit_text_text_answer);
                break;
            case 3:
                addScore = 5;
                quizRule.setText(R.string.quiz_three_hint);
                if(!orientationChanged) {
                    checkBoxRandom = randomNumber.nextInt(checkQuiz.size());
                }
                checkBoxQuizData = new ArrayList(checkQuiz.get(checkBoxRandom).getCheckRadioQuizData());

                TextView checkBoxQuestion = (TextView) findViewById(R.id.checkbox_question);
                checkBoxQuestion.setText(checkBoxQuizData.get(0));

                checkBoxes = new ArrayList();
                checkBoxes.add((CheckBox) findViewById(R.id.answer_one_check));
                checkBoxes.add((CheckBox) findViewById(R.id.answer_two_check));
                checkBoxes.add((CheckBox) findViewById(R.id.answer_three_check));
                checkBoxes.add((CheckBox) findViewById(R.id.answer_four_check));
                checkBoxes.add((CheckBox) findViewById(R.id.answer_five_check));

                int counterCheckBox = 1;
                for(CheckBox checkBox : checkBoxes){
                    checkBox.setText(checkBoxQuizData.get(counterCheckBox));
                    counterCheckBox += 2;
                }

                break;
            case 4:
                addScore = 5;
                quizRule.setText(R.string.quiz_four_hint);
                if(!orientationChanged) {
                    radioButtonRandom = randomNumber.nextInt(radioQuiz.size());
                }
                radioQuizData = new ArrayList(radioQuiz.get(radioButtonRandom).getCheckRadioQuizData());

                TextView radioQuestion = (TextView) findViewById(R.id.radio_button_question);
                radioQuestion.setText(radioQuizData.get(0));

                radioButtons = new ArrayList();
                radioButtons.add((RadioButton) findViewById(R.id.answer_one_radio));
                radioButtons.add((RadioButton) findViewById(R.id.answer_two_radio));
                radioButtons.add((RadioButton) findViewById(R.id.answer_three_radio));
                radioButtons.add((RadioButton) findViewById(R.id.answer_four_radio));
                radioButtons.add((RadioButton) findViewById(R.id.answer_five_radio));

                int counterRadio = 1;
                for(RadioButton radioButton : radioButtons){
                    radioButton.setText(radioQuizData.get(counterRadio));
                    counterRadio += 2;
                }

                break;
            case 5:
                TextView endCardText = (TextView) findViewById(R.id.end_card_text);
                endCardText.setText(getString(R.string.end_thank_first)+playerName+getString(R.string.end_thank_second)+score+getString(R.string.end_thank_third));

                TextView questionOne = (TextView) findViewById(R.id.end_question_one);
                questionOne.setText(editNumberQuizData.get(0));
                TextView answerOne = (TextView) findViewById(R.id.end_answer_one);
                answerOne.setText(editNumberQuizData.get(1));

                TextView questionTwo = (TextView) findViewById(R.id.end_question_two);
                questionTwo.setText(editTextQuizData.get(0));
                TextView answerTwo = (TextView) findViewById(R.id.end_answer_two);
                answerTwo.setText(editTextQuizData.get(1));
                ImageView endImage = (ImageView) findViewById(R.id.end_image_view);

                if(editTextQuizData.get(1).equals("CUPCAKE")){
                    endImage.setBackgroundResource(R.drawable.cupcake);
                }
                if(editTextQuizData.get(1).equals("NOUGAT")){
                    endImage.setBackgroundResource(R.drawable.nougat);
                }
                if(editTextQuizData.get(1).equals("LOLLIPOP")){
                    endImage.setBackgroundResource(R.drawable.lollipop);
                }
                switch(editTextTextRandom){
                    case 0:endImage.setBackgroundResource(R.drawable.cupcake);
                        break;
                    case 1:endImage.setBackgroundResource(R.drawable.donut);
                        break;
                    case 2:endImage.setBackgroundResource(R.drawable.eclair);
                        break;
                    case 3:endImage.setBackgroundResource(R.drawable.froyo);
                        break;
                    case 4:endImage.setBackgroundResource(R.drawable.gingerbread);
                        break;
                    case 5:endImage.setBackgroundResource(R.drawable.honeycomb);
                        break;
                    case 6:endImage.setBackgroundResource(R.drawable.icecreamsandwich);
                        break;
                    case 7:endImage.setBackgroundResource(R.drawable.jellybean);
                        break;
                    case 8:endImage.setBackgroundResource(R.drawable.kitkat);
                        break;
                    case 9:endImage.setBackgroundResource(R.drawable.lollipop);
                        break;
                    case 10:endImage.setBackgroundResource(R.drawable.marshmallow);
                        break;
                    case 11:endImage.setBackgroundResource(R.drawable.nougat);
                        break;
                }

                TextView questionThree = (TextView) findViewById(R.id.end_question_three);
                questionThree.setText(checkBoxQuizData.get(0));
                ArrayList<CheckBox> endCheckBoxes = new ArrayList();
                endCheckBoxes.add((CheckBox) findViewById(R.id.end_answer_check_one));
                endCheckBoxes.add((CheckBox) findViewById(R.id.end_answer_check_two));
                endCheckBoxes.add((CheckBox) findViewById(R.id.end_answer_check_three));
                endCheckBoxes.add((CheckBox) findViewById(R.id.end_answer_check_four));
                endCheckBoxes.add((CheckBox) findViewById(R.id.end_answer_check_five));
                int boxCounter = 0;
                for(CheckBox endCheckBox : endCheckBoxes){
                    endCheckBox.setChecked(false);
                    endCheckBox.setEnabled(true);
                    endCheckBox.setText(checkBoxes.get(boxCounter).getText().toString());
                    if (Boolean.parseBoolean(checkBoxQuizData.get(2 + (boxCounter * 2)))){
                        endCheckBox.setChecked(true);
                    }
                    endCheckBox.setEnabled(false);
                    boxCounter+=1;
                }

                TextView questionFour = (TextView) findViewById(R.id.end_question_four);
                questionFour.setText(radioQuizData.get(0));
                ArrayList<RadioButton> endRadioButtons = new ArrayList();
                endRadioButtons.add((RadioButton) findViewById(R.id.end_answer_radio_one));
                endRadioButtons.add((RadioButton) findViewById(R.id.end_answer_radio_two));
                endRadioButtons.add((RadioButton) findViewById(R.id.end_answer_radio_three));
                endRadioButtons.add((RadioButton) findViewById(R.id.end_answer_radio_four));
                endRadioButtons.add((RadioButton) findViewById(R.id.end_answer_radio_five));
                int countRadio = 1;
                for(RadioButton radioButton : endRadioButtons){
                    radioButton.setChecked(false);
                    radioButton.setEnabled(true);
                    radioButton.setText(radioQuizData.get(countRadio));
                    if(Boolean.parseBoolean(radioQuizData.get(countRadio + 1))){
                        radioButton.setChecked(true);
                    }
                    radioButton.setEnabled(false);
                    countRadio += 2;
                }
                break;
        }
    }

    //CHECK QUIZ ANSWER ON ACTUAL VIEW
    public void checkQuiz(int actualQuiz){

        switch(actualQuiz){
            case 1:
                if(editTextNumberAnswer.getText().toString().equals(editNumberQuizData.get(1))){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            getString(R.string.wright_answer)+" +"+addScore+"score.",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    score += addScore;
                    scoreText.setText(getString(R.string.score_text) + score);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            getString(R.string.wrong_answer)+editNumberQuizData.get(1)+"!",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                editTextNumberAnswer.setFocusable(false);
                editTextNumberAnswer.setFocusableInTouchMode(false);
                break;
            case 2:
                if(editTextTextAnswer.getText().toString().toUpperCase().equals(editTextQuizData.get(1))){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            getString(R.string.wright_answer)+" +"+addScore+"score.",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    score += addScore;
                    scoreText.setText(getString(R.string.score_text) + score);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            getString(R.string.wrong_answer)+editTextQuizData.get(1)+"!",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                editTextTextAnswer.setFocusable(false);
                editTextTextAnswer.setFocusableInTouchMode(false);
                break;
            case 3:
                int wrightAnswersCheck = 0;
                int counterCheck = 2;
                for(CheckBox checkBox : checkBoxes){
                    if (checkBox.isChecked() == Boolean.parseBoolean(checkBoxQuizData.get(counterCheck))){
                        wrightAnswersCheck++;
                    }
                    checkBox.setEnabled(false);
                    counterCheck += 2;
                }
                if(wrightAnswersCheck == 5){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            getString(R.string.wright_answer)+" +"+addScore+"score.",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    score += addScore;
                    scoreText.setText(getString(R.string.score_text) + score);
                }else{
                    String text;
                    text = getString(R.string.wrong_answer);
                    for (int i = 2; i < checkBoxQuizData.size(); i+=2){
                        if(checkBoxQuizData.get(i).equals("true")){
                            text += ", " + checkBoxQuizData.get(i-1)+"";
                        }
                    }
                    text += " !";
                    Toast toast = Toast.makeText(getApplicationContext(),
                            text,
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case 4:
                int wrightAnswersRadio = 0;
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
                int selectedButton = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = (RadioButton) findViewById(selectedButton);
                if(selectedButton != -1) {
                    for (int i = 1; i < radioQuizData.size(); i += 2) {
                        if ((selectedRadioButton.getText().toString().equals(radioQuizData.get(i))
                        &&(Boolean.parseBoolean(radioQuizData.get(i+1))))) {
                            wrightAnswersRadio++;
                        }
                    }
                }
                for(RadioButton radioButton : radioButtons){
                    radioButton.setEnabled(false);
                }
                if(wrightAnswersRadio == 1){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            getString(R.string.wright_answer)+" +"+addScore+"score.",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    score += addScore;
                    scoreText.setText(getString(R.string.score_text) + score);
                }else{
                    String text;
                    text = getString(R.string.wrong_answer);
                    for (int i = 2; i < radioQuizData.size(); i+=2){
                        if(radioQuizData.get(i).equals("true")){
                            text += ", " + radioQuizData.get(i-1)+"";
                        }
                    }
                    text += " !";
                    Toast toast = Toast.makeText(getApplicationContext(),
                            text,
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    //HANDLE ORIENTATION CHANGE
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("playerName",playerName);
        outState.putString("score",scoreText.getText().toString());
        outState.putString("quizLevel",quizLevel.getText().toString());
        outState.putString("quizRule",quizRule.getText().toString());
        outState.putString("submitNextButton",submitNextButton.getText().toString());

        outState.putString("quizOneQuestion",editTextNumberQuestion.getText().toString());
        outState.putString("quizOneAnswer",editTextNumberAnswer.getText().toString());


        outState.putInt("actualView",actualView);
        outState.putInt("editTextNumberRandom",editTextNumberRandom);
        outState.putInt("editTextTextRandom",editTextTextRandom);
        outState.putInt("checkBoxRandom",checkBoxRandom);
        outState.putInt("radioButtonRandom",radioButtonRandom);

        super.onSaveInstanceState(outState);
    }

    //RECREATE VIEW AFTER ORIENTATION CHANGE
    private void setRotateView(int rotatedView){

        switch(rotatedView){
            case 0:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    playerScore.setVisibility(View.GONE);
                    appTheme.setVisibility(View.VISIBLE);
                    quizEnd.setVisibility(View.GONE);
                } else{
                    playerScoreLL.setVisibility(View.GONE);
                    appThemeLL.setVisibility(View.VISIBLE);
                    quizEndLL.setVisibility(View.GONE);
                }

                quizLayouts.get(0).setVisibility(View.VISIBLE);
                quizLayouts.get(1).setVisibility(View.GONE);
                quizLayouts.get(2).setVisibility(View.GONE);
                quizLayouts.get(3).setVisibility(View.GONE);
                quizLayouts.get(4).setVisibility(View.GONE);
                quizLayouts.get(5).setVisibility(View.GONE);

                break;
            case 1:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    playerScore.setVisibility(View.VISIBLE);
                    appTheme.setVisibility(View.GONE);
                    quizEnd.setVisibility(View.GONE);
                } else{
                    playerScoreLL.setVisibility(View.VISIBLE);
                    appThemeLL.setVisibility(View.GONE);
                    quizEndLL.setVisibility(View.GONE);
                }

                quizLayouts.get(0).setVisibility(View.GONE);
                quizLayouts.get(1).setVisibility(View.VISIBLE);
                quizLayouts.get(2).setVisibility(View.GONE);
                quizLayouts.get(3).setVisibility(View.GONE);
                quizLayouts.get(4).setVisibility(View.GONE);
                quizLayouts.get(5).setVisibility(View.GONE);

                break;
            case 2:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    playerScore.setVisibility(View.VISIBLE);
                    appTheme.setVisibility(View.GONE);
                    quizEnd.setVisibility(View.GONE);
                } else{
                    playerScoreLL.setVisibility(View.VISIBLE);
                    appThemeLL.setVisibility(View.GONE);
                    quizEndLL.setVisibility(View.GONE);
                }
                quizLayouts.get(0).setVisibility(View.GONE);
                quizLayouts.get(1).setVisibility(View.GONE);
                quizLayouts.get(2).setVisibility(View.VISIBLE);
                quizLayouts.get(3).setVisibility(View.GONE);
                quizLayouts.get(4).setVisibility(View.GONE);
                quizLayouts.get(5).setVisibility(View.GONE);
                break;
            case 3:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    playerScore.setVisibility(View.VISIBLE);
                    appTheme.setVisibility(View.GONE);
                    quizEnd.setVisibility(View.GONE);
                } else{
                    playerScoreLL.setVisibility(View.VISIBLE);
                    appThemeLL.setVisibility(View.GONE);
                    quizEndLL.setVisibility(View.GONE);
                }
                quizLayouts.get(0).setVisibility(View.GONE);
                quizLayouts.get(1).setVisibility(View.GONE);
                quizLayouts.get(2).setVisibility(View.GONE);
                quizLayouts.get(3).setVisibility(View.VISIBLE);
                quizLayouts.get(4).setVisibility(View.GONE);
                quizLayouts.get(5).setVisibility(View.GONE);
                break;
            case 4:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    playerScore.setVisibility(View.VISIBLE);
                    appTheme.setVisibility(View.GONE);
                    quizEnd.setVisibility(View.GONE);
                } else{
                    playerScoreLL.setVisibility(View.VISIBLE);
                    appThemeLL.setVisibility(View.GONE);
                    quizEndLL.setVisibility(View.GONE);
                }
                quizLayouts.get(0).setVisibility(View.GONE);
                quizLayouts.get(1).setVisibility(View.GONE);
                quizLayouts.get(2).setVisibility(View.GONE);
                quizLayouts.get(3).setVisibility(View.GONE);
                quizLayouts.get(4).setVisibility(View.VISIBLE);
                quizLayouts.get(5).setVisibility(View.GONE);
                break;
            case 5:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    playerScore.setVisibility(View.GONE);
                    appTheme.setVisibility(View.GONE);
                    quizEnd.setVisibility(View.VISIBLE);
                } else{
                    playerScoreLL.setVisibility(View.GONE);
                    appThemeLL.setVisibility(View.GONE);
                    quizEndLL.setVisibility(View.VISIBLE);
                }
                quizLayouts.get(0).setVisibility(View.GONE);
                quizLayouts.get(1).setVisibility(View.GONE);
                quizLayouts.get(2).setVisibility(View.GONE);
                quizLayouts.get(3).setVisibility(View.GONE);
                quizLayouts.get(4).setVisibility(View.GONE);
                quizLayouts.get(5).setVisibility(View.VISIBLE);
                break;
        }
    }
}
