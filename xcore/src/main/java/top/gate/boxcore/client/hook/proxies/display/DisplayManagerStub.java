package top.gate.boxcore.client.hook.proxies.display;

import android.os.IInterface;

import java.lang.reflect.Method;

import mirror.android.hardware.display.DisplayManagerGlobal;
import top.gate.boxcore.client.hook.ClassInvocationStub;
import top.gate.boxcore.client.hook.MethodHook;
import top.gate.boxcore.utils.MethodParameterUtils;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class DisplayManagerStub extends ClassInvocationStub {

    public DisplayManagerStub() {
    }

    @Override
    protected Object getWho() {
        return DisplayManagerGlobal.mDm.get(DisplayManagerGlobal.getInstance.call());
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        Object dmg = DisplayManagerGlobal.getInstance.call();
        DisplayManagerGlobal.mDm.set(dmg, (IInterface) getProxyInvocation());
    }

    @Override
    public boolean isBadEnv() {
        Object dmg = DisplayManagerGlobal.getInstance.call();
        IInterface mDm = DisplayManagerGlobal.mDm.get(dmg);
        return mDm != getProxyInvocation();
    }

    @Override
    protected void onBindMethod() {
        addMethodHook(new CreateVirtualDisplay());
    }

    static class CreateVirtualDisplay extends MethodHook {

        @Override
        protected String getMethodName() {
            return "createVirtualDisplay";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(args);
            return method.invoke(who, args);
        }
    }
}
