# LoadingImagevIew
* 通过ValueAnimator实现图片上下浮动
* 在第一次加载布局文件时，会调用两次onLayout方法，对ImageView进行布局，确定位置，并不是setTop调用引起onLayout方法调用
* setTop方法设置值以后，不会引起onLayout方法的调用
