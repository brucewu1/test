/**
 * Created by 尼恩 at 疯狂创客圈
 */

package cn.vfc.zk.cocurrent;


public interface CallbackTask<R>
{

     R execute() throws Exception;

     void onSuccess(R r);

     void onFailure(Throwable t);
}
