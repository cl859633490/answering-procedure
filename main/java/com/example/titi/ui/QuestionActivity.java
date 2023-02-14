package com.example.titi.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

   /* setContentView(R.layout.exam);

    questionView= (TextView) this.findViewById(R.id.ques_question);

    quesExplainView= (TextView) this.findViewById(R.id.ques_explain);

    radioButtons= new RadioButton[4];

    radioButtons[0] =(RadioButton) findViewById(R.id.ques_an1);

    radioButtons[1] =(RadioButton) findViewById(R.id.ques_an2);

    radioButtons[2] =(RadioButton) findViewById(R.id.ques_an3);

    radioButtons[3] =(RadioButton) findViewById(R.id.ques_an4);

    btn_next=(Button) findViewById(R.id.btn_next);

    btn_previous=(Button) findViewById(R.id.btn_previous);

    radioGroup=(RadioGroup) findViewById(R.id.ques_radiogroup);

    quesList= newDBService().getQuestions();

    currQuesIdx= 0;

    Question currQues=quesList.get(currQuesIdx);

    setQuestionToGadioGroup(currQues);

    addListener();

}private void setQuestionToGadioGroup(Question ques) {

    radioButtons[0].setText(ques.getAnswerA());

    radioButtons[1].setText(ques.getAnswerB());

    radioButtons[2].setText(ques.getAnswerC());

    radioButtons[3].setText(ques.getAnswerD());

    questionView.setText(ques.getQuestion());

    quesExplainView.setText(ques.getExplain());

}private void addListener() {//下一题

    btn_next.setOnClickListener(newView.OnClickListener() {

        @Overridepublic voidonClick(View v) {

            Log.v("info", "btn_next,currQuesIdx:" +currQuesIdx);if (currQuesIdx < quesList.size() - 1) {

                currQuesIdx++;

                Question ques=quesList.get(currQuesIdx);

                setQuestionToGadioGroup(ques);

                radioGroup.clearCheck();if (ques.getSelectedAnswer() != -1) {

                    radioButtons[ques.getSelectedAnswer()].setChecked(true);

                }

            }else if (currQuesIdx == quesList.size() - 1) { //最后一题

                final List wrongList =checkAnswer();if (wrongList.size() < 1) {new AlertDialog.Builder(ExamActivity.this).setTitle("提示").setMessage("恭喜你全部回答正确！")

                        .setPositiveButton("确定", newDialogInterface.OnClickListener() {

                    @Overridepublic void onClick(DialogInterface dialog, intwhich) {

                        ExamActivity.this.finish();

                    }

                }).show();

                }else if (currQuesIdx == quesList.size() - 1 && wrongMode == true) {new AlertDialog.Builder(ExamActivity.this).setTitle("提示").setMessage("已经到达最后一题，是否退出？")

                        .setPositiveButton("确定", newDialogInterface.OnClickListener() {

                    @Overridepublic void onClick(DialogInterface dialog, intwhich) {

                        ExamActivity.this.finish();

                    }

                }).setNegativeButton("取消", null).show();

                }else{new AlertDialog.Builder(ExamActivity.this)

                        .setTitle("提示").setMessage("您答对了" + (quesList.size() - wrongList.size()) + "道题目，答错了"

                                + wrongList.size() + "道题目。是否查看错题？")

                        .setPositiveButton("确定", newDialogInterface.OnClickListener() {

                    @Overridepublic void onClick(DialogInterface dialog, intwhich) {

                        wrongMode= true;

                        List newList = new ArrayList();for (int i = 0; i < wrongList.size(); i++) {

                            newList.add(quesList.get(wrongList.get(i)));

                        }

                        quesList.clear();for (int i = 0; i < newList.size(); i++) {

                            quesList.add(newList.get(i));

                        }

                        currQuesIdx= 0;

                        Question ques=quesList.get(currQuesIdx);

                        setQuestionToGadioGroup(ques);

                        quesExplainView.setVisibility(View.VISIBLE);

                    }

                }).setNegativeButton("取消", newDialogInterface.OnClickListener() {

                    @Overridepublic void onClick(DialogInterface dialog, intwhich) {

                        ExamActivity.this.finish();

                    }

                }).show();

                }

            }

        }

    });//上一题

    btn_previous.setOnClickListener(newView.OnClickListener() {

        @Overridepublic voidonClick(View v) {

            Log.v("info", "btn_previous,currQuesIdx:" +currQuesIdx);if (currQuesIdx > 0) {

                currQuesIdx--;

                Question ques=quesList.get(currQuesIdx);

                setQuestionToGadioGroup(ques);

                radioGroup.clearCheck();if (ques.selectedAnswer != -1) {

                    radioButtons[ques.selectedAnswer].setChecked(true);

                }

            }

        }

    });

    radioGroup.setOnCheckedChangeListener(newRadioGroup.OnCheckedChangeListener() {

        @Overridepublic void onCheckedChanged(RadioGroup group, intcheckedId) {for (int i = 0; i < 4; i++) {if(radioButtons[i].isChecked()) {

            quesList.get(currQuesIdx).setSelectedAnswer(i);

        }

        }

        }

    });

}/*** 判断是否答对了

 *

private ListcheckAnswer() {

    List wrongList = new ArrayList();for (int i = 0; i < quesList.size(); i++) {if (quesList.get(i).answer !=quesList.get(i).selectedAnswer) {

        wrongList.add(i);

    }

    }return wrongList;

}
 *@paramlist

 *@return

 */

    }
}
