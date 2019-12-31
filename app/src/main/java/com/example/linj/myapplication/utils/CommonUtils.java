package com.example.linj.myapplication.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

	/**
	 * 网络返回登录信息
	 */
	public static final int networkSession = 1;
	/**
	 * @category TAKE_PICTURE 拍照显示的照片1
	 * @category CHOOSE_PICTURE 拍照显示的照片2
	 * @category SCALE 照片缩小比例
	 */
	public static final int TAKE_PICTURE = 0, CHOOSE_PICTURE = 1, SCALE = 8;

	/**
	 * @category FLX_SY 机器巡检-首页
	 * @category FLX_TG 机器巡检-通告
	 * @category FLX_MY 机器巡检-我的
	 */
	public static final int JQXJ_SY = 0, JQXJ_TG = 1, JQXJ_MY = 2;

	public static final String channelId = "CKRO_ANDROID", channelNo = "CKRO",
			channelPassword = "CKRO";

	public static final String CKHS_SMHS = "村口回收_上门回收";
	/**
	 * 发放机的选择的数组
	 */
	public static final int ONE = 1, TWO = 2, THREE = 3, FOUR = 4, FIVE = 5,
			SIX = 6, SEVEN = 7, EIGHT = 8, NINE = 9, TEN = 10, ELEVEN = 11,
			TWELVE = 12, THIRTEEN = 13, FOURTEEN = 14;

	/**
	 * 得到当时时间
	 */
	@SuppressLint("SimpleDateFormat")
	public static String initTime() {
		SimpleDateFormat sDateFormat_Complete = new SimpleDateFormat(
				"yyyy-MM-dd kk:mm:ss");
		return sDateFormat_Complete.format(new java.util.Date());
	}

	/**
	 * 得到当时时间年月日
	 */
	@SuppressLint("SimpleDateFormat")
	public static String Time_Day() {
		SimpleDateFormat sDateFormat_Complete = new SimpleDateFormat(
				"yyyy-MM-dd");
		return sDateFormat_Complete.format(new java.util.Date());
	}

	/**
	 * 得到当时时间年月日
	 */
	@SuppressLint("SimpleDateFormat")
	public static String Time() {
		SimpleDateFormat sDateFormat_Complete = new SimpleDateFormat("yyyyMMddhhmmss");
		return sDateFormat_Complete.format(new java.util.Date());
	}

	/**
	 * 设置字体样式 宋字体
	 */
	@SuppressLint("SimpleDateFormat")
	public static Typeface Typeface_Song(Context context) {
		Typeface fontFace = Typeface.createFromAsset(context.getAssets(),
				"fonts/song.ttf");
		return fontFace;
	}

	/**
	 * 设置字体样式 楷字体
	 */
	@SuppressLint("SimpleDateFormat")
	public static Typeface Typeface_Kai(Context context) {
		Typeface fontFace = Typeface.createFromAsset(context.getAssets(),
				"fonts/kai.ttf");
		return fontFace;
	}


	/**
	 * 压缩图片质量
	 *
	 * @param image
	 * @return
	 */
	public static Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//
		// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 500) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			options -= 10;// 每次都减少10
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	/**
	 * 图片的压缩
	 *
	 * @param image
	 * @return
	 */
	public static Bitmap comp(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		Options newOpts = new Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return bitmap;// 压缩好比例大小后再进行质量压缩
	}
	/**

	 * @param context
	 * @param mBitmap
	 *            bitmap图片
	 * @param bitName
	 *            图片名称
	 */
	public static String saveMyBitmap(Context context, Bitmap mBitmap, String bitName) {
		// String path = "/sdcard/CloudAnt/image";
		File destDir = new File(Environment.getExternalStorageDirectory() + "/photos/");
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		try {
			File f = new File(Environment.getExternalStorageDirectory() + "/photos/", bitName + ".jpg");
			if (f.exists()) {
				f.delete();
			}
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			int options = 100;
			mBitmap.compress(Bitmap.CompressFormat.JPEG, options, os);
			FileOutputStream out = new FileOutputStream(f);
			out.write(os.toByteArray());
			out.flush();
			out.close();
			return Environment.getExternalStorageDirectory() + "/photos/" + bitName + ".jpg";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 将异常Exception对象转换成字符串
	 *
	 * @param tr
	 *            异常
	 */
	public static String getStackTraceString(Throwable tr) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		tr.printStackTrace(printWriter);
		printWriter.close();
		return writer.toString();
	}

	/**
	 * 判断这个字符串是不是数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static Bitmap copressImage(String imgPath) {
		File picture = new File(imgPath);
		Options bitmapFactoryOptions = new Options();
		// 下面这个设置是将图片边界不可调节变为可调节
		bitmapFactoryOptions.inJustDecodeBounds = true;
		bitmapFactoryOptions.inSampleSize = 2;
		int outWidth = bitmapFactoryOptions.outWidth;
		int outHeight = bitmapFactoryOptions.outHeight;
		Bitmap bmap = BitmapFactory.decodeFile(picture.getAbsolutePath(),
				bitmapFactoryOptions);
		float imagew = 200;
		float imageh = 200;
		int yRatio = (int) Math.ceil(bitmapFactoryOptions.outHeight / imageh);
		int xRatio = (int) Math.ceil(bitmapFactoryOptions.outWidth / imagew);
		if (yRatio > 1 || xRatio > 1) {
			if (yRatio > xRatio) {
				bitmapFactoryOptions.inSampleSize = yRatio;
			} else {
				bitmapFactoryOptions.inSampleSize = xRatio;
			}

		}
		bitmapFactoryOptions.inJustDecodeBounds = false;
		bmap = BitmapFactory.decodeFile(picture.getAbsolutePath(),
				bitmapFactoryOptions);
		if (bmap != null) {
			// ivwCouponImage.setImageBitmap(bmap);
			return bmap;
		}
		return null;
	}

	public void showPicturePicker(Context context) {
		// File imgDir = new File(GI_The_Garbage_Collection.IMAGEPATH);
		// if (!imgDir.exists())
		// imgDir.mkdirs();
		// Intent intent = new Intent("android.media.action.IMAGE_CAPTURE"); //
		// // 调用系统相机
		// // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
		// // 拍照保存路径
		// photoPath = GI_The_Garbage_Collection.IMAGEPATH + "IMG.jpg";
		// SharedPreferences shared = this.getSharedPreferences("photopath",
		// Activity.MODE_PRIVATE);
		// android.content.SharedPreferences.Editor editor = shared.edit();
		// editor.putString("photopath", photoPath);
		// editor.commit();
		// File file = new File(photoPath);
		//
		// Uri imageUri = Uri.fromFile(file);// 获取图片的content Uri
		// intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		// // 直接使用，没有缩小
		// startActivityForResult(intent, TAKE_PICTURE); // 用户点击了从相机获取
		// File destDir = new File(Environment.getExternalStorageDirectory()
		// + The_server_Address.FILE_ADDRESS);
		// if (!destDir.exists()) {
		// destDir.mkdirs();
		// }
		//
		// Intent openCameraIntent = new
		// Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// // time_Complete = getCharacterAndNumber();
		// // time_Complete +
		//
		// Uri imageUri = Uri.fromFile(new File(Environment
		// .getExternalStorageDirectory()
		// + The_server_Address.FILE_ADDRESS + "/", "image.jpg"));
		// // saveMyBitmap(
		// // getApplicationContext(),
		// // compressImage(comp(BitmapFactory.decodeFile(Environment
		// // .getExternalStorageDirectory() + "/image.jpg"))),
		// // time_Complete);
		// openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		// startActivityForResult(openCameraIntent, TAKE_PICTURE);
		// Intent openCameraIntent = new
		// Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//
		// startActivityForResult(openCameraIntent, TAKE_PICTURE);
	}

	/**
	 * 判断输入的是不是手机号码
	 *
	 * @param str
	 * @return
	 */
	public static boolean isPhone(String str) {
		String regExp = "^1[345678]\\d{9}";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.find();// boolean
	}

}
