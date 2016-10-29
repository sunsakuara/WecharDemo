package zhuruyi.net.wechardemo.http;

/**
 * Created by ruyi on 2016/10/24.
 */

public interface RequestListener {
    /*请求之前调用的方法*/
    public void onPreRequest();

    /*请求成功调用，
    * @Param response*/
    public void  onRequestSuccess(BaseRespone respone);

    /*请求调用失败，致命错误
    * @param code
    * @param msg*/
    public void onRequestError(int code,String msg);

   /*请求调用失败
   * @param code
   * @param msg*/
    public void onRequestFail(int code,String msg);
}
