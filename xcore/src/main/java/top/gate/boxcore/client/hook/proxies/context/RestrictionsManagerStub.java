package top.gate.boxcore.client.hook.proxies.context;

import android.content.Context;

import java.lang.reflect.Method;

import mirror.android.content.IRestrictionsManager;
import mirror.android.os.ServiceManager;
import top.gate.boxcore.BBoxCore;
import top.gate.boxcore.client.hook.BinderInvocationStub;
import top.gate.boxcore.client.hook.MethodHook;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class RestrictionsManagerStub extends BinderInvocationStub {

    public RestrictionsManagerStub() {
        super(ServiceManager.getService.call(Context.RESTRICTIONS_SERVICE));
    }

    @Override
    protected Object getWho() {
        return IRestrictionsManager.Stub.asInterface.call(ServiceManager.getService.call(Context.RESTRICTIONS_SERVICE));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService(Context.RESTRICTIONS_SERVICE);
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }

    @Override
    protected void onBindMethod() {
        super.onBindMethod();
        addMethodHook(new GetApplicationRestrictions());
    }

    static class GetApplicationRestrictions extends MethodHook {

        @Override
        protected String getMethodName() {
            return "getApplicationRestrictions";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            args[0] = BBoxCore.getHostPkg();
            return method.invoke(who, args);
        }
    }
}
