package net.sinoace.ui.lancher3d;

import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 
 * ��Launcher�е�WorkSapce���������һ����л���Ļ����
 * 
 * @author Yao.GUET
 * 
 *         blog: http://blog.csdn.net/Yao_GUET
 * 
 *         date: 2011-05-04
 */

public class Launcher3dScrollLayout extends ViewGroup {
	// ��ǰ����Ļ��ͼ
	private int mCurScreen = 1;
	// �������ٶ�
	private static final int SNAP_VELOCITY = 600;

	private static final String TAG = "ScrollLayout";
	// ���¼���״̬
	private static final int TOUCH_STATE_REST = 0;
	// �����϶���״̬
	private static final int TOUCH_STATE_SCROLLING = 1;

	private Launcher3dMainActivity mContext;

	private float mLastMotionX;
	// ���ڻ�������
	private Scroller mScroller;

	private int mTouchSlop;

	private int mTouchState = TOUCH_STATE_REST;
	// �������ٴ����ٶȵ���
	private VelocityTracker mVelocityTracker;

	private int mWidth;

	// ������������Ч�����
	private Camera mCamera;
	private Matrix mMatrix;
	// ��ת�ĽǶȣ����Խ����޸����۲�Ч��
	private float angle = 90;

	public Launcher3dScrollLayout(Context context, AttributeSet attrs) {

		this(context, attrs, 0);

	}

	// �ڹ������г�ʼ��
	public Launcher3dScrollLayout(Context context, AttributeSet attrs,
			int defStyle) {

		super(context, attrs, defStyle);

		mContext = (Launcher3dMainActivity) context;

		mScroller = new Scroller(context);

		mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

		mCamera = new Camera();
		mMatrix = new Matrix();

	}

	 
	public void addView(View child) {

		super.addView(child);
	}

	 
	protected void attachViewToParent(View child, int index, LayoutParams params) {

		super.attachViewToParent(child, index, params);
	}

	 
	public void computeScroll() {

		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}

	}

	/*
	 * ������View����ʱ���ᵼ�µ�ǰ��View��Ч���ú���������Ƕ�View�������»���
	 * ����drawScreen����
	 */
	 
	protected void dispatchDraw(Canvas canvas) {
		// super.dispatchDraw(canvas);
		final long drawingTime = getDrawingTime();
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			drawScreen(canvas, i, drawingTime);
		}
	}

	/*
	 * ����ѭ�� ����Ч���ʵ�ֺ��� ,screenΪ��һ����View
	 */
	public void drawScreen(Canvas canvas, int screen, long drawingTime) {
		// �õ���ǰ��View�Ŀ��
		final int width = getWidth();
		final int scrollWidth = screen * width;
		System.out.println("scrollWidth--->" + scrollWidth);
		final int scrollX = this.getScrollX();
		System.out.println("scrollX---->" + scrollX);
		// ƫ���������ʱ��ֱ��
		if (scrollWidth > scrollX + width || scrollWidth + width < scrollX) {
			return;
		}
		final View child = getChildAt(screen);
		final int faceIndex = screen;
		final float currentDegree = getScrollX() * (angle / getMeasuredWidth());
		System.out.println("getMeasuredWidth---->" + getMeasuredWidth()
				+ "getScrollX()----->" + getScrollX() + "currentDegree--->"
				+ currentDegree);
		final float faceDegree = currentDegree - faceIndex * angle;
		System.out.println("faceDegree--->" + faceDegree);
		if (faceDegree > 90 || faceDegree < -90) {
			return;
		}
		final float centerX = (scrollWidth < scrollX) ? scrollWidth + width
				: scrollWidth;
		final float centerY = getHeight() / 2;
		final Camera camera = mCamera;
		final Matrix matrix = mMatrix;
		canvas.save();
		camera.save();
		camera.rotateY(-faceDegree);
		camera.getMatrix(matrix);
		camera.restore();
		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);
		canvas.concat(matrix);
		drawChild(canvas, child, drawingTime);
		canvas.restore();
	}

	 
	public void dispatchWindowFocusChanged(boolean hasFocus) {

		Log.d("Windows",
				"dispatchWindowFocusChanged -- >"
						+ getChildAt(mCurScreen).toString());
		super.dispatchWindowFocusChanged(hasFocus);
	}

	 
	public void dispatchWindowVisibilityChanged(int visibility) {
		Log.d("Windows",
				"dispatchWindowVisibilityChanged -- >"
						+ getChildAt(mCurScreen).toString());

		super.dispatchWindowVisibilityChanged(visibility);
	}

	public View getCurScreen() {

		return this.getChildAt(mCurScreen);

	}

	 
	protected void onAttachedToWindow() {

		Log.d("Windows", "onAttachedToWindow -- >"
				+ getChildAt(mCurScreen).toString());

		startCurrentView();
		super.onAttachedToWindow();
	}

	 
	protected void onDetachedFromWindow() {

		Log.d("Windows", "onDetachedFromWindow -- >"
				+ getChildAt(mCurScreen).toString());

		super.onDetachedFromWindow();
	}

	 
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		Log.d(TAG, "onInterceptTouchEvent-slop:" + mTouchSlop);

		final int action = ev.getAction();
		if ((action == MotionEvent.ACTION_MOVE)
				&& (mTouchState != TOUCH_STATE_REST)) {
			return true;
		}
		final float x = ev.getX();
		switch (action) {
		case MotionEvent.ACTION_MOVE:
			final int xDiff = (int) Math.abs(mLastMotionX - x);
			if (xDiff > mTouchSlop) {
				mTouchState = TOUCH_STATE_SCROLLING;
			}
			break;

		case MotionEvent.ACTION_DOWN:
			mLastMotionX = x;
			mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST
					: TOUCH_STATE_SCROLLING;
			break;

		case MotionEvent.ACTION_CANCEL:

		case MotionEvent.ACTION_UP:
			mTouchState = TOUCH_STATE_REST;
			break;

		}

		return mTouchState != TOUCH_STATE_REST;

	}

	/*
	 * 
	 * Ϊ��Viewָ��λ��
	 */
	 
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		int childLeft = 0;
		final int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View childView = getChildAt(i);
			if (childView.getVisibility() != View.GONE) {
				final int childWidth = childView.getMeasuredWidth();
				childView.layout(childLeft, 0, childLeft + childWidth,
						childView.getMeasuredHeight());
				childLeft += childWidth;
			}

		}
	}

	// ��д�˷�����������߶ȺͿ��
	 
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		final int width = MeasureSpec.getSize(widthMeasureSpec);
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		Log.e(TAG, "onMeasure width = " + width);
		// Exactly��width�����Ǿ�ȷ�ĳߴ�
		// AT_MOST��width���������ɻ�õĿռ�
		if (widthMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException(
					"ScrollLayout only canmCurScreen run at EXACTLY mode!");
		}

		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (heightMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException(
					"ScrollLayout only can run at EXACTLY mode!");
		}

		// The children are given the same width and height as the scrollLayout
		// �õ�����ҳ(��View)���������ǵĿ�͸�
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
		}

		// Log.e(TAG, "moving to screen "+mCurScreen);

		scrollTo(mCurScreen * width, 0);

	}

	 
	public boolean onTouchEvent(MotionEvent event) {

		if (mVelocityTracker == null) {
			// ʹ��obtain�����õ�VelocityTracker��һ������
			mVelocityTracker = VelocityTracker.obtain();
		}
		// ����ǰ�Ĵ����¼����ݸ�VelocityTracker����
		mVelocityTracker.addMovement(event);
		// �õ������¼�������
		final int action = event.getAction();
		final float x = event.getX();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "event down!");
			if (!mScroller.isFinished()) {
				mScroller.abortAnimation();
			}
			mLastMotionX = x;
			break;

		case MotionEvent.ACTION_MOVE:

			int deltaX = (int) (mLastMotionX - x);

			mLastMotionX = x;

			scrollBy(deltaX, 0);

			break;

		case MotionEvent.ACTION_UP:

			// if (mTouchState == TOUCH_STATE_SCROLLING) {

			final VelocityTracker velocityTracker = mVelocityTracker;
			// ���㵱ǰ���ٶ�
			velocityTracker.computeCurrentVelocity(1000);
			// ��õ�ǰ���ٶ�
			int velocityX = (int) velocityTracker.getXVelocity();
			Log.d(TAG, "velocityX:" + velocityX + "; event : up");
			if (velocityX > SNAP_VELOCITY && mCurScreen > 0) {
				// Fling enough to move left
				Log.d(TAG, "snap left");
				snapToScreen(mCurScreen - 1);
			} else if (velocityX < -SNAP_VELOCITY
					&& mCurScreen < getChildCount() - 1) {
				// Fling enough to move right
				Log.d(TAG, "snap right");
				snapToScreen(mCurScreen + 1);
			} else {
				snapToDestination();
			}
			if (mVelocityTracker != null) {
				mVelocityTracker.recycle();
				mVelocityTracker = null;
			}
			mTouchState = TOUCH_STATE_REST;
			break;
		case MotionEvent.ACTION_CANCEL:
			mTouchState = TOUCH_STATE_REST;
			break;
		}

		return true;

	}

	 
	public void onWindowFocusChanged(boolean hasWindowFocus) {

		Log.d("Windows", "onWindowFocusChanged -- >"
				+ getChildAt(mCurScreen).toString());

		super.onWindowFocusChanged(hasWindowFocus);
	}

	 
	protected void onWindowVisibilityChanged(int visibility) {

		Log.d("Windows",
				"onWindowVisibilityChanged -- >"
						+ getChildAt(mCurScreen).toString());

		super.onWindowVisibilityChanged(visibility);
	}

	 
	public void requestChildFocus(View child, View focused) {

		Log.d("requestChildFocus", "child = " + child);

		super.requestChildFocus(child, focused);
	}

	private void setMWidth() {
		if (mWidth == 0) {
			mWidth = getWidth();
		}
	}

	private void setNext() {
		int count = this.getChildCount();
		View view = getChildAt(count - 1);
		removeViewAt(count - 1);
		addView(view, 0);
	}

	private void setPre() {
		int count = this.getChildCount();
		View view = getChildAt(0);
		removeViewAt(0);
		addView(view, count - 1);
	}

	public void setToScreen(int whichScreen) {
		whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
		scrollTo(whichScreen * mWidth, 0);
		if (whichScreen > mCurScreen) {
			setPre();
		} else if (whichScreen < mCurScreen) {
			setNext();
		}

	}

	/**
	 * 
	 * According to the position of current layout
	 * 
	 * scroll to the destination page.
	 */

	public void snapToDestination() {
		setMWidth();
		// ���View�Ŀ���Լ�������ֵ���ж����ĸ�View
		final int destScreen = (getScrollX() + mWidth / 2) / mWidth;
		snapToScreen(destScreen);

	}

	public void snapToScreen(int whichScreen) {
		// get the valid layout page
		whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
		setMWidth();
		int scrollX = getScrollX();
		int startWidth = whichScreen * mWidth;

		if (scrollX != startWidth) {

			int delta = 0;
			int startX = 0;

			if (whichScreen > mCurScreen) {
				setPre();
				delta = startWidth - scrollX;
				startX = mWidth - startWidth + scrollX;

			} else if (whichScreen < mCurScreen) {
				setNext();
				delta = -scrollX;
				startX = scrollX + mWidth;
			} else {
				startX = scrollX;
				delta = startWidth - scrollX;

			}

			mScroller.startScroll(startX, 0, delta, 0, Math.abs(delta) * 2);

			invalidate(); // Redraw the layout

		}

		startCurrentView();

	}

	private void startCurrentView() {

		String viewTag = (String) getChildAt(mCurScreen).getTag();

		Message message = new Message();

		if (TextUtils.equals(viewTag, Launcher3dMainActivity.FIRST_INTENT_TAG)) {
			mContext.mLocalActivityManager.startActivity(
					Launcher3dMainActivity.FIRST_INTENT_TAG, new Intent(
							mContext, Launcher3dFirstActivity.class));
			message.what = Launcher3dMainActivity.FIRST_VIEW;
		} else if (TextUtils.equals(viewTag,
				Launcher3dMainActivity.SECOND_INTENT_TAG)) {
			mContext.mLocalActivityManager.startActivity(
					Launcher3dMainActivity.SECOND_INTENT_TAG, new Intent(
							mContext, Launcher3dSecondActivity.class));
			message.what = Launcher3dMainActivity.SECOND_VIEW;
		} else {
			mContext.mLocalActivityManager.startActivity(
					Launcher3dMainActivity.THIRD_INTENT_TAG, new Intent(
							mContext, Launcher3dThirdActivity.class));
			message.what = Launcher3dMainActivity.THIRD_VIEW;
		}

		mContext.mHandler.sendMessage(message);
	}

}
