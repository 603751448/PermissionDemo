package adu.permissiontest;

import java.util.List;

/**
 * 项目名：  PermissionTest
 * 包名：    adu.permissiontest
 * 文件名:   PermissionListener
 * 创建者:   dpc
 * 创建时间:  2016/12/27 22:47
 * 描述：    权限管理接口
 */

public interface PermissionListener {

    void onGranted();
    void onDenied(List<String> deniedPermission);
}
