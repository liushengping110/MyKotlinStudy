package wizorle.mykotlin.util

import android.annotation.TargetApi
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.util.Util
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.CropCircleTransformation
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.util.concurrent.ExecutionException

/**
 * Created by 何人执笔？ on 2018/6/14.
 * liushengping
 */
object ImageUtil{

    fun isDestroy(activity:Activity):Boolean{
        if (activity == null || activity.isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed())) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * 加载网络图片
     */
    fun loadUrlImage(context: Activity, url:String, imageView:ImageView, bitmap:Int){
        if(isDestroy(context)){
            Glide.with(context)
                    .load(url)
                    .dontAnimate()
                    .centerCrop()
                    .placeholder(bitmap)
                    .into(imageView);
        }
    }

    /**
     * 加载绝对路径图片
     */
    fun loadPathImage(context:Activity,path:String,imageView:ImageView,bitmap:Int){
        if(isDestroy(context)){
            Glide.with(context)                             //配置上下文
                    .load(Uri.fromFile(File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                    .error(bitmap)           //设置错误图片
                    .placeholder(bitmap)     //设置占位图片
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .into(imageView)
        }
    }

    /**
     * 加载本地资源图片
     */
    fun loadDrawableImage(context:Activity,rid:Int,imageView:ImageView,bitmap:Int){
        if(isDestroy(context)){
            Glide.with(context)
                    .load(rid)
                    .dontAnimate()
                    .centerCrop()
                    .placeholder(bitmap)
                    .into(imageView)
        }
    }

    /**
     * 加载网络图片
     * 圆形显示
     */
    fun loadUrlRoundImage(context:Activity,url:String,imageView:ImageView,bitmap:Int){
        if(isDestroy(context)){
            Glide.with(context)
                    .load(url)
                    .placeholder(bitmap)
                    .bitmapTransform(CropCircleTransformation(context))
                    .crossFade(100)
                    .into(imageView)
        }
    }

    /**
     * 加载本地资源图片
     * 圆形显示
     */
    fun loadDrawableRoundImage(context:Activity,rid:Int,imageView:ImageView,bitmap:Int){
        if(isDestroy(context)){
            Glide.with(context)
                    .load(rid)
                    .placeholder(bitmap)
                    .bitmapTransform(CropCircleTransformation(context))
                    .crossFade(100)
                    .into(imageView)
        }
    }

    /**
     * 加载网络图片
     * 高斯模糊显示
     */
    fun loadUrlBlurTeansImage(context:Activity,url:String,imageView:ImageView,bitmap:Int){
        if(isDestroy(context)){
            Glide.with(context)
                    .load(url)
                    .placeholder(bitmap)
                    .bitmapTransform(BlurTransformation(context, 25))
                    .crossFade(100)
                    .into(imageView)
        }
    }

    /**
     * 加载本地资源图片
     * 高斯模糊显示
     */
    fun loadDrawableBlurTeansImage(context:Activity,rid:Int,imageView:ImageView,bitmap:Int){
        if(isDestroy(context)){
            Glide.with(context)
                    .load(rid)
                    .placeholder(bitmap)
                    .bitmapTransform(BlurTransformation(context, 25))
                    .crossFade(100)
                    .into(imageView)
        }
    }

    /**
     * 加载网络图片
     * View的背景图片高斯模糊显示
     */
    fun loadViewBlurTeansBg(context: Activity,url: String,view: View,bitmap: Int){
        if(isDestroy(context)){
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .into(object : SimpleTarget<Bitmap>(180, 180) {
                        //设置宽高
                        override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                            val drawable = BitmapDrawable(resource)
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                view.background = drawable//设置背景
                            }
                        }
                    })

        }
    }


    /**
     * 自适应宽度加载图片。保持图片的长宽比例不变，
     * 通过修改imageView的高度来完全显示图片。
     */
    fun loadUrlWarpContentImage(context: Activity,url: String,imageView: ImageView,bitmap: Int){
        if(isDestroy(context)){
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .listener(object : RequestListener<String, GlideDrawable> {
                        override fun onException(e: Exception, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                            return false
                        }

                        override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                            if (imageView == null) {
                                return false
                            }
                            if (imageView.scaleType != ImageView.ScaleType.FIT_XY) {
                                imageView.scaleType = ImageView.ScaleType.FIT_XY
                            }
                            val params = imageView.layoutParams
                            val vw = imageView.width - imageView.paddingLeft - imageView.paddingRight
                            val scale = vw.toFloat() / resource.intrinsicWidth.toFloat()
                            val vh = Math.round(resource.intrinsicHeight * scale)
                            params.height = vh + imageView.paddingTop + imageView.paddingBottom
                            imageView.layoutParams = params
                            return false
                        }
                    })
                    .placeholder(bitmap)
                    .error(bitmap)
                    .into(imageView)
        }
    }

    /**
     * 获取网络图片的Bitmap对象
     */
    fun loadUrlBitmap(context: Activity,url: String):Bitmap?{
        if(isDestroy(context)){
            return try {
                Glide.with(context)
                        .load(url)
                        .asBitmap()
                        .fitCenter()
                        .into(500, 500)
                        .get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
                null
            } catch (e: ExecutionException) {
                e.printStackTrace()
                null
            }
        }else{
            return null
        }
    }


    /**
     * 获取图片绝对路径
     */
    @TargetApi(19)
    fun getImageAbsolutePath(context: Activity?, imageUri: Uri?): String? {
        if (context == null || imageUri == null)
            return null
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                val docId = DocumentsContract.getDocumentId(imageUri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }
            } else if (isDownloadsDocument(imageUri)) {
                val id = DocumentsContract.getDocumentId(imageUri)
                val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)!!)
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(imageUri)) {
                val docId = DocumentsContract.getDocumentId(imageUri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = MediaStore.Images.Media._ID + "=?"
                val selectionArgs = arrayOf(split[1])
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } // MediaStore (and general)
        else if ("content".equals(imageUri.scheme, ignoreCase = true)) {
            // Return the remote address
            return if (isGooglePhotosUri(imageUri)) imageUri.lastPathSegment else getDataColumn(context, imageUri, null, null)
        } else if ("file".equals(imageUri.scheme, ignoreCase = true)) {
            return imageUri.path
        }// File
        return null
    }
    //方法被上调用
    private fun getDataColumn(context: Activity, uri: Uri?, selection: String?, selectionArgs: Array<String>?): String? {
        var cursor: Cursor? = null
        val column = MediaStore.Images.Media.DATA
        val projection = arrayOf(column)
        try {
            cursor = context.contentResolver.query(uri!!, projection, selection, selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            if (cursor != null)
                cursor.close()
        }
        return null
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private fun isExternalStorageDocument(uri: Uri) = "com.android.externalstorage.documents" == uri.authority

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private fun isDownloadsDocument(uri: Uri) = "com.android.providers.downloads.documents" == uri.authority

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private fun isMediaDocument(uri: Uri) = "com.android.providers.media.documents" == uri.authority

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    private fun isGooglePhotosUri(uri: Uri) = "com.google.android.apps.photos.content" == uri.authority

    /**
     * 根据给定的宽和高进行拉伸
     * @param origin    原图
     * @param newWidth  新图的宽
     * @param newHeight 新图的高
     * @return new Bitmap
     */
    fun scaleBitmap(origin: Bitmap?, newWidth: Int, newHeight: Int): Bitmap? {
        if (origin == null) {
            return null
        }
        val height = origin.height
        val width = origin.width
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)// 使用后乘
        val newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false)
        if (!origin.isRecycled) {
            origin.recycle()
        }
        return newBM
    }

    /**
     * 按比例缩放图片
     * @param origin 原图
     * @param ratio  比例
     * @return 新的bitmap
     */
    fun scaleBitmap(origin: Bitmap?, ratio: Float): Bitmap? {
        if (origin == null) {
            return null
        }
        val width = origin.width
        val height = origin.height
        val matrix = Matrix()
        matrix.preScale(ratio, ratio)
        val newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false)
        if (newBM == origin) {
            return newBM
        }
        origin.recycle()
        return newBM
    }

    /**
     * 裁剪
     * @param bitmap 原图
     * @return 裁剪后的图像
     */
    fun cropBitmap(bitmap: Bitmap): Bitmap {
        val w = bitmap.width // 得到图片的宽，高
        val h = bitmap.height
        var cropWidth = if (w >= h) h else w// 裁切后所取的正方形区域边长
        cropWidth /= 2
        val cropHeight = (cropWidth / 1.2).toInt()
        return Bitmap.createBitmap(bitmap, w / 3, 0, cropWidth, cropHeight, null, false)
    }

    /**
     * 选择变换
     * @param origin 原图
     * @param alpha  旋转角度，可正可负
     * @return 旋转后的图片
     */
    fun rotateBitmap(origin: Bitmap?, alpha: Float): Bitmap? {
        if (origin == null) {
            return null
        }
        val width = origin.width
        val height = origin.height
        val matrix = Matrix()
        matrix.setRotate(alpha)
        // 围绕原地进行旋转
        val newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false)
        if (newBM == origin) {
            return newBM
        }
        origin.recycle()
        return newBM
    }

    /**
     * 偏移效果
     * @param origin 原图
     * @return 偏移后的bitmap
     */
    fun skewBitmap(origin: Bitmap?): Bitmap? {
        if (origin == null) {
            return null
        }
        val width = origin.width
        val height = origin.height
        val matrix = Matrix()
        matrix.postSkew(-0.6f, -0.3f)
        val newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false)
        if (newBM == origin) {
            return newBM
        }
        origin.recycle()
        return newBM
    }

    /**
     * 压缩图片
     * @param image
     * @param size    期望值
     * @param options 压缩率
     * @return
     */
    fun compressBitmap(image: Bitmap, size: Int, options: Int): Bitmap {
        var options = options
        val baos = ByteArrayOutputStream()
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        image.compress(Bitmap.CompressFormat.JPEG, 80, baos)
        // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().size / 1024 > size) {
            options -= 10// 每次都减少10
            baos.reset()// 重置baos即清空baos
            // 这里压缩options%，把压缩后的数据存放到baos中
            image.compress(Bitmap.CompressFormat.JPEG, options, baos)
        }
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        val isBm = ByteArrayInputStream(baos.toByteArray())
        // 把ByteArrayInputStream数据生成图片
        return BitmapFactory.decodeStream(isBm, null, null)
    }

    /**
     * 将图片变成圆形
     */
    fun toRoundBitmap(bitmap: Bitmap): Bitmap {
        var width = bitmap.width
        var height = bitmap.height
        val roundPx: Float
        val left: Float
        val top: Float
        val right: Float
        val bottom: Float
        val dst_left: Float
        val dst_top: Float
        val dst_right: Float
        val dst_bottom: Float
        if (width <= height) {
            roundPx = (width / 2).toFloat()
            top = 0f
            bottom = width.toFloat()
            left = 0f
            right = width.toFloat()
            height = width
            dst_left = 0f
            dst_top = 0f
            dst_right = width.toFloat()
            dst_bottom = width.toFloat()
        } else {
            roundPx = (height / 2).toFloat()
            val clip = ((width - height) / 2).toFloat()
            left = clip
            right = width - clip
            top = 0f
            bottom = height.toFloat()
            width = height
            dst_left = 0f
            dst_top = 0f
            dst_right = height.toFloat()
            dst_bottom = height.toFloat()
        }
        val output = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        val src = Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
        val dst = Rect(dst_left.toInt(), dst_top.toInt(), dst_right.toInt(), dst_bottom.toInt())
        val rectF = RectF(dst)
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, src, dst, paint)
        return output
    }

    /**
     * bitmap转Base64
     */
    fun bitmapToBase64(bitmap: Bitmap?): String? {
        var result: String? = null
        var baos: ByteArrayOutputStream? = null
        try {
            if (bitmap != null) {
                baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

                baos.flush()
                baos.close()

                val bitmapBytes = baos.toByteArray()
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (baos != null) {
                    baos.flush()
                    baos.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return result
    }

    /**
     * base64转为bitmap
     */
    fun base64ToBitmap(base64Data: String): Bitmap {
        val bytes = Base64.decode(base64Data, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }


    fun getPath(): String {
        return Environment.getExternalStorageDirectory().path
    }
}