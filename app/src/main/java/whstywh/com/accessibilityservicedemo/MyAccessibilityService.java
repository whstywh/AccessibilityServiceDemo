package whstywh.com.accessibilityservicedemo;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;


public class MyAccessibilityService extends AccessibilityService {

    private static final String INSTALL_AND_UNINSTALL = "com.android.packageinstaller";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if (event.getPackageName().equals(INSTALL_AND_UNINSTALL)) {
            if (event.getSource() != null) {

                List<AccessibilityNodeInfo> ti_nodes = event.getSource().findAccessibilityNodeInfosByText("确定");
                if (ti_nodes != null && !ti_nodes.isEmpty()) {
                    AccessibilityNodeInfo node;
                    for (int i = 0; i < ti_nodes.size(); i++) {
                        node = ti_nodes.get(i);
                        if (node.getClassName().equals("android.widget.Button") && node.isEnabled()) {
                            Log.d("flag", "确定");
                            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}
