package top.gate.boxcore.client.hook.proxies.pm;

import android.content.Context;

import java.lang.reflect.Method;

import mirror.android.content.pm.ILauncherApps;
import mirror.android.os.ServiceManager;
import top.gate.boxcore.client.hook.BinderInvocationStub;
import top.gate.boxcore.utils.MethodParameterUtils;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class LauncherAppsStub extends BinderInvocationStub {

    public LauncherAppsStub() {
        super(ServiceManager.getService.call(Context.LAUNCHER_APPS_SERVICE));
    }

    @Override
    protected Object getWho() {
        return ILauncherApps.Stub.asInterface.call(ServiceManager.getService.call(Context.LAUNCHER_APPS_SERVICE));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService(Context.LAUNCHER_APPS_SERVICE);
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }

    @Override
    protected void onBindMethod() {
        super.onBindMethod();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodParameterUtils.replaceFirstAppPkg(args);
        // todo shouldHideFromSuggestions
        return super.invoke(proxy, method, args);
    }

}
