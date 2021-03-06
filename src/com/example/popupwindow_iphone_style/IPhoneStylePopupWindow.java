package com.example.popupwindow_iphone_style;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

/*
版权所有：版权所有(C)2013，固派软件
文件名称：com.goopai.shenma.ljf.pic_photo_popup_window.java
系统编号：
系统名称：Shenma
模块编号：
模块名称：
设计文档：
创建日期：2013-12-19 上午12:03:26
作 者：陆键霏
内容摘要：
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class IPhoneStylePopupWindow extends PopupWindow {

	private View mMenuView;
	private LinearLayout sheetView; 
	private Context context;
	private int marginBottom = 30;
	private int padding = 20;
	private int _textSize = 20;
	private int _titleTextColor = Color.WHITE;
	TextView txt_title;
	OnItemClickListener itemClickListener = null;
	
	public void addButton(String title,OnItemClickListener listener){
		addButton(title,listener,0,0);
	}
	public void addButton(String title,final OnItemClickListener listener,int shapeResId,int textColorResId
			){
		addButton(title,listener,shapeResId,textColorResId,0);
	}
	public void addButton(String title,final OnItemClickListener listener,int shapeResId,int textColorResId,
			int textSize){
		Button bt = new Button(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		bt.setLayoutParams(params);
		bt.setText(title);
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IPhoneStylePopupWindow.this.dismiss();
				if(listener!=null){
					listener.itemClick(v);
				}
			}
		});
		if(shapeResId != 0){
			bt.setBackgroundResource(shapeResId);
		}
		else{
			bt.setBackgroundResource(R.drawable.popup_window_btn1_style);
		}
		if(textColorResId !=0){
			ColorStateList cs1 = (ColorStateList)context.getResources().getColorStateList(textColorResId);
			bt.setTextColor(cs1);
		}else{
			ColorStateList cs1 = (ColorStateList)context.getResources().getColorStateList(R.color.popup_window_text1_style);
			bt.setTextColor(cs1);
		}
		if(textSize !=0 ){
			bt.setTextSize(textSize);
		}else{
			bt.setTextSize(this._textSize);
		}
		params.setMargins(0, 0, 0,marginBottom);
		sheetView.addView(bt);
	}
	void addTitle(){
		txt_title = new TextView(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		
		txt_title.setGravity(Gravity.CENTER_HORIZONTAL);
		params.setMargins(0,0 , 0, marginBottom);
		txt_title.setLayoutParams(params);
		txt_title.setText("");
		txt_title.setTextColor(_titleTextColor);
		txt_title.setTextSize(_textSize);
		txt_title.setVisibility(View.GONE);
		sheetView.addView(txt_title);
	}
	public void setTitle(String title){
		setTitle(title,0);
	}
	public void setTitle(String title,int color){
		txt_title.setText(title);
		if(color==0){
			txt_title.setTextColor(_titleTextColor);
		}else{
			txt_title.setTextColor(color);
		}
		txt_title.setVisibility(View.VISIBLE);
	}
	public IPhoneStylePopupWindow(Activity context) {
		super(context);
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.ljf_iphone_style_popup_window,
				null);
		
		sheetView = (LinearLayout)mMenuView.findViewById(R.id.sheetView);
		sheetView.setPadding(padding, padding, padding, padding);
		// 设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.FILL_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		// this.setAnimationStyle(R.style.AnimBottom);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		mMenuView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int height = mMenuView.findViewById(R.id.sheetView).getTop();
				int y = (int) event.getY();
				log(String.format("height=%s y=%s", height, y));
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});
		addTitle();
	}
	public interface OnItemClickListener{
		void itemClick(View v);
	}
	void log(String msg) {
		Log.d(getClass().getSimpleName(), msg);
	}
}

