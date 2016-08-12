# QuickIndex
## 算出每个字母格子（Cell）的高度，再在里面DrawText
* Paint
```Java
mPaint.setAntiAlias(true);//抗锯齿
mPaint.setTypeface(Typeface.DEFAULT_BOLD);//粗体
```

* Draw
```Java
 //计算坐标
        for (int i = 0; i < letters.length; i++) {
            String text = letters[i];
            int x = (int) (cellWidth / 2.0f - mPaint.measureText(text) /     2.0f);//画笔可以测量text的长度
            Rect bounds = new Rect();
            mPaint.getTextBounds(text, 0, text.length(), bounds);//填充bounds
            int textHeight = bounds.height();
            int y = (int) (cellHeight / 2.0f + textHeight / 2.0f + i * cellHeight);
            mPaint.setColor(touchIndex==i?Color.GREEN:Color.WHITE);
            canvas.drawText(text, x, y, mPaint);//x，y分别为字母左下角距离单个cell的左上角的距离
        }
```
> 1. 这里需要注意，DrawText是通过填充一个Rect矩形，再得到Text的高度
> 2. Paint可以测量Text的长度
> 2. 并且Text是从左下角开始Draw，而非左上角

## 剩下的都是辣鸡了
