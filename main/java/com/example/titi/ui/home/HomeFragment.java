package com.example.titi.ui.home;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.titi.R;
import com.example.titi.databinding.FragmentHomeBinding;
import com.example.titi.ui.notifications.NotificationsViewModel;
import com.example.titi.ui.notifications.setting;
import com.example.titi.ui.ques_makesi;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment<AlbumInfo> extends Fragment  {

    private FragmentHomeBinding binding;
    private List<AlbumInfo> mData = new ArrayList<>();
    /// 储存原始的总数据
    private List<AlbumInfo> search_mData = new ArrayList<>();
    private String[] str = { "中国近代史纲要", "思想道德与法制", "马克思主义", "中国特色社会主义概论体系", "雅思真题", "   ",
            "  ", "  ", "  ", "  ", "   ", "  ", "   " };
    private ListView lv;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel HomeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();









        SearchView searchView = root.findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(true);
        searchView.setSaveEnabled(true);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        searchView.setQueryHint("  \t 输入搜索内容 ");
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
              //  Toast.makeText(getActivity(), "提交内容-" + query, Toast.LENGTH_SHORT).show();//

                Intent intent = new Intent();
                intent.setClass(getActivity(), ques_makesi.class);
                startActivity(intent);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
            //    Toast.makeText(getActivity(), "输入变化-" + newText, Toast.LENGTH_SHORT).show();//
                // 如果query不为零的字符串
                if (TextUtils.isEmpty(newText)) {
// 清除listview的过滤
                    lv.clearTextFilter();
                } else {
// 使用listview输入文本进行过滤
                    lv.setFilterText(newText);
                    lv.dispatchDisplayHint(View.INVISIBLE);
                }
                str[0]=newText;

                return false;
            }
        });




        lv = root.findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, str));
        lv.setTextFilterEnabled(true);
        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                }
                return false;
            }
        });

        lv.setOnItemClickListener(new ItemClickEvent());
        return root;
    }


    //继承OnItemClickListener，当子项目被点击的时候触发
     final class ItemClickEvent implements AdapterView.OnItemClickListener {
        @Override
        //这里需要注意的是第三个参数arg2，这是代表单击第几个选项
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {


            //通过单击事件，获得单击选项的内容
            String text = lv.getItemAtPosition(arg2)+"";

                Intent intent1 = new Intent();
                intent1.setClass(getActivity(), ques_makesi.class);
                startActivity(intent1);

            //通过吐丝对象显示出来。
            Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
        }
    }







    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public boolean onMenuItemActionExpand(MenuItem item) {
        Toast. makeText (getActivity() , "案秀云Expand!" , Toast. LENGTH_SHORT ).show();
        return true ;
    }
    public void onDraw(Canvas canvas){

        Paint paint=new Paint();
        paint.setColor(0xFFFF00);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(10,10,100,100,paint);
    }

}