package top.gate.boxcore.client.hook.proxies.appos;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;

import java.lang.reflect.Method;

import mirror.android.os.ServiceManager;
import mirror.com.android.internal.app.IAppOpsService;
import top.gate.boxcore.BBoxCore;
import top.gate.boxcore.client.hook.BinderInvocationStub;
import top.gate.boxcore.client.hook.MethodHook;
import top.gate.boxcore.utils.MethodParameterUtils;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class AppOpsManagerStub extends BinderInvocationStub {
    public AppOpsManagerStub() {
        super(ServiceManager.getService.call(Context.APP_OPS_SERVICE));
    }

    @Override
    protected Object getWho() {
        IBinder call = ServiceManager.getService.call(Context.APP_OPS_SERVICE);
        return IAppOpsService.Stub.asInterface.call(call);
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        if (mirror.android.app.AppOpsManager.mService != null) {
            AppOpsManager appOpsManager = (AppOpsManager) BBoxCore.getContext().getSystemService(Context.APP_OPS_SERVICE);
            try {
                mirror.android.app.AppOpsManager.mService.set(appOpsManager, (IInterface) getProxyInvocation());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        replaceSystemService(Context.APP_OPS_SERVICE);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodParameterUtils.replaceFirstAppPkg(args);
        MethodParameterUtils.replaceLastUserId(args);
        return super.invoke(proxy, method, args);
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }

    @Override
    protected void onBindMethod() {
        super.onBindMethod();
        addMethodHook(new CheckPackage());
        addMethodHook(new CheckOperation());
        addMethodHook(new NoteOperation());
    }

    static class CheckPackage extends MethodHook {

        @Override
        protected String getMethodName() {
            return "checkPackage";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            // todo
            return AppOpsManager.MODE_ALLOWED;
        }
    }

    static class CheckOperation extends MethodHook {

        @Override
        protected String getMethodName() {
            return "checkPackage";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return method.invoke(who, args);
        }
    }

    static class NoteOperation extends MethodHook {

        @Override
        protected String getMethodName() {
            return "noteOperation";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return method.invoke(who, args);
        }
    }
}
