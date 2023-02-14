package com.example.titi.ui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.titi.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ques_makesi extends AppCompatActivity {
boolean wrongMode=false;
    TextView tv_title,tv_result;
    RadioButton[] mRadioButton=new RadioButton[4];
    Button btn_down, btn_up;
    RadioGroup mRadioGroup;  int corrent = 0;
int count;
    private Chronometer ch;
    private  int mP=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionjiemian1);
        new AlertDialog.Builder(this).setTitle("提示").setMessage("恭喜你全部回答正确！");

        ch = findViewById(R.id.chronometer);
        ch.setBase(SystemClock.elapsedRealtime());
        ch.setFormat("%s");
        ch.start();
        ch.setOnChronometerTickListener(e -> {
            //if (SystemClock.elapsedRealtime() - ch.getBase() >= 600000) ch.stop();//
        });
       initView();
       initDB();
}
    private void initView() {
        tv_title = (TextView) findViewById(R.id.textView2);

        mRadioButton[0] = (RadioButton) findViewById(R.id.radioButton);
        mRadioButton[1] = (RadioButton) findViewById(R.id.radioButton2);
        mRadioButton[2] = (RadioButton) findViewById(R.id.radioButton3);
        mRadioButton[3] = (RadioButton) findViewById(R.id.radioButton4);

        btn_down = (Button) findViewById(R.id.button2);
        btn_up = (Button) findViewById(R.id.button3);

        tv_result = (TextView) findViewById(R.id.textView3);
        tv_title.getPaint().setFakeBoldText(true);
        mRadioGroup = (RadioGroup) findViewById(R.id.radio);
}
    /**
     * 判断是否答题正确
     * @param list
     * @return
     */
    private List<Integer> checkAnswer(List<Question>list){
        List<Integer>wrongList= new ArrayList<>();
        for(int i = 0 ; i<list.size();i++){
            //判断对错
            if (list.get(i).answer != list.get(i).selectedAnswer){
                wrongList.add(i);
            }

        }
        return wrongList;
    }

    private void initDB() {
        DBService dbService = new DBService();
       final List<Question> list = dbService.getQuestion();

         count = list.size();


        Question q = list.get(0);
        tv_title.setText(q.question);

        mRadioButton[0].setText(q.answerA);
        mRadioButton[0].setOnClickListener(v->{

        });
        mRadioButton[1].setText(q.answerB);
        mRadioButton[2].setText(q.answerC);
        mRadioButton[3].setText(q.answerD);

        //上一题
        btn_up.setOnClickListener(v -> {
            if (corrent > 0) {
                corrent--;

                Question q1 = list.get(corrent);
                mRadioGroup.clearCheck();

                //设置选中
                if (q1.selectedAnswer != -1) {
                    mRadioButton[q1.selectedAnswer].setChecked(false);
                }
                tv_title.setText(q1.question);

                mRadioButton[0].setText(q1.answerA);
                mRadioButton[1].setText(q1.answerB);
                mRadioButton[2].setText(q1.answerC);
                mRadioButton[3].setText(q1.answerD);

                tv_result.setText(q1.explaination);


            }

        });

        //下一题
        btn_down.setOnClickListener(v -> {

            //判断是否为最后一题
            if (corrent < count - 1) {
                corrent++;
                Question q12 = list.get(corrent);

                tv_title.setText(q12.question);

                mRadioButton[0].setText(q12.answerA);
                mRadioButton[1].setText(q12.answerB);
                mRadioButton[2].setText(q12.answerC);
                mRadioButton[3].setText(q12.answerD);

                tv_result.setText(q12.explaination);
                mRadioGroup.check(-1);
                mRadioGroup.clearCheck();




                //设置选中
                if (q12.selectedAnswer != -1) {
                    mRadioButton[q12.selectedAnswer].setChecked(false);
                }
            } else {
                Toast.makeText(ques_makesi.this, "最后一题啦！", Toast.LENGTH_SHORT).show();
  if (corrent == count - 1 && wrongMode == true) {

                    new AlertDialog.Builder(this).setTitle("提示").setMessage("已经到达最后一道题，是否退出？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).setNegativeButton("取消",null).show();}

//没有题目了，开始检测正确性
              else   {final List<Integer> wrongList = checkAnswer(list);

            if(wrongList.size() == 0){
                new AlertDialog.Builder(this).setTitle("提示").setMessage("" +
                                "你好厉害，答对了所有题！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setNegativeButton("取消",null).show();
            }

            //窗口提示
            new AlertDialog.Builder(this).setTitle("恭喜，答题完成！")
                    .setMessage("答对了" + (list.size() - wrongList.size()) + "道题" + "\n"
                            + "答错了" + wrongList.size() + "道题" + "\n"
                            + "是否查看错题？").setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            wrongMode = true;
                            List<Question> newList = new ArrayList<Question>();
                            for (int i = 0; i < wrongList.size(); i++) {
                                newList.add(list.get(wrongList.get(i)));
                            }
                            list.clear();
                            for (int i = 0; i < newList.size(); i++) {
                                list.add(newList.get(i));
                            }
                            corrent = 0;
                            count = list.size();

                            //更新当前显示的内容
                            Question q = list.get(corrent);

                            tv_title.setText(q.question);

                            mRadioButton[0].setText(q.answerA);
                            mRadioButton[1].setText(q.answerB);
                            mRadioButton[2].setText(q.answerC);
                            mRadioButton[3].setText(q.answerD);

                            tv_result.setText(q.explaination);
                            //显示结果
                            tv_result.setVisibility(View.VISIBLE);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();}}
        });

        //答案选中
        mRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            for (int i = 0; i < 4; i++) {
                if (mRadioButton[i].isChecked()) {
                    list.get(corrent).selectedAnswer = i;
                    break;
                }
            }
        });




    }


}
