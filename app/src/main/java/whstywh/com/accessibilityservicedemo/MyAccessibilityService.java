package whstywh.com.accessibilityservicedemo;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;


public class MyAccessibilityService extends AccessibilityService {

    private final String INSTALL_AND_UNINSTALL = "com.android.packageinstaller";
    private String[] key = new String[]{"确定", "下一步", "安装"};

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if (event.getSource() != null) {
            switch (event.getPackageName().toString()) {
                case INSTALL_AND_UNINSTALL:
                    for (int i = 0; i < key.length; i++) {
                        List<AccessibilityNodeInfo> nodes = event.getSource().findAccessibilityNodeInfosByText(key[i]);
                        if (nodes != null && !nodes.isEmpty()) {
                            AccessibilityNodeInfo node;
                            for (int j = 0; j < nodes.size(); j++) {
                                node = nodes.get(j);
                                if (node.getClassName().equals("android.widget.Button") && node.isEnabled()) {
                                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                    break;
                                }
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}
