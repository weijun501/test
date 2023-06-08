package top.gate.boxcore.client.hook.proxies.accessibility;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.lang.reflect.Method;

import mirror.android.os.ServiceManager;
import mirror.android.view.accessibility.IAccessibilityManager;
import top.gate.boxcore.BBoxCore;
import top.gate.boxcore.client.hook.BinderInvocationStub;
import top.gate.boxcore.client.hook.MethodHook;
import top.gate.boxcore.server.user.BUserHandle;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class AccessibilityManagerStub extends BinderInvocationStub {

    public AccessibilityManagerStub() {
        super(ServiceManager.getService.call(Context.ACCESSIBILITY_SERVICE));
    }

    @Override
    protected Object getWho() {
        return IAccessibilityManager.Stub.asInterface.call(ServiceManager.getService.call(Context.ACCESSIBILITY_SERVICE));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService(Context.ACCESSIBILITY_SERVICE);
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }

    @Override
    protected void onBindMethod() {
        super.onBindMethod();
        addMethodHook(new ReplaceUserId("interrupt"));
        addMethodHook(new ReplaceUserId("sendAccessibilityEvent"));
        addMethodHook(new ReplaceUserId("addClient"));
        addMethodHook(new ReplaceUserId("getInstalledAccessibilityServiceList"));
        addMethodHook(new ReplaceUserId("getEnabledAccessibilityServiceList"));
        addMethodHook(new ReplaceUserId("addAccessibilityInteractionConnection"));
        addMethodHook(new ReplaceUserId("getWindowToken"));
    }

    private static class ReplaceUserId extends MethodHook {
        private String name;

        public ReplaceUserId(String name) {
            this.name = name;
        }

        @Override
        protected String getMethodName() {
            return name;
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null) {
                int index = args.length - 1;
                Object arg = args[index];
                if (arg instanceof Integer) {
                    ApplicationInfo applicationInfo = BBoxCore.getContext().getApplicationInfo();
                    args[index] = BUserHandle.getUserId(applicationInfo.uid);
                }
            }
            return method.invoke(who, args);
        }
    }
}
