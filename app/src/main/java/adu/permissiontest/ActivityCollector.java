package adu.permissiontest;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：  PermissionTest
 * 包名：    adu.permissiontest
 * 文件名:   ActivityCollector
 * 创建者:   dpc
 * 创建时间:  2016/12/27 22:50
 * 描述：    TODO
 */

public class ActivityCollector {

    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }
    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    public static Activity getTopActivity(){
        if(activityList.isEmpty()){
            return null;
        }else {
            return activityList.get(activityList.size() - 1);
        }
    }
}
