package com.test.cmplayerdemo.view.widget;

public class MultiLineLabelLayout/* extends FrameLayout*/
{
  /*private int firstRowTopMargin = -1;
  private int maxLine = 1000;
  public int rightMargin;
  private List<RowInfo> rowList;

  public MultiLineLabelLayout(Context paramContext)
  {
    super(paramContext);
  }

  public MultiLineLabelLayout(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.maxLine = paramInt;
  }

  public MultiLineLabelLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public MultiLineLabelLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private FrameLayout.LayoutParams getChildParams(View view)
  {
    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
    if ((layoutParams != null) && ((layoutParams instanceof FrameLayout.LayoutParams)))
      return (FrameLayout.LayoutParams)layoutParams;
    return new FrameLayout.LayoutParams(view.getWidth(), view.getHeight());
  }

  private int resizeHeight(int height)
  {
    int i = 0;
    this.rowList = new ArrayList();
    RowInfo localRowInfo1 = new RowInfo();
    this.rowList.add(localRowInfo1);
    int j = 0;
    RowInfo localRowInfo2 = localRowInfo1;
    int k = 0;
    int childWidth;
    int childHeight;
    label143: FrameLayout.LayoutParams localLayoutParams1;
    int i3;
    if (j < getChildCount())
    {
      View childView = getChildAt(j);
      childWidth = childView.getMeasuredWidth();
      childHeight = childView.getMeasuredHeight();
      ViewGroup.LayoutParams layoutParams = childView.getLayoutParams();
      if ((layoutParams != null) && ((layoutParams instanceof FrameLayout.LayoutParams)))
        childWidth += this.rightMargin;
      if (childWidth > height)
        childWidth = height;
      k += childWidth;
      if (k > height)
      {
        if (k - this.rightMargin <= height)
          k -= this.rightMargin;
      }
      else
      {
        if ((layoutParams == null) || (!(layoutParams instanceof FrameLayout.LayoutParams)))
          break label339;
        localLayoutParams1 = (FrameLayout.LayoutParams)layoutParams;
        if ((getFirstRowTopMargin() == -1) || (this.rowList.size() != 1))
          break label262;
        i3 = childHeight + getFirstRowTopMargin() + localLayoutParams1.bottomMargin;
      }
    }
    else
    {
      if (i3 > localRowInfo2.maxMeasuredHeight)
        localRowInfo2.maxMeasuredHeight = i3;
      localRowInfo2.viewCount = (1 + localRowInfo2.viewCount);
      j++;
      break;
      localRowInfo2 = new RowInfo();
      this.rowList.add(localRowInfo2);
      k = childWidth;
      break label143;
      label262: i3 = childHeight + localLayoutParams1.topMargin + localLayoutParams1.bottomMargin;
      continue;
      int n;
      for (int m = 0; (i < this.rowList.size()) && (i < this.maxLine); m = n)
      {
        n = m + ((RowInfo)this.rowList.get(i)).maxMeasuredHeight;
        i++;
      }
      return m;
      label339: i3 = childHeight;
    }
  }

  public int getFirstRowTopMargin()
  {
    return this.firstRowTopMargin;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (getChildCount() <= 0)
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    int i = getMeasuredWidth();
    int j = 0;
    RowInfo localRowInfo1 = (RowInfo)this.rowList.get(0);
    int k = 0;
    int m = 0;
    Object localObject1 = localRowInfo1;
    int n = 0;
    label56: int i1;
    View childView;
    FrameLayout.LayoutParams layoutParams;
    int childHeight;
    int i4;
    int i5;
    int i9;
    int i6;
    Object localObject2;
    int i7;
    int i8;
    if (k < getChildCount())
    {
      i1 = this.rightMargin;
      childView = getChildAt(k);
      layoutParams = getChildParams(childView);
      int childWidth = childView.getMeasuredWidth();
      childHeight = childView.getMeasuredHeight();
      i4 = childWidth + this.rightMargin;
      if (i4 > i)
        i4 = i;
      i5 = n + i4;
      if (i5 <= i)
        break label338;
      if (i5 - this.rightMargin > i)
        break label272;
      int i15 = i5 - this.rightMargin;
      int i16 = i4 - this.rightMargin;
      i9 = 0;
      i4 = i16;
      i6 = m;
      localObject2 = localObject1;
      i7 = j;
      i8 = i15;
    }
    else
    {
      label188: int i10 = i8 - i4;
      int i11 = i8 - i9;
      if ((getFirstRowTopMargin() != -1) && (i6 == 0));
      for (int i12 = getFirstRowTopMargin(); ; i12 = i7 + layoutParams.topMargin)
      {
        int i13 = i12 + childHeight;
        if (i6 >= this.maxLine)
          break;
        childView.layout(i10, i12, i11, i13);
        k++;
        n = i8;
        j = i7;
        localObject1 = localObject2;
        m = i6;
        break label56;
        break;
        label272: int i14 = m + 1;
        i7 = j + ((RowInfo)localObject1).maxMeasuredHeight;
        RowInfo localRowInfo2 = (RowInfo)this.rowList.get(i14);
        i8 = i4 + 0;
        i6 = i14;
        localObject2 = localRowInfo2;
        i9 = i1;
        break label188;
      }
      label338: i6 = m;
      localObject2 = localObject1;
      i7 = j;
      i8 = i5;
      i9 = i1;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (getChildCount() <= 0)
      return;
    int measuredWidth = getMeasuredWidth();
    setMeasuredDimension(measuredWidth, resizeHeight(measuredWidth));
  }

  public void setFirstRowTopMargin(int paramInt)
  {
    this.firstRowTopMargin = paramInt;
  }*/
}