package com.wl.mylibrary.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.wl.mylibrary.R;


/**
 * 带清除按钮的EditText
 * @author ymx
 */
public class ClearEditText extends EditText {

	private Drawable leftSearchIcon;
	private Drawable mClearDrawable;

	public ClearEditText(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);

		//获取第一个控件，通过下标获取
		leftSearchIcon = getCompoundDrawables()[0];
		mClearDrawable = getCompoundDrawables()[2];
		if (mClearDrawable == null) {
			mClearDrawable = getResources().getDrawable(R.drawable.core_delete_selector);
		}
		//指定绑定矩形的绘制。可将其draw()时调用方法。
		mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
	}

	/**
	 * 处理触摸事件分发
	 * @param event
	 * @return
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if ((mClearDrawable != null) && (event.getAction() == MotionEvent.ACTION_UP)) {
			float f1 = event.getX();
			float f2 = getWidth() - 80;
			if (f1 > f2)
				setText(null);
		}
		return super.dispatchTouchEvent(event);
	}

	/**
	 * 设置视图显示
	 * @return
	 */
	@Override
	public boolean onPreDraw() {
		if (TextUtils.isEmpty(getText())) {
			setCompoundDrawables(leftSearchIcon, null, null, null);
		} else {
			setCompoundDrawables(leftSearchIcon, null, mClearDrawable, null);
		}
		if (TextUtils.isEmpty(getText())) {
			setCompoundDrawables(null, null, null, null);
		} else {
			setCompoundDrawables(null, null, mClearDrawable, null);
		}
		return true;
	}

}