package guo.customnumaddsubview;

import android.content.Context;
import android.support.v7.widget.TintTypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by ${GuoZhaoHui} on 2017/5/8.
 * email:guozhaohui628@gmail.com
 */

public class NumAddSubView extends LinearLayout implements View.OnClickListener{

    private LayoutInflater layoutInflater;

    private Button btSub;

    private Button btAdd;

    private TextView tvShow;

    private int value;

    private int minVal;

    private int maxVal;

    private onButtonClickListener mListener;

    public int getValue() {

        String val = tvShow.getText().toString();
        if(!TextUtils.isEmpty(val)){
           this.value =  Integer.parseInt(val);
        }
        return value;
    }

    public void setValue(int value) {
        tvShow.setText(value+"");
        this.value = value;
    }

    public int getMinVal() {
        return minVal;
    }

    public void setMinVal(int minVal) {
        this.minVal = minVal;
    }

    public int getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(int maxVal) {
        this.maxVal = maxVal;
    }

    public NumAddSubView(Context context) {
        this(context,null);
    }

    public NumAddSubView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NumAddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        layoutInflater = LayoutInflater.from(context);
        initView();

        if(attrs!=null){
            TintTypedArray a = TintTypedArray.obtainStyledAttributes(context,attrs,R.styleable.NumAddSubView,defStyleAttr,0);

            int val = a.getInt(R.styleable.NumAddSubView_value, 0);
            setValue(val);

            int minVal = a.getInt(R.styleable.NumAddSubView_minVal, 0);
            setMinVal(minVal);

            int maxVal = a.getInt(R.styleable.NumAddSubView_maxVal, 0);
            setMaxVal(maxVal);

            a.recycle();
        }

    }


    public void initView(){

        View view = layoutInflater.inflate(R.layout.view_addsub, this, true);

        btSub = (Button) view.findViewById(R.id.bt_sub);
        btAdd = (Button) view.findViewById(R.id.bt_add);
        tvShow = (TextView) view.findViewById(R.id.tv_show);

        btSub.setOnClickListener(this);
        btAdd.setOnClickListener(this);
    }


    public void setOnButtonClickListener(onButtonClickListener listener){
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.bt_add){

            addNum();
            if(mListener!=null){
                mListener.onButtonAdd(v,value);
            }

        }else if(v.getId()==R.id.bt_sub){

            subNum();
            if(mListener!=null){
                mListener.onButtonSub(v,value);
            }

        }
    }


    void addNum(){

        if(value<maxVal){
            value++;
        }
        tvShow.setText(value+"");
    }

    void subNum(){

        if(value>minVal){
            value = value-1;
        }
        tvShow.setText(value+"");
    }

    public interface onButtonClickListener{

        void onButtonAdd(View view,int value);

        void onButtonSub(View view,int valus);
    }

}
